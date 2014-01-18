/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Alarme;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Users;
import br.com.fox.db.Protocolo;
import br.com.fox.db.Cliente;

/**
 *
 * @author Dvr
 */
public class AlarmeJpaController implements Serializable {

    public AlarmeJpaController() {
    }

    public AlarmeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alarme alarme) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users username = alarme.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                alarme.setUsername(username);
            }
            Protocolo idprotocolo = alarme.getIdprotocolo();
            if (idprotocolo != null) {
                idprotocolo = em.getReference(idprotocolo.getClass(), idprotocolo.getIdprotocolo());
                alarme.setIdprotocolo(idprotocolo);
            }
            Cliente idcliente = alarme.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                alarme.setIdcliente(idcliente);
            }
            em.persist(alarme);
            if (username != null) {
                username.getAlarmeList().add(alarme);
                username = em.merge(username);
            }
            if (idprotocolo != null) {
                idprotocolo.getAlarmeList().add(alarme);
                idprotocolo = em.merge(idprotocolo);
            }
            if (idcliente != null) {
                idcliente.getAlarmeList().add(alarme);
                idcliente = em.merge(idcliente);
            }
            if(alarme.getCheckHorario() == null)
                alarme.setCheckHorario("false");
                
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alarme alarme) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alarme persistentAlarme = em.find(Alarme.class, alarme.getIdalarme());
            Users usernameOld = persistentAlarme.getUsername();
            Users usernameNew = alarme.getUsername();
            Protocolo idprotocoloOld = persistentAlarme.getIdprotocolo();
            Protocolo idprotocoloNew = alarme.getIdprotocolo();
            Cliente idclienteOld = persistentAlarme.getIdcliente();
            Cliente idclienteNew = alarme.getIdcliente();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                alarme.setUsername(usernameNew);
            }
            if (idprotocoloNew != null) {
                idprotocoloNew = em.getReference(idprotocoloNew.getClass(), idprotocoloNew.getIdprotocolo());
                alarme.setIdprotocolo(idprotocoloNew);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                alarme.setIdcliente(idclienteNew);
            }
            alarme = em.merge(alarme);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getAlarmeList().remove(alarme);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getAlarmeList().add(alarme);
                usernameNew = em.merge(usernameNew);
            }
            if (idprotocoloOld != null && !idprotocoloOld.equals(idprotocoloNew)) {
                idprotocoloOld.getAlarmeList().remove(alarme);
                idprotocoloOld = em.merge(idprotocoloOld);
            }
            if (idprotocoloNew != null && !idprotocoloNew.equals(idprotocoloOld)) {
                idprotocoloNew.getAlarmeList().add(alarme);
                idprotocoloNew = em.merge(idprotocoloNew);
            }
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getAlarmeList().remove(alarme);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getAlarmeList().add(alarme);
                idclienteNew = em.merge(idclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alarme.getIdalarme();
                if (findAlarme(id) == null) {
                    throw new NonexistentEntityException("The alarme with id " + id + " no longer exists.");
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
            Alarme alarme;
            try {
                alarme = em.getReference(Alarme.class, id);
                alarme.getIdalarme();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alarme with id " + id + " no longer exists.", enfe);
            }
            Users username = alarme.getUsername();
            if (username != null) {
                username.getAlarmeList().remove(alarme);
                username = em.merge(username);
            }
            Protocolo idprotocolo = alarme.getIdprotocolo();
            if (idprotocolo != null) {
                idprotocolo.getAlarmeList().remove(alarme);
                idprotocolo = em.merge(idprotocolo);
            }
            Cliente idcliente = alarme.getIdcliente();
            if (idcliente != null) {
                idcliente.getAlarmeList().remove(alarme);
                idcliente = em.merge(idcliente);
            }
            em.remove(alarme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alarme> findAlarmeEntities() {
        return findAlarmeEntities(true, -1, -1);
    }

    public List<Alarme> findAlarmeEntities(int maxResults, int firstResult) {
        return findAlarmeEntities(false, maxResults, firstResult);
    }

    private List<Alarme> findAlarmeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alarme.class));
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

    public Alarme findAlarme(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alarme.class, id);
        } finally {
            em.close();
        }
    }
    
    public Alarme findAlarmeByLastDataRecebimento(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Alarme a "
                    + "WHERE a.idcliente.idcliente = " + cliente.getIdcliente() 
                    + " order by a.dataRecebimento DESC");
                    //+ " order by a.dataRecebimento DESC LIMIT 1");
                    //+ "a.idprotocolo.idprotocolo = 166 order by a.dataRecebimento DESC");

            q.setMaxResults(1);

            return (Alarme) q.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }    

    public Alarme findAlarmeByTestLine(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Alarme a "
                    + "WHERE a.idcliente.idcliente = " + cliente.getIdcliente() + " AND "                     
                    + "a.idprotocolo.idprotocolo = 166 order by a.dataRecebimento DESC");

            q.setMaxResults(1);

            return (Alarme) q.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Alarme findAlarmeArmado(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Alarme a "
                    //+ "JOIN protocolo p "
                    + "WHERE a.idcliente.idcliente = " + cliente.getIdcliente() + " AND "
                    + "a.idprotocolo.descricao LIKE 'ARMADO%' AND a.idprotocolo.descricao NOT LIKE 'ARMADO FORA D%' "
                    + "order by a.dataRecebimento DESC");

            q.setMaxResults(1);

            return (Alarme) q.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public Alarme findAlarmeDesarmado(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Alarme a "
                    //+ "JOIN protocolo p "
                    + "WHERE a.idcliente.idcliente = " + cliente.getIdcliente() + " AND "
                    + "a.idprotocolo.descricao LIKE 'DESARMADO%' AND a.idprotocolo.descricao NOT LIKE 'DESARMADO FORA D%' "
                    + "order by a.dataRecebimento DESC");

            q.setMaxResults(1);

            return (Alarme) q.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getAlarmeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alarme> rt = cq.from(Alarme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
