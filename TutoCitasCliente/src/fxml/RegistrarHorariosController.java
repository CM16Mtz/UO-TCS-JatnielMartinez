package fxml;

import cliente.Cliente;
import contexto.Contexto;
import entidades.Bloque;
import entidades.Periodo;
import entidades.Tutor;
import entidades.TutorHasBloque;
import interfaces.InterfazServidor;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

  /**
   * Guarda los horarios, y se regresa al menú de administrador.
   * @param evt
   * @throws RemoteException Si se produce un error remoto
   * @throws IOException Si se produce un error de entrada/salida
   */
  @FXML
  void registrarHorarios(ActionEvent evt) throws IOException {
    //Periodo con id 10 que representa el periodo FEB-JUL2019
    Periodo periodo = new Periodo(10);
    //Bloque que contendrá el id correspondiente al CheckBox seleccionado
    Bloque bloque;
    //Evalúa cada uno de los checkbox para determinar si insertar una fila en la base de datos
    if (chk0700Lun.isSelected()) {
      bloque = new Bloque(1);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0730Lun.isSelected()) {
      bloque = new Bloque(2);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0800Lun.isSelected()) {
      bloque = new Bloque(3);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0830Lun.isSelected()) {
      bloque = new Bloque(4);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0900Lun.isSelected()) {
      bloque = new Bloque(5);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0930Lun.isSelected()) {
      bloque = new Bloque(6);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1000Lun.isSelected()) {
      bloque = new Bloque(7);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1030Lun.isSelected()) {
      bloque = new Bloque(8);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1100Lun.isSelected()) {
      bloque = new Bloque(9);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1130Lun.isSelected()) {
      bloque = new Bloque(10);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1200Lun.isSelected()) {
      bloque = new Bloque(11);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1230Lun.isSelected()) {
      bloque = new Bloque(12);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1300Lun.isSelected()) {
      bloque = new Bloque(13);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1330Lun.isSelected()) {
      bloque = new Bloque(14);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1400Lun.isSelected()) {
      bloque = new Bloque(15);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1430Lun.isSelected()) {
      bloque = new Bloque(16);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1500Lun.isSelected()) {
      bloque = new Bloque(17);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1530Lun.isSelected()) {
      bloque = new Bloque(18);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1600Lun.isSelected()) {
      bloque = new Bloque(19);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1630Lun.isSelected()) {
      bloque = new Bloque(20);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1700Lun.isSelected()) {
      bloque = new Bloque(21);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1730Lun.isSelected()) {
      bloque = new Bloque(22);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1800Lun.isSelected()) {
      bloque = new Bloque(23);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1830Lun.isSelected()) {
      bloque = new Bloque(24);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1900Lun.isSelected()) {
      bloque = new Bloque(25);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1930Lun.isSelected()) {
      bloque = new Bloque(26);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2000Lun.isSelected()) {
      bloque = new Bloque(27);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2030Lun.isSelected()) {
      bloque = new Bloque(28);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0700Mar.isSelected()) {
      bloque = new Bloque(29);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0730Mar.isSelected()) {
      bloque = new Bloque(30);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0800Mar.isSelected()) {
      bloque = new Bloque(31);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0830Mar.isSelected()) {
      bloque = new Bloque(32);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0900Mar.isSelected()) {
      bloque = new Bloque(33);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0930Mar.isSelected()) {
      bloque = new Bloque(34);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1000Mar.isSelected()) {
      bloque = new Bloque(35);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1030Mar.isSelected()) {
      bloque = new Bloque(36);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1100Mar.isSelected()) {
      bloque = new Bloque(37);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1130Mar.isSelected()) {
      bloque = new Bloque(38);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1200Mar.isSelected()) {
      bloque = new Bloque(39);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1230Mar.isSelected()) {
      bloque = new Bloque(40);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1300Mar.isSelected()) {
      bloque = new Bloque(41);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1330Mar.isSelected()) {
      bloque = new Bloque(42);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1400Mar.isSelected()) {
      bloque = new Bloque(43);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1430Mar.isSelected()) {
      bloque = new Bloque(44);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1500Mar.isSelected()) {
      bloque = new Bloque(45);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1530Mar.isSelected()) {
      bloque = new Bloque(46);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1600Mar.isSelected()) {
      bloque = new Bloque(47);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1630Mar.isSelected()) {
      bloque = new Bloque(48);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1700Mar.isSelected()) {
      bloque = new Bloque(49);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1730Mar.isSelected()) {
      bloque = new Bloque(50);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1800Mar.isSelected()) {
      bloque = new Bloque(51);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1830Mar.isSelected()) {
      bloque = new Bloque(52);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1900Mar.isSelected()) {
      bloque = new Bloque(53);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1930Mar.isSelected()) {
      bloque = new Bloque(54);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2000Mar.isSelected()) {
      bloque = new Bloque(55);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2030Mar.isSelected()) {
      bloque = new Bloque(56);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0700Mie.isSelected()) {
      bloque = new Bloque(57);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0730Mie.isSelected()) {
      bloque = new Bloque(58);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0800Mie.isSelected()) {
      bloque = new Bloque(59);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0830Mie.isSelected()) {
      bloque = new Bloque(60);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0900Mie.isSelected()) {
      bloque = new Bloque(61);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0930Mie.isSelected()) {
      bloque = new Bloque(62);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1000Mie.isSelected()) {
      bloque = new Bloque(63);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1030Mie.isSelected()) {
      bloque = new Bloque(64);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1100Mie.isSelected()) {
      bloque = new Bloque(65);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1130Mie.isSelected()) {
      bloque = new Bloque(66);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1200Mie.isSelected()) {
      bloque = new Bloque(67);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1230Mie.isSelected()) {
      bloque = new Bloque(68);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1300Mie.isSelected()) {
      bloque = new Bloque(69);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1330Mie.isSelected()) {
      bloque = new Bloque(70);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1400Mie.isSelected()) {
      bloque = new Bloque(71);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1430Mie.isSelected()) {
      bloque = new Bloque(72);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1500Mie.isSelected()) {
      bloque = new Bloque(73);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1530Mie.isSelected()) {
      bloque = new Bloque(74);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1600Mie.isSelected()) {
      bloque = new Bloque(75);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1630Mie.isSelected()) {
      bloque = new Bloque(76);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1700Mie.isSelected()) {
      bloque = new Bloque(77);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1730Mie.isSelected()) {
      bloque = new Bloque(78);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1800Mie.isSelected()) {
      bloque = new Bloque(79);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1830Mie.isSelected()) {
      bloque = new Bloque(80);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1900Mie.isSelected()) {
      bloque = new Bloque(81);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1930Mie.isSelected()) {
      bloque = new Bloque(82);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2000Mie.isSelected()) {
      bloque = new Bloque(83);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2030Mie.isSelected()) {
      bloque = new Bloque(84);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0700Jue.isSelected()) {
      bloque = new Bloque(85);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0730Jue.isSelected()) {
      bloque = new Bloque(86);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0800Jue.isSelected()) {
      bloque = new Bloque(87);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0830Jue.isSelected()) {
      bloque = new Bloque(88);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0900Jue.isSelected()) {
      bloque = new Bloque(89);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0930Jue.isSelected()) {
      bloque = new Bloque(90);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1000Jue.isSelected()) {
      bloque = new Bloque(91);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1030Jue.isSelected()) {
      bloque = new Bloque(92);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1100Jue.isSelected()) {
      bloque = new Bloque(93);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1130Jue.isSelected()) {
      bloque = new Bloque(94);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1200Jue.isSelected()) {
      bloque = new Bloque(95);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1230Jue.isSelected()) {
      bloque = new Bloque(96);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1300Jue.isSelected()) {
      bloque = new Bloque(97);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1330Jue.isSelected()) {
      bloque = new Bloque(98);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1400Jue.isSelected()) {
      bloque = new Bloque(99);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1430Jue.isSelected()) {
      bloque = new Bloque(100);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1500Jue.isSelected()) {
      bloque = new Bloque(101);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1530Jue.isSelected()) {
      bloque = new Bloque(102);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1600Jue.isSelected()) {
      bloque = new Bloque(103);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1630Jue.isSelected()) {
      bloque = new Bloque(104);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1700Jue.isSelected()) {
      bloque = new Bloque(105);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1730Jue.isSelected()) {
      bloque = new Bloque(106);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1800Jue.isSelected()) {
      bloque = new Bloque(107);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1830Jue.isSelected()) {
      bloque = new Bloque(108);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1900Jue.isSelected()) {
      bloque = new Bloque(109);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1930Jue.isSelected()) {
      bloque = new Bloque(110);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2000Jue.isSelected()) {
      bloque = new Bloque(111);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2030Jue.isSelected()) {
      bloque = new Bloque(112);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0700Vie.isSelected()) {
      bloque = new Bloque(113);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0730Vie.isSelected()) {
      bloque = new Bloque(114);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0800Vie.isSelected()) {
      bloque = new Bloque(115);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0830Vie.isSelected()) {
      bloque = new Bloque(116);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0900Vie.isSelected()) {
      bloque = new Bloque(117);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk0930Vie.isSelected()) {
      bloque = new Bloque(118);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1000Vie.isSelected()) {
      bloque = new Bloque(119);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1030Vie.isSelected()) {
      bloque = new Bloque(120);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1100Vie.isSelected()) {
      bloque = new Bloque(121);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1130Vie.isSelected()) {
      bloque = new Bloque(122);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1200Vie.isSelected()) {
      bloque = new Bloque(123);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1230Vie.isSelected()) {
      bloque = new Bloque(124);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1300Vie.isSelected()) {
      bloque = new Bloque(125);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1330Vie.isSelected()) {
      bloque = new Bloque(126);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1400Vie.isSelected()) {
      bloque = new Bloque(127);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1430Vie.isSelected()) {
      bloque = new Bloque(128);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1500Vie.isSelected()) {
      bloque = new Bloque(129);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1530Vie.isSelected()) {
      bloque = new Bloque(130);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1600Vie.isSelected()) {
      bloque = new Bloque(131);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1630Vie.isSelected()) {
      bloque = new Bloque(132);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1700Vie.isSelected()) {
      bloque = new Bloque(133);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1730Vie.isSelected()) {
      bloque = new Bloque(134);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1800Vie.isSelected()) {
      bloque = new Bloque(135);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1830Vie.isSelected()) {
      bloque = new Bloque(136);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1900Vie.isSelected()) {
      bloque = new Bloque(137);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk1930Vie.isSelected()) {
      bloque = new Bloque(138);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2000Vie.isSelected()) {
      bloque = new Bloque(139);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    if (chk2030Vie.isSelected()) {
      bloque = new Bloque(140);
      servidor.registrarHorarios(new TutorHasBloque(bloque, periodo, tutor));
    }
    //El sistema avisa al administrador del registro exitoso
    Alert info = new Alert(AlertType.INFORMATION);
    info.setTitle("Éxito");
    info.setHeaderText(null);
    info.setContentText("Tutor registrado con éxito");
    info.showAndWait();
    //Se cierra la ventana
    Contexto.getInstancia().setCliente(cliente);
    Contexto.getInstancia().setServidor(servidor);
    Stage stageRegistrarHorarios;
    stageRegistrarHorarios = (Stage) btnRegistrar.getScene().getWindow();
    stageRegistrarHorarios.close();
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
   * Initializes the controller class.
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cliente = Contexto.getInstancia().getCliente();
    servidor = Contexto.getInstancia().getServidor();
    tutor = Contexto.getInstancia().getTutor();
  }  
  
}
