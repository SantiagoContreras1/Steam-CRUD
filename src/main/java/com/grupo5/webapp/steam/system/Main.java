package com.grupo5.webapp.steam.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.grupo5.webapp.steam.SteamApplication;
import com.grupo5.webapp.steam.controller.FXController.FXCategoriaController;

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
        stage.setTitle("Steam APP");
        FXCategoriaView();
        stage.show();
    }

    public Initializable SwitchScene(String fxmlName, int width, int height) throws IOException{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));


        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        resultado = (Initializable)loader.getController();
        return resultado;
    }

    public void FXCategoriaView(){
        try {
            FXCategoriaController CategoriaView = (FXCategoriaController)SwitchScene("categoria.fxml", 1000, 600);
            CategoriaView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
