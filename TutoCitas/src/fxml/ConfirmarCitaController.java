package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class ConfirmarCitaController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnConfirmar;
  @FXML private DatePicker dtpDia;
  @FXML private Label lblCita;
  @FXML private Label lblDia;
  @FXML private Label lblHora;
  @FXML private Label lblTutorado;
  @FXML private TextField txfHora;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
