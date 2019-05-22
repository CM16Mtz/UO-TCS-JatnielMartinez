package fxml;

import cliente.Cliente;
import entidades.Administrador;
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
import interfaces.InterfazCliente;
import interfaces.InterfazServidor;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class MenuAdministradorController implements Initializable {
  
  @FXML private Button btnCerrar;
  @FXML private Button btnRegistrarTutor;
  @FXML private Button btnRegistrarTutorado;
  @FXML private Label lblBienvenido;
  
  private Administrador administrador;
  private Cliente cliente;
  private InterfazServidor servidor;
  
  @FXML
  void cerrarSesion(ActionEvent evt) throws IOException {
    servidor.cerrarSesion((InterfazCliente) cliente);
    //Se cierra la ventana y, por ende, la sesión actuales
    Stage stageMenuAdministrador;
    stageMenuAdministrador = (Stage) btnCerrar.getScene().getWindow();
    stageMenuAdministrador.close();
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
  void irARegistrarTutor(ActionEvent evt) throws IOException {
    Contexto.getInstancia().setCliente(cliente);
    Contexto.getInstancia().setServidor(servidor);
    //Se cierra la ventana
    Stage stageMenuAdministrador;
    stageMenuAdministrador = (Stage) btnRegistrarTutor.getScene().getWindow();
    stageMenuAdministrador.hide();
    //Se redirige a la ventana RegistrarTutor.fxml
    Stage stageRegistrarTutor = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/RegistrarTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageRegistrarTutor.setScene(scene);
    stageRegistrarTutor.show();
  }
  
  @FXML
  void irARegistrarTutorado(ActionEvent evt) throws IOException {
    Contexto.getInstancia().setCliente(cliente);
    Contexto.getInstancia().setServidor(servidor);
    //Se cierra la ventana
    Stage stageMenuAdministrador;
    stageMenuAdministrador = (Stage) btnRegistrarTutorado.getScene().getWindow();
    stageMenuAdministrador.hide();
    //Se redirige a la ventana RegistrarTutorado.fxml
    Stage stageRegistrarTutorado = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/RegistrarTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageRegistrarTutorado.setScene(scene);
    stageRegistrarTutorado.show();
  }

  /**
   * Se obtiene la instancia de la sesión del administrador.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    administrador = Contexto.getInstancia().getAdministrador();
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
  }
  
}
