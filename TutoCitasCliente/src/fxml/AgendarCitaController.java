package fxml;

import entidades.Tutor;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tutocitas.Contexto;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class AgendarCitaController implements Initializable {
  
  @FXML private Button btnConfirmar;
  @FXML private Button btnRegresar;
  @FXML private Label lblEncabezado;
  @FXML private TableColumn colDia;
  @FXML private TableColumn colHora;
  @FXML private TableColumn colTutorado;
  @FXML private TableView tblCitas;
  
  private Tutor tutor;
  
  @FXML
  void confirmarCita(ActionEvent evt) {
    
  }
  
  @FXML
  void regresar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageAgendarCita;
    stageAgendarCita = (Stage) btnRegresar.getScene().getWindow();
    stageAgendarCita.close();
    //Se regresa al menú del tutor
    Stage stageMenuTutor = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutor.setScene(scene);
    stageMenuTutor.show();
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tutor = Contexto.getInstancia().getTutor();
  }  
  
}
