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
import entidades.Usuario;
import entidades.Tutor;
import entidades.Tutorado;
import entidades.Tutoria;
import entidades.controladores.exceptions.IllegalOrphanException;
import entidades.controladores.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class TutoradoJpaController implements Serializable {

  public TutoradoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Tutorado tutorado) {
    if (tutorado.getTutoriaList() == null) {
      tutorado.setTutoriaList(new ArrayList<Tutoria>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario usuarioidUsuario = tutorado.getUsuarioidUsuario();
      if (usuarioidUsuario != null) {
        usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
        tutorado.setUsuarioidUsuario(usuarioidUsuario);
      }
      Tutor tutoridTutor = tutorado.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor = em.getReference(tutoridTutor.getClass(), tutoridTutor.getIdTutor());
        tutorado.setTutoridTutor(tutoridTutor);
      }
      List<Tutoria> attachedTutoriaList = new ArrayList<Tutoria>();
      for (Tutoria tutoriaListTutoriaToAttach : tutorado.getTutoriaList()) {
        tutoriaListTutoriaToAttach = em.getReference(tutoriaListTutoriaToAttach.getClass(), tutoriaListTutoriaToAttach.getIdTutoria());
        attachedTutoriaList.add(tutoriaListTutoriaToAttach);
      }
      tutorado.setTutoriaList(attachedTutoriaList);
      em.persist(tutorado);
      if (usuarioidUsuario != null) {
        usuarioidUsuario.getTutoradoList().add(tutorado);
        usuarioidUsuario = em.merge(usuarioidUsuario);
      }
      if (tutoridTutor != null) {
        tutoridTutor.getTutoradoList().add(tutorado);
        tutoridTutor = em.merge(tutoridTutor);
      }
      for (Tutoria tutoriaListTutoria : tutorado.getTutoriaList()) {
        Tutorado oldTutoradoidTutoradoOfTutoriaListTutoria = tutoriaListTutoria.getTutoradoidTutorado();
        tutoriaListTutoria.setTutoradoidTutorado(tutorado);
        tutoriaListTutoria = em.merge(tutoriaListTutoria);
        if (oldTutoradoidTutoradoOfTutoriaListTutoria != null) {
          oldTutoradoidTutoradoOfTutoriaListTutoria.getTutoriaList().remove(tutoriaListTutoria);
          oldTutoradoidTutoradoOfTutoriaListTutoria = em.merge(oldTutoradoidTutoradoOfTutoriaListTutoria);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Tutorado tutorado) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Tutorado persistentTutorado = em.find(Tutorado.class, tutorado.getIdTutorado());
      Usuario usuarioidUsuarioOld = persistentTutorado.getUsuarioidUsuario();
      Usuario usuarioidUsuarioNew = tutorado.getUsuarioidUsuario();
      Tutor tutoridTutorOld = persistentTutorado.getTutoridTutor();
      Tutor tutoridTutorNew = tutorado.getTutoridTutor();
      List<Tutoria> tutoriaListOld = persistentTutorado.getTutoriaList();
      List<Tutoria> tutoriaListNew = tutorado.getTutoriaList();
      List<String> illegalOrphanMessages = null;
      for (Tutoria tutoriaListOldTutoria : tutoriaListOld) {
        if (!tutoriaListNew.contains(tutoriaListOldTutoria)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Tutoria " + tutoriaListOldTutoria + " since its tutoradoidTutorado field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (usuarioidUsuarioNew != null) {
        usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
        tutorado.setUsuarioidUsuario(usuarioidUsuarioNew);
      }
      if (tutoridTutorNew != null) {
        tutoridTutorNew = em.getReference(tutoridTutorNew.getClass(), tutoridTutorNew.getIdTutor());
        tutorado.setTutoridTutor(tutoridTutorNew);
      }
      List<Tutoria> attachedTutoriaListNew = new ArrayList<Tutoria>();
      for (Tutoria tutoriaListNewTutoriaToAttach : tutoriaListNew) {
        tutoriaListNewTutoriaToAttach = em.getReference(tutoriaListNewTutoriaToAttach.getClass(), tutoriaListNewTutoriaToAttach.getIdTutoria());
        attachedTutoriaListNew.add(tutoriaListNewTutoriaToAttach);
      }
      tutoriaListNew = attachedTutoriaListNew;
      tutorado.setTutoriaList(tutoriaListNew);
      tutorado = em.merge(tutorado);
      if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
        usuarioidUsuarioOld.getTutoradoList().remove(tutorado);
        usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
      }
      if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
        usuarioidUsuarioNew.getTutoradoList().add(tutorado);
        usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
      }
      if (tutoridTutorOld != null && !tutoridTutorOld.equals(tutoridTutorNew)) {
        tutoridTutorOld.getTutoradoList().remove(tutorado);
        tutoridTutorOld = em.merge(tutoridTutorOld);
      }
      if (tutoridTutorNew != null && !tutoridTutorNew.equals(tutoridTutorOld)) {
        tutoridTutorNew.getTutoradoList().add(tutorado);
        tutoridTutorNew = em.merge(tutoridTutorNew);
      }
      for (Tutoria tutoriaListNewTutoria : tutoriaListNew) {
        if (!tutoriaListOld.contains(tutoriaListNewTutoria)) {
          Tutorado oldTutoradoidTutoradoOfTutoriaListNewTutoria = tutoriaListNewTutoria.getTutoradoidTutorado();
          tutoriaListNewTutoria.setTutoradoidTutorado(tutorado);
          tutoriaListNewTutoria = em.merge(tutoriaListNewTutoria);
          if (oldTutoradoidTutoradoOfTutoriaListNewTutoria != null && !oldTutoradoidTutoradoOfTutoriaListNewTutoria.equals(tutorado)) {
            oldTutoradoidTutoradoOfTutoriaListNewTutoria.getTutoriaList().remove(tutoriaListNewTutoria);
            oldTutoradoidTutoradoOfTutoriaListNewTutoria = em.merge(oldTutoradoidTutoradoOfTutoriaListNewTutoria);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = tutorado.getIdTutorado();
        if (findTutorado(id) == null) {
          throw new NonexistentEntityException("The tutorado with id " + id + " no longer exists.");
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
      Tutorado tutorado;
      try {
        tutorado = em.getReference(Tutorado.class, id);
        tutorado.getIdTutorado();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tutorado with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<Tutoria> tutoriaListOrphanCheck = tutorado.getTutoriaList();
      for (Tutoria tutoriaListOrphanCheckTutoria : tutoriaListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Tutorado (" + tutorado + ") cannot be destroyed since the Tutoria " + tutoriaListOrphanCheckTutoria + " in its tutoriaList field has a non-nullable tutoradoidTutorado field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Usuario usuarioidUsuario = tutorado.getUsuarioidUsuario();
      if (usuarioidUsuario != null) {
        usuarioidUsuario.getTutoradoList().remove(tutorado);
        usuarioidUsuario = em.merge(usuarioidUsuario);
      }
      Tutor tutoridTutor = tutorado.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor.getTutoradoList().remove(tutorado);
        tutoridTutor = em.merge(tutoridTutor);
      }
      em.remove(tutorado);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Tutorado> findTutoradoEntities() {
    return findTutoradoEntities(true, -1, -1);
  }

  public List<Tutorado> findTutoradoEntities(int maxResults, int firstResult) {
    return findTutoradoEntities(false, maxResults, firstResult);
  }

  private List<Tutorado> findTutoradoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Tutorado.class));
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

  public Tutorado findTutorado(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Tutorado.class, id);
    } finally {
      em.close();
    }
  }

  public int getTutoradoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Tutorado> rt = cq.from(Tutorado.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
