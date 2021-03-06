package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Tutor;
import entidades.Usuario;
import entidades.controladores.TutorJpaController;
import interfaces.InterfazServidor;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class RegistrarTutorController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnRegistrar;
  @FXML private Label lblApMaterno;
  @FXML private Label lblApPaterno;
  @FXML private Label lblContrasena;
  @FXML private Label lblCorreo;
  @FXML private Label lblNombre;
  @FXML private Label lblNoPersonal;
  @FXML private PasswordField pwfContrasena;
  @FXML private TextField txfApMaterno;
  @FXML private TextField txfApPaterno;
  @FXML private TextField txfCorreo;
  @FXML private TextField txfNombre;
  @FXML private TextField txfNoPersonal;
  
  private Cliente cliente;
  private InterfazServidor servidor;

  /**
   * Se regresa al menú del administrador
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    Stage stageRegistrarTutor;
    stageRegistrarTutor = (Stage) btnCancelar.getScene().getWindow();
    stageRegistrarTutor.close();
    Stage stageMenuAdministrador = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuAdministrador.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuAdministrador.setScene(scene);
    stageMenuAdministrador.show();
  }
  
  /**
   * Registra un usuario y un tutor, y procede a solicitar los horarios a registrar.
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void registrarTutor(ActionEvent evt) throws IOException {
    String nombre = txfNombre.getText();
    String apPaterno = txfApPaterno.getText();
    String apMaterno = txfApMaterno.getText();
    String correo = txfCorreo.getText();
    String noPersonal = txfNoPersonal.getText();
    String contrasena = pwfContrasena.getText();
    if (!nombre.isEmpty() && !apPaterno.isEmpty() && !correo.isEmpty() && !noPersonal.isEmpty()
        && !contrasena.isEmpty() && noPersonal.length() == 5) {
      try {
        //Se crea el usuario
        Usuario usuario = new Usuario(noPersonal, contrasena, "Tutor", nombre, apPaterno, correo);
        if (!apMaterno.isEmpty()) {
          usuario.setApMaterno(apMaterno);
        }
        //Se crea el tutor
        Tutor tutor = new Tutor();
        tutor.setNoPersonal(noPersonal);
        //Se llama al método para registrar el tutor
        servidor.registrarTutor(usuario, tutor);
        //Ahora se obtiene el id generado automáticamente del tutor
        TutorJpaController controller
            = new TutorJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
        Integer id = controller.getIdByNoPersonal(tutor);
        tutor.setIdTutor(id);
        //Se guarda el tutor para utilizarlo en la asignación de sus horarios
        Contexto.getInstancia().setTutor(tutor);
        //Asimismo se conservan las instancias del cliente y el servidor
        Contexto.getInstancia().setCliente(cliente);
        Contexto.getInstancia().setServidor(servidor);
        //Se cierra la ventana
        Stage stageRegistrarTutor;
        stageRegistrarTutor = (Stage) btnRegistrar.getScene().getWindow();
        stageRegistrarTutor.close();
        //Se redirige a RegistrarHorarios.fxml
        Stage stageRegistrarHorarios = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RegistrarHorarios.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stageRegistrarHorarios.setScene(scene);
        stageRegistrarHorarios.show();
      } catch (Exception ex) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("ERROR");
        error.setHeaderText(null);
        error.setContentText("Se produjo un error inesperado");
        error.showAndWait();
        Logger.getLogger(RegistrarTutorController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
      }
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Datos inválidos");
      advertencia.setHeaderText("No se puede registrar a un tutor con campos vacíos");
      advertencia.setContentText("Por favor, llene todos los datos (con excepción del apellido materno)");
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
    txfNoPersonal.setPrefColumnCount(5);
  }  
  
}
