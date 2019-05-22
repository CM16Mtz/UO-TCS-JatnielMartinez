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
import entidades.Tutoria;
import interfaces.InterfazServidor;
import java.rmi.RemoteException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutor tutor;
  
  @FXML
  void confirmarCita(ActionEvent evt) throws IOException {
    Tutoria tutoria = (Tutoria) tblCitas.getSelectionModel().getSelectedItem();
    if (tutoria != null) {
      Contexto.getInstancia().setCliente(cliente);
      Contexto.getInstancia().setServidor(servidor);
      Contexto.getInstancia().setTutor(tutor);
      Contexto.getInstancia().setTutoria(tutoria);
      //Se cierra la ventana
      Stage stageAgendarCita;
      stageAgendarCita = (Stage) btnConfirmar.getScene().getWindow();
      stageAgendarCita.close();
      //Se regresa al menú del tutor
      Stage stageConfirmarCita = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/ConfirmarCita.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stageConfirmarCita.setScene(scene);
      stageConfirmarCita.show();
    }
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
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutor = Contexto.getInstancia().getTutor();
    colDia.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    colTutorado.setCellValueFactory(new PropertyValueFactory<>("Tutorado_idTutorado"));
    try {
      List<Tutoria> tutorias = servidor.consultarTutorias(tutor);
      ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
      if (lista.size() > 0) {
        tblCitas.setItems(lista);
      } else {
        tblCitas.setItems(null);
        Alert advertencia = new Alert(AlertType.WARNING);
        advertencia.setTitle("No hay citas");
        advertencia.setHeaderText(null);
        advertencia.setContentText("No hay citas por agendar");
        advertencia.showAndWait();
      }
    } catch (RemoteException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de conexión");
      error.setHeaderText("No se pudieron recuperar las tutorías de la base de datos");
      error.setContentText("Por favor, consulte sus citas más tarde");
      error.showAndWait();
    }
  }  
  
}
