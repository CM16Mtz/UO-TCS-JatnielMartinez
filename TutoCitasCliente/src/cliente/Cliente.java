package cliente;

import entidades.Usuario;
import entidades.controladores.UsuarioJpaController;
import interfaces.InterfazCliente;
import interfaces.InterfazServidor;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.persistence.Persistence;

/**
 *
 * @author JatnielMartínez
 */
public class Cliente extends UnicastRemoteObject {
  
  private static InterfazServidor servidor;
  private static String nombre;
  private static String nombreServidor;
  private static int puertoServidor;
  
  public Cliente() throws RemoteException {
    super();
    nombre = "TutoCitas";
    nombreServidor = "192.168.8.5";
    puertoServidor = 5678;
  }
  
  public static void main(String[] args) {
    try {
      Cliente cliente;
      cliente = new Cliente();
      Registry registro = LocateRegistry.getRegistry(nombreServidor, puertoServidor);
      servidor = (InterfazServidor) registro.lookup(nombre);
      //servidor.iniciarSesion(cliente);
    } catch (RemoteException | NotBoundException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al conectarse con el servidor");
      error.showAndWait();
    }
  }
  
  public InterfazServidor getServidor() {
    return servidor;
  }
  
}
