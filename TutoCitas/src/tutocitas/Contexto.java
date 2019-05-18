package tutocitas;

import entidades.Administrador;
import entidades.Tutor;
import entidades.Tutorado;

/**
 * Clase auxiliar que establece la instancia de un administrador, tutor o tutorado.
 * @author JatnielMartínez
 */
public class Contexto {
  
  private static final Contexto INSTANCIA = new Contexto();
  
  private Administrador administrador;
  private Integer numActor;    //1 = administrador, 2 = tutor, 3 = tutorado
  private Tutor tutor;
  private Tutorado tutorado;
  
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
  
  public Integer getNumActor() {
    return numActor;
  }
  
  public void setNumActor(Integer numActor) {
    this.numActor = numActor;
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

}
