/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.controladores;

import entidades.Reporte;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Tutoria;
import entidades.controladores.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class ReporteJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public ReporteJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Reporte reporte) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Tutoria tutoriaidTutoria = reporte.getTutoriaidTutoria();
      if (tutoriaidTutoria != null) {
        tutoriaidTutoria = em.getReference(tutoriaidTutoria.getClass(), tutoriaidTutoria.getIdTutoria());
        reporte.setTutoriaidTutoria(tutoriaidTutoria);
      }
      em.persist(reporte);
      if (tutoriaidTutoria != null) {
        tutoriaidTutoria.getReporteList().add(reporte);
        tutoriaidTutoria = em.merge(tutoriaidTutoria);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Reporte reporte) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Reporte persistentReporte = em.find(Reporte.class, reporte.getIdReporte());
      Tutoria tutoriaidTutoriaOld = persistentReporte.getTutoriaidTutoria();
      Tutoria tutoriaidTutoriaNew = reporte.getTutoriaidTutoria();
      if (tutoriaidTutoriaNew != null) {
        tutoriaidTutoriaNew = em.getReference(tutoriaidTutoriaNew.getClass(), tutoriaidTutoriaNew.getIdTutoria());
        reporte.setTutoriaidTutoria(tutoriaidTutoriaNew);
      }
      reporte = em.merge(reporte);
      if (tutoriaidTutoriaOld != null && !tutoriaidTutoriaOld.equals(tutoriaidTutoriaNew)) {
        tutoriaidTutoriaOld.getReporteList().remove(reporte);
        tutoriaidTutoriaOld = em.merge(tutoriaidTutoriaOld);
      }
      if (tutoriaidTutoriaNew != null && !tutoriaidTutoriaNew.equals(tutoriaidTutoriaOld)) {
        tutoriaidTutoriaNew.getReporteList().add(reporte);
        tutoriaidTutoriaNew = em.merge(tutoriaidTutoriaNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = reporte.getIdReporte();
        if (findReporte(id) == null) {
          throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
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
      Reporte reporte;
      try {
        reporte = em.getReference(Reporte.class, id);
        reporte.getIdReporte();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
      }
      Tutoria tutoriaidTutoria = reporte.getTutoriaidTutoria();
      if (tutoriaidTutoria != null) {
        tutoriaidTutoria.getReporteList().remove(reporte);
        tutoriaidTutoria = em.merge(tutoriaidTutoria);
      }
      em.remove(reporte);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Reporte> findReporteEntities() {
    return findReporteEntities(true, -1, -1);
  }

  public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
    return findReporteEntities(false, maxResults, firstResult);
  }

  private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Reporte.class));
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

  public Reporte findReporte(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Reporte.class, id);
    } finally {
      em.close();
    }
  }

  public int getReporteCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Reporte> rt = cq.from(Reporte.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
