package comunicacion.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JatnielMartínez
 */
public interface InterfazCliente extends Remote {
  
  public void registrarTutorado() throws RemoteException;
  public void iniciarSesion() throws RemoteException;
  public void reservarCita() throws RemoteException;
  public void notificarCita() throws RemoteException;
  public void iniciarCronometroCita() throws RemoteException;
  public void notificarCancelacionCita() throws RemoteException;
  public void cancelarCita() throws RemoteException;
  public void cerrarSesion() throws RemoteException;
  public void notificar(String mensaje) throws RemoteException;
  
}
