/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.controladores;

import entidades.Tutor;
import entidades.TutorHasBloque;
import entidades.Tutorado;
import entidades.Tutoria;
import entidades.Usuario;
import entidades.exceptions.IllegalOrphanException;
import entidades.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class TutorJpaController implements Serializable {
  
  private static final long serialVersionUID = 1L;
  //Se crean mensajes constantes para no ser duplicados a lo largo de la clase
  private static final String MESSAGE_1 = "You must retain ";
  private static final String MESSAGE_2 = " since its tutoridTutor field is not nullable.";
  private static final String MESSAGE_3 = "This Tutor (";
  private static final String MESSAGE_4 = ") cannot be destroyed since the";
  private static final String MESSAGE_5 = "field has a non-nullable tutoridTutor field.";

  public TutorJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private transient EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Tutor tutor) {
    if (tutor.getTutoradoList() == null) {
      tutor.setTutoradoList(new ArrayList<Tutorado>());
    }
    if (tutor.getTutoriaList() == null) {
      tutor.setTutoriaList(new ArrayList<Tutoria>());
    }
    if (tutor.getTutorHasBloqueList() == null) {
      tutor.setTutorHasBloqueList(new ArrayList<TutorHasBloque>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario usuarioidUsuario = tutor.getUsuarioidUsuario();
      if (usuarioidUsuario != null) {
        usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
        tutor.setUsuarioidUsuario(usuarioidUsuario);
      }
      List<Tutorado> attachedTutoradoList = new ArrayList<>();
      for (Tutorado tutoradoListTutoradoToAttach : tutor.getTutoradoList()) {
        tutoradoListTutoradoToAttach = em.getReference(tutoradoListTutoradoToAttach.getClass(), tutoradoListTutoradoToAttach.getIdTutorado());
        attachedTutoradoList.add(tutoradoListTutoradoToAttach);
      }
      tutor.setTutoradoList(attachedTutoradoList);
      List<Tutoria> attachedTutoriaList = new ArrayList<>();
      for (Tutoria tutoriaListTutoriaToAttach : tutor.getTutoriaList()) {
        tutoriaListTutoriaToAttach = em.getReference(tutoriaListTutoriaToAttach.getClass(), tutoriaListTutoriaToAttach.getIdTutoria());
        attachedTutoriaList.add(tutoriaListTutoriaToAttach);
      }
      tutor.setTutoriaList(attachedTutoriaList);
      List<TutorHasBloque> attachedTutorHasBloqueList = new ArrayList<>();
      for (TutorHasBloque tutorHasBloqueListTutorHasBloqueToAttach : tutor.getTutorHasBloqueList()) {
        tutorHasBloqueListTutorHasBloqueToAttach = em.getReference(tutorHasBloqueListTutorHasBloqueToAttach.getClass(), tutorHasBloqueListTutorHasBloqueToAttach.getId());
        attachedTutorHasBloqueList.add(tutorHasBloqueListTutorHasBloqueToAttach);
      }
      tutor.setTutorHasBloqueList(attachedTutorHasBloqueList);
      em.persist(tutor);
      if (usuarioidUsuario != null) {
        usuarioidUsuario.getTutorList().add(tutor);
        usuarioidUsuario = em.merge(usuarioidUsuario);
      }
      for (Tutorado tutoradoListTutorado : tutor.getTutoradoList()) {
        Tutor oldTutoridTutorOfTutoradoListTutorado = tutoradoListTutorado.getTutoridTutor();
        tutoradoListTutorado.setTutoridTutor(tutor);
        tutoradoListTutorado = em.merge(tutoradoListTutorado);
        if (oldTutoridTutorOfTutoradoListTutorado != null) {
          oldTutoridTutorOfTutoradoListTutorado.getTutoradoList().remove(tutoradoListTutorado);
          oldTutoridTutorOfTutoradoListTutorado = em.merge(oldTutoridTutorOfTutoradoListTutorado);
        }
      }
      for (Tutoria tutoriaListTutoria : tutor.getTutoriaList()) {
        Tutor oldTutoridTutorOfTutoriaListTutoria = tutoriaListTutoria.getTutoridTutor();
        tutoriaListTutoria.setTutoridTutor(tutor);
        tutoriaListTutoria = em.merge(tutoriaListTutoria);
        if (oldTutoridTutorOfTutoriaListTutoria != null) {
          oldTutoridTutorOfTutoriaListTutoria.getTutoriaList().remove(tutoriaListTutoria);
          oldTutoridTutorOfTutoriaListTutoria = em.merge(oldTutoridTutorOfTutoriaListTutoria);
        }
      }
      for (TutorHasBloque tutorHasBloqueListTutorHasBloque : tutor.getTutorHasBloqueList()) {
        Tutor oldTutoridTutorOfTutorHasBloqueListTutorHasBloque = tutorHasBloqueListTutorHasBloque.getTutoridTutor();
        tutorHasBloqueListTutorHasBloque.setTutoridTutor(tutor);
        tutorHasBloqueListTutorHasBloque = em.merge(tutorHasBloqueListTutorHasBloque);
        if (oldTutoridTutorOfTutorHasBloqueListTutorHasBloque != null) {
          oldTutoridTutorOfTutorHasBloqueListTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListTutorHasBloque);
          oldTutoridTutorOfTutorHasBloqueListTutorHasBloque = em.merge(oldTutoridTutorOfTutorHasBloqueListTutorHasBloque);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Tutor tutor) throws IllegalOrphanException, NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutor());
      Usuario usuarioidUsuarioOld = persistentTutor.getUsuarioidUsuario();
      Usuario usuarioidUsuarioNew = tutor.getUsuarioidUsuario();
      List<Tutorado> tutoradoListOld = persistentTutor.getTutoradoList();
      List<Tutorado> tutoradoListNew = tutor.getTutoradoList();
      List<Tutoria> tutoriaListOld = persistentTutor.getTutoriaList();
      List<Tutoria> tutoriaListNew = tutor.getTutoriaList();
      List<TutorHasBloque> tutorHasBloqueListOld = persistentTutor.getTutorHasBloqueList();
      List<TutorHasBloque> tutorHasBloqueListNew = tutor.getTutorHasBloqueList();
      List<String> illegalOrphanMessages = null;
      for (Tutorado tutoradoListOldTutorado : tutoradoListOld) {
        if (!tutoradoListNew.contains(tutoradoListOldTutorado)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<>();
          }
          illegalOrphanMessages.add(MESSAGE_1 + "Tutorado " + tutoradoListOldTutorado + MESSAGE_2);
        }
      }
      for (Tutoria tutoriaListOldTutoria : tutoriaListOld) {
        if (!tutoriaListNew.contains(tutoriaListOldTutoria)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<>();
          }
          illegalOrphanMessages.add(MESSAGE_1 + "Tutoria " + tutoriaListOldTutoria + MESSAGE_2);
        }
      }
      for (TutorHasBloque tutorHasBloqueListOldTutorHasBloque : tutorHasBloqueListOld) {
        if (!tutorHasBloqueListNew.contains(tutorHasBloqueListOldTutorHasBloque)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<>();
          }
          illegalOrphanMessages.add(MESSAGE_1 + "TutorHasBloque " + tutorHasBloqueListOldTutorHasBloque + MESSAGE_2);
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (usuarioidUsuarioNew != null) {
        usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
        tutor.setUsuarioidUsuario(usuarioidUsuarioNew);
      }
      List<Tutorado> attachedTutoradoListNew = new ArrayList<>();
      for (Tutorado tutoradoListNewTutoradoToAttach : tutoradoListNew) {
        tutoradoListNewTutoradoToAttach = em.getReference(tutoradoListNewTutoradoToAttach.getClass(), tutoradoListNewTutoradoToAttach.getIdTutorado());
        attachedTutoradoListNew.add(tutoradoListNewTutoradoToAttach);
      }
      tutoradoListNew = attachedTutoradoListNew;
      tutor.setTutoradoList(tutoradoListNew);
      List<Tutoria> attachedTutoriaListNew = new ArrayList<>();
      for (Tutoria tutoriaListNewTutoriaToAttach : tutoriaListNew) {
        tutoriaListNewTutoriaToAttach = em.getReference(tutoriaListNewTutoriaToAttach.getClass(), tutoriaListNewTutoriaToAttach.getIdTutoria());
        attachedTutoriaListNew.add(tutoriaListNewTutoriaToAttach);
      }
      tutoriaListNew = attachedTutoriaListNew;
      tutor.setTutoriaList(tutoriaListNew);
      List<TutorHasBloque> attachedTutorHasBloqueListNew = new ArrayList<>();
      for (TutorHasBloque tutorHasBloqueListNewTutorHasBloqueToAttach : tutorHasBloqueListNew) {
        tutorHasBloqueListNewTutorHasBloqueToAttach = em.getReference(tutorHasBloqueListNewTutorHasBloqueToAttach.getClass(), tutorHasBloqueListNewTutorHasBloqueToAttach.getId());
        attachedTutorHasBloqueListNew.add(tutorHasBloqueListNewTutorHasBloqueToAttach);
      }
      tutorHasBloqueListNew = attachedTutorHasBloqueListNew;
      tutor.setTutorHasBloqueList(tutorHasBloqueListNew);
      tutor = em.merge(tutor);
      if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
        usuarioidUsuarioOld.getTutorList().remove(tutor);
        usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
      }
      if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
        usuarioidUsuarioNew.getTutorList().add(tutor);
        usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
      }
      for (Tutorado tutoradoListNewTutorado : tutoradoListNew) {
        if (!tutoradoListOld.contains(tutoradoListNewTutorado)) {
          Tutor oldTutoridTutorOfTutoradoListNewTutorado = tutoradoListNewTutorado.getTutoridTutor();
          tutoradoListNewTutorado.setTutoridTutor(tutor);
          tutoradoListNewTutorado = em.merge(tutoradoListNewTutorado);
          if (oldTutoridTutorOfTutoradoListNewTutorado != null && !oldTutoridTutorOfTutoradoListNewTutorado.equals(tutor)) {
            oldTutoridTutorOfTutoradoListNewTutorado.getTutoradoList().remove(tutoradoListNewTutorado);
            oldTutoridTutorOfTutoradoListNewTutorado = em.merge(oldTutoridTutorOfTutoradoListNewTutorado);
          }
        }
      }
      for (Tutoria tutoriaListNewTutoria : tutoriaListNew) {
        if (!tutoriaListOld.contains(tutoriaListNewTutoria)) {
          Tutor oldTutoridTutorOfTutoriaListNewTutoria = tutoriaListNewTutoria.getTutoridTutor();
          tutoriaListNewTutoria.setTutoridTutor(tutor);
          tutoriaListNewTutoria = em.merge(tutoriaListNewTutoria);
          if (oldTutoridTutorOfTutoriaListNewTutoria != null && !oldTutoridTutorOfTutoriaListNewTutoria.equals(tutor)) {
            oldTutoridTutorOfTutoriaListNewTutoria.getTutoriaList().remove(tutoriaListNewTutoria);
            oldTutoridTutorOfTutoriaListNewTutoria = em.merge(oldTutoridTutorOfTutoriaListNewTutoria);
          }
        }
      }
      for (TutorHasBloque tutorHasBloqueListNewTutorHasBloque : tutorHasBloqueListNew) {
        if (!tutorHasBloqueListOld.contains(tutorHasBloqueListNewTutorHasBloque)) {
          Tutor oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque = tutorHasBloqueListNewTutorHasBloque.getTutoridTutor();
          tutorHasBloqueListNewTutorHasBloque.setTutoridTutor(tutor);
          tutorHasBloqueListNewTutorHasBloque = em.merge(tutorHasBloqueListNewTutorHasBloque);
          if (oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque != null && !oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque.equals(tutor)) {
            oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque.getTutorHasBloqueList().remove(tutorHasBloqueListNewTutorHasBloque);
            oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque = em.merge(oldTutoridTutorOfTutorHasBloqueListNewTutorHasBloque);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = tutor.getIdTutor();
        if (findTutor(id) == null) {
          throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
      Tutor tutor;
      try {
        tutor = em.getReference(Tutor.class, id);
        tutor.getIdTutor();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      List<Tutorado> tutoradoListOrphanCheck = tutor.getTutoradoList();
      for (Tutorado tutoradoListOrphanCheckTutorado : tutoradoListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<>();
        }
        illegalOrphanMessages.add(MESSAGE_3 + tutor + MESSAGE_4 + " Tutorado " + tutoradoListOrphanCheckTutorado + " in its tutoradoList " + MESSAGE_5);
      }
      List<Tutoria> tutoriaListOrphanCheck = tutor.getTutoriaList();
      for (Tutoria tutoriaListOrphanCheckTutoria : tutoriaListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<>();
        }
        illegalOrphanMessages.add(MESSAGE_3 + tutor + MESSAGE_4 + " Tutoria " + tutoriaListOrphanCheckTutoria + " in its tutoriaList " + MESSAGE_5);
      }
      List<TutorHasBloque> tutorHasBloqueListOrphanCheck = tutor.getTutorHasBloqueList();
      for (TutorHasBloque tutorHasBloqueListOrphanCheckTutorHasBloque : tutorHasBloqueListOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<>();
        }
        illegalOrphanMessages.add(MESSAGE_3 + tutor + MESSAGE_4 + " TutorHasBloque " + tutorHasBloqueListOrphanCheckTutorHasBloque + " in its tutorHasBloqueList " + MESSAGE_5);
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Usuario usuarioidUsuario = tutor.getUsuarioidUsuario();
      if (usuarioidUsuario != null) {
        usuarioidUsuario.getTutorList().remove(tutor);
        usuarioidUsuario = em.merge(usuarioidUsuario);
      }
      em.remove(tutor);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Tutor> findTutorEntities() {
    return findTutorEntities(true, -1, -1);
  }

  public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
    return findTutorEntities(false, maxResults, firstResult);
  }

  private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Tutor.class));
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

  public Tutor findTutor(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Tutor.class, id);
    } finally {
      em.close();
    }
  }
  
  public Tutor findTutorByUser(Usuario usuario) {
    Tutor tutor = null;
    EntityManager em = null;
    try {
      em = getEntityManager();
      tutor = (Tutor) em.createQuery("SELECT c FROM Tutor c WHERE c.usuarioidUsuario.idUsuario = :idUsuario")
          .setParameter("idUsuario", usuario.getIdUsuario())
          .getSingleResult();
    } catch (Exception ex) {
      Logger.getLogger(TutorJpaController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (em != null) {
        try {
          em.close();
        } catch (Exception ex) {
          Logger.getLogger(TutorJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return tutor;
  }
  
  public int getIdByNoPersonal(Tutor tutor) {
    Integer id = 0;
    EntityManager em = null;
    try {
      em = getEntityManager();
      id = (Integer) em.createQuery(
          "SELECT c.idTutor FROM Tutor c WHERE c.noPersonal = :noPersonal")
          .setParameter("noPersonal", tutor.getNoPersonal())
          .getSingleResult();
    } catch (Exception ex) {
      Logger.getLogger(TutorJpaController.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if(em != null) {
        try {
          em.close();
        } catch(Exception ex) {
          Logger.getLogger(TutorJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return id;
  }

  public int getTutorCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Tutor> rt = cq.from(Tutor.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
