package interfaces;

import entidades.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JatnielMartínez
 */
public interface InterfazCliente extends Remote {
  
  public void notificarCancelacionCita() throws RemoteException;
  public void notificar(String mensaje) throws RemoteException;
  
}
