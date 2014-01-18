/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Authorities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Users;

/**
 *
 * @author Dvr
 */
public class AuthoritiesJpaController implements Serializable {

    public AuthoritiesJpaController() {
    }

    public AuthoritiesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Authorities authorities) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users username = authorities.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                authorities.setUsername(username);
            }
            em.persist(authorities);
            if (username != null) {
                username.getAuthoritiesList().add(authorities);
                username = em.merge(username);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Authorities authorities) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Authorities persistentAuthorities = em.find(Authorities.class, authorities.getIdauthorities());
            Users usernameOld = persistentAuthorities.getUsername();
            Users usernameNew = authorities.getUsername();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                authorities.setUsername(usernameNew);
            }
            authorities = em.merge(authorities);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getAuthoritiesList().remove(authorities);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getAuthoritiesList().add(authorities);
                usernameNew = em.merge(usernameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = authorities.getIdauthorities();
                if (findAuthorities(id) == null) {
                    throw new NonexistentEntityException("The authorities with id " + id + " no longer exists.");
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
            Authorities authorities;
            try {
                authorities = em.getReference(Authorities.class, id);
                authorities.getIdauthorities();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The authorities with id " + id + " no longer exists.", enfe);
            }
            Users username = authorities.getUsername();
            if (username != null) {
                username.getAuthoritiesList().remove(authorities);
                username = em.merge(username);
            }
            em.remove(authorities);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Authorities> findAuthoritiesEntities() {
        return findAuthoritiesEntities(true, -1, -1);
    }

    public List<Authorities> findAuthoritiesEntities(int maxResults, int firstResult) {
        return findAuthoritiesEntities(false, maxResults, firstResult);
    }

    private List<Authorities> findAuthoritiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Authorities.class));
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

    public Authorities findAuthorities(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Authorities.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthoritiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Authorities> rt = cq.from(Authorities.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
