package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class GenerarReporteController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnGenerar;
  @FXML private CheckBox chkNo;
  @FXML private CheckBox chkSi;
  @FXML private ComboBox cmbTutorados;
  @FXML private DatePicker dtpFecha;
  @FXML private Label lbl1;
  @FXML private Label lbl2;
  @FXML private Label lbl3;
  @FXML private Label lbl4;
  @FXML private Label lbl5;
  @FXML private Label lbl6;
  @FXML private Label lbl7;
  @FXML private Label lblMinutos;
  @FXML private Label lblTutor;
  @FXML private TextField txfDuracion;
  @FXML private TextField txfHora;
  @FXML private TextField txfNumero;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
