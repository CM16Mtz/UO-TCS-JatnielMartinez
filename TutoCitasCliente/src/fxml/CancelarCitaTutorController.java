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
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class CancelarCitaTutorController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnRegresar;
  @FXML private Label lblEncabezado;
  @FXML private TableColumn colDia;
  @FXML private TableColumn colHora;
  @FXML private TableColumn colTutorado;
  @FXML private TableView tblCitas;
  
  private InterfazServidor servidor;
  private Tutor tutor;
  
  /**
   * Marca la cita seleccionada como cancelada.
   * @param evt 
   */
  @FXML
  void cancelarCita(ActionEvent evt) {
    Tutoria tutoria = (Tutoria) tblCitas.getSelectionModel().getSelectedItem();
    if (tutoria != null) {
      Alert confirmacion = new Alert(
          AlertType.CONFIRMATION,
          "¿Está seguro que desea cancelar la cita seleccionada?",
          ButtonType.YES,
          ButtonType.NO);
      confirmacion.showAndWait();
      if (confirmacion.getResult() == ButtonType.YES) {
        try {
          tutoria.setCancelada(true);
          tutoria.setCausa("Cita cancelada por el tutor");
          servidor.cancelarCita(tutoria);
          llenarTabla();
        } catch (RemoteException ex) {
          Alert error = new Alert(AlertType.ERROR);
          error.setTitle("Error al cancelar");
          error.setHeaderText("No se pudo contactar con el servidor para cancelar la cita");
          error.setContentText("Por favor, realice la cancelación más tarde");
          error.showAndWait();
        }
      }
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Advertencia");
      advertencia.setHeaderText(null);
      advertencia.setContentText("Por favor, seleccione una cita de la tabla");
      advertencia.showAndWait();
    }
  }
  
  /**
   * Se regresa al menú del tutor.
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void regresar(ActionEvent evt) throws IOException {
    Stage stageCancelarCita;
    stageCancelarCita = (Stage) btnRegresar.getScene().getWindow();
    stageCancelarCita.close();
    Stage stageMenuTutor = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutor.setScene(scene);
    stageMenuTutor.show();
  }

  /**
   * Llena la tabla con las citas que no tiene canceladas.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Cliente cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutor = Contexto.getInstancia().getTutor();
    llenarTabla();
  }
  
  void llenarTabla() {
    colDia.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    colTutorado.setCellValueFactory(new PropertyValueFactory<>("username"));
    try {
      List<Tutoria> tutorias = servidor.consultarTutoriasByTutor(tutor);
      ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
      if (lista.size() > 0) {
        tblCitas.setItems(lista);
      } else {
        tblCitas.setItems(null);
        Alert advertencia = new Alert(AlertType.WARNING);
        advertencia.setTitle("No hay citas");
        advertencia.setHeaderText(null);
        advertencia.setContentText("Usted no tiene citas programadas");
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
