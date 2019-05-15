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
public class IniciarSesionController implements Initializable {
  
  @FXML private Button btnIngresar;
  @FXML private Label lblContrasena;
  @FXML private Label lblUsuario;
  @FXML private PasswordField pwfContrasena;
  @FXML private TextField txfUsuario;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
