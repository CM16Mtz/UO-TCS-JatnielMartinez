/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tutoria")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tutoria.findAll", query = "SELECT t FROM Tutoria t")
  , @NamedQuery(name = "Tutoria.findByIdTutoria", query = "SELECT t FROM Tutoria t WHERE t.idTutoria = :idTutoria")
  , @NamedQuery(name = "Tutoria.findByFecha", query = "SELECT t FROM Tutoria t WHERE t.fecha = :fecha")
  , @NamedQuery(name = "Tutoria.findByHora", query = "SELECT t FROM Tutoria t WHERE t.hora = :hora")
  , @NamedQuery(name = "Tutoria.findByCancelada", query = "SELECT t FROM Tutoria t WHERE t.cancelada = :cancelada")
  , @NamedQuery(name = "Tutoria.findByCausa", query = "SELECT t FROM Tutoria t WHERE t.causa = :causa")})
public class Tutoria implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idTutoria")
  private Integer idTutoria;
  @Basic(optional = false)
  @Column(name = "fecha")
  @Temporal(TemporalType.DATE)
  private Date fecha;
  @Basic(optional = false)
  @Column(name = "hora")
  @Temporal(TemporalType.TIMESTAMP)
  private Date hora;
  @Basic(optional = false)
  @Column(name = "cancelada")
  private boolean cancelada;
  @Column(name = "causa")
  private String causa;
  @JoinColumn(name = "Tutorado_idTutorado", referencedColumnName = "idTutorado")
  @ManyToOne(optional = false)
  private Tutorado tutoradoidTutorado;
  @JoinColumn(name = "tutor_idTutor", referencedColumnName = "idTutor")
  @ManyToOne(optional = false)
  private Tutor tutoridTutor;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoriaidTutoria")
  private List<Reporte> reporteList;

  public Tutoria() {
  }

  public Tutoria(Integer idTutoria) {
    this.idTutoria = idTutoria;
  }

  public Tutoria(Integer idTutoria, Date fecha, Date hora, boolean cancelada) {
    this.idTutoria = idTutoria;
    this.fecha = fecha;
    this.hora = hora;
    this.cancelada = cancelada;
  }

  public Integer getIdTutoria() {
    return idTutoria;
  }

  public void setIdTutoria(Integer idTutoria) {
    this.idTutoria = idTutoria;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getHora() {
    return hora;
  }

  public void setHora(Date hora) {
    this.hora = hora;
  }

  public boolean getCancelada() {
    return cancelada;
  }

  public void setCancelada(boolean cancelada) {
    this.cancelada = cancelada;
  }

  public String getCausa() {
    return causa;
  }

  public void setCausa(String causa) {
    this.causa = causa;
  }

  public Tutorado getTutoradoidTutorado() {
    return tutoradoidTutorado;
  }

  public void setTutoradoidTutorado(Tutorado tutoradoidTutorado) {
    this.tutoradoidTutorado = tutoradoidTutorado;
  }

  public Tutor getTutoridTutor() {
    return tutoridTutor;
  }

  public void setTutoridTutor(Tutor tutoridTutor) {
    this.tutoridTutor = tutoridTutor;
  }

  @XmlTransient
  public List<Reporte> getReporteList() {
    return reporteList;
  }

  public void setReporteList(List<Reporte> reporteList) {
    this.reporteList = reporteList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idTutoria != null ? idTutoria.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tutoria)) {
      return false;
    }
    Tutoria other = (Tutoria) object;
    if ((this.idTutoria == null && other.idTutoria != null) || (this.idTutoria != null && !this.idTutoria.equals(other.idTutoria))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entidades.Tutoria[ idTutoria=" + idTutoria + " ]";
  }
  
}
