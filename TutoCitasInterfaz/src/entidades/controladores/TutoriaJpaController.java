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
import entidades.Tutorado;
import entidades.Tutor;
import entidades.Reporte;
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
public class TutoriaJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public TutoriaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Tutoria tutoria) {
    if (tutoria.getReporteList() == null) {
      tutoria.setReporteList(new ArrayList<Reporte>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Tutorado tutoradoidTutorado = tutoria.getTutoradoidTutorado();
      if (tutoradoidTutorado != null) {
        tutoradoidTutorado = em.getReference(tutoradoidTutorado.getClass(), tutoradoidTutorado.getIdTutorado());
        tutoria.setTutoradoidTutorado(tutoradoidTutorado);
      }
      Tutor tutoridTutor = tutoria.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor = em.getReference(tutoridTutor.getClass(), tutoridTutor.getIdTutor());
        tutoria.setTutoridTutor(tutoridTutor);
      }
      List<Reporte> attachedReporteList = new ArrayList<Reporte>();
      for (Reporte reporteListReporteToAttach : tutoria.getReporteList()) {
        reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdReporte());
        attachedReporteList.add(reporteListReporteToAttach);
      }
      tutoria.setReporteList(attachedReporteList);
      em.persist(tutoria);
      if (tutoradoidTutorado != null) {
        tutoradoidTutorado.getTutoriaList().add(tutoria);
        tutoradoidTutorado = em.merge(tutoradoidTutorado);
      }
      if (tutoridTutor != null) {
        tutoridTutor.getTutoriaList().add(tutoria);
        tutoridTutor = em.merge(tutoridTutor);
      }
      for (Reporte reporteListReporte : tutoria.getReporteList()) {
        Tutoria oldTutoriaidTutoriaOfReporteListReporte = reporteListReporte.getTutoriaidTutoria();
        reporteListReporte.setTutoriaidTutoria(tutoria);
        reporteListReporte = em.merge(reporteListReporte);
        if (oldTutoriaidTutoriaOfReporteListReporte != null) {
          oldTutoriaidTutoriaOfReporteListReporte.getReporteList().remove(reporteListReporte);
          oldTutoriaidTutoriaOfReporteListReporte = em.merge(oldTutoriaidTutoriaOfReporteListReporte);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Tutoria tutoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Tutoria persistentTutoria = em.find(Tutoria.class, tutoria.getIdTutoria());
      Tutorado tutoradoidTutoradoOld = persistentTutoria.getTutoradoidTutorado();
      Tutorado tutoradoidTutoradoNew = tutoria.getTutoradoidTutorado();
      Tutor tutoridTutorOld = persistentTutoria.getTutoridTutor();
      Tutor tutoridTutorNew = tutoria.getTutoridTutor();
      List<Reporte> reporteListOld = persistentTutoria.getReporteList();
      List<Reporte> reporteListNew = tutoria.getReporteList();
      List<String> illegalOrphanMessages = null;
      for (Reporte reporteListOldReporte : reporteListOld) {
        if (!reporteListNew.contains(reporteListOldReporte)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its tutoriaidTutoria field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (tutoradoidTutoradoNew != null) {
        tutoradoidTutoradoNew = em.getReference(tutoradoidTutoradoNew.getClass(), tutoradoidTutoradoNew.getIdTutorado());
        tutoria.setTutoradoidTutorado(tutoradoidTutoradoNew);
      }
      if (tutoridTutorNew != null) {
        tutoridTutorNew = em.getReference(tutoridTutorNew.getClass(), tutoridTutorNew.getIdTutor());
        tutoria.setTutoridTutor(tutoridTutorNew);
      }
      List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
      for (Reporte reporteListNewReporteToAttach : reporteListNew) {
        reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdReporte());
        attachedReporteListNew.add(reporteListNewReporteToAttach);
      }
      reporteListNew = attachedReporteListNew;
      tutoria.setReporteList(reporteListNew);
      tutoria = em.merge(tutoria);
      if (tutoradoidTutoradoOld != null && !tutoradoidTutoradoOld.equals(tutoradoidTutoradoNew)) {
        tutoradoidTutoradoOld.getTutoriaList().remove(tutoria);
        tutoradoidTutoradoOld = em.merge(tutoradoidTutoradoOld);
      }
      if (tutoradoidTutoradoNew != null && !tutoradoidTutoradoNew.equals(tutoradoidTutoradoOld)) {
        tutoradoidTutoradoNew.getTutoriaList().add(tutoria);
        tutoradoidTutoradoNew = em.merge(tutoradoidTutoradoNew);
      }
      if (tutoridTutorOld != null && !tutoridTutorOld.equals(tutoridTutorNew)) {
        tutoridTutorOld.getTutoriaList().remove(tutoria);
        tutoridTutorOld = em.merge(tutoridTutorOld);
      }
      if (tutoridTutorNew != null && !tutoridTutorNew.equals(tutoridTutorOld)) {
        tutoridTutorNew.getTutoriaList().add(tutoria);
        tutoridTutorNew = em.merge(tutoridTutorNew);
      }
      for (Reporte reporteListNewReporte : reporteListNew) {
        if (!reporteListOld.contains(reporteListNewReporte)) {
          Tutoria oldTutoriaidTutoriaOfReporteListNewReporte = reporteListNewReporte.getTutoriaidTutoria();
          reporteListNewReporte.setTutoriaidTutoria(tutoria);
          reporteListNewReporte = em.merge(reporteListNewReporte);
          if (oldTutoriaidTutoriaOfReporteListNewReporte != null && !oldTutoriaidTutoriaOfReporteListNewReporte.equals(tutoria)) {
            oldTutoriaidTutoriaOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
            oldTutoriaidTutoriaOfReporteListNewReporte = em.merge(oldTutoriaidTutoriaOfReporteListNewReporte);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = tutoria.getIdTutoria();
        if (findTutoria(id) == null) {
          throw new NonexistentEntityException("The tutoria with id " + id + " no longer exists.");
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
      Tutoria tutoria;
      try {
        tutoria = em.getReference(Tutoria.class, id);
        tutoria.getIdTutoria();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tutoria with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<Reporte> reporteListOrphanCheck = tutoria.getReporteList();
      for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Tutoria (" + tutoria + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable tutoriaidTutoria field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Tutorado tutoradoidTutorado = tutoria.getTutoradoidTutorado();
      if (tutoradoidTutorado != null) {
        tutoradoidTutorado.getTutoriaList().remove(tutoria);
        tutoradoidTutorado = em.merge(tutoradoidTutorado);
      }
      Tutor tutoridTutor = tutoria.getTutoridTutor();
      if (tutoridTutor != null) {
        tutoridTutor.getTutoriaList().remove(tutoria);
        tutoridTutor = em.merge(tutoridTutor);
      }
      em.remove(tutoria);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Tutoria> findTutoriaEntities() {
    return findTutoriaEntities(true, -1, -1);
  }

  public List<Tutoria> findTutoriaEntities(int maxResults, int firstResult) {
    return findTutoriaEntities(false, maxResults, firstResult);
  }

  private List<Tutoria> findTutoriaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Tutoria.class));
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
  
  public List<Tutoria> findTutoriaEntitiesByTutor(Tutor tutor) {
    List<Tutoria> tutorias = null;
    EntityManager em = null;
    try {
      em = getEntityManager();
      tutorias = em.createQuery("SELECT c.fecha, c.hora, u.username FROM Tutoria c "
          + "INNER JOIN Tutorado t ON (c.Tutorado_idTutorado = t.idTutorado)"
          + "INNER JOIN Usuario u ON (t.Usuario_idUsuario = u.idUsuario)"
          + "WHERE c.tutoridTutor = :tutoridTutor AND c.cancelada = FALSE")
          .setParameter("tutoridTutor", tutor)
          .getResultList();
    } catch (Exception ex) {
      System.err.println("Excepción: " + ex.getMessage());
      ex.printStackTrace();
    } finally {
      if (em != null) {
        try {
          em.close();
        } catch (Exception ex) {
          System.err.println("Error al cerrar EntityManager: " + ex.getMessage());
        }
      }
    }
    return tutorias;
  }
  
  public List<Tutoria> findTutoriaEntitiesByTutorado(Tutorado tutorado) {
    List<Tutoria> tutorias = null;
    EntityManager em = null;
    try {
      em = getEntityManager();
      tutorias = (List<Tutoria>) em.createQuery("SELECT c FROM Tutoria c "
          + "WHERE c.tutoradoidTutorado = :tutoradoidTutorado AND c.cancelada = FALSE")
          .setParameter("tutoradoidTutorado", tutorado)
          .getResultList();
    } catch (Exception ex) {
      System.err.println("Excepción: " + ex.getMessage());
      ex.printStackTrace();
    } finally {
      if (em != null) {
        try {
          em.close();
        } catch (Exception ex) {
          System.err.println("Error al cerrar EntityManager: " + ex.getMessage());
        }
      }
    }
    return tutorias;
  }

  public Tutoria findTutoria(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Tutoria.class, id);
    } finally {
      em.close();
    }
  }

  public int getTutoriaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Tutoria> rt = cq.from(Tutoria.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
