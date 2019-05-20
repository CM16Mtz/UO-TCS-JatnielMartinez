package fxml;

import entidades.Tutoria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tutocitas.Contexto;

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
  
  private Tutoria tutoria;
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageConfirmarCita;
    stageConfirmarCita = (Stage) btnCancelar.getScene().getWindow();
    stageConfirmarCita.close();
    //Se regresa a CancelarCitaTutor.fxml
    Stage stageCancelarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/CancelarCitaTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageCancelarCita.setScene(scene);
    stageCancelarCita.show();
  }
  
  @FXML
  void confirmar(ActionEvent evt) {
    
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tutoria = Contexto.getInstancia().getTutoria();
  }  
  
}
