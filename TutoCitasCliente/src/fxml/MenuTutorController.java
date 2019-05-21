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
import javafx.stage.Stage;
import contexto.Contexto;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class MenuTutorController implements Initializable {
  
  @FXML private Button btnAgendar;
  @FXML private Button btnCancelar;
  @FXML private Button btnCerrar;
  @FXML private Button btnConsultar;
  @FXML private Button btnGenerar;
  @FXML private Label lblBienvenido;
  
  private Tutor tutor;
  
  @FXML
  void irAAgendarCita(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutor;
    stageMenuTutor = (Stage) btnAgendar.getScene().getWindow();
    stageMenuTutor.hide();
    //Se establece el contexto del tutor
    Contexto.getInstancia().setTutor(tutor);
    //Se redirige a la ventana AgendarCita.fxml
    Stage stageAgendarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/AgendarCita.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageAgendarCita.setScene(scene);
    stageAgendarCita.show();
  }
  
  @FXML
  void irACancelarCita(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutor;
    stageMenuTutor = (Stage) btnCancelar.getScene().getWindow();
    stageMenuTutor.hide();
    //Se establece el contexto del tutor
    Contexto.getInstancia().setTutor(tutor);
    //Se redirige a la ventana CancelarCitaTutor.fxml
    Stage stageCancelarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/CancelarCitaTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageCancelarCita.setScene(scene);
    stageCancelarCita.show();
  }
  
  @FXML
  void cerrarSesion(ActionEvent evt) throws IOException {
    //Se cierra la ventana y, por ende, la sesión actuales
    Stage stageMenuTutor;
    stageMenuTutor = (Stage) btnCerrar.getScene().getWindow();
    stageMenuTutor.close();
    //Se regresa a la ventana de inicio de sesión
    Stage stageInicioSesion = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/IniciarSesion.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageInicioSesion.setScene(scene);
    stageInicioSesion.show();
  }
  
  @FXML
  void irAConsultarCitas(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutor;
    stageMenuTutor = (Stage) btnConsultar.getScene().getWindow();
    stageMenuTutor.hide();
    //Se establece el contexto del tutor
    Contexto.getInstancia().setTutor(tutor);
    //Se redirige a la ventana ConsultarCitas.fxml
    Stage stageConsultarCitas = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/ConsultarCitas.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageConsultarCitas.setScene(scene);
    stageConsultarCitas.show();
  }
  
  @FXML
  void irAGenerarReporte(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutor;
    stageMenuTutor = (Stage) btnGenerar.getScene().getWindow();
    stageMenuTutor.hide();
    //Se establece el contexto del tutor
    Contexto.getInstancia().setTutor(tutor);
    //Se redirige a la ventana GenerarReporte.fxml
    Stage stageGenerarReporte = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/GenerarReporte.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageGenerarReporte.setScene(scene);
    stageGenerarReporte.show();
  }

  /**
   * Se obtiene la instancia de la sesión del tutor.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tutor = Contexto.getInstancia().getTutor();
  }  
  
}
