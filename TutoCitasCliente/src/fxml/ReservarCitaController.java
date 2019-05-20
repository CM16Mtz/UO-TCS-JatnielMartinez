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
public class ReservarCitaController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnReservar;
  @FXML private DatePicker dtpDia;
  @FXML private Label lblDia;
  @FXML private Label lblHora;
  @FXML private Label lblEncabezado;
  @FXML private TextField txfHora;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
