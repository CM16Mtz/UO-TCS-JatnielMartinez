package fxml;

import cliente.Cliente;
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
import contexto.Contexto;
import entidades.Tutor;
import interfaces.InterfazServidor;
import java.rmi.RemoteException;
import java.sql.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutor tutor;
  private Tutoria tutoria;
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageConfirmarCita;
    stageConfirmarCita = (Stage) btnCancelar.getScene().getWindow();
    stageConfirmarCita.close();
    //Se regresa a CancelarCitaTutor.fxml
    Stage stageAgendarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/AgendarCita.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageAgendarCita.setScene(scene);
    stageAgendarCita.show();
  }
  
  @FXML
  void confirmar(ActionEvent evt) {
    Date fecha = Date.valueOf(dtpDia.getValue());
    String hora = txfHora.getText();
    tutoria.setFecha(fecha);
    tutoria.setHora(hora);
    if (fecha != null && !hora.isEmpty()) {
      try {
        servidor.confirmarCita(tutoria);
      } catch (RemoteException ex) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Error al guardar la cita");
        error.setHeaderText("No se pudo contactar con el servidor para guardar la cita");
        error.setContentText("Por favor, realice la confirmación más tarde");
        error.showAndWait();
      }
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Datos inválidos");
      advertencia.setHeaderText(null);
      advertencia.setContentText("Por favor, ingrese una fecha y una hora en formato HH:MM");
      advertencia.showAndWait();
    }
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutor = Contexto.getInstancia().getTutor();
    tutoria = Contexto.getInstancia().getTutoria();
    String nombreTutorado = tutoria.getTutoradoidTutorado().getUsuarioidUsuario().getNombre();
    String apPaternoTutorado = tutoria.getTutoradoidTutorado().getUsuarioidUsuario().getApPaterno();
    String apMaternoTutorado = tutoria.getTutoradoidTutorado().getUsuarioidUsuario().getApMaterno();
    String nombreCompletoTutorado = nombreTutorado + " " + apPaternoTutorado + " " + apMaternoTutorado;
    lblTutorado.setText(nombreCompletoTutorado);
  }  
  
}
