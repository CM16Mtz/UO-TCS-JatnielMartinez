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
import entidades.Bloque;
import entidades.Periodo;
import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.controladores.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class TutorHasBloqueJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public TutorHasBloqueJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(TutorHasBloque tutorHasBloque) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Bloque bloqueidBloque = tutorHasBloque.getBloqueidBloque();
      if (bloqueidBloque != null) {
        bloqueidBloque = em.getReference(bloqueidBloque.getClass(), bloqueidBloque.getIdBloque());
        tutorHasBloque.setBloqueidBloque(bloqueidBloque);
      }
      Periodo periodoidPeriodo = tutorHasBloque.getPeriodoidPeriodo();
      if (periodoidPeriodo != null) {
        periodoidPeriodo = em.getReference(periodoidPeriodo.getClass(), periodoidPeriodo.getIdPeriodo());
        tutorHasBloque.setPeriodoidPeriodo(periodoidPeriodo);
      }
      Tutor tutoridTutor = tutorHasBloque.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor = em.getReference(tutoridTutor.getClass(), tutoridTutor.getIdTutor());
        tutorHasBloque.setTutoridTutor(tutoridTutor);
      }
      em.persist(tutorHasBloque);
      if (bloqueidBloque != null) {
        bloqueidBloque.getTutorHasBloqueList().add(tutorHasBloque);
        bloqueidBloque = em.merge(bloqueidBloque);
      }
      if (periodoidPeriodo != null) {
        periodoidPeriodo.getTutorHasBloqueList().add(tutorHasBloque);
        periodoidPeriodo = em.merge(periodoidPeriodo);
      }
      if (tutoridTutor != null) {
        tutoridTutor.getTutorHasBloqueList().add(tutorHasBloque);
        tutoridTutor = em.merge(tutoridTutor);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(TutorHasBloque tutorHasBloque) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TutorHasBloque persistentTutorHasBloque = em.find(TutorHasBloque.class, tutorHasBloque.getId());
      Bloque bloqueidBloqueOld = persistentTutorHasBloque.getBloqueidBloque();
      Bloque bloqueidBloqueNew = tutorHasBloque.getBloqueidBloque();
      Periodo periodoidPeriodoOld = persistentTutorHasBloque.getPeriodoidPeriodo();
      Periodo periodoidPeriodoNew = tutorHasBloque.getPeriodoidPeriodo();
      Tutor tutoridTutorOld = persistentTutorHasBloque.getTutoridTutor();
      Tutor tutoridTutorNew = tutorHasBloque.getTutoridTutor();
      if (bloqueidBloqueNew != null) {
        bloqueidBloqueNew = em.getReference(bloqueidBloqueNew.getClass(), bloqueidBloqueNew.getIdBloque());
        tutorHasBloque.setBloqueidBloque(bloqueidBloqueNew);
      }
      if (periodoidPeriodoNew != null) {
        periodoidPeriodoNew = em.getReference(periodoidPeriodoNew.getClass(), periodoidPeriodoNew.getIdPeriodo());
        tutorHasBloque.setPeriodoidPeriodo(periodoidPeriodoNew);
      }
      if (tutoridTutorNew != null) {
        tutoridTutorNew = em.getReference(tutoridTutorNew.getClass(), tutoridTutorNew.getIdTutor());
        tutorHasBloque.setTutoridTutor(tutoridTutorNew);
      }
      tutorHasBloque = em.merge(tutorHasBloque);
      if (bloqueidBloqueOld != null && !bloqueidBloqueOld.equals(bloqueidBloqueNew)) {
        bloqueidBloqueOld.getTutorHasBloqueList().remove(tutorHasBloque);
        bloqueidBloqueOld = em.merge(bloqueidBloqueOld);
      }
      if (bloqueidBloqueNew != null && !bloqueidBloqueNew.equals(bloqueidBloqueOld)) {
        bloqueidBloqueNew.getTutorHasBloqueList().add(tutorHasBloque);
        bloqueidBloqueNew = em.merge(bloqueidBloqueNew);
      }
      if (periodoidPeriodoOld != null && !periodoidPeriodoOld.equals(periodoidPeriodoNew)) {
        periodoidPeriodoOld.getTutorHasBloqueList().remove(tutorHasBloque);
        periodoidPeriodoOld = em.merge(periodoidPeriodoOld);
      }
      if (periodoidPeriodoNew != null && !periodoidPeriodoNew.equals(periodoidPeriodoOld)) {
        periodoidPeriodoNew.getTutorHasBloqueList().add(tutorHasBloque);
        periodoidPeriodoNew = em.merge(periodoidPeriodoNew);
      }
      if (tutoridTutorOld != null && !tutoridTutorOld.equals(tutoridTutorNew)) {
        tutoridTutorOld.getTutorHasBloqueList().remove(tutorHasBloque);
        tutoridTutorOld = em.merge(tutoridTutorOld);
      }
      if (tutoridTutorNew != null && !tutoridTutorNew.equals(tutoridTutorOld)) {
        tutoridTutorNew.getTutorHasBloqueList().add(tutorHasBloque);
        tutoridTutorNew = em.merge(tutoridTutorNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = tutorHasBloque.getId();
        if (findTutorHasBloque(id) == null) {
          throw new NonexistentEntityException("The tutorHasBloque with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TutorHasBloque tutorHasBloque;
      try {
        tutorHasBloque = em.getReference(TutorHasBloque.class, id);
        tutorHasBloque.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tutorHasBloque with id " + id + " no longer exists.", enfe);
      }
      Bloque bloqueidBloque = tutorHasBloque.getBloqueidBloque();
      if (bloqueidBloque != null) {
        bloqueidBloque.getTutorHasBloqueList().remove(tutorHasBloque);
        bloqueidBloque = em.merge(bloqueidBloque);
      }
      Periodo periodoidPeriodo = tutorHasBloque.getPeriodoidPeriodo();
      if (periodoidPeriodo != null) {
        periodoidPeriodo.getTutorHasBloqueList().remove(tutorHasBloque);
        periodoidPeriodo = em.merge(periodoidPeriodo);
      }
      Tutor tutoridTutor = tutorHasBloque.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor.getTutorHasBloqueList().remove(tutorHasBloque);
        tutoridTutor = em.merge(tutoridTutor);
      }
      em.remove(tutorHasBloque);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<TutorHasBloque> findTutorHasBloqueEntities() {
    return findTutorHasBloqueEntities(true, -1, -1);
  }

  public List<TutorHasBloque> findTutorHasBloqueEntities(int maxResults, int firstResult) {
    return findTutorHasBloqueEntities(false, maxResults, firstResult);
  }

  private List<TutorHasBloque> findTutorHasBloqueEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(TutorHasBloque.class));
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

  public TutorHasBloque findTutorHasBloque(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(TutorHasBloque.class, id);
    } finally {
      em.close();
    }
  }

  public int getTutorHasBloqueCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<TutorHasBloque> rt = cq.from(TutorHasBloque.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
