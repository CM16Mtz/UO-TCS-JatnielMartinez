package cliente;

import interfaces.InterfazCliente;
import interfaces.InterfazServidor;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author JatnielMartínez
 */
public class Cliente extends UnicastRemoteObject implements InterfazCliente {
  
  private static InterfazServidor servidor;
  private static String nombre;
  private static String nombreServidor;
  private static int puertoServidor;
  
  public Cliente() throws RemoteException {
    super();
    nombre = "TutoCitas";
    nombreServidor = "localhost";
    puertoServidor = 5678;
    try {
      Registry registro = LocateRegistry.getRegistry(nombreServidor, puertoServidor);
      servidor = (InterfazServidor) registro.lookup(nombre);
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
    /*  Cliente cliente;
      cliente = new Cliente();
      Registry registro = LocateRegistry.getRegistry(nombreServidor, puertoServidor);
      servidor = (InterfazServidor) registro.lookup(nombre);
      servidor.registerForCallback(this);*/
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
    
  }

  @Override
  public void notificar(String mensaje) throws RemoteException {
    
  }
  
}
