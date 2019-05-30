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
  
  /**
   * Se regresa al menú del administrador
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
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
  
  /**
   * Guarda un usuario y un tutorado en la base de datos, y se regresa al menú del administrador
   * @param evt
   * @throws IOException 
   */
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
        && !matricula.isEmpty() && !contrasena.isEmpty() && tutor != null && contrasena.startsWith("s")) {
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
      //Se llama al método para registrar el tutorado
      servidor.registrarTutorado(usuario, tutorado);
      //El sistema avisa al administrador del registro exitoso
      Alert info = new Alert(AlertType.INFORMATION);
      info.setTitle("Éxito");
      info.setHeaderText(null);
      info.setContentText("Tutor registrado con éxito");
      info.showAndWait();
      //Se cierra la ventana
      Contexto.getInstancia().setCliente(cliente);
      Contexto.getInstancia().setServidor(servidor);
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
      advertencia.setContentText("Por favor, llene todos los datos (con excepción del apellido materno)");
      advertencia.showAndWait();
    }
  }

  /**
   * Carga el combo box con los tutores guardados en la base de datos
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    txfMatricula.setPrefColumnCount(9);
    try {
      List<Tutor> tutores = servidor.consultarTutores();
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
