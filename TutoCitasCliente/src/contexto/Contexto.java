package contexto;

import cliente.Cliente;
import entidades.Administrador;
import entidades.Tutor;
import entidades.Tutorado;
import entidades.Tutoria;
import interfaces.InterfazServidor;

/**
 * Clase auxiliar que establece la instancia de un administrador, tutor o tutorado.
 * @author JatnielMartínez
 */
public class Contexto {
  
  private static final Contexto INSTANCIA = new Contexto();
  
  private Administrador administrador;
  private Cliente cliente;
  private Integer numActor;    //1 = administrador, 2 = tutor, 3 = tutorado
  private InterfazServidor servidor;
  private Tutor tutor;
  private Tutorado tutorado;
  private Tutoria tutoria;
  
  public Contexto() {
    
  }
  
  /**
   * 
   * @return La instancia creada
   */
  public static Contexto getInstancia() {
    return INSTANCIA;
  }

  public Administrador getAdministrador() {
    return administrador;
  }

  public void setAdministrador(Administrador administrador) {
    this.administrador = administrador;
  }
  
  public Cliente getCliente() {
    return cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public Integer getNumActor() {
    return numActor;
  }
  
  public void setNumActor(Integer numActor) {
    this.numActor = numActor;
  }
  
  public InterfazServidor getServidor() {
    return servidor;
  }
  
  public void setServidor(InterfazServidor servidor) {
    this.servidor = servidor;
  }

  public Tutor getTutor() {
    return tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  public Tutorado getTutorado() {
    return tutorado;
  }

  public void setTutorado(Tutorado tutorado) {
    this.tutorado = tutorado;
  }
  
  public Tutoria getTutoria() {
    return tutoria;
  }
  
  public void setTutoria(Tutoria tutoria) {
    this.tutoria = tutoria;
  }

}
