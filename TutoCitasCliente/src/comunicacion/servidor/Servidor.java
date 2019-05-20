package comunicacion.servidor;

import comunicacion.interfaces.InterfazCliente;
import comunicacion.interfaces.InterfazServidor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author JatnielMartínez
 */
public class Servidor extends UnicastRemoteObject implements InterfazServidor {
  
  private static final int PUERTO = 5678;
  private final List<InterfazCliente> clientes;
  
  public Servidor() throws RemoteException {
    super();
    clientes = new ArrayList<>();
  }
  
  public static void main() throws RemoteException {
    (new Servidor()).iniciarServidor();
  }
  
  public void iniciarServidor() {
    try {
      String direccion = (InetAddress.getLocalHost()).toString();
      Alert iniciando = new Alert(AlertType.INFORMATION);
      iniciando.setTitle("Servidor");
      iniciando.setHeaderText(null);
      iniciando.setContentText("Iniciando servidor en " + direccion + ":" + PUERTO);
      iniciando.showAndWait();
      Registry registro = LocateRegistry.createRegistry(PUERTO);
      registro.bind("TutoCitas", (InterfazServidor) this);
      Alert iniciado = new Alert(AlertType.INFORMATION);
      iniciado.setTitle("Servidor");
      iniciado.setHeaderText(null);
      iniciado.setContentText("Servidor iniciado");
      iniciado.showAndWait();
    } catch (UnknownHostException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de servidor");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al iniciar el servidor");
      error.showAndWait();
    } catch (RemoteException | AlreadyBoundException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de servidor");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error en el servidor");
      error.showAndWait();
    }
  }

  @Override
  public void registrarTutor() throws RemoteException {
    
  }

  @Override
  public void registrarHorarios() throws RemoteException {
    
  }

  @Override
  public void setAtendida() throws RemoteException {
    
  }

  @Override
  public void reportarProximasTutorias() throws RemoteException {
    
  }

  @Override
  public void cancelarCita() throws RemoteException {
    
  }

  @Override
  public void generarReporte() throws RemoteException {
    
  }

  @Override
  public void registrarCallback(InterfazCliente cliente) throws RemoteException {
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
      hacerCallback();
    }
  }

  @Override
  public void cerrarCallback(InterfazCliente cliente) throws RemoteException {
    clientes.remove(cliente);
  }
  
  private synchronized void hacerCallback() throws RemoteException {
    
  }
  
}
