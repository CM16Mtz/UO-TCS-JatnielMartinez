package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Tutorado;
import interfaces.InterfazServidor;
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
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana y, por ende, la sesión actuales
    Stage stageReservarCita;
    stageReservarCita = (Stage) btnCancelar.getScene().getWindow();
    stageReservarCita.close();
    //Se regresa al menú de tutorado
    Stage stageMenuTutorado = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutorado.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutorado.setScene(scene);
    stageMenuTutorado.show();
  }
  
  @FXML
  void reservar(ActionEvent evt) {
    
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
