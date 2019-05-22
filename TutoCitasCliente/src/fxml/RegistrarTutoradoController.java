package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Tutor;
import entidades.Tutorado;
import entidades.Usuario;
import interfaces.InterfazServidor;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class RegistrarTutoradoController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnRegistrar;
  @FXML private ComboBox cmbTutor;
  @FXML private Label lblApMaterno;
  @FXML private Label lblApPaterno;
  @FXML private Label lblCarrera;
  @FXML private Label lblCorreo;
  @FXML private Label lblContrasena;
  @FXML private Label lblMatricula;
  @FXML private Label lblNombre;
  @FXML private Label lblTutor;
  @FXML private PasswordField pwfContrasena;
  @FXML private TextField txfApMaterno;
  @FXML private TextField txfApPaterno;
  @FXML private TextField txfCarrera;
  @FXML private TextField txfCorreo;
  @FXML private TextField txfMatricula;
  @FXML private TextField txfNombre;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private List<Tutor> tutores;
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageRegistrarTutorado;
    stageRegistrarTutorado = (Stage) btnCancelar.getScene().getWindow();
    stageRegistrarTutorado.close();
    //Se regresa al menú del administrador
    Stage stageMenuAdministrador = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuAdministrador.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuAdministrador.setScene(scene);
    stageMenuAdministrador.show();
  }
  
  @FXML
  void registrar(ActionEvent evt) throws IOException {
    String nombre = txfNombre.getText();
    String apPaterno = txfApPaterno.getText();
    String apMaterno = txfApMaterno.getText();
    String correo = txfCorreo.getText();
    String carrera = txfCarrera.getText();
    String matricula = txfMatricula.getText();
    String contrasena = pwfContrasena.getText();
    Tutor tutor = (Tutor) cmbTutor.getValue();
    if (!nombre.isEmpty() && !apPaterno.isEmpty() && !correo.isEmpty() && !carrera.isEmpty()
        && !matricula.isEmpty() && !contrasena.isEmpty()) {
      //Se crea el usuario
      Usuario usuario = new Usuario(matricula, contrasena, "Tutorado", nombre, apPaterno, correo);
      if (!apMaterno.isEmpty()) {
        usuario.setApMaterno(apMaterno);
      }
      //Se crea el tutorado
      Tutorado tutorado = new Tutorado();
      tutorado.setCarrera(carrera);
      tutorado.setMatricula(matricula);
      tutorado.setTutoridTutor(tutor);
      //Se llama al método para registrar el tutor
      servidor.registrarTutorado(usuario, tutorado);
      //Se cierra la ventana
      Stage stageRegistrarTutorado;
      stageRegistrarTutorado = (Stage) btnRegistrar.getScene().getWindow();
      stageRegistrarTutorado.close();
      //Se regresa al menú del administrador
      Stage stageMenuAdministrador = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/MenuAdministrador.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stageMenuAdministrador.setScene(scene);
      stageMenuAdministrador.show();
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Datos inválidos");
      advertencia.setHeaderText("No se puede registrar a un tutorado con campos vacíos");
      advertencia.setContentText("Por favor, llene todos los datos");
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
    try {
      tutores = servidor.consultarTutores();
      ObservableList<Tutor> lista = FXCollections.observableArrayList(tutores);
      cmbTutor.setItems(lista);
    } catch (RemoteException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de conexión");
      error.setHeaderText("No se pudieron recuperar los tutores de la base de datos");
      error.setContentText("Por favor, realice el registro más tarde");
      error.showAndWait();
    }
  }  
  
}
