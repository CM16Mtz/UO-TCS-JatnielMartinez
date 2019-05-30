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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Establece los métodos para manipular la base de datos.
 * @author JatnielMartínez
 */
public class Servidor extends UnicastRemoteObject implements InterfazServidor {
  
  private static final int PUERTO = 5678;
  private final transient List<InterfazCliente> clientes;
  //Contiene el nombre de la persistencia
   //Guarda los clientes conectados
  private final transient EntityManagerFactory emf = Persistence.createEntityManagerFactory("TutoCitasInterfazPU");
  private static final long serialVersionUID = 1;
  
  public Servidor() throws RemoteException {
    super();
    clientes = new ArrayList<>();
  }
  
  public static void main(String[] args) throws RemoteException {
    (new Servidor()).iniciarServidor();
  }
  
  /**
   * Establece la dirección a conectarse y crea el registro.
   */
  public void iniciarServidor() {
    try {
      String direccion = (InetAddress.getLocalHost()).toString();
      String mensaje = "Iniciando servidor en " + direccion + ":" + PUERTO;
      Logger.getLogger(Servidor.class.getName())
          .log(Level.INFO, mensaje);
      Registry registro = LocateRegistry.createRegistry(PUERTO);
      registro.bind("TutoCitas", (InterfazServidor) this);
      Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Servidor iniciado");
    } catch (UnknownHostException | RemoteException | AlreadyBoundException ex) {
      Logger.getLogger(Servidor.class.getName())
          .log(Level.SEVERE, "Se produjo un error al ejecutar el servidor", ex);
    }
  }
  
  //Por cada método se pasa el EntityManagerFactory para conectarse a la base de datos
  
  /**
   * Registra un tutorado en la base de datos.
   * @param usuario El usuario que contiene el username y la contraseña
   * @param tutorado El usuario que contiene la matrícula
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void registrarTutorado(Usuario usuario, Tutorado tutorado) throws RemoteException {
    UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
    controladorUsuario.create(usuario);
    tutorado.setUsuarioidUsuario(usuario);
    TutoradoJpaController controladorTutorado = new TutoradoJpaController(emf);
    controladorTutorado.create(tutorado);
    emf.close();
  }

  /**
   * Registra un tutor en la base de datos.
   * @param usuario El usuario que contiene el username y la contraseña
   * @param tutor El usuario que contiene el número de personal
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void registrarTutor(Usuario usuario, Tutor tutor) throws RemoteException {
    UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
    controladorUsuario.create(usuario);
    tutor.setUsuarioidUsuario(usuario);
    TutorJpaController controladorTutor = new TutorJpaController(emf);
    controladorTutor.create(tutor);
    emf.close();
  }

  /**
   * Registra los horarios del tutor.
   * @param horarios Los horarios que tendrá disponible el tutor para atender citas
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void registrarHorarios(TutorHasBloque horarios) throws RemoteException {
    TutorHasBloqueJpaController controlador = new TutorHasBloqueJpaController(emf);
    controlador.create(horarios);
    emf.close();
  }

  /**
   * Guarda una cita por parte del tutorado.
   * @param tutoria La cita que reserva el tutorado
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void reservarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(emf);
    controlador.create(tutoria);
    emf.close();
  }
  
  /**
   * El tutor confirma la cita, en el mismo día o en uno diferente al que solicitó el tutorado.
   * @param tutoria La tutoria que editará el tutor
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void confirmarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(emf);
    try {
      controlador.edit(tutoria);
    } catch (Exception ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de cancelación");
      error.setHeaderText(null);
      error.setContentText("Se produjo un error al confirmar la cita");
      error.showAndWait();
    } finally {
      emf.close();
    }
  }

  @Override
  public void reportarProximasTutorias() throws RemoteException {
    Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Método aún no utilizado");
  }

  /**
   * Se cancela una cita registrada en la base de datos.
   * @param tutoria La tutoria que se desea cancelar.
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void cancelarCita(Tutoria tutoria) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(emf);
    try {
      controlador.edit(tutoria);
    } catch (Exception ex) {
      Alert error = new Alert(AlertType.ERROR);
      error.setTitle("Error de cancelación");
      error.setHeaderText(null);
      error.setContentText("La cita que usted quiere cancelar no se encuentra en nuestra base de datos");
      error.showAndWait();
    } finally {
      emf.close();
    }
  }

  /**
   * El tutor genera un reporte.
   * @param reporte El reporte de la tutoría
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void generarReporte(Reporte reporte) throws RemoteException {
    ReporteJpaController controlador = new ReporteJpaController(emf);
    controlador.create(reporte);
    emf.close();
  }

  /**
   * El sistema valida las credenciales ingresadas para darle acceso al usuario.
   * @param cliente Instancia de InterfazCliente registrada
   * @param username Nombre de usuario ingresado
   * @param contrasena Contraseña ingresada
   * @return El tipo de usuario que contiene las credenciales ingresadas
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public Usuario iniciarSesion(InterfazCliente cliente, String username, String contrasena) throws RemoteException {
    UsuarioJpaController controlador = new UsuarioJpaController(emf);
    Usuario aux = controlador.findUsuario(username, contrasena);
    if (aux != null) {
      registerForCallback(cliente);
      return aux;
    }
    return null;
  }

  /**
   * Se cierra la sesión en el sistema.
   * @param cliente Instancia de InterfazCliente que finaliza
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void cerrarSesion(InterfazCliente cliente) throws RemoteException {
    unregisterForCallback(cliente);
  }
  
  /**
   * Se obtienen los tutores para llenar el combo box.
   * @return Una lista con los tutores guardados en la base de datos
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public List<Tutor> consultarTutores() throws RemoteException {
    TutorJpaController controlador = new TutorJpaController(emf);
    return controlador.findTutorEntities();
  }

  /**
   * Se obtienen las tutorías relacionadas con el tutor.
   * @param tutor El tutor que está haciendo uso del sistema
   * @return Una lista con las tutorías que tiene asignadas
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public List<Tutoria> consultarTutoriasByTutor(Tutor tutor) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(emf);
    return controlador.findTutoriaEntitiesByTutor(tutor);
  }
  
  /**
   * Se obtienen las tutorías que tiene reservadas el tutorado.
   * @param tutorado El tutorado que está haciendo uso del sistema
   * @return Una lista con las tutorías que ha reservado
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public List<Tutoria> consultarTutoriasByTutorado(Tutorado tutorado) throws RemoteException {
    TutoriaJpaController controlador = new TutoriaJpaController(emf);
    return controlador.findTutoriaEntitiesByTutorado(tutorado);
  }

  /**
   * Registra al cliente conectado.
   * @param cliente Instancia de InterfazCliente que se conecta a este servidor
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void registerForCallback(InterfazCliente cliente) throws RemoteException {
    if (!clientes.contains(cliente)) {
      clientes.add(cliente);
    }
  }

  /**
   * Elimina al cliente conectado.
   * @param cliente Instancia de InterfazCliente que se desconecta de este servidor
   * @throws RemoteException Si se produce un error remoto
   */
  @Override
  public void unregisterForCallback(InterfazCliente cliente) throws RemoteException {
    clientes.remove(cliente);
  }

}
