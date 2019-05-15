package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
