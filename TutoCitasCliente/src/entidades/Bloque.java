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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "bloque")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bloque.findAll", query = "SELECT b FROM Bloque b")
  , @NamedQuery(name = "Bloque.findByIdBloque", query = "SELECT b FROM Bloque b WHERE b.idBloque = :idBloque")
  , @NamedQuery(name = "Bloque.findByHoraInicio", query = "SELECT b FROM Bloque b WHERE b.horaInicio = :horaInicio")
  , @NamedQuery(name = "Bloque.findByHoraFin", query = "SELECT b FROM Bloque b WHERE b.horaFin = :horaFin")
  , @NamedQuery(name = "Bloque.findByDia", query = "SELECT b FROM Bloque b WHERE b.dia = :dia")})
public class Bloque implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idBloque")
  private Integer idBloque;
  @Basic(optional = false)
  @Column(name = "horaInicio")
  private String horaInicio;
  @Basic(optional = false)
  @Column(name = "horaFin")
  private String horaFin;
  @Basic(optional = false)
  @Column(name = "dia")
  private String dia;

  public Bloque() {
  }

  public Bloque(Integer idBloque) {
    this.idBloque = idBloque;
  }

  public Bloque(Integer idBloque, String horaInicio, String horaFin, String dia) {
    this.idBloque = idBloque;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.dia = dia;
  }

  public Integer getIdBloque() {
    return idBloque;
  }

  public void setIdBloque(Integer idBloque) {
    this.idBloque = idBloque;
  }

  public String getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(String horaInicio) {
    this.horaInicio = horaInicio;
  }

  public String getHoraFin() {
    return horaFin;
  }

  public void setHoraFin(String horaFin) {
    this.horaFin = horaFin;
  }

  public String getDia() {
    return dia;
  }

  public void setDia(String dia) {
    this.dia = dia;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idBloque != null ? idBloque.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Bloque)) {
      return false;
    }
    Bloque other = (Bloque) object;
    if ((this.idBloque == null && other.idBloque != null) || (this.idBloque != null && !this.idBloque.equals(other.idBloque))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entidades.Bloque[ idBloque=" + idBloque + " ]";
  }
  
}
