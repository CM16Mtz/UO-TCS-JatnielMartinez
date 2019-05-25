package servidor;



import entidades.Reporte;
import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.Tutorado;
import entidades.Tutoria;
import entidades.Usuario;
import entidades.controladores.ReporteJpaController;
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
  private static final long SERIAL_VERSION_UID = 1L;
  private final List<InterfazCliente> clientes;
  
  public Servidor() throws RemoteException {
    super();
    clientes = new ArrayList<>();
  }
  
  public static void main(String[] args) throws RemoteException {
    (new Servidor()).iniciarServidor();
  }
  
  public void iniciarServidor() {
    try {
      String direccion = (InetAddress.getLocalHost()).toString();
      System.out.println("Iniciando servidor en " + direccion + ":" + PUERTO);
      Registry registro = LocateRegistry.createRegistry(PUERTO);
      registro.bind("TutoCitas", (InterfazServidor) this);
      System.out.println("Servidor iniciado");
    } catch (UnknownHostException ex) {
      System.err.println("Se produjo un error al iniciar el servidor");
      System.err.println("UnknownHostException: " + ex.getMessage());
    } catch (RemoteException | AlreadyBoundException ex) {
      System.err.println("Se produjo un error al iniciar el servidor");
      System.err.println("RemoteException | AlreadyBoundException: " + ex.getMessage());
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
  public void reservarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    controlador.create(tutoria);
  }
  
  @Override
  public void confirmarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    try {
      controlador.edit(tutoria);
    } catch (Exception ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de cancelación");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al confirmar la cita");
      error.showAndWait();
    }
  }

  @Override
  public void reportarProximasTutorias() throws RemoteException {
    
  }

  @Override
  public void cancelarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    try {
      controlador.edit(tutoria);
    } catch (Exception ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de cancelación");
      error.setHeaderText(null);
      error.setContentText("La cita que usted quiere cancelar no se encuentra en nuestra base de datos");
      error.showAndWait();
    }
  }

  @Override
  public void generarReporte(Reporte reporte) throws RemoteException {
    ReporteJpaController controlador = new ReporteJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    controlador.create(reporte);
  }

  @Override
  public Usuario iniciarSesion(String username, String contrasena) throws RemoteException {
    Usuario aux = new Usuario();
    UsuarioJpaController controlador = new UsuarioJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    aux = controlador.findUsuario(username, contrasena);
    if (aux != null) {
      return aux;
    }
    return null;
  }

  @Override
  public void cerrarSesion(InterfazCliente cliente) throws RemoteException {
    unregisterForCallback(cliente);
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
  
  @Override
  public List<Tutoria> consultarTutorias(Tutorado tutorado) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(Persistence.createEntityManagerFactory("TutoCitasInterfazPU"));
    List<Tutoria> tutorias = controlador.findTutoriaEntitiesByTutorado(tutorado);
    return tutorias;
  }

  @Override
  public void registerForCallback(InterfazCliente cliente) throws RemoteException {
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
    }
  }

  @Override
  public void unregisterForCallback(InterfazCliente cliente) throws RemoteException {
    clientes.remove(cliente);
  }
  
  private synchronized void hacerCallback() throws RemoteException {
    
  }

}
