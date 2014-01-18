/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Protocolo;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Cuc;
import br.com.fox.db.Alarme;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Dvr
 */
public class ProtocoloJpaController implements Serializable {

    public ProtocoloJpaController() {
    }

    public ProtocoloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Protocolo protocolo) {
        if (protocolo.getAlarmeList() == null) {
            protocolo.setAlarmeList(new ArrayList<Alarme>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuc cuc = protocolo.getCuc();
            if (cuc != null) {
                cuc = em.getReference(cuc.getClass(), cuc.getIdcuc());
                protocolo.setCuc(cuc);
            }
            List<Alarme> attachedAlarmeList = new ArrayList<Alarme>();
            for (Alarme alarmeListAlarmeToAttach : protocolo.getAlarmeList()) {
                alarmeListAlarmeToAttach = em.getReference(alarmeListAlarmeToAttach.getClass(), alarmeListAlarmeToAttach.getIdalarme());
                attachedAlarmeList.add(alarmeListAlarmeToAttach);
            }
            protocolo.setAlarmeList(attachedAlarmeList);
            em.persist(protocolo);
            if (cuc != null) {
                cuc.getProtocoloList().add(protocolo);
                cuc = em.merge(cuc);
            }
            for (Alarme alarmeListAlarme : protocolo.getAlarmeList()) {
                Protocolo oldIdprotocoloOfAlarmeListAlarme = alarmeListAlarme.getIdprotocolo();
                alarmeListAlarme.setIdprotocolo(protocolo);
                alarmeListAlarme = em.merge(alarmeListAlarme);
                if (oldIdprotocoloOfAlarmeListAlarme != null) {
                    oldIdprotocoloOfAlarmeListAlarme.getAlarmeList().remove(alarmeListAlarme);
                    oldIdprotocoloOfAlarmeListAlarme = em.merge(oldIdprotocoloOfAlarmeListAlarme);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Protocolo protocolo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Protocolo persistentProtocolo = em.find(Protocolo.class, protocolo.getIdprotocolo());
            Cuc cucOld = persistentProtocolo.getCuc();
            Cuc cucNew = protocolo.getCuc();
            List<Alarme> alarmeListOld = persistentProtocolo.getAlarmeList();
            List<Alarme> alarmeListNew = protocolo.getAlarmeList();
            if (cucNew != null) {
                cucNew = em.getReference(cucNew.getClass(), cucNew.getIdcuc());
                protocolo.setCuc(cucNew);
            }
            List<Alarme> attachedAlarmeListNew = new ArrayList<Alarme>();
            for (Alarme alarmeListNewAlarmeToAttach : alarmeListNew) {
                alarmeListNewAlarmeToAttach = em.getReference(alarmeListNewAlarmeToAttach.getClass(), alarmeListNewAlarmeToAttach.getIdalarme());
                attachedAlarmeListNew.add(alarmeListNewAlarmeToAttach);
            }
            alarmeListNew = attachedAlarmeListNew;
            protocolo.setAlarmeList(alarmeListNew);
            protocolo = em.merge(protocolo);
            if (cucOld != null && !cucOld.equals(cucNew)) {
                cucOld.getProtocoloList().remove(protocolo);
                cucOld = em.merge(cucOld);
            }
            if (cucNew != null && !cucNew.equals(cucOld)) {
                cucNew.getProtocoloList().add(protocolo);
                cucNew = em.merge(cucNew);
            }
            for (Alarme alarmeListOldAlarme : alarmeListOld) {
                if (!alarmeListNew.contains(alarmeListOldAlarme)) {
                    alarmeListOldAlarme.setIdprotocolo(null);
                    alarmeListOldAlarme = em.merge(alarmeListOldAlarme);
                }
            }
            for (Alarme alarmeListNewAlarme : alarmeListNew) {
                if (!alarmeListOld.contains(alarmeListNewAlarme)) {
                    Protocolo oldIdprotocoloOfAlarmeListNewAlarme = alarmeListNewAlarme.getIdprotocolo();
                    alarmeListNewAlarme.setIdprotocolo(protocolo);
                    alarmeListNewAlarme = em.merge(alarmeListNewAlarme);
                    if (oldIdprotocoloOfAlarmeListNewAlarme != null && !oldIdprotocoloOfAlarmeListNewAlarme.equals(protocolo)) {
                        oldIdprotocoloOfAlarmeListNewAlarme.getAlarmeList().remove(alarmeListNewAlarme);
                        oldIdprotocoloOfAlarmeListNewAlarme = em.merge(oldIdprotocoloOfAlarmeListNewAlarme);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = protocolo.getIdprotocolo();
                if (findProtocolo(id) == null) {
                    throw new NonexistentEntityException("The protocolo with id " + id + " no longer exists.");
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
            Protocolo protocolo;
            try {
                protocolo = em.getReference(Protocolo.class, id);
                protocolo.getIdprotocolo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The protocolo with id " + id + " no longer exists.", enfe);
            }
            Cuc cuc = protocolo.getCuc();
            if (cuc != null) {
                cuc.getProtocoloList().remove(protocolo);
                cuc = em.merge(cuc);
            }
            List<Alarme> alarmeList = protocolo.getAlarmeList();
            for (Alarme alarmeListAlarme : alarmeList) {
                alarmeListAlarme.setIdprotocolo(null);
                alarmeListAlarme = em.merge(alarmeListAlarme);
            }
            em.remove(protocolo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Protocolo> findProtocoloEntities() {
        return findProtocoloEntities(true, -1, -1);
    }

    public List<Protocolo> findProtocoloEntities(int maxResults, int firstResult) {
        return findProtocoloEntities(false, maxResults, firstResult);
    }

    private List<Protocolo> findProtocoloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Protocolo.class));
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

    public Protocolo findProtocolo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Protocolo.class, id);
        } finally {
            em.close();
        }
    }

    public Protocolo findProtocolo(String evento) {
        Protocolo protocolo = null;
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Protocolo p WHERE "
                    + "p.evento like '" + evento + "'");                      

            protocolo = (Protocolo) q.getSingleResult();           
            
        } catch(NoResultException e) {
            
        } finally {
            em.close();
        }
        
        return protocolo;
    }
    
    public Protocolo findProtocoloByDescricao(String descricao) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Protocolo p WHERE "
                    + "p.descricao like '" + descricao + "'"); 
                                                       
            return (Protocolo) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getProtocoloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Protocolo> rt = cq.from(Protocolo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
