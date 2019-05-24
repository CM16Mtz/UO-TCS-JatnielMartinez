package fxml;

import cliente.Cliente;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class CancelarCitaTutoradoController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnRegresar;
  @FXML private Label lblEncabezado;
  @FXML private TableColumn colFecha;
  @FXML private TableColumn colHora;
  @FXML private TableView tblCitas;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutorado tutorado;
  
  @FXML
  void clicCancelar(ActionEvent evt) {
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
          tutoria.setCausa("Cita cancelada por el tutorado");
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
    }
  }
  
  @FXML
  void clicRegresar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageCancelarCita;
    stageCancelarCita = (Stage) btnRegresar.getScene().getWindow();
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
  
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutorado = Contexto.getInstancia().getTutorado();
    llenarTabla();
  }
  
  void llenarTabla() {
    colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    try {
      List<Tutoria> tutorias = servidor.consultarTutorias(tutorado);
      ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
      if (lista.size() > 0) {
        tblCitas.setItems(lista);
      } else {
        Alert advertencia = new Alert(AlertType.WARNING);
        advertencia.setTitle("No hay citas");
        advertencia.setHeaderText(null);
        advertencia.setContentText("Usted no ha reservado citas");
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