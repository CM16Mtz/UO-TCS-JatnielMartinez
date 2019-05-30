package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Tutorado;
import entidades.Tutoria;
import interfaces.InterfazServidor;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class ReservarCitaController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnReservar;
  @FXML private DatePicker dtpDia;
  @FXML private Label lblDia;
  @FXML private Label lblHora;
  @FXML private Label lblEncabezado;
  @FXML private TextField txfHora;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutorado tutorado;
  
  /**
   * Se regresa al menú del tutorado
   * @param evt
   * @throws IOException 
   */
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    Stage stageReservarCita;
    stageReservarCita = (Stage) btnCancelar.getScene().getWindow();
    stageReservarCita.close();
    Stage stageMenuTutorado = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutorado.setScene(scene);
    stageMenuTutorado.show();
  }
  
  /**
   * Guarda una reservación en la base de datos.
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void reservar(ActionEvent evt) throws IOException {
    Date fecha = Date.valueOf(dtpDia.getValue());
    String hora = txfHora.getText();
    if (fecha != null && !hora.isEmpty()) {
      Tutoria tutoria = new Tutoria(fecha, hora, false, tutorado, tutorado.getTutoridTutor());
      try {
        servidor.reservarCita(tutoria);
        //El sistema avisa al tutorado de la reservación exitosa
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Éxito");
        info.setHeaderText(null);
        info.setContentText("Cita reservada con éxito");
        info.showAndWait();
        //Se cierra la ventana
        Contexto.getInstancia().setCliente(cliente);
        Contexto.getInstancia().setServidor(servidor);
        Contexto.getInstancia().setTutorado(tutorado);
        Stage stageReservarCita = (Stage) btnReservar.getScene().getWindow();
        stageReservarCita.close();
        //Se regresa al menú del tutorado
        Stage stageMenuTutorado = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stageMenuTutorado.setScene(scene);
        stageMenuTutorado.show();
      } catch (RemoteException ex) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Error al guardar la cita");
        error.setHeaderText("No se pudo contactar con el servidor para guardar la cita");
        error.setContentText("Por favor, realice la reservación más tarde");
        error.showAndWait();
      } catch (Exception ex) {
        Logger.getLogger(ReservarCitaController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
    tutorado = Contexto.getInstancia().getTutorado();
  }  
  
}
