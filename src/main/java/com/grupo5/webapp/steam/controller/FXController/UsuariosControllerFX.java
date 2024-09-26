package com.grupo5.webapp.steam.controller.FXController;


import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.model.Usuario;
import com.grupo5.webapp.steam.service.UsuarioService;
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
public class UsuariosControllerFX implements Initializable{
    @FXML
    TextField tfId,tfNombre,tfMail,tfPassword,tfEdad,tfBuscar,tfFecha;
    @FXML
    Button btnGuardar,btnLimpiar,btnEliminar,btnRegresar, btnBuscar;
    @FXML
    TableView tblUsuarios;
    @FXML
    TableColumn colId,colNombre,colMail,colFechaRegistro,colPassword,colEdad;

    @Setter
    private Main stage;
    @Autowired
    private UsuarioService usuariosService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            boolean exito;
            if (tfId.getText().isBlank()) {
                exito = agregarUsuario();
                if (exito) {
                    SteamAlert.getInstance().mostrarAlertaInfo(401);
                    cargarDatos();
                }else{

                }
            } else {
                if (SteamAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK) {
                    exito = editarUsuario();
                    if (exito) {
                        cargarDatos();
                    } else {
                    }
                }
            }
        }else if (event.getSource() == btnLimpiar) {
            cleanForm();
        }else if (event.getSource() == btnEliminar) {
            if(SteamAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                eliminarUsuario();
            }
        }else if (event.getSource() == btnRegresar) {
            stage.indexView();
        }else if (event.getSource() == btnBuscar) {
            tblUsuarios.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            }else{
                tblUsuarios.getItems().add(buscarUsuario());
                colId.setCellValueFactory(new PropertyValueFactory<Usuario,Long>("id"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
                colMail.setCellValueFactory(new PropertyValueFactory<Usuario,String>("mail"));
                colFechaRegistro.setCellValueFactory(new PropertyValueFactory<Usuario,Date>("fechaRegistro"));
                colPassword.setCellValueFactory(new PropertyValueFactory<Usuario,String>("psswd"));
                colEdad.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("edad"));        
            }
        }
    }


    public void cargarDatos(){
        tblUsuarios.setItems(listarUsuarios());
        colId.setCellValueFactory(new PropertyValueFactory<Usuario,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        colMail.setCellValueFactory(new PropertyValueFactory<Usuario,String>("mail"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<Usuario,Date>("fechaRegistro"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Usuario,String>("psswd"));
        colEdad.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("edad"));

    }

    public ObservableList<Usuario> listarUsuarios(){
        return FXCollections.observableList(usuariosService.listarUsuarios());
    }

    public void cleanForm(){
        tfId.clear();
        tfNombre.clear();
        tfMail.clear();
        tfFecha.clear();
        tfPassword.clear();
        tfEdad.clear();
    }

    public Boolean agregarUsuario(){
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(tfNombre.getText());
            usuario.setMail(tfMail.getText());
            usuario.setFechaRegistro(Date.valueOf(tfFecha.getText()));
            usuario.setPsswd(tfPassword.getText());
            usuario.setEdad(Integer.parseInt(tfEdad.getText()));
            return usuariosService.guardarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cargarFormEdit(){
        Usuario usuario = (Usuario)tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuario!=null) {
            tfId.setText(Long.toString(usuario.getId()));
            tfNombre.setText(usuario.getNombre());
            tfMail.setText(usuario.getMail());
            tfFecha.setText(usuario.getFechaRegistro().toString());
            tfPassword.setText(usuario.getPsswd());
            tfEdad.setText(Integer.toString(usuario.getEdad()));
        }
    }

    public Boolean editarUsuario(){
        try {
            Usuario usuario = usuariosService.buscarUsuario(Long.parseLong(tfId.getText()));
            usuario.setNombre(tfNombre.getText());
            usuario.setMail(tfMail.getText());
            usuario.setFechaRegistro(Date.valueOf(tfFecha.getText()));
            usuario.setPsswd(tfPassword.getText());
            usuario.setEdad(Integer.parseInt(tfEdad.getText()));
            return usuariosService.guardarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public void eliminarUsuario(){
        Usuario usuario = usuariosService.buscarUsuario(Long.parseLong(tfId.getText()));
        usuariosService.eliminarUsuario(usuario);
        cargarDatos();
    }

    public Usuario buscarUsuario(){
        return usuariosService.buscarUsuario(Long.parseLong(tfBuscar.getText()));
    }

}
