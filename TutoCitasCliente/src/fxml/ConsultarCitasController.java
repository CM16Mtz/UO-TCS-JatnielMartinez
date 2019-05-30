package fxml;

import cliente.Cliente;
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
import contexto.Contexto;
import entidades.Tutorado;
import entidades.Tutoria;
import interfaces.InterfazServidor;
import java.rmi.RemoteException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class ConsultarCitasController implements Initializable {
  
  @FXML private Button btnRegresar;
  @FXML private Label lblEncabezado;
  @FXML private TableColumn colFecha;
  @FXML private TableColumn colHora;
  @FXML private TableColumn colTutorado;
  @FXML private TableView tblCitas;
  
  private Tutor tutor;
  private Tutorado tutorado;
  
  /**
   * Se regresa al menú correspondiente.
   * @param evt
   * @throws IOException 
   */
  @FXML
  void regresar(ActionEvent evt) throws IOException {
    Stage stageConsultarCitas;
    stageConsultarCitas = (Stage) btnRegresar.getScene().getWindow();
    stageConsultarCitas.close();
    //Se regresa al menú de tutor o tutorado
    Stage stageMenu = new Stage();
    FXMLLoader loader = new FXMLLoader();
    //El sistema se regresa al menú correspondiente al contexto
    if (tutor != null && tutorado == null) {
      loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
    } else if (tutor == null && tutorado != null) {
      loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
    }
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenu.setScene(scene);
    stageMenu.show();
  }

  /**
   * Llena la tabla con las tutorías correspondientes.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Cliente cliente = Contexto.getInstancia().getCliente();
    InterfazServidor servidor = Contexto.getInstancia().getServidor();
    tutor = Contexto.getInstancia().getTutor();
    tutorado = Contexto.getInstancia().getTutorado();
    colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    colTutorado.setCellValueFactory(new PropertyValueFactory<>("username"));
    List<Tutoria> tutorias = null;
    try {
      if (tutor != null && tutorado == null) {
        tutorias = servidor.consultarTutoriasByTutor(tutor);
      } else if (tutor == null && tutorado != null) {
        tutorias = servidor.consultarTutoriasByTutor(tutorado.getTutoridTutor());
      }
      ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
      if (lista.size() > 0) {
        tblCitas.setItems(lista);
      } else {
        tblCitas.setItems(null);
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle("No hay citas");
        advertencia.setHeaderText(null);
        if (tutor != null && tutorado == null) {
          advertencia.setContentText("Usted no tiene citas programadas");
        } else if (tutor == null && tutorado != null) {
          advertencia.setContentText("Su tutor no tiene citas programadas");
        }
        advertencia.showAndWait();
      }
    } catch (RemoteException ex) {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setTitle("Error de conexión");
      error.setHeaderText("No se pudieron recuperar las tutorías de la base de datos");
      error.setContentText("Por favor, consulte sus citas más tarde");
      error.showAndWait();
    }
  }  
  
}
