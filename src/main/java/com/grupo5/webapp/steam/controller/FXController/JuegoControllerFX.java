package com.grupo5.webapp.steam.controller.FXController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.model.Categoria;
import com.grupo5.webapp.steam.model.Desarrollador;
import com.grupo5.webapp.steam.model.Juego;
import com.grupo5.webapp.steam.service.CategoriaService;
import com.grupo5.webapp.steam.service.DesarrolladorService;
import com.grupo5.webapp.steam.service.JuegoService;
import com.grupo5.webapp.steam.system.Main;
import com.grupo5.webapp.steam.utils.SteamAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class JuegoControllerFX implements Initializable {
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar,btnRegresar,btnBuscar;
    @FXML
    TextField tfId, tfNombre, tfLanzamiento, tfPrecio, tfBuscar;
    @FXML
    TextArea taDescripcion;
    @FXML
    ComboBox cmbCategoria;
    @FXML
    ComboBox<Desarrollador> cmbDesarrollador;
    @FXML
    TableView tblJuegos;
    @FXML
    TableColumn colId, colNombre, colDescripcion, colLanzamiento, colPrecio, colCategoria, colDesarrollador;
    
    @Setter
    private Main stage;

    @Autowired
    JuegoService juegoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    DesarrolladorService desarrolladorService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
        cmbCategoria.setItems(FXCollections.observableList(categoriaService.listarCategorias()));
        cmbDesarrollador.setItems(FXCollections.observableList(desarrolladorService.listarDesarrolladores())); // Cargar desarrolladores
    }

        public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar){
            boolean exito;
            if (tfId.getText().isBlank()) {
                exito = agregarJuego();
                if (exito) {
                    SteamAlert.getInstance().mostrarAlertaInfo(401);
                    cargarDatos();
                }else{

                }
            } else {
                if (SteamAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK) {
                    exito = editarJuego();
                    if (exito) {
                        cargarDatos();
                    } else {
                    }
                }
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarForm();
        }else if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnEliminar) {
            if(SteamAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                eliminarJuego();
            }
        } else if (event.getSource() == btnBuscar) {
            tblJuegos.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblJuegos.getItems().add(buscarJuego());
                colId.setCellValueFactory(new PropertyValueFactory<Juego, Long>("id"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
                colDescripcion.setCellValueFactory(new PropertyValueFactory<Juego, String>("descripcion"));
                colLanzamiento.setCellValueFactory(new PropertyValueFactory<Juego, Date>("fechaDeLanzamiento"));
                colPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
                colCategoria.setCellValueFactory(new PropertyValueFactory<Juego, Categoria>("categoria"));
            }
        }
    }

    public void cargarDatos() {
        tblJuegos.setItems(listarJuegos());
        colId.setCellValueFactory(new PropertyValueFactory<Juego, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Juego, String>("descripcion"));
        colLanzamiento.setCellValueFactory(new PropertyValueFactory<Juego, Date>("fechaDeLanzamiento"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<Juego, Categoria>("categoria"));
    }

    public void cargarForm() {
        Juego juego = (Juego) tblJuegos.getSelectionModel().getSelectedItem();
        if (juego != null) {
            tfId.setText(juego.getId().toString());
            tfNombre.setText(juego.getNombre());
            taDescripcion.setText(juego.getDescripcion());
            tfLanzamiento.setText(juego.getFechaDeLanzamiento().toString());
            tfPrecio.setText(juego.getPrecio().toString());
            cmbCategoria.getSelectionModel().select(obtenerIndexCategoria());
        }
    }

    public void limpiarForm() {
        tfId.clear();
        tfNombre.clear();
        tfLanzamiento.clear();
        tfPrecio.clear();
        taDescripcion.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        cmbDesarrollador.getSelectionModel().clearSelection();
    }

    public ObservableList<Juego>listarJuegos(){
        return FXCollections.observableList(juegoService.listarJuegos());   
    }

    public Boolean agregarJuego() {
        try {
            Juego juego = new Juego();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date lanzamiento = formatter.parse(tfLanzamiento.getText());
            juego.setNombre(tfNombre.getText());
            juego.setDescripcion(taDescripcion.getText());
            juego.setFechaDeLanzamiento(new java.sql.Date(lanzamiento.getTime()));
            juego.setPrecio(Double.parseDouble(tfPrecio.getText()));
            juego.setCategoria((Categoria) cmbCategoria.getSelectionModel().getSelectedItem());

            Desarrollador desarrolladorSeleccionado = cmbDesarrollador.getSelectionModel().getSelectedItem();
            if (desarrolladorSeleccionado != null) {
                juego.getDesarrolladores().add(desarrolladorSeleccionado); 
            }

            return juegoService.guardarJuego(juego);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public Boolean editarJuego() {
        try {
            Juego juego = juegoService.buscarJuegoPorId(Long.parseLong(tfId.getText()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date lanzamiento = formatter.parse(tfLanzamiento.getText());
            juego.setNombre(tfNombre.getText());
            juego.setDescripcion(taDescripcion.getText());
            juego.setFechaDeLanzamiento(new java.sql.Date(lanzamiento.getTime()));
            juego.setPrecio(Double.parseDouble(tfPrecio.getText()));
            juego.setCategoria((Categoria) cmbCategoria.getSelectionModel().getSelectedItem());

            Desarrollador desarrolladorSeleccionado = cmbDesarrollador.getSelectionModel().getSelectedItem();
            if (desarrolladorSeleccionado != null) {
                juego.getDesarrolladores().add(desarrolladorSeleccionado);
            }
            return juegoService.guardarJuego(juego);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public void eliminarJuego(){
        Juego juego = juegoService.buscarJuegoPorId(Long.parseLong(tfId.getText()));
        juegoService.eliminarJuego(juego);
        cargarDatos();
    }

    public Juego buscarJuego() {
        return juegoService.buscarJuegoPorId(Long.parseLong(tfBuscar.getText()));
    }

    public int obtenerIndexCategoria() {
        int index = 0;
        for (int i = 0; i < cmbCategoria.getItems().size(); i++) {
            String categoriaCmb = cmbCategoria.getItems().get(i).toString();
            String categoriaTbl = ((Juego) tblJuegos.getSelectionModel().getSelectedItem()).getCategoria().toString();
            System.out.println(categoriaCmb);
            System.out.println(categoriaTbl);
            if (categoriaCmb.equals(categoriaTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }


}
