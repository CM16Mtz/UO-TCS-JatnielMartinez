/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tutor_has_bloque")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TutorHasBloque.findAll", query = "SELECT t FROM TutorHasBloque t")
  , @NamedQuery(name = "TutorHasBloque.findById", query = "SELECT t FROM TutorHasBloque t WHERE t.id = :id")})
public class TutorHasBloque implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @JoinColumn(name = "Bloque_idBloque", referencedColumnName = "idBloque")
  @ManyToOne(optional = false)
  private Bloque bloqueidBloque;
  @JoinColumn(name = "Periodo_idPeriodo", referencedColumnName = "idPeriodo")
  @ManyToOne(optional = false)
  private Periodo periodoidPeriodo;
  @JoinColumn(name = "Tutor_idTutor", referencedColumnName = "idTutor")
  @ManyToOne(optional = false)
  private Tutor tutoridTutor;

  public TutorHasBloque() {
  }

  public TutorHasBloque(Integer id) {
    this.id = id;
  }
  
  public TutorHasBloque(Bloque bloque, Periodo periodo, Tutor tutor) {
    this.bloqueidBloque = bloque;
    this.periodoidPeriodo = periodo;
    this.tutoridTutor = tutor;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Bloque getBloqueidBloque() {
    return bloqueidBloque;
  }

  public void setBloqueidBloque(Bloque bloqueidBloque) {
    this.bloqueidBloque = bloqueidBloque;
  }

  public Periodo getPeriodoidPeriodo() {
    return periodoidPeriodo;
  }

  public void setPeriodoidPeriodo(Periodo periodoidPeriodo) {
    this.periodoidPeriodo = periodoidPeriodo;
  }

  public Tutor getTutoridTutor() {
    return tutoridTutor;
  }

  public void setTutoridTutor(Tutor tutoridTutor) {
    this.tutoridTutor = tutoridTutor;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof TutorHasBloque)) {
      return false;
    }
    TutorHasBloque other = (TutorHasBloque) object;
    return
        (this.id == null && other.id != null) ||
        (this.id != null && !this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "entidades.TutorHasBloque[ id=" + id + " ]";
  }
  
}
