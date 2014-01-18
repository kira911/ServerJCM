/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.controller.exceptions.PreexistingEntityException;
import br.com.fox.db.Users;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Authorities;
import java.util.ArrayList;
import java.util.List;
import br.com.fox.db.SinalRouter;
import br.com.fox.db.Alarme;

/**
 *
 * @author Dvr
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController() {
    }

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) throws PreexistingEntityException, Exception {
        if (users.getAuthoritiesList() == null) {
            users.setAuthoritiesList(new ArrayList<Authorities>());
        }
        if (users.getSinalRouterList() == null) {
            users.setSinalRouterList(new ArrayList<SinalRouter>());
        }
        if (users.getAlarmeList() == null) {
            users.setAlarmeList(new ArrayList<Alarme>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Authorities> attachedAuthoritiesList = new ArrayList<Authorities>();
            for (Authorities authoritiesListAuthoritiesToAttach : users.getAuthoritiesList()) {
                authoritiesListAuthoritiesToAttach = em.getReference(authoritiesListAuthoritiesToAttach.getClass(), authoritiesListAuthoritiesToAttach.getIdauthorities());
                attachedAuthoritiesList.add(authoritiesListAuthoritiesToAttach);
            }
            users.setAuthoritiesList(attachedAuthoritiesList);
            List<SinalRouter> attachedSinalRouterList = new ArrayList<SinalRouter>();
            for (SinalRouter sinalRouterListSinalRouterToAttach : users.getSinalRouterList()) {
                sinalRouterListSinalRouterToAttach = em.getReference(sinalRouterListSinalRouterToAttach.getClass(), sinalRouterListSinalRouterToAttach.getIdsinalRouter());
                attachedSinalRouterList.add(sinalRouterListSinalRouterToAttach);
            }
            users.setSinalRouterList(attachedSinalRouterList);
            List<Alarme> attachedAlarmeList = new ArrayList<Alarme>();
            for (Alarme alarmeListAlarmeToAttach : users.getAlarmeList()) {
                alarmeListAlarmeToAttach = em.getReference(alarmeListAlarmeToAttach.getClass(), alarmeListAlarmeToAttach.getIdalarme());
                attachedAlarmeList.add(alarmeListAlarmeToAttach);
            }
            users.setAlarmeList(attachedAlarmeList);
            em.persist(users);
            for (Authorities authoritiesListAuthorities : users.getAuthoritiesList()) {
                Users oldUsernameOfAuthoritiesListAuthorities = authoritiesListAuthorities.getUsername();
                authoritiesListAuthorities.setUsername(users);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
                if (oldUsernameOfAuthoritiesListAuthorities != null) {
                    oldUsernameOfAuthoritiesListAuthorities.getAuthoritiesList().remove(authoritiesListAuthorities);
                    oldUsernameOfAuthoritiesListAuthorities = em.merge(oldUsernameOfAuthoritiesListAuthorities);
                }
            }
            for (SinalRouter sinalRouterListSinalRouter : users.getSinalRouterList()) {
                Users oldUsernameOfSinalRouterListSinalRouter = sinalRouterListSinalRouter.getUsername();
                sinalRouterListSinalRouter.setUsername(users);
                sinalRouterListSinalRouter = em.merge(sinalRouterListSinalRouter);
                if (oldUsernameOfSinalRouterListSinalRouter != null) {
                    oldUsernameOfSinalRouterListSinalRouter.getSinalRouterList().remove(sinalRouterListSinalRouter);
                    oldUsernameOfSinalRouterListSinalRouter = em.merge(oldUsernameOfSinalRouterListSinalRouter);
                }
            }
            for (Alarme alarmeListAlarme : users.getAlarmeList()) {
                Users oldUsernameOfAlarmeListAlarme = alarmeListAlarme.getUsername();
                alarmeListAlarme.setUsername(users);
                alarmeListAlarme = em.merge(alarmeListAlarme);
                if (oldUsernameOfAlarmeListAlarme != null) {
                    oldUsernameOfAlarmeListAlarme.getAlarmeList().remove(alarmeListAlarme);
                    oldUsernameOfAlarmeListAlarme = em.merge(oldUsernameOfAlarmeListAlarme);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsers(users.getUsername()) != null) {
                throw new PreexistingEntityException("Users " + users + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUsername());
            List<Authorities> authoritiesListOld = persistentUsers.getAuthoritiesList();
            List<Authorities> authoritiesListNew = users.getAuthoritiesList();
            List<SinalRouter> sinalRouterListOld = persistentUsers.getSinalRouterList();
            List<SinalRouter> sinalRouterListNew = users.getSinalRouterList();
            List<Alarme> alarmeListOld = persistentUsers.getAlarmeList();
            List<Alarme> alarmeListNew = users.getAlarmeList();
            List<Authorities> attachedAuthoritiesListNew = new ArrayList<Authorities>();
            for (Authorities authoritiesListNewAuthoritiesToAttach : authoritiesListNew) {
                authoritiesListNewAuthoritiesToAttach = em.getReference(authoritiesListNewAuthoritiesToAttach.getClass(), authoritiesListNewAuthoritiesToAttach.getIdauthorities());
                attachedAuthoritiesListNew.add(authoritiesListNewAuthoritiesToAttach);
            }
            authoritiesListNew = attachedAuthoritiesListNew;
            users.setAuthoritiesList(authoritiesListNew);
            List<SinalRouter> attachedSinalRouterListNew = new ArrayList<SinalRouter>();
            for (SinalRouter sinalRouterListNewSinalRouterToAttach : sinalRouterListNew) {
                sinalRouterListNewSinalRouterToAttach = em.getReference(sinalRouterListNewSinalRouterToAttach.getClass(), sinalRouterListNewSinalRouterToAttach.getIdsinalRouter());
                attachedSinalRouterListNew.add(sinalRouterListNewSinalRouterToAttach);
            }
            sinalRouterListNew = attachedSinalRouterListNew;
            users.setSinalRouterList(sinalRouterListNew);
            List<Alarme> attachedAlarmeListNew = new ArrayList<Alarme>();
            for (Alarme alarmeListNewAlarmeToAttach : alarmeListNew) {
                alarmeListNewAlarmeToAttach = em.getReference(alarmeListNewAlarmeToAttach.getClass(), alarmeListNewAlarmeToAttach.getIdalarme());
                attachedAlarmeListNew.add(alarmeListNewAlarmeToAttach);
            }
            alarmeListNew = attachedAlarmeListNew;
            users.setAlarmeList(alarmeListNew);
            users = em.merge(users);
            for (Authorities authoritiesListOldAuthorities : authoritiesListOld) {
                if (!authoritiesListNew.contains(authoritiesListOldAuthorities)) {
                    authoritiesListOldAuthorities.setUsername(null);
                    authoritiesListOldAuthorities = em.merge(authoritiesListOldAuthorities);
                }
            }
            for (Authorities authoritiesListNewAuthorities : authoritiesListNew) {
                if (!authoritiesListOld.contains(authoritiesListNewAuthorities)) {
                    Users oldUsernameOfAuthoritiesListNewAuthorities = authoritiesListNewAuthorities.getUsername();
                    authoritiesListNewAuthorities.setUsername(users);
                    authoritiesListNewAuthorities = em.merge(authoritiesListNewAuthorities);
                    if (oldUsernameOfAuthoritiesListNewAuthorities != null && !oldUsernameOfAuthoritiesListNewAuthorities.equals(users)) {
                        oldUsernameOfAuthoritiesListNewAuthorities.getAuthoritiesList().remove(authoritiesListNewAuthorities);
                        oldUsernameOfAuthoritiesListNewAuthorities = em.merge(oldUsernameOfAuthoritiesListNewAuthorities);
                    }
                }
            }
            for (SinalRouter sinalRouterListOldSinalRouter : sinalRouterListOld) {
                if (!sinalRouterListNew.contains(sinalRouterListOldSinalRouter)) {
                    sinalRouterListOldSinalRouter.setUsername(null);
                    sinalRouterListOldSinalRouter = em.merge(sinalRouterListOldSinalRouter);
                }
            }
            for (SinalRouter sinalRouterListNewSinalRouter : sinalRouterListNew) {
                if (!sinalRouterListOld.contains(sinalRouterListNewSinalRouter)) {
                    Users oldUsernameOfSinalRouterListNewSinalRouter = sinalRouterListNewSinalRouter.getUsername();
                    sinalRouterListNewSinalRouter.setUsername(users);
                    sinalRouterListNewSinalRouter = em.merge(sinalRouterListNewSinalRouter);
                    if (oldUsernameOfSinalRouterListNewSinalRouter != null && !oldUsernameOfSinalRouterListNewSinalRouter.equals(users)) {
                        oldUsernameOfSinalRouterListNewSinalRouter.getSinalRouterList().remove(sinalRouterListNewSinalRouter);
                        oldUsernameOfSinalRouterListNewSinalRouter = em.merge(oldUsernameOfSinalRouterListNewSinalRouter);
                    }
                }
            }
            for (Alarme alarmeListOldAlarme : alarmeListOld) {
                if (!alarmeListNew.contains(alarmeListOldAlarme)) {
                    alarmeListOldAlarme.setUsername(null);
                    alarmeListOldAlarme = em.merge(alarmeListOldAlarme);
                }
            }
            for (Alarme alarmeListNewAlarme : alarmeListNew) {
                if (!alarmeListOld.contains(alarmeListNewAlarme)) {
                    Users oldUsernameOfAlarmeListNewAlarme = alarmeListNewAlarme.getUsername();
                    alarmeListNewAlarme.setUsername(users);
                    alarmeListNewAlarme = em.merge(alarmeListNewAlarme);
                    if (oldUsernameOfAlarmeListNewAlarme != null && !oldUsernameOfAlarmeListNewAlarme.equals(users)) {
                        oldUsernameOfAlarmeListNewAlarme.getAlarmeList().remove(alarmeListNewAlarme);
                        oldUsernameOfAlarmeListNewAlarme = em.merge(oldUsernameOfAlarmeListNewAlarme);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = users.getUsername();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<Authorities> authoritiesList = users.getAuthoritiesList();
            for (Authorities authoritiesListAuthorities : authoritiesList) {
                authoritiesListAuthorities.setUsername(null);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
            }
            List<SinalRouter> sinalRouterList = users.getSinalRouterList();
            for (SinalRouter sinalRouterListSinalRouter : sinalRouterList) {
                sinalRouterListSinalRouter.setUsername(null);
                sinalRouterListSinalRouter = em.merge(sinalRouterListSinalRouter);
            }
            List<Alarme> alarmeList = users.getAlarmeList();
            for (Alarme alarmeListAlarme : alarmeList) {
                alarmeListAlarme.setUsername(null);
                alarmeListAlarme = em.merge(alarmeListAlarme);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
