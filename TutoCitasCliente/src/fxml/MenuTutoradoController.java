package fxml;

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
import tutocitas.Contexto;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class MenuTutoradoController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnCerrar;
  @FXML private Button btnConsultar;
  @FXML private Button btnReservar;
  @FXML private Label lblBienvenido;
  
  private Tutorado tutorado;
  
  @FXML
  void irACancelarCita(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutorado;
    stageMenuTutorado = (Stage) btnCancelar.getScene().getWindow();
    stageMenuTutorado.hide();
    //Se establece el contexto del tutorado
    Contexto.getInstancia().setTutorado(tutorado);
    //Se redirige a la ventana CancelarCitaTutorado.fxml
    Stage stageCancelarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/CancelarCitaTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageCancelarCita.setScene(scene);
    stageCancelarCita.show();
  }
  
  @FXML
  void cerrarSesion(ActionEvent evt) throws IOException {
    //Se cierra la ventana y, por ende, la sesión actuales
    Stage stageMenuTutorado;
    stageMenuTutorado = (Stage) btnCerrar.getScene().getWindow();
    stageMenuTutorado.close();
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
    Stage stageMenuTutorado;
    stageMenuTutorado = (Stage) btnConsultar.getScene().getWindow();
    stageMenuTutorado.hide();
    //Se establece el contexto del tutor del tutorado
    Contexto.getInstancia().setTutor(tutorado.getTutoridTutor());
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
  void irAReservarCita(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageMenuTutorado;
    stageMenuTutorado = (Stage) btnReservar.getScene().getWindow();
    stageMenuTutorado.hide();
    //Se establece el contexto del tutor
    Contexto.getInstancia().setTutorado(tutorado);
    //Se redirige a la ventana ReservarCita.fxml
    Stage stageReservarCita = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/ReservarCita.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageReservarCita.setScene(scene);
    stageReservarCita.show();
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    tutorado = Contexto.getInstancia().getTutorado();
  }  
  
}
