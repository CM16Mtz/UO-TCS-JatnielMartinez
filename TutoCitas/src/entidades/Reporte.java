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
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r")
  , @NamedQuery(name = "Reporte.findByIdReporte", query = "SELECT r FROM Reporte r WHERE r.idReporte = :idReporte")
  , @NamedQuery(name = "Reporte.findByNumTutoria", query = "SELECT r FROM Reporte r WHERE r.numTutoria = :numTutoria")
  , @NamedQuery(name = "Reporte.findByDuracion", query = "SELECT r FROM Reporte r WHERE r.duracion = :duracion")
  , @NamedQuery(name = "Reporte.findByAtendida", query = "SELECT r FROM Reporte r WHERE r.atendida = :atendida")
  , @NamedQuery(name = "Reporte.findByCausa", query = "SELECT r FROM Reporte r WHERE r.causa = :causa")})
public class Reporte implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idReporte")
  private Integer idReporte;
  @Basic(optional = false)
  @Column(name = "numTutoria")
  private int numTutoria;
  @Basic(optional = false)
  @Column(name = "duracion")
  private int duracion;
  @Basic(optional = false)
  @Column(name = "atendida")
  private boolean atendida;
  @Column(name = "causa")
  private String causa;
  @JoinColumn(name = "Tutoria_idTutoria", referencedColumnName = "idTutoria")
  @ManyToOne(optional = false)
  private Tutoria tutoriaidTutoria;

  public Reporte() {
  }

  public Reporte(Integer idReporte) {
    this.idReporte = idReporte;
  }

  public Reporte(Integer idReporte, int numTutoria, int duracion, boolean atendida) {
    this.idReporte = idReporte;
    this.numTutoria = numTutoria;
    this.duracion = duracion;
    this.atendida = atendida;
  }

  public Integer getIdReporte() {
    return idReporte;
  }

  public void setIdReporte(Integer idReporte) {
    this.idReporte = idReporte;
  }

  public int getNumTutoria() {
    return numTutoria;
  }

  public void setNumTutoria(int numTutoria) {
    this.numTutoria = numTutoria;
  }

  public int getDuracion() {
    return duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public boolean getAtendida() {
    return atendida;
  }

  public void setAtendida(boolean atendida) {
    this.atendida = atendida;
  }

  public String getCausa() {
    return causa;
  }

  public void setCausa(String causa) {
    this.causa = causa;
  }

  public Tutoria getTutoriaidTutoria() {
    return tutoriaidTutoria;
  }

  public void setTutoriaidTutoria(Tutoria tutoriaidTutoria) {
    this.tutoriaidTutoria = tutoriaidTutoria;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idReporte != null ? idReporte.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Reporte)) {
      return false;
    }
    Reporte other = (Reporte) object;
    if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entidades.Reporte[ idReporte=" + idReporte + " ]";
  }
  
}
