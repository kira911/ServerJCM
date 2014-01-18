/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Cuc;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Protocolo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dvr
 */
public class CucJpaController implements Serializable {

    public CucJpaController() {
    }

    public CucJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuc cuc) {
        if (cuc.getProtocoloList() == null) {
            cuc.setProtocoloList(new ArrayList<Protocolo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Protocolo> attachedProtocoloList = new ArrayList<Protocolo>();
            for (Protocolo protocoloListProtocoloToAttach : cuc.getProtocoloList()) {
                protocoloListProtocoloToAttach = em.getReference(protocoloListProtocoloToAttach.getClass(), protocoloListProtocoloToAttach.getIdprotocolo());
                attachedProtocoloList.add(protocoloListProtocoloToAttach);
            }
            cuc.setProtocoloList(attachedProtocoloList);
            em.persist(cuc);
            for (Protocolo protocoloListProtocolo : cuc.getProtocoloList()) {
                Cuc oldCucOfProtocoloListProtocolo = protocoloListProtocolo.getCuc();
                protocoloListProtocolo.setCuc(cuc);
                protocoloListProtocolo = em.merge(protocoloListProtocolo);
                if (oldCucOfProtocoloListProtocolo != null) {
                    oldCucOfProtocoloListProtocolo.getProtocoloList().remove(protocoloListProtocolo);
                    oldCucOfProtocoloListProtocolo = em.merge(oldCucOfProtocoloListProtocolo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuc cuc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuc persistentCuc = em.find(Cuc.class, cuc.getIdcuc());
            List<Protocolo> protocoloListOld = persistentCuc.getProtocoloList();
            List<Protocolo> protocoloListNew = cuc.getProtocoloList();
            List<Protocolo> attachedProtocoloListNew = new ArrayList<Protocolo>();
            for (Protocolo protocoloListNewProtocoloToAttach : protocoloListNew) {
                protocoloListNewProtocoloToAttach = em.getReference(protocoloListNewProtocoloToAttach.getClass(), protocoloListNewProtocoloToAttach.getIdprotocolo());
                attachedProtocoloListNew.add(protocoloListNewProtocoloToAttach);
            }
            protocoloListNew = attachedProtocoloListNew;
            cuc.setProtocoloList(protocoloListNew);
            cuc = em.merge(cuc);
            for (Protocolo protocoloListOldProtocolo : protocoloListOld) {
                if (!protocoloListNew.contains(protocoloListOldProtocolo)) {
                    protocoloListOldProtocolo.setCuc(null);
                    protocoloListOldProtocolo = em.merge(protocoloListOldProtocolo);
                }
            }
            for (Protocolo protocoloListNewProtocolo : protocoloListNew) {
                if (!protocoloListOld.contains(protocoloListNewProtocolo)) {
                    Cuc oldCucOfProtocoloListNewProtocolo = protocoloListNewProtocolo.getCuc();
                    protocoloListNewProtocolo.setCuc(cuc);
                    protocoloListNewProtocolo = em.merge(protocoloListNewProtocolo);
                    if (oldCucOfProtocoloListNewProtocolo != null && !oldCucOfProtocoloListNewProtocolo.equals(cuc)) {
                        oldCucOfProtocoloListNewProtocolo.getProtocoloList().remove(protocoloListNewProtocolo);
                        oldCucOfProtocoloListNewProtocolo = em.merge(oldCucOfProtocoloListNewProtocolo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuc.getIdcuc();
                if (findCuc(id) == null) {
                    throw new NonexistentEntityException("The cuc with id " + id + " no longer exists.");
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
            Cuc cuc;
            try {
                cuc = em.getReference(Cuc.class, id);
                cuc.getIdcuc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuc with id " + id + " no longer exists.", enfe);
            }
            List<Protocolo> protocoloList = cuc.getProtocoloList();
            for (Protocolo protocoloListProtocolo : protocoloList) {
                protocoloListProtocolo.setCuc(null);
                protocoloListProtocolo = em.merge(protocoloListProtocolo);
            }
            em.remove(cuc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuc> findCucEntities() {
        return findCucEntities(true, -1, -1);
    }

    public List<Cuc> findCucEntities(int maxResults, int firstResult) {
        return findCucEntities(false, maxResults, firstResult);
    }

    private List<Cuc> findCucEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuc.class));
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

    public Cuc findCuc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuc.class, id);
        } finally {
            em.close();
        }
    }

    public int getCucCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuc> rt = cq.from(Cuc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
