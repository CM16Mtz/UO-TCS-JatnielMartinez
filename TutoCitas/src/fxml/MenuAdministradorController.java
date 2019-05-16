package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class MenuAdministradorController implements Initializable {
  
  @FXML private Button btnCerrar;
  @FXML private Button btnRegistrarTutor;
  @FXML private Button btnRegistrarTutorado;
  @FXML private Label lblBienvenido;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
