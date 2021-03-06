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
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
  , @NamedQuery(name = "Administrador.findByIdAdministrador", query = "SELECT a FROM Administrador a WHERE a.idAdministrador = :idAdministrador")})
public class Administrador implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idAdministrador")
  private Integer idAdministrador;
  @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario usuarioidUsuario;

  public Administrador() {
  }

  public Administrador(Integer idAdministrador) {
    this.idAdministrador = idAdministrador;
  }

  public Integer getIdAdministrador() {
    return idAdministrador;
  }

  public void setIdAdministrador(Integer idAdministrador) {
    this.idAdministrador = idAdministrador;
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
    hash += (idAdministrador != null ? idAdministrador.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Administrador)) {
      return false;
    }
    Administrador other = (Administrador) object;
    return
        (this.idAdministrador == null && other.idAdministrador != null) ||
        (this.idAdministrador != null && !this.idAdministrador.equals(other.idAdministrador));
  }

  @Override
  public String toString() {
    return "entidades.Administrador[ idAdministrador=" + idAdministrador + " ]";
  }
  
}
