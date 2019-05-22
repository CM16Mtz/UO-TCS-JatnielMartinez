package servidor;

import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.Tutorado;
import entidades.Tutoria;
import entidades.Usuario;
import entidades.controladores.TutorHasBloqueJpaController;
import entidades.controladores.TutorJpaController;
import entidades.controladores.TutoradoJpaController;
import entidades.controladores.TutoriaJpaController;
import entidades.controladores.UsuarioJpaController;
import interfaces.InterfazCliente;
import interfaces.InterfazServidor;
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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
  public void registrarTutorado(Usuario usuario, Tutorado tutorado) throws RemoteException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TutoCitasInterfazPU");
    UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
    controladorUsuario.create(usuario);
    tutorado.setUsuarioidUsuario(usuario);
    TutoradoJpaController controladorTutorado = new TutoradoJpaController(emf);
    controladorTutorado.create(tutorado);
    emf.close();
  }

  @Override
  public void registrarTutor(Usuario usuario, Tutor tutor) throws RemoteException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TutoCitasInterfazPU");
    UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
    controladorUsuario.create(usuario);
    tutor.setUsuarioidUsuario(usuario);
    TutorJpaController controladorTutor = new TutorJpaController(emf);
    controladorTutor.create(tutor);
    emf.close();
  }

  @Override
  public void registrarHorarios(TutorHasBloque horarios) throws RemoteException {
    TutorHasBloqueJpaController controlador = new TutorHasBloqueJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    controlador.create(horarios);
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
  public Usuario iniciarSesion(InterfazCliente cliente, String username, String contrasena) throws RemoteException {
    Usuario aux = null;
    UsuarioJpaController controlador = new UsuarioJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    aux = controlador.findUsuario(username, contrasena);
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
      return aux;
      //hacerCallback();
    }
    return null;
  }

  @Override
  public void cerrarSesion(InterfazCliente cliente) throws RemoteException {
    clientes.remove(cliente);
  }
  
  @Override
  public List<Tutor> consultarTutores() throws RemoteException {
    TutorJpaController controlador = new TutorJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    List<Tutor> tutores = controlador.findTutorEntities();
    return tutores;
  }

  @Override
  public List<Tutoria> consultarTutorias(Tutor tutor) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    List<Tutoria> tutorias = controlador.findTutoriaEntitiesByTutor(tutor);
    return tutorias;
  }
  
  private synchronized void hacerCallback() throws RemoteException {
    
  }

}
