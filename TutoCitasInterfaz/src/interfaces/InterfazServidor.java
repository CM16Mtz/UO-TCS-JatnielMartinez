package interfaces;

import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JatnielMartínez
 */
public interface InterfazServidor extends Remote {
  
  public void registrarTutor(Usuario usuario, Tutor tutor) throws RemoteException ;
  public void registrarHorarios(TutorHasBloque horarios) throws RemoteException;
  public void setAtendida() throws RemoteException;
  public void reportarProximasTutorias() throws RemoteException;
  public void cancelarCita() throws RemoteException;
  public void generarReporte() throws RemoteException;
  public Usuario iniciarSesion(InterfazCliente cliente, String username, String contrasena) throws RemoteException;
  public void cerrarSesion(InterfazCliente cliente) throws RemoteException;
  
}
