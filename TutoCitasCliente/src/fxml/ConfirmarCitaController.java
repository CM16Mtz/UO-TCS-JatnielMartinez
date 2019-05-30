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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
  
  /**
   * Se regresa a la GUI AgendarCita.fxml
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    Stage stageConfirmarCita;
    stageConfirmarCita = (Stage) btnCancelar.getScene().getWindow();
    stageConfirmarCita.close();
    Stage stageAgendarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/AgendarCita.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageAgendarCita.setScene(scene);
    stageAgendarCita.show();
  }
  
  /**
   * Guarda la cita con los cambios realizados, si es que se hicieron.
   * @param evt 
   */
  @FXML
  void confirmar(ActionEvent evt) throws IOException {
    Date fecha = Date.from(dtpDia.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    String hora = txfHora.getText();
    tutoria.setFecha(fecha);
    tutoria.setHora(hora);
    if (fecha != null && !hora.isEmpty() && hora.length() == 5) {
      try {
        servidor.confirmarCita(tutoria);
        //El sistema avisa al tutor de la confirmación exitosa
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Éxito");
        info.setHeaderText(null);
        info.setContentText("Cita confirmada con éxito");
        info.showAndWait();
        //Se cierra la ventana
        Contexto.getInstancia().setCliente(cliente);
        Contexto.getInstancia().setServidor(servidor);
        Contexto.getInstancia().setTutor(tutor);
        Stage stageConfirmarCita = (Stage) btnConfirmar.getScene().getWindow();
        stageConfirmarCita.close();
        //Se regresa al menú del tutor
        Stage stageMenuTutor = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stageMenuTutor.setScene(scene);
        stageMenuTutor.show();
      } catch (RemoteException ex) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Error al guardar la cita");
        error.setHeaderText("No se pudo contactar con el servidor para guardar la cita");
        error.setContentText("Por favor, realice la confirmación más tarde");
        error.showAndWait();
        ex.printStackTrace();
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
   * TOma los datos de la tutoría seleccionada.
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
    dtpDia.setValue(tutoria.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    txfHora.setText(tutoria.getHora());
  }  
  
}
