package cliente;

import interfaces.InterfazCliente;
import interfaces.InterfazServidor;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author JatnielMartínez
 */
public class Cliente extends UnicastRemoteObject implements InterfazCliente {
  
  private transient InterfazServidor servidor;
  private static final String NOMBRE = "TutoCitas";
  private static final String NOMBRE_SERVIDOR = "10.30.5.42";
  private static final int PUERTO_SERVIDOR = 5678;
  
  /**
   * Se conecta a un servidor
   * @throws RemoteException 
   */
  public Cliente() throws RemoteException {
    super();
    try {
      Registry registro = LocateRegistry.getRegistry(NOMBRE_SERVIDOR, PUERTO_SERVIDOR);
      servidor = (InterfazServidor) registro.lookup(NOMBRE);
      servidor.registerForCallback(this);
    } catch (NotBoundException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al conectarse con el servidor");
      error.showAndWait();
    }
  }
  
  public static void main(String[] args) {
    try {
      new Cliente();
    } catch (RemoteException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al ejecutar el cliente");
      error.showAndWait();
    }
  }
  
  public InterfazServidor getServidor() {
    return servidor;
  }

  @Override
  public void notificarCancelacionCita() throws RemoteException {
    Logger.getLogger(Cliente.class.getName()).log(Level.INFO, "Método no utilizado");
  }

  @Override
  public void notificar(String mensaje) throws RemoteException {
    Logger.getLogger(Cliente.class.getName()).log(Level.INFO, "Método no utilizado");
  }
  
}
