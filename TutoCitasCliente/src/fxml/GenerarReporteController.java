package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Reporte;
import entidades.Tutor;
import entidades.Tutoria;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class GenerarReporteController implements Initializable {
  
  @FXML private Button btnCancelar;
  @FXML private Button btnGenerar;
  @FXML private CheckBox chkNo;
  @FXML private CheckBox chkSi;
  @FXML private ComboBox cmbTutorias;
  @FXML private Label lbl1;
  @FXML private Label lbl2;
  @FXML private Label lbl3;
  @FXML private Label lbl4;
  @FXML private Label lbl5;
  @FXML private Label lbl6;
  @FXML private Label lblMinutos;
  @FXML private Label lblTutor;
  @FXML private TextArea txaCausa;
  @FXML private TextField txfDuracion;
  @FXML private TextField txfNumero;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutor tutor;
  
  @FXML
  void cancelar(ActionEvent evt) throws IOException {
    //Se cierra la ventana
    Stage stageGenerarReporte;
    stageGenerarReporte = (Stage) btnCancelar.getScene().getWindow();
    stageGenerarReporte.close();
    //Se regresa al menú del tutor
    Stage stageMenuTutor = new Stage();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stageMenuTutor.setScene(scene);
    stageMenuTutor.show();
  }
  
  @FXML
  void generar(ActionEvent evt) throws IOException {
    String numTutoria = txfNumero.getText();
    String duracion = txfDuracion.getText();
    boolean atendida = false;
    if (chkSi.isSelected() && !chkNo.isSelected()) {
      atendida = true;
    }
    String causa = txaCausa.getText();
    Tutoria tutoria = (Tutoria) cmbTutorias.getValue();
    if (!numTutoria.isEmpty() && !duracion.isEmpty() && tutoria != null) {
      Reporte reporte = new Reporte(Integer.parseInt(numTutoria), Integer.parseInt(duracion), atendida, tutoria);
      if (!causa.isEmpty()) {
        reporte.setCausa(causa);
      }
      servidor.generarReporte(reporte);
      //Se cierra la ventana
      Stage stageGenerarReporte;
      stageGenerarReporte = (Stage) btnGenerar.getScene().getWindow();
      stageGenerarReporte.close();
      //Se regresa al menú del tutor
      Stage stageMenuAdministrador = new Stage();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/MenuTutor.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stageMenuAdministrador.setScene(scene);
      stageMenuAdministrador.show();
    } else {
      Alert advertencia = new Alert(AlertType.WARNING);
      advertencia.setTitle("Datos inválidos");
      advertencia.setHeaderText("No se puede generar un reporte con campos vacíos");
      advertencia.setContentText("Sólo la causa puede quedar vacía");
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
    tutor = Contexto.getInstancia().getTutor();
    try {
      List<Tutoria> tutorias = servidor.consultarTutorias(tutor);
      ObservableList<Tutoria> lista = FXCollections.observableArrayList(tutorias);
      cmbTutorias.setItems(lista);
    } catch (RemoteException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de conexión");
      error.setHeaderText("No se pudieron recuperar las tutorías de la base de datos");
      error.setContentText("Por favor, realice el reporte más tarde");
      error.showAndWait();
    }
    if (chkSi.isSelected()) {
      chkNo.setSelected(false);
    }
    if (chkNo.isSelected()) {
      chkSi.setSelected(false);
      txaCausa.setDisable(false);
    }
  }  
  
}
