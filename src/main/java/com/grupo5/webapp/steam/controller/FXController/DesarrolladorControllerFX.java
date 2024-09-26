package com.grupo5.webapp.steam.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.model.Desarrollador;
import com.grupo5.webapp.steam.service.DesarrolladorService;
import com.grupo5.webapp.steam.system.Main;
import com.grupo5.webapp.steam.utils.SteamAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import lombok.Setter;

@Component
public class DesarrolladorControllerFX implements Initializable{
    @FXML
    TextField tfId, tfNombre, tfPais, tfBuscar;
    @FXML
    Button btnGuardar,btnLimpiar,btnEliminar,btnRegresar, btnBuscar;
    @FXML
    TableView tblDesarrolladores;
    @FXML
    TableColumn colId,colNombre, colPais;
    @Setter
    private Main stage;
    @Autowired
     DesarrolladorService desarrolladorService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }
    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnGuardar){

            boolean exito;
            if (tfId.getText().isBlank()) {
                exito = agregarDesarrollador();
                if (exito) {
                    SteamAlert.getInstance().mostrarAlertaInfo(401);
                    cargarDatos();
                }else{

                }
            } else {
                if (SteamAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK) {
                    exito = editarDesarrollador();
                    if (exito) {
                        cargarDatos();
                    } else {
                    }
                }
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarForm();
        }else if(event.getSource() == btnEliminar){
             if(SteamAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                eliminarDesarrollador();
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }else if (event.getSource() == btnBuscar) {
            tblDesarrolladores.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblDesarrolladores.getItems().add(buscarDesarrollador());
                colId.setCellValueFactory(new PropertyValueFactory<Desarrollador,Long>("id"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Desarrollador,String>("nombre"));
                colPais.setCellValueFactory(new PropertyValueFactory<Desarrollador,String>("pais"));
            }
        }
    }

    public void cargarDatos(){
        tblDesarrolladores.setItems(listarDesarrollador());
        colId.setCellValueFactory(new PropertyValueFactory<Desarrollador,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Desarrollador,String>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<Desarrollador,String>("pais"));
    }
    public ObservableList<Desarrollador> listarDesarrollador(){

        return FXCollections.observableList(desarrolladorService.listarDesarrolladores());
    }
    
    public void limpiarForm(){
        tfId.clear();
        tfNombre.clear();
        tfPais.clear();
    }


    public Boolean agregarDesarrollador(){
        try {
            Desarrollador desarrollador = new Desarrollador();
            desarrollador.setNombre(tfNombre.getText());
            desarrollador.setPais(tfPais.getText());
            return desarrolladorService.guardarDesarrollador(desarrollador);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cargarFormEditar(){
        Desarrollador desarrollador = (Desarrollador)tblDesarrolladores.getSelectionModel().getSelectedItem();
        if(desarrollador != null){
            tfId.setText(Long.toString(desarrollador.getId()));
            tfNombre.setText(desarrollador.getNombre());
            tfPais.setText(desarrollador.getPais());
        }
    }
    
    public Boolean editarDesarrollador(){
       try {
        Desarrollador desarrollador = desarrolladorService.buscarDesarrolladorPorId(Long.parseLong(tfId.getText()));
        desarrollador.setNombre(tfNombre.getText());
        desarrollador.setPais(tfPais.getText());
        return desarrolladorService.guardarDesarrollador(desarrollador);
       } catch (Exception e) {
        e.printStackTrace();
        return false;
       }
    }

    public void eliminarDesarrollador(){
        Desarrollador desarrollador = desarrolladorService.buscarDesarrolladorPorId(Long.parseLong(tfId.getText()));
        desarrolladorService.eliminarDesarrollador(desarrollador);
        cargarDatos();
    }
    public Desarrollador buscarDesarrollador() {
        return desarrolladorService.buscarDesarrolladorPorId(Long.parseLong(tfBuscar.getText()));
    }

    
}
