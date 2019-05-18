package comunicacion.cliente;

import comunicacion.interfaces.InterfazCliente;
import comunicacion.interfaces.InterfazServidor;
import entidades.Usuario;
import entidades.controladores.UsuarioJpaController;
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
public class Cliente extends UnicastRemoteObject implements InterfazCliente {
  
  public Cliente() throws RemoteException {
    super();
  }
  
  public static void main(String[] args) {
    InterfazServidor servidor;
    String nombre = "TutoCitas";
    String nombreServidor = "192.168.8.5";
    int puertoServidor = 5678;
    try {
      Cliente cliente = new Cliente();
      Registry registro = LocateRegistry.getRegistry(nombreServidor, puertoServidor);
      servidor = (InterfazServidor) registro.lookup(nombre);
      servidor.registrarCallback(cliente);
    } catch (RemoteException | NotBoundException ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al conectarse con el servidor");
      error.showAndWait();
    }
  }
  
  @Override
  public Usuario iniciarSesion(String username, String contrasena) throws RemoteException {
    Usuario aux = null;
    UsuarioJpaController controlador = new UsuarioJpaController(Persistence.createEntityManagerFactory("TutoCitasPU"));
    aux = controlador.findUsuario(username, contrasena);
    return aux;
  }
  
  @Override
  public void registrarTutorado() throws RemoteException {
    
  }

  @Override
  public void reservarCita() throws RemoteException {
    
  }

  @Override
  public void notificarCita() throws RemoteException {
    
  }

  @Override
  public void iniciarCronometroCita() throws RemoteException {
    
  }

  @Override
  public void notificarCancelacionCita() throws RemoteException {
    
  }

  @Override
  public void cancelarCita() throws RemoteException {
    
  }

  @Override
  public void cerrarSesion() throws RemoteException {
    
  }

  @Override
  public void notificar(String mensaje) throws RemoteException {
    
  }
  
}
