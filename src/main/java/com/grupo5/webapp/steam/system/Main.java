package com.grupo5.webapp.steam.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.grupo5.webapp.steam.SteamApplication;
import com.grupo5.webapp.steam.controller.FXController.DesarrolladorControllerFX;
import com.grupo5.webapp.steam.controller.FXController.IndexController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(SteamApplication.class).run();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Steam Spring");
        //vista inicial
        indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int width, int height) throws IOException{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName) ;
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        resultado = (Initializable)loader.getController();
        return resultado;
    }

    public void indexView(){
        try{
            IndexController indexView = (IndexController)switchScene("index.fxml", 1000, 600);
            indexView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void desarrolladorView(){
        try{
            DesarrolladorControllerFX desarrolladorView = (DesarrolladorControllerFX)switchScene("desarrollador.fxml", 1000, 600);
            desarrolladorView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
