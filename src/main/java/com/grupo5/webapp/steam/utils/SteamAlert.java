package com.grupo5.webapp.steam.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SteamAlert {
    
    private static SteamAlert instance;
    
    private SteamAlert(){
        
    }
    
    public static SteamAlert getInstance(){
        if(instance == null){
            instance = new SteamAlert();
            
        }
        return instance;
    }
    
    public void mostrarAlertaInfo(int code){
        if(code == 401){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmacion De Registro");
            alert.setHeaderText("Confirmacion De Registro");
            alert.setContentText("El Registro Se Ha Creado Con Exito");
            alert.showAndWait();
        }else if(code == 602){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario Incorrecto");
            alert.setHeaderText("Usuario Incorrecto");
            alert.setContentText("El Usuario Ingresado No Existe O No Es Correcto");
            alert.showAndWait();
        }else if(code == 005){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contraseña Incorrecta");
            alert.setHeaderText("Contraseña Incorrecta");
            alert.setContentText("La Contraseña Ingresada No Existe O No Es Correcta");
            alert.showAndWait();
        }else if(code == 107){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eliminacion No Completada");
            alert.setHeaderText("Eliminacion No Completada");
            alert.setContentText("El Registro Que Desea Eliminar Posee Llaves Foraneas, Lo Cual Impide Su Eliminacion");
            alert.showAndWait();
        }else if(code == 106){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Categoria No Creada");
            alert.setHeaderText("Categoria No Creada");
            alert.setContentText("La Categoria Que Desea Crear Posee Un Nombre Ya Existente");
            alert.showAndWait();
        }else if(code == 105){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Compra No Valida");
            alert.setHeaderText("Compra No Valida");
            alert.setContentText("El Numero De La Tarjeta No Es Valido");
            alert.showAndWait();
        }else if(code == 104){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reseña No Realizada");
            alert.setHeaderText("Reseña No Realizada");
            alert.setContentText("Utilizar Palabras Inapropiadas No Esta Permitido");
            alert.showAndWait();
        }else if(code == 103){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario No Creado");
            alert.setHeaderText("Usuario No Creado");
            alert.setContentText("Usted Es Menor De Edad, Solo Pueden Ingresar +18");
            alert.showAndWait();
        }else if(code == 102){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Juego No Creado");
            alert.setHeaderText("Juego No Creado");
            alert.setContentText("El Nombre Del Juego No Puede Ser Nulo");
            alert.showAndWait();
        }else if(code == 101){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Desarrollador No Creado");
            alert.setHeaderText("Desarrollador No Creado");
            alert.setContentText("El Desarrollador Que Desea Crear Posee Un Nombre Ya Existente");
            alert.showAndWait();
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional <ButtonType> action = null;
        if(code == 405){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminacion De Registro");
        alert.setHeaderText("Eliminacion De Registro");
        alert.setContentText("¿Desea Confirmar La Eliminacion De Registro?");
        action = alert.showAndWait();
        }else if(code == 406){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edicion De Registro");
        alert.setHeaderText("Edicion De Registro");
        alert.setContentText("¿Desea Confirmar La Edicion De Registro?");
        action = alert.showAndWait();
        }
        return action;
    }
    
    public void alertaSaludo(String usuario){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bienvenido");
        alert.setHeaderText("Bienvenido " + usuario);
        alert.showAndWait();
    }
}
