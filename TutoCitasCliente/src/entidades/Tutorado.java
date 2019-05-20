/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tutorado")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tutorado.findAll", query = "SELECT t FROM Tutorado t")
  , @NamedQuery(name = "Tutorado.findByIdTutorado", query = "SELECT t FROM Tutorado t WHERE t.idTutorado = :idTutorado")
  , @NamedQuery(name = "Tutorado.findByCarrera", query = "SELECT t FROM Tutorado t WHERE t.carrera = :carrera")})
public class Tutorado implements Serializable {

  @Column(name = "matricula")
  private String matricula;

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idTutorado")
  private Integer idTutorado;
  @Basic(optional = false)
  @Column(name = "carrera")
  private String carrera;
  @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario usuarioidUsuario;
  @JoinColumn(name = "tutor_idTutor", referencedColumnName = "idTutor")
  @ManyToOne(optional = false)
  private Tutor tutoridTutor;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoradoidTutorado")
  private List<Tutoria> tutoriaList;

  public Tutorado() {
  }

  public Tutorado(Integer idTutorado) {
    this.idTutorado = idTutorado;
  }

  public Tutorado(Integer idTutorado, String carrera) {
    this.idTutorado = idTutorado;
    this.carrera = carrera;
  }

  public Integer getIdTutorado() {
    return idTutorado;
  }

  public void setIdTutorado(Integer idTutorado) {
    this.idTutorado = idTutorado;
  }

  public String getCarrera() {
    return carrera;
  }

  public void setCarrera(String carrera) {
    this.carrera = carrera;
  }

  public Usuario getUsuarioidUsuario() {
    return usuarioidUsuario;
  }

  public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
    this.usuarioidUsuario = usuarioidUsuario;
  }

  public Tutor getTutoridTutor() {
    return tutoridTutor;
  }

  public void setTutoridTutor(Tutor tutoridTutor) {
    this.tutoridTutor = tutoridTutor;
  }

  @XmlTransient
  public List<Tutoria> getTutoriaList() {
    return tutoriaList;
  }

  public void setTutoriaList(List<Tutoria> tutoriaList) {
    this.tutoriaList = tutoriaList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idTutorado != null ? idTutorado.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tutorado)) {
      return false;
    }
    Tutorado other = (Tutorado) object;
    if ((this.idTutorado == null && other.idTutorado != null) || (this.idTutorado != null && !this.idTutorado.equals(other.idTutorado))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entidades.Tutorado[ idTutorado=" + idTutorado + " ]";
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }
  
}
