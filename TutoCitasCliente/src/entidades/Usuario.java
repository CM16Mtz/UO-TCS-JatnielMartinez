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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
  , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
  , @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username")
  , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
  , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
  , @NamedQuery(name = "Usuario.findByApPaterno", query = "SELECT u FROM Usuario u WHERE u.apPaterno = :apPaterno")
  , @NamedQuery(name = "Usuario.findByApMaterno", query = "SELECT u FROM Usuario u WHERE u.apMaterno = :apMaterno")
  , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")})
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idUsuario")
  private Integer idUsuario;
  @Basic(optional = false)
  @Column(name = "username")
  private String username;
  @Basic(optional = false)
  @Column(name = "contrasena")
  private String contrasena;
  @Basic(optional = false)
  @Column(name = "tipoUsuario")
  private String tipoUsuario;
  @Basic(optional = false)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @Column(name = "apPaterno")
  private String apPaterno;
  @Column(name = "apMaterno")
  private String apMaterno;
  @Basic(optional = false)
  @Column(name = "correo")
  private String correo;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
  private List<Administrador> administradorList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
  private List<Tutorado> tutoradoList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
  private List<Tutor> tutorList;

  public Usuario() {
  }

  public Usuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Usuario(Integer idUsuario, String username, String contrasena, String tipoUsuario, String nombre, String apPaterno, String correo) {
    this.idUsuario = idUsuario;
    this.username = username;
    this.contrasena = contrasena;
    this.tipoUsuario = tipoUsuario;
    this.nombre = nombre;
    this.apPaterno = apPaterno;
    this.correo = correo;
  }

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }
  
  public String getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApPaterno() {
    return apPaterno;
  }

  public void setApPaterno(String apPaterno) {
    this.apPaterno = apPaterno;
  }

  public String getApMaterno() {
    return apMaterno;
  }

  public void setApMaterno(String apMaterno) {
    this.apMaterno = apMaterno;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  @XmlTransient
  public List<Administrador> getAdministradorList() {
    return administradorList;
  }

  public void setAdministradorList(List<Administrador> administradorList) {
    this.administradorList = administradorList;
  }

  @XmlTransient
  public List<Tutorado> getTutoradoList() {
    return tutoradoList;
  }

  public void setTutoradoList(List<Tutorado> tutoradoList) {
    this.tutoradoList = tutoradoList;
  }

  @XmlTransient
  public List<Tutor> getTutorList() {
    return tutorList;
  }

  public void setTutorList(List<Tutor> tutorList) {
    this.tutorList = tutorList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idUsuario != null ? idUsuario.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Usuario)) {
      return false;
    }
    Usuario other = (Usuario) object;
    if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entidades.Usuario[ idUsuario=" + idUsuario + " ]";
  }
  
}
