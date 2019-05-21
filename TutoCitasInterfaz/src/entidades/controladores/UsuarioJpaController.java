/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Administrador;
import java.util.ArrayList;
import java.util.List;
import entidades.Tutorado;
import entidades.Tutor;
import entidades.Usuario;
import entidades.controladores.exceptions.IllegalOrphanException;
import entidades.controladores.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class UsuarioJpaController implements Serializable {

  public UsuarioJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Usuario usuario) {
    if (usuario.getAdministradorList() == null) {
      usuario.setAdministradorList(new ArrayList<Administrador>());
    }
    if (usuario.getTutoradoList() == null) {
      usuario.setTutoradoList(new ArrayList<Tutorado>());
    }
    if (usuario.getTutorList() == null) {
      usuario.setTutorList(new ArrayList<Tutor>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      List<Administrador> attachedAdministradorList = new ArrayList<Administrador>();
      for (Administrador administradorListAdministradorToAttach : usuario.getAdministradorList()) {
        administradorListAdministradorToAttach = em.getReference(administradorListAdministradorToAttach.getClass(), administradorListAdministradorToAttach.getIdAdministrador());
        attachedAdministradorList.add(administradorListAdministradorToAttach);
      }
      usuario.setAdministradorList(attachedAdministradorList);
      List<Tutorado> attachedTutoradoList = new ArrayList<Tutorado>();
      for (Tutorado tutoradoListTutoradoToAttach : usuario.getTutoradoList()) {
        tutoradoListTutoradoToAttach = em.getReference(tutoradoListTutoradoToAttach.getClass(), tutoradoListTutoradoToAttach.getIdTutorado());
        attachedTutoradoList.add(tutoradoListTutoradoToAttach);
      }
      usuario.setTutoradoList(attachedTutoradoList);
      List<Tutor> attachedTutorList = new ArrayList<Tutor>();
      for (Tutor tutorListTutorToAttach : usuario.getTutorList()) {
        tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
        attachedTutorList.add(tutorListTutorToAttach);
      }
      usuario.setTutorList(attachedTutorList);
      em.persist(usuario);
      for (Administrador administradorListAdministrador : usuario.getAdministradorList()) {
        Usuario oldUsuarioidUsuarioOfAdministradorListAdministrador = administradorListAdministrador.getUsuarioidUsuario();
        administradorListAdministrador.setUsuarioidUsuario(usuario);
        administradorListAdministrador = em.merge(administradorListAdministrador);
        if (oldUsuarioidUsuarioOfAdministradorListAdministrador != null) {
          oldUsuarioidUsuarioOfAdministradorListAdministrador.getAdministradorList().remove(administradorListAdministrador);
          oldUsuarioidUsuarioOfAdministradorListAdministrador = em.merge(oldUsuarioidUsuarioOfAdministradorListAdministrador);
        }
      }
      for (Tutorado tutoradoListTutorado : usuario.getTutoradoList()) {
        Usuario oldUsuarioidUsuarioOfTutoradoListTutorado = tutoradoListTutorado.getUsuarioidUsuario();
        tutoradoListTutorado.setUsuarioidUsuario(usuario);
        tutoradoListTutorado = em.merge(tutoradoListTutorado);
        if (oldUsuarioidUsuarioOfTutoradoListTutorado != null) {
          oldUsuarioidUsuarioOfTutoradoListTutorado.getTutoradoList().remove(tutoradoListTutorado);
          oldUsuarioidUsuarioOfTutoradoListTutorado = em.merge(oldUsuarioidUsuarioOfTutoradoListTutorado);
        }
      }
      for (Tutor tutorListTutor : usuario.getTutorList()) {
        Usuario oldUsuarioidUsuarioOfTutorListTutor = tutorListTutor.getUsuarioidUsuario();
        tutorListTutor.setUsuarioidUsuario(usuario);
        tutorListTutor = em.merge(tutorListTutor);
        if (oldUsuarioidUsuarioOfTutorListTutor != null) {
          oldUsuarioidUsuarioOfTutorListTutor.getTutorList().remove(tutorListTutor);
          oldUsuarioidUsuarioOfTutorListTutor = em.merge(oldUsuarioidUsuarioOfTutorListTutor);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
      List<Administrador> administradorListOld = persistentUsuario.getAdministradorList();
      List<Administrador> administradorListNew = usuario.getAdministradorList();
      List<Tutorado> tutoradoListOld = persistentUsuario.getTutoradoList();
      List<Tutorado> tutoradoListNew = usuario.getTutoradoList();
      List<Tutor> tutorListOld = persistentUsuario.getTutorList();
      List<Tutor> tutorListNew = usuario.getTutorList();
      List<String> illegalOrphanMessages = null;
      for (Administrador administradorListOldAdministrador : administradorListOld) {
        if (!administradorListNew.contains(administradorListOldAdministrador)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Administrador " + administradorListOldAdministrador + " since its usuarioidUsuario field is not nullable.");
        }
      }
      for (Tutorado tutoradoListOldTutorado : tutoradoListOld) {
        if (!tutoradoListNew.contains(tutoradoListOldTutorado)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Tutorado " + tutoradoListOldTutorado + " since its usuarioidUsuario field is not nullable.");
        }
      }
      for (Tutor tutorListOldTutor : tutorListOld) {
        if (!tutorListNew.contains(tutorListOldTutor)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its usuarioidUsuario field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      List<Administrador> attachedAdministradorListNew = new ArrayList<Administrador>();
      for (Administrador administradorListNewAdministradorToAttach : administradorListNew) {
        administradorListNewAdministradorToAttach = em.getReference(administradorListNewAdministradorToAttach.getClass(), administradorListNewAdministradorToAttach.getIdAdministrador());
        attachedAdministradorListNew.add(administradorListNewAdministradorToAttach);
      }
      administradorListNew = attachedAdministradorListNew;
      usuario.setAdministradorList(administradorListNew);
      List<Tutorado> attachedTutoradoListNew = new ArrayList<Tutorado>();
      for (Tutorado tutoradoListNewTutoradoToAttach : tutoradoListNew) {
        tutoradoListNewTutoradoToAttach = em.getReference(tutoradoListNewTutoradoToAttach.getClass(), tutoradoListNewTutoradoToAttach.getIdTutorado());
        attachedTutoradoListNew.add(tutoradoListNewTutoradoToAttach);
      }
      tutoradoListNew = attachedTutoradoListNew;
      usuario.setTutoradoList(tutoradoListNew);
      List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
      for (Tutor tutorListNewTutorToAttach : tutorListNew) {
        tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
        attachedTutorListNew.add(tutorListNewTutorToAttach);
      }
      tutorListNew = attachedTutorListNew;
      usuario.setTutorList(tutorListNew);
      usuario = em.merge(usuario);
      for (Administrador administradorListNewAdministrador : administradorListNew) {
        if (!administradorListOld.contains(administradorListNewAdministrador)) {
          Usuario oldUsuarioidUsuarioOfAdministradorListNewAdministrador = administradorListNewAdministrador.getUsuarioidUsuario();
          administradorListNewAdministrador.setUsuarioidUsuario(usuario);
          administradorListNewAdministrador = em.merge(administradorListNewAdministrador);
          if (oldUsuarioidUsuarioOfAdministradorListNewAdministrador != null && !oldUsuarioidUsuarioOfAdministradorListNewAdministrador.equals(usuario)) {
            oldUsuarioidUsuarioOfAdministradorListNewAdministrador.getAdministradorList().remove(administradorListNewAdministrador);
            oldUsuarioidUsuarioOfAdministradorListNewAdministrador = em.merge(oldUsuarioidUsuarioOfAdministradorListNewAdministrador);
          }
        }
      }
      for (Tutorado tutoradoListNewTutorado : tutoradoListNew) {
        if (!tutoradoListOld.contains(tutoradoListNewTutorado)) {
          Usuario oldUsuarioidUsuarioOfTutoradoListNewTutorado = tutoradoListNewTutorado.getUsuarioidUsuario();
          tutoradoListNewTutorado.setUsuarioidUsuario(usuario);
          tutoradoListNewTutorado = em.merge(tutoradoListNewTutorado);
          if (oldUsuarioidUsuarioOfTutoradoListNewTutorado != null && !oldUsuarioidUsuarioOfTutoradoListNewTutorado.equals(usuario)) {
            oldUsuarioidUsuarioOfTutoradoListNewTutorado.getTutoradoList().remove(tutoradoListNewTutorado);
            oldUsuarioidUsuarioOfTutoradoListNewTutorado = em.merge(oldUsuarioidUsuarioOfTutoradoListNewTutorado);
          }
        }
      }
      for (Tutor tutorListNewTutor : tutorListNew) {
        if (!tutorListOld.contains(tutorListNewTutor)) {
          Usuario oldUsuarioidUsuarioOfTutorListNewTutor = tutorListNewTutor.getUsuarioidUsuario();
          tutorListNewTutor.setUsuarioidUsuario(usuario);
          tutorListNewTutor = em.merge(tutorListNewTutor);
          if (oldUsuarioidUsuarioOfTutorListNewTutor != null && !oldUsuarioidUsuarioOfTutorListNewTutor.equals(usuario)) {
            oldUsuarioidUsuarioOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
            oldUsuarioidUsuarioOfTutorListNewTutor = em.merge(oldUsuarioidUsuarioOfTutorListNewTutor);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = usuario.getIdUsuario();
        if (findUsuario(id) == null) {
          throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario usuario;
      try {
        usuario = em.getReference(Usuario.class, id);
        usuario.getIdUsuario();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<Administrador> administradorListOrphanCheck = usuario.getAdministradorList();
      for (Administrador administradorListOrphanCheckAdministrador : administradorListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Administrador " + administradorListOrphanCheckAdministrador + " in its administradorList field has a non-nullable usuarioidUsuario field.");
      }
      List<Tutorado> tutoradoListOrphanCheck = usuario.getTutoradoList();
      for (Tutorado tutoradoListOrphanCheckTutorado : tutoradoListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Tutorado " + tutoradoListOrphanCheckTutorado + " in its tutoradoList field has a non-nullable usuarioidUsuario field.");
      }
      List<Tutor> tutorListOrphanCheck = usuario.getTutorList();
      for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable usuarioidUsuario field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(usuario);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Usuario> findUsuarioEntities() {
    return findUsuarioEntities(true, -1, -1);
  }

  public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
    return findUsuarioEntities(false, maxResults, firstResult);
  }

  private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Usuario.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Usuario findUsuario(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Usuario.class, id);
    } finally {
      em.close();
    }
  }
  
  public Usuario findUsuario(String username, String contrasena) {
    EntityManager em = getEntityManager();
    Usuario usuario = (Usuario) em.createQuery(
        "SELECT c FROM Usuario c WHERE c.username = :username AND c.contrasena = :contrasena")
        .setParameter("username", username)
        .setParameter("contrasena", contrasena)
        .getSingleResult();
    em.close();
    return usuario;
  }

  public int getUsuarioCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Usuario> rt = cq.from(Usuario.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
