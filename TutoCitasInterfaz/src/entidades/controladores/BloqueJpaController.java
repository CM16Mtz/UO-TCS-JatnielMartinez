/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.controladores;

import entidades.Bloque;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.TutorHasBloque;
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
public class BloqueJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public BloqueJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Bloque bloque) {
    if (bloque.getTutorHasBloqueList() == null) {
      bloque.setTutorHasBloqueList(new ArrayList<TutorHasBloque>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      List<TutorHasBloque> attachedTutorHasBloqueList = new ArrayList<TutorHasBloque>();
      for (TutorHasBloque tutorHasBloqueListTutorHasBloqueToAttach : bloque.getTutorHasBloqueList()) {
        tutorHasBloqueListTutorHasBloqueToAttach = em.getReference(tutorHasBloqueListTutorHasBloqueToAttach.getClass(), tutorHasBloqueListTutorHasBloqueToAttach.getId());
        attachedTutorHasBloqueList.add(tutorHasBloqueListTutorHasBloqueToAttach);
      }
      bloque.setTutorHasBloqueList(attachedTutorHasBloqueList);
      em.persist(bloque);
      for (TutorHasBloque tutorHasBloqueListTutorHasBloque : bloque.getTutorHasBloqueList()) {
        Bloque oldBloqueidBloqueOfTutorHasBloqueListTutorHasBloque = tutorHasBloqueListTutorHasBloque.getBloqueidBloque();
        tutorHasBloqueListTutorHasBloque.setBloqueidBloque(bloque);
        tutorHasBloqueListTutorHasBloque = em.merge(tutorHasBloqueListTutorHasBloque);
        if (oldBloqueidBloqueOfTutorHasBloqueListTutorHasBloque != null) {
          oldBloqueidBloqueOfTutorHasBloqueListTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListTutorHasBloque);
          oldBloqueidBloqueOfTutorHasBloqueListTutorHasBloque = em.merge(oldBloqueidBloqueOfTutorHasBloqueListTutorHasBloque);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Bloque bloque) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Bloque persistentBloque = em.find(Bloque.class, bloque.getIdBloque());
      List<TutorHasBloque> tutorHasBloqueListOld = persistentBloque.getTutorHasBloqueList();
      List<TutorHasBloque> tutorHasBloqueListNew = bloque.getTutorHasBloqueList();
      List<String> illegalOrphanMessages = null;
      for (TutorHasBloque tutorHasBloqueListOldTutorHasBloque : tutorHasBloqueListOld) {
        if (!tutorHasBloqueListNew.contains(tutorHasBloqueListOldTutorHasBloque)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain TutorHasBloque " + tutorHasBloqueListOldTutorHasBloque + " since its bloqueidBloque field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      List<TutorHasBloque> attachedTutorHasBloqueListNew = new ArrayList<TutorHasBloque>();
      for (TutorHasBloque tutorHasBloqueListNewTutorHasBloqueToAttach : tutorHasBloqueListNew) {
        tutorHasBloqueListNewTutorHasBloqueToAttach = em.getReference(tutorHasBloqueListNewTutorHasBloqueToAttach.getClass(), tutorHasBloqueListNewTutorHasBloqueToAttach.getId());
        attachedTutorHasBloqueListNew.add(tutorHasBloqueListNewTutorHasBloqueToAttach);
      }
      tutorHasBloqueListNew = attachedTutorHasBloqueListNew;
      bloque.setTutorHasBloqueList(tutorHasBloqueListNew);
      bloque = em.merge(bloque);
      for (TutorHasBloque tutorHasBloqueListNewTutorHasBloque : tutorHasBloqueListNew) {
        if (!tutorHasBloqueListOld.contains(tutorHasBloqueListNewTutorHasBloque)) {
          Bloque oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque = tutorHasBloqueListNewTutorHasBloque.getBloqueidBloque();
          tutorHasBloqueListNewTutorHasBloque.setBloqueidBloque(bloque);
          tutorHasBloqueListNewTutorHasBloque = em.merge(tutorHasBloqueListNewTutorHasBloque);
          if (oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque != null && !oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque.equals(bloque)) {
            oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListNewTutorHasBloque);
            oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque = em.merge(oldBloqueidBloqueOfTutorHasBloqueListNewTutorHasBloque);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = bloque.getIdBloque();
        if (findBloque(id) == null) {
          throw new NonexistentEntityException("The bloque with id " + id + " no longer exists.");
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
      Bloque bloque;
      try {
        bloque = em.getReference(Bloque.class, id);
        bloque.getIdBloque();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The bloque with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<TutorHasBloque> tutorHasBloqueListOrphanCheck = bloque.getTutorHasBloqueList();
      for (TutorHasBloque tutorHasBloqueListOrphanCheckTutorHasBloque : tutorHasBloqueListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Bloque (" + bloque + ") cannot be destroyed since the TutorHasBloque " + tutorHasBloqueListOrphanCheckTutorHasBloque + " in its tutorHasBloqueList field has a non-nullable bloqueidBloque field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(bloque);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Bloque> findBloqueEntities() {
    return findBloqueEntities(true, -1, -1);
  }

  public List<Bloque> findBloqueEntities(int maxResults, int firstResult) {
    return findBloqueEntities(false, maxResults, firstResult);
  }

  private List<Bloque> findBloqueEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Bloque.class));
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

  public Bloque findBloque(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Bloque.class, id);
    } finally {
      em.close();
    }
  }

  public int getBloqueCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Bloque> rt = cq.from(Bloque.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
