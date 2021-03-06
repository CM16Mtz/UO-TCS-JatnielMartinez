package fxml;

import cliente.Cliente;
import entidades.Administrador;
import entidades.Tutor;
import entidades.Tutorado;
import entidades.Usuario;
import entidades.controladores.AdministradorJpaController;
import entidades.controladores.TutorJpaController;
import entidades.controladores.TutoradoJpaController;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import contexto.Contexto;
import interfaces.InterfazServidor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class IniciarSesionController implements Initializable {
  
  @FXML private Button btnIngresar;
  @FXML private Label lblContrasena;
  @FXML private Label lblUsuario;
  @FXML private PasswordField pwfContrasena;
  @FXML private TextField txfUsuario;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TutoCitasInterfazPU");
  
  /**
   * Intenta acceder al sistema; dependiendo del tipo de usuario, carga el menú correspondiente.
   * @param evt
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void iniciarSesion(ActionEvent evt) throws IOException {
    String username = txfUsuario.getText();
    String contrasena = pwfContrasena.getText();
    if (!username.isEmpty() && !contrasena.isEmpty()) {
      try {
        //Valida el usuario de acuerdo a los datos ingresador
        Usuario usuario = servidor.iniciarSesion(cliente, username, contrasena);
        Contexto.getInstancia().setCliente(cliente);
        Contexto.getInstancia().setServidor(servidor);
        if (usuario != null) {    //Evalúa si el usuario existe en la base de datos
          //Dependiendo del tipo de usuario, carga el menú correspondiente
          switch (usuario.getTipoUsuario()) {
            case "Administrador":
              AdministradorJpaController controladorAdministrador =
                  new AdministradorJpaController(emf);
              Administrador administrador;
              administrador = controladorAdministrador.findAdministrador(usuario);
              Contexto.getInstancia().setAdministrador(administrador);
              Contexto.getInstancia().setNumActor(1);
              //Se carga el menú de administrador
              FXMLLoader loaderAdministrador = new FXMLLoader();
              loaderAdministrador.setLocation(getClass().getResource("/fxml/MenuAdministrador.fxml"));
              Parent rootAdministrador = loaderAdministrador.load();
              Scene sceneAdministrador = new Scene(rootAdministrador);
              Stage menuAdministrador = (Stage) ((Node) evt.getSource()).getScene().getWindow();
              menuAdministrador.setScene(sceneAdministrador);
              menuAdministrador.show();
              break;
            case "Tutor":
              TutorJpaController controladorTutor = new TutorJpaController(emf);
              Tutor tutor;
              tutor = controladorTutor.findTutorByUser(usuario);
              Contexto.getInstancia().setTutor(tutor);
              Contexto.getInstancia().setNumActor(2);
              //Se carga el menú de tutor
              FXMLLoader loaderTutor = new FXMLLoader();
              loaderTutor.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
              Parent rootTutor = loaderTutor.load();
              Scene sceneTutor = new Scene(rootTutor);
              Stage menuTutor = (Stage) ((Node) evt.getSource()).getScene().getWindow();
              menuTutor.setScene(sceneTutor);
              menuTutor.show();
              break;
            case "Tutorado":
              TutoradoJpaController controladorTutorado = new TutoradoJpaController(emf);
              Tutorado tutorado;
              tutorado = controladorTutorado.findTutoradoByUser(usuario);
              Contexto.getInstancia().setTutorado(tutorado);
              Contexto.getInstancia().setNumActor(3);
              //Se carga el menú de tutorado
              FXMLLoader loaderTutorado = new FXMLLoader();
              loaderTutorado.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
              Parent rootTutorado = loaderTutorado.load();
              Scene sceneTutorado = new Scene(rootTutorado);
              Stage menuTutorado = (Stage) ((Node) evt.getSource()).getScene().getWindow();
              menuTutorado.setScene(sceneTutorado);
              menuTutorado.show();
              break;
            default:
              Alert usuarioNoExiste = new Alert(AlertType.WARNING);
              usuarioNoExiste.setTitle("No encontrado");
              usuarioNoExiste.setHeaderText("Las credenciales que ingresó no coinciden con las registradas en el sistema");
              usuarioNoExiste.setContentText("Ingrese las credenciales correctas");
              usuarioNoExiste.showAndWait();
              break;
          }
        } else {
          Alert usuarioNoExiste = new Alert(AlertType.WARNING);
          usuarioNoExiste.setTitle("No encontrado");
          usuarioNoExiste.setHeaderText("Las credenciales que ingresó no coinciden con las registradas en el sistema");
          usuarioNoExiste.setContentText("Ingrese las credenciales correctas");
          usuarioNoExiste.showAndWait();
        }
      } catch (RemoteException ex) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Se produjo un error al iniciar sesión");
        error.setContentText("Por favor, ingrese al sistema más tarde");
        error.showAndWait();
        Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
      }
    } else {
      Alert info = new Alert(AlertType.WARNING);
      info.setTitle("Acceso inválido");
      info.setHeaderText(null);
      info.setContentText("Por favor, ingrese un usuario y una contraseña");
      info.showAndWait();
    }
  }

  /**
   * Se carga la GUI con la configuración establecida.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
  }
  
}
