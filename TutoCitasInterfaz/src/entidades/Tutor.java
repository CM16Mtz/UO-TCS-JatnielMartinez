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
@Table(name = "tutor")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
  , @NamedQuery(name = "Tutor.findByIdTutor", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor")
  , @NamedQuery(name = "Tutor.findByNoPersonal", query = "SELECT t FROM Tutor t WHERE t.noPersonal = :noPersonal")})
public class Tutor implements Serializable {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoridTutor")
  private List<TutorHasBloque> tutorHasBloqueList;

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idTutor")
  private Integer idTutor;
  @Column(name = "noPersonal")
  private String noPersonal;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoridTutor")
  private List<Tutorado> tutoradoList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoridTutor")
  private List<Tutoria> tutoriaList;
  @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario usuarioidUsuario;

  public Tutor() {
    
  }
  
  public Tutor(String noPersonal, Usuario usuario) {
    this.noPersonal = noPersonal;
    this.usuarioidUsuario = usuario;
  }

  public Tutor(Integer idTutor) {
    this.idTutor = idTutor;
  }

  public Integer getIdTutor() {
    return idTutor;
  }

  public void setIdTutor(Integer idTutor) {
    this.idTutor = idTutor;
  }

  public String getNoPersonal() {
    return noPersonal;
  }

  public void setNoPersonal(String noPersonal) {
    this.noPersonal = noPersonal;
  }

  @XmlTransient
  public List<Tutorado> getTutoradoList() {
    return tutoradoList;
  }

  public void setTutoradoList(List<Tutorado> tutoradoList) {
    this.tutoradoList = tutoradoList;
  }

  @XmlTransient
  public List<Tutoria> getTutoriaList() {
    return tutoriaList;
  }

  public void setTutoriaList(List<Tutoria> tutoriaList) {
    this.tutoriaList = tutoriaList;
  }

  public Usuario getUsuarioidUsuario() {
    return usuarioidUsuario;
  }

  public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
    this.usuarioidUsuario = usuarioidUsuario;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idTutor != null ? idTutor.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tutor)) {
      return false;
    }
    Tutor other = (Tutor) object;
    if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    //return "entidades.Tutor[ idTutor=" + idTutor + " ]";
    return noPersonal + " - " + usuarioidUsuario.getNombre() + " " + usuarioidUsuario.getApPaterno()
        + " " + usuarioidUsuario.getApMaterno();
  }

  @XmlTransient
  public List<TutorHasBloque> getTutorHasBloqueList() {
    return tutorHasBloqueList;
  }

  public void setTutorHasBloqueList(List<TutorHasBloque> tutorHasBloqueList) {
    this.tutorHasBloqueList = tutorHasBloqueList;
  }
  
}
