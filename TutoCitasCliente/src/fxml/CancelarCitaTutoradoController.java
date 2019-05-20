package fxml;

import entidades.Tutorado;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tutocitas.Contexto;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class CancelarCitaTutoradoController implements Initializable {
  
  @FXML private Button btnNo;
  @FXML private Button btnSi;
  @FXML private Label lblEncabezado;
  @FXML private Label lblPregunta;
  
  private Tutorado tutorado;
  
  @FXML
  void clicNo(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageCancelarCita;
    stageCancelarCita = (Stage) btnNo.getScene().getWindow();
    stageCancelarCita.close();
    //Se regresa al menú de tutorado
    Stage stageMenuTutorado = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutorado.setScene(scene);
    stageMenuTutorado.show();
  }
  
  @FXML
  void clicSi(ActionEvent evt) {
    
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tutorado = Contexto.getInstancia().getTutorado();
  }  
  
}
