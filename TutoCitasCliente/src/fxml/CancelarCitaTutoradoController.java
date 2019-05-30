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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
  
  private InterfazServidor servidor;
  private Tutorado tutorado;
  
  /**
   * Marca la cita seleccionada como cancelada.
   * @param evt 
   */
  @FXML
  void clicCancelar(ActionEvent evt) {
    Tutoria tutoria = (Tutoria) tblCitas.getSelectionModel().getSelectedItem();
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
      Logger.getLogger(CancelarCitaTutoradoController.class.getName()).log(Level.SEVERE, "Error al cancelar", ex);
    }
  }
  
  /**
   * Se regresa al menú del tutorado.
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void clicRegresar(ActionEvent evt) throws IOException {
    Stage stageCancelarCita;
    stageCancelarCita = (Stage) btnRegresar.getScene().getWindow();
    stageCancelarCita.close();
    Stage stageMenuTutorado = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutorado.setScene(scene);
    stageMenuTutorado.show();
  }
  
  /**
   * Llena la tabla con las citas que solicitó.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Cliente cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutorado = Contexto.getInstancia().getTutorado();
    llenarTabla();
  }
  
  void llenarTabla() {
    colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    try {
      List<Tutoria> tutorias = servidor.consultarTutoriasByTutorado(tutorado);
      if (!tutorias.isEmpty()) {
        ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
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
