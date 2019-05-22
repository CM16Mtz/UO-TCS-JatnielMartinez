package interfaces;

import entidades.Reporte;
import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.Tutorado;
import entidades.Tutoria;
import entidades.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author JatnielMartínez
 */
public interface InterfazServidor extends Remote {
  
  public void registrarTutorado(Usuario usuario, Tutorado tutorado) throws RemoteException;
  public void registrarTutor(Usuario usuario, Tutor tutor) throws RemoteException ;
  public void registrarHorarios(TutorHasBloque horarios) throws RemoteException;
  public void setAtendida() throws RemoteException;
  public void reportarProximasTutorias() throws RemoteException;
  public void cancelarCita(Tutoria tutoria) throws RemoteException;
  public void generarReporte(Reporte reporte) throws RemoteException;
  public Usuario iniciarSesion(InterfazCliente cliente, String username, String contrasena) throws RemoteException;
  public void cerrarSesion(InterfazCliente cliente) throws RemoteException;
  public List<Tutor> consultarTutores() throws RemoteException;
  public List<Tutoria> consultarTutorias(Tutor tutor) throws RemoteException;
  
}
