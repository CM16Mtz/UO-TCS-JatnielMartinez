package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class RegistrarTutorController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnRegistrar;
  @FXML private Label lblApMaterno;
  @FXML private Label lblApPaterno;
  @FXML private Label lblContrasena;
  @FXML private Label lblCorreo;
  @FXML private Label lblNombre;
  @FXML private Label lblNoPersonal;
  @FXML private PasswordField pwfContrasena;
  @FXML private TextField txfApMaterno;
  @FXML private TextField txfApPaterno;
  @FXML private TextField txfCorreo;
  @FXML private TextField txfNombre;
  @FXML private TextField txfNoPersonal;
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageRegistrarTutor;
    stageRegistrarTutor = (Stage) btnCancelar.getScene().getWindow();
    stageRegistrarTutor.close();
    //Se regresa al menú del administrador
    Stage stageMenuAdministrador = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuAdministrador.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuAdministrador.setScene(scene);
    stageMenuAdministrador.show();
  }
  
  @FXML
  void registrarTutor(ActionEvent evt) {
    String nombre = txfNombre.getText();
    String apPaterno = txfApPaterno.getText();
    String apMaterno = txfApMaterno.getText();
    String correo = txfCorreo.getText();
    String noPersonal = txfNoPersonal.getText();
    String contrasena = pwfContrasena.getText();
    if (!nombre.isEmpty() && !apPaterno.isEmpty() && !apMaterno.isEmpty() && !correo.isEmpty()
        && !noPersonal.isEmpty() && !contrasena.isEmpty()) {
      
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Datos inválidos");
      advertencia.setHeaderText("No se puede registrar a un tutor con campos vacíos");
      advertencia.setContentText("Por favor, llene todos los datos");
      advertencia.showAndWait();
    }
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
