package comunicacion.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JatnielMartínez
 */
public interface InterfazServidor extends Remote {
  
  public void registrarTutor() throws RemoteException ;
  public void registrarHorarios() throws RemoteException;
  public void setAtendida() throws RemoteException;
  public void reportarProximasTutorias() throws RemoteException;
  public void cancelarCita() throws RemoteException;
  public void generarReporte() throws RemoteException;
  public void registrarCallback(InterfazCliente cliente) throws RemoteException;
  public void cerrarCallback(InterfazCliente cliente) throws RemoteException;
  
}
