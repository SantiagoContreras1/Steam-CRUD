package com.grupo5.webapp.steam.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.model.Categoria;
import com.grupo5.webapp.steam.model.Juego;
import com.grupo5.webapp.steam.service.CategoriaService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class CategoriaControllerFX implements Initializable {

    @FXML
    TextField tfId, tfNombre, tfBuscar;
    @FXML
    Button btnGuardar, btnRegresar, btnEliminar, btnLimpiar, btnBuscar;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId, colNombre;
    

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnGuardar){
            boolean exito;
            if (tfId.getText().isBlank()) {
                exito = agregarCategoria();
                if (exito) {
                    SteamAlert.getInstance().mostrarAlertaInfo(401);
                    cargarDatos();
                }else{

                }
            } else {
                if (SteamAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK) {
                    exito = editarCategoria();
                    if (exito) {
                        cargarDatos();
                    } else {
                    }
                }
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarFormEditar();

        }else if (event.getSource() == btnEliminar){
            if(SteamAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
            eliminarCategoria();
            }
        }else if (event.getSource() == btnRegresar){
            stage.indexView();
        } else if (event.getSource() == btnBuscar){
            tblCategorias.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblCategorias.getItems().add(buscarCategoria());
                colId.setCellValueFactory(new PropertyValueFactory<Juego, Long>("Id"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("juegoCategoria"));
            }
        }
    }


    public void cargarDatos(){
        tblCategorias.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria, Long>("Id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("juegoCategoria"));

    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria)tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null)
        tfId.setText(Long.toString(categoria.getId()));
        tfNombre.setText(categoria.getJuegoCategoria());
    }

    public void limpiarFormEditar(){
        tfId.clear();
        tfNombre.clear();
    }

    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public Boolean agregarCategoria() {
        try {
            Categoria categoria = new Categoria();
            categoria.setJuegoCategoria(tfNombre.getText());
            return categoriaService.guardarCategoria(categoria);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public Boolean editarCategoria() {
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
            categoria.setJuegoCategoria(tfNombre.getText());
            return categoriaService.guardarCategoria(categoria);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public void eliminarCategoria() {
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

     public Categoria buscarCategoria() {
        return categoriaService.buscarCategoriaPorId(Long.parseLong(tfBuscar.getText()));
    }

}
