package com.grupo5.webapp.steam.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;
@Component
public class IndexController implements Initializable{
    @Setter
    private Main stage;
    @FXML
    MenuItem btnDesarrollador, btnJuego, btnCategoria, btnUsuarios;
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnDesarrollador){
            stage.desarrolladorView();
        }else if(event.getSource() == btnJuego){
            stage.juegoView();
        }else if(event.getSource() == btnCategoria){
            stage.categoriaView();
        }else if(event.getSource() == btnUsuarios){
            stage.usuariosView();
        }
    }
    
}
