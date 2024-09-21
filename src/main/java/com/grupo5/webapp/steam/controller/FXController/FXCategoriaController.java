package com.grupo5.webapp.steam.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.webapp.steam.model.Categoria;
import com.grupo5.webapp.steam.service.CategoriaService;
import com.grupo5.webapp.steam.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class FXCategoriaController implements Initializable {

    @FXML
    TextField tfId, tfNombre;
    @FXML
    Button btnGuardar, btnRegresar, btnEliminar, btnLimpiar;
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
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarFormEditar();

        }else if (event.getSource() == btnEliminar){
            eliminarCategoria();
        }else if (event.getSource() == btnRegresar){
            //index
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

    public void agregarCategoria() {
        String nombre = tfNombre.getText();
        if (categoriaService.buscarCategoriaPorNombre(nombre).isPresent()) {
            mostrarAlerta("Error", "La categoría ya existe.");
            return;
        }

        Categoria categoria = new Categoria();
        categoria.setJuegoCategoria(nombre);
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategoria() {
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        String nuevoNombre = tfNombre.getText();

        if (!categoria.getJuegoCategoria().equals(nuevoNombre) && 
            categoriaService.buscarCategoriaPorNombre(nuevoNombre).isPresent()) {
            mostrarAlerta("Error", "La categoría ya existe.");
            return;
        }

        categoria.setJuegoCategoria(nuevoNombre);
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria() {
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
