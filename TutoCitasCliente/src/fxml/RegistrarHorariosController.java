package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Periodo;
import entidades.Tutor;
import entidades.TutorHasBloque;
import interfaces.InterfazServidor;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JatnielMartínez
 */
public class RegistrarHorariosController implements Initializable {
  
  @FXML private Button btnRegistrar;
  @FXML private CheckBox chk0700Lun;
  @FXML private CheckBox chk0730Lun;
  @FXML private CheckBox chk0800Lun;
  @FXML private CheckBox chk0830Lun;
  @FXML private CheckBox chk0900Lun;
  @FXML private CheckBox chk0930Lun;
  @FXML private CheckBox chk1000Lun;
  @FXML private CheckBox chk1030Lun;
  @FXML private CheckBox chk1100Lun;
  @FXML private CheckBox chk1130Lun;
  @FXML private CheckBox chk1200Lun;
  @FXML private CheckBox chk1230Lun;
  @FXML private CheckBox chk1300Lun;
  @FXML private CheckBox chk1330Lun;
  @FXML private CheckBox chk1400Lun;
  @FXML private CheckBox chk1430Lun;
  @FXML private CheckBox chk1500Lun;
  @FXML private CheckBox chk1530Lun;
  @FXML private CheckBox chk1600Lun;
  @FXML private CheckBox chk1630Lun;
  @FXML private CheckBox chk1700Lun;
  @FXML private CheckBox chk1730Lun;
  @FXML private CheckBox chk1800Lun;
  @FXML private CheckBox chk1830Lun;
  @FXML private CheckBox chk1900Lun;
  @FXML private CheckBox chk1930Lun;
  @FXML private CheckBox chk2000Lun;
  @FXML private CheckBox chk2030Lun;
  @FXML private CheckBox chk0700Mar;
  @FXML private CheckBox chk0730Mar;
  @FXML private CheckBox chk0800Mar;
  @FXML private CheckBox chk0830Mar;
  @FXML private CheckBox chk0900Mar;
  @FXML private CheckBox chk0930Mar;
  @FXML private CheckBox chk1000Mar;
  @FXML private CheckBox chk1030Mar;
  @FXML private CheckBox chk1100Mar;
  @FXML private CheckBox chk1130Mar;
  @FXML private CheckBox chk1200Mar;
  @FXML private CheckBox chk1230Mar;
  @FXML private CheckBox chk1300Mar;
  @FXML private CheckBox chk1330Mar;
  @FXML private CheckBox chk1400Mar;
  @FXML private CheckBox chk1430Mar;
  @FXML private CheckBox chk1500Mar;
  @FXML private CheckBox chk1530Mar;
  @FXML private CheckBox chk1600Mar;
  @FXML private CheckBox chk1630Mar;
  @FXML private CheckBox chk1700Mar;
  @FXML private CheckBox chk1730Mar;
  @FXML private CheckBox chk1800Mar;
  @FXML private CheckBox chk1830Mar;
  @FXML private CheckBox chk1900Mar;
  @FXML private CheckBox chk1930Mar;
  @FXML private CheckBox chk2000Mar;
  @FXML private CheckBox chk2030Mar;
  @FXML private CheckBox chk0700Mie;
  @FXML private CheckBox chk0730Mie;
  @FXML private CheckBox chk0800Mie;
  @FXML private CheckBox chk0830Mie;
  @FXML private CheckBox chk0900Mie;
  @FXML private CheckBox chk0930Mie;
  @FXML private CheckBox chk1000Mie;
  @FXML private CheckBox chk1030Mie;
  @FXML private CheckBox chk1100Mie;
  @FXML private CheckBox chk1130Mie;
  @FXML private CheckBox chk1200Mie;
  @FXML private CheckBox chk1230Mie;
  @FXML private CheckBox chk1300Mie;
  @FXML private CheckBox chk1330Mie;
  @FXML private CheckBox chk1400Mie;
  @FXML private CheckBox chk1430Mie;
  @FXML private CheckBox chk1500Mie;
  @FXML private CheckBox chk1530Mie;
  @FXML private CheckBox chk1600Mie;
  @FXML private CheckBox chk1630Mie;
  @FXML private CheckBox chk1700Mie;
  @FXML private CheckBox chk1730Mie;
  @FXML private CheckBox chk1800Mie;
  @FXML private CheckBox chk1830Mie;
  @FXML private CheckBox chk1900Mie;
  @FXML private CheckBox chk1930Mie;
  @FXML private CheckBox chk2000Mie;
  @FXML private CheckBox chk2030Mie;
  @FXML private CheckBox chk0700Jue;
  @FXML private CheckBox chk0730Jue;
  @FXML private CheckBox chk0800Jue;
  @FXML private CheckBox chk0830Jue;
  @FXML private CheckBox chk0900Jue;
  @FXML private CheckBox chk0930Jue;
  @FXML private CheckBox chk1000Jue;
  @FXML private CheckBox chk1030Jue;
  @FXML private CheckBox chk1100Jue;
  @FXML private CheckBox chk1130Jue;
  @FXML private CheckBox chk1200Jue;
  @FXML private CheckBox chk1230Jue;
  @FXML private CheckBox chk1300Jue;
  @FXML private CheckBox chk1330Jue;
  @FXML private CheckBox chk1400Jue;
  @FXML private CheckBox chk1430Jue;
  @FXML private CheckBox chk1500Jue;
  @FXML private CheckBox chk1530Jue;
  @FXML private CheckBox chk1600Jue;
  @FXML private CheckBox chk1630Jue;
  @FXML private CheckBox chk1700Jue;
  @FXML private CheckBox chk1730Jue;
  @FXML private CheckBox chk1800Jue;
  @FXML private CheckBox chk1830Jue;
  @FXML private CheckBox chk1900Jue;
  @FXML private CheckBox chk1930Jue;
  @FXML private CheckBox chk2000Jue;
  @FXML private CheckBox chk2030Jue;
  @FXML private CheckBox chk0700Vie;
  @FXML private CheckBox chk0730Vie;
  @FXML private CheckBox chk0800Vie;
  @FXML private CheckBox chk0830Vie;
  @FXML private CheckBox chk0900Vie;
  @FXML private CheckBox chk0930Vie;
  @FXML private CheckBox chk1000Vie;
  @FXML private CheckBox chk1030Vie;
  @FXML private CheckBox chk1100Vie;
  @FXML private CheckBox chk1130Vie;
  @FXML private CheckBox chk1200Vie;
  @FXML private CheckBox chk1230Vie;
  @FXML private CheckBox chk1300Vie;
  @FXML private CheckBox chk1330Vie;
  @FXML private CheckBox chk1400Vie;
  @FXML private CheckBox chk1430Vie;
  @FXML private CheckBox chk1500Vie;
  @FXML private CheckBox chk1530Vie;
  @FXML private CheckBox chk1600Vie;
  @FXML private CheckBox chk1630Vie;
  @FXML private CheckBox chk1700Vie;
  @FXML private CheckBox chk1730Vie;
  @FXML private CheckBox chk1800Vie;
  @FXML private CheckBox chk1830Vie;
  @FXML private CheckBox chk1900Vie;
  @FXML private CheckBox chk1930Vie;
  @FXML private CheckBox chk2000Vie;
  @FXML private CheckBox chk2030Vie;
  @FXML private Label lblEncabezado;
  @FXML private Label lblLunes;
  @FXML private Label lblMartes;
  @FXML private Label lblMiercoles;
  @FXML private Label lblJueves;
  @FXML private Label lblViernes;
  
  private Cliente cliente;
  private InterfazServidor servidor;
  private Tutor tutor;
  
  @FXML
  void registrarHorarios(ActionEvent evt) throws RemoteException {
    if (chk0700Lun.isSelected()) {
     servidor.registrarHorarios(new TutorHasBloque());
    }
    if (chk0730Lun.isSelected()) {
      
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
  }  
  
}
