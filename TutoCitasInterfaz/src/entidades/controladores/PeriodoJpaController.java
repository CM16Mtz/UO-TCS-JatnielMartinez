/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.controladores;

import entidades.Periodo;
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
public class PeriodoJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public PeriodoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Periodo periodo) {
    if (periodo.getTutorHasBloqueList() == null) {
      periodo.setTutorHasBloqueList(new ArrayList<TutorHasBloque>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      List<TutorHasBloque> attachedTutorHasBloqueList = new ArrayList<TutorHasBloque>();
      for (TutorHasBloque tutorHasBloqueListTutorHasBloqueToAttach : periodo.getTutorHasBloqueList()) {
        tutorHasBloqueListTutorHasBloqueToAttach = em.getReference(tutorHasBloqueListTutorHasBloqueToAttach.getClass(), tutorHasBloqueListTutorHasBloqueToAttach.getId());
        attachedTutorHasBloqueList.add(tutorHasBloqueListTutorHasBloqueToAttach);
      }
      periodo.setTutorHasBloqueList(attachedTutorHasBloqueList);
      em.persist(periodo);
      for (TutorHasBloque tutorHasBloqueListTutorHasBloque : periodo.getTutorHasBloqueList()) {
        Periodo oldPeriodoidPeriodoOfTutorHasBloqueListTutorHasBloque = tutorHasBloqueListTutorHasBloque.getPeriodoidPeriodo();
        tutorHasBloqueListTutorHasBloque.setPeriodoidPeriodo(periodo);
        tutorHasBloqueListTutorHasBloque = em.merge(tutorHasBloqueListTutorHasBloque);
        if (oldPeriodoidPeriodoOfTutorHasBloqueListTutorHasBloque != null) {
          oldPeriodoidPeriodoOfTutorHasBloqueListTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListTutorHasBloque);
          oldPeriodoidPeriodoOfTutorHasBloqueListTutorHasBloque = em.merge(oldPeriodoidPeriodoOfTutorHasBloqueListTutorHasBloque);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Periodo periodo) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Periodo persistentPeriodo = em.find(Periodo.class, periodo.getIdPeriodo());
      List<TutorHasBloque> tutorHasBloqueListOld = persistentPeriodo.getTutorHasBloqueList();
      List<TutorHasBloque> tutorHasBloqueListNew = periodo.getTutorHasBloqueList();
      List<String> illegalOrphanMessages = null;
      for (TutorHasBloque tutorHasBloqueListOldTutorHasBloque : tutorHasBloqueListOld) {
        if (!tutorHasBloqueListNew.contains(tutorHasBloqueListOldTutorHasBloque)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain TutorHasBloque " + tutorHasBloqueListOldTutorHasBloque + " since its periodoidPeriodo field is not nullable.");
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
      periodo.setTutorHasBloqueList(tutorHasBloqueListNew);
      periodo = em.merge(periodo);
      for (TutorHasBloque tutorHasBloqueListNewTutorHasBloque : tutorHasBloqueListNew) {
        if (!tutorHasBloqueListOld.contains(tutorHasBloqueListNewTutorHasBloque)) {
          Periodo oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque = tutorHasBloqueListNewTutorHasBloque.getPeriodoidPeriodo();
          tutorHasBloqueListNewTutorHasBloque.setPeriodoidPeriodo(periodo);
          tutorHasBloqueListNewTutorHasBloque = em.merge(tutorHasBloqueListNewTutorHasBloque);
          if (oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque != null && !oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque.equals(periodo)) {
            oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListNewTutorHasBloque);
            oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque = em.merge(oldPeriodoidPeriodoOfTutorHasBloqueListNewTutorHasBloque);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = periodo.getIdPeriodo();
        if (findPeriodo(id) == null) {
          throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
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
      Periodo periodo;
      try {
        periodo = em.getReference(Periodo.class, id);
        periodo.getIdPeriodo();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<TutorHasBloque> tutorHasBloqueListOrphanCheck = periodo.getTutorHasBloqueList();
      for (TutorHasBloque tutorHasBloqueListOrphanCheckTutorHasBloque : tutorHasBloqueListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Periodo (" + periodo + ") cannot be destroyed since the TutorHasBloque " + tutorHasBloqueListOrphanCheckTutorHasBloque + " in its tutorHasBloqueList field has a non-nullable periodoidPeriodo field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(periodo);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Periodo> findPeriodoEntities() {
    return findPeriodoEntities(true, -1, -1);
  }

  public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
    return findPeriodoEntities(false, maxResults, firstResult);
  }

  private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Periodo.class));
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

  public Periodo findPeriodo(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Periodo.class, id);
    } finally {
      em.close();
    }
  }

  public int getPeriodoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Periodo> rt = cq.from(Periodo.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
