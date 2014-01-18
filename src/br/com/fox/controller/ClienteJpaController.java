/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller;

import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Cliente;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Horario;
import java.util.ArrayList;
import java.util.List;
import br.com.fox.db.Contato;
import br.com.fox.db.Usuario;
import br.com.fox.db.SinalRouter;
import br.com.fox.db.Zona;
import br.com.fox.db.Alarme;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Dvr
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
    }

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ServerJCMPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getHorarioList() == null) {
            cliente.setHorarioList(new ArrayList<Horario>());
        }
        if (cliente.getContatoList() == null) {
            cliente.setContatoList(new ArrayList<Contato>());
        }
        if (cliente.getUsuarioList() == null) {
            cliente.setUsuarioList(new ArrayList<Usuario>());
        }
        if (cliente.getSinalRouterList() == null) {
            cliente.setSinalRouterList(new ArrayList<SinalRouter>());
        }
        if (cliente.getZonaList() == null) {
            cliente.setZonaList(new ArrayList<Zona>());
        }
        if (cliente.getAlarmeList() == null) {
            cliente.setAlarmeList(new ArrayList<Alarme>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Horario> attachedHorarioList = new ArrayList<Horario>();
            for (Horario horarioListHorarioToAttach : cliente.getHorarioList()) {
                horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getIdhorario());
                attachedHorarioList.add(horarioListHorarioToAttach);
            }
            cliente.setHorarioList(attachedHorarioList);
            List<Contato> attachedContatoList = new ArrayList<Contato>();
            for (Contato contatoListContatoToAttach : cliente.getContatoList()) {
                contatoListContatoToAttach = em.getReference(contatoListContatoToAttach.getClass(), contatoListContatoToAttach.getIdcontatos());
                attachedContatoList.add(contatoListContatoToAttach);
            }
            cliente.setContatoList(attachedContatoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : cliente.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdusuarios());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            cliente.setUsuarioList(attachedUsuarioList);
            List<SinalRouter> attachedSinalRouterList = new ArrayList<SinalRouter>();
            for (SinalRouter sinalRouterListSinalRouterToAttach : cliente.getSinalRouterList()) {
                sinalRouterListSinalRouterToAttach = em.getReference(sinalRouterListSinalRouterToAttach.getClass(), sinalRouterListSinalRouterToAttach.getIdsinalRouter());
                attachedSinalRouterList.add(sinalRouterListSinalRouterToAttach);
            }
            cliente.setSinalRouterList(attachedSinalRouterList);
            List<Zona> attachedZonaList = new ArrayList<Zona>();
            for (Zona zonaListZonaToAttach : cliente.getZonaList()) {
                zonaListZonaToAttach = em.getReference(zonaListZonaToAttach.getClass(), zonaListZonaToAttach.getIdzonas());
                attachedZonaList.add(zonaListZonaToAttach);
            }
            cliente.setZonaList(attachedZonaList);
            List<Alarme> attachedAlarmeList = new ArrayList<Alarme>();
            for (Alarme alarmeListAlarmeToAttach : cliente.getAlarmeList()) {
                alarmeListAlarmeToAttach = em.getReference(alarmeListAlarmeToAttach.getClass(), alarmeListAlarmeToAttach.getIdalarme());
                attachedAlarmeList.add(alarmeListAlarmeToAttach);
            }
            cliente.setAlarmeList(attachedAlarmeList);
            em.persist(cliente);
            for (Horario horarioListHorario : cliente.getHorarioList()) {
                Cliente oldIdclienteOfHorarioListHorario = horarioListHorario.getIdcliente();
                horarioListHorario.setIdcliente(cliente);
                horarioListHorario = em.merge(horarioListHorario);
                if (oldIdclienteOfHorarioListHorario != null) {
                    oldIdclienteOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
                    oldIdclienteOfHorarioListHorario = em.merge(oldIdclienteOfHorarioListHorario);
                }
            }
            for (Contato contatoListContato : cliente.getContatoList()) {
                Cliente oldIdclienteOfContatoListContato = contatoListContato.getIdcliente();
                contatoListContato.setIdcliente(cliente);
                contatoListContato = em.merge(contatoListContato);
                if (oldIdclienteOfContatoListContato != null) {
                    oldIdclienteOfContatoListContato.getContatoList().remove(contatoListContato);
                    oldIdclienteOfContatoListContato = em.merge(oldIdclienteOfContatoListContato);
                }
            }
            for (Usuario usuarioListUsuario : cliente.getUsuarioList()) {
                Cliente oldIdclienteOfUsuarioListUsuario = usuarioListUsuario.getIdcliente();
                usuarioListUsuario.setIdcliente(cliente);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdclienteOfUsuarioListUsuario != null) {
                    oldIdclienteOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdclienteOfUsuarioListUsuario = em.merge(oldIdclienteOfUsuarioListUsuario);
                }
            }
            for (SinalRouter sinalRouterListSinalRouter : cliente.getSinalRouterList()) {
                Cliente oldCodCliOfSinalRouterListSinalRouter = sinalRouterListSinalRouter.getCodCli();
                sinalRouterListSinalRouter.setCodCli(cliente);
                sinalRouterListSinalRouter = em.merge(sinalRouterListSinalRouter);
                if (oldCodCliOfSinalRouterListSinalRouter != null) {
                    oldCodCliOfSinalRouterListSinalRouter.getSinalRouterList().remove(sinalRouterListSinalRouter);
                    oldCodCliOfSinalRouterListSinalRouter = em.merge(oldCodCliOfSinalRouterListSinalRouter);
                }
            }
            for (Zona zonaListZona : cliente.getZonaList()) {
                Cliente oldIdclienteOfZonaListZona = zonaListZona.getIdcliente();
                zonaListZona.setIdcliente(cliente);
                zonaListZona = em.merge(zonaListZona);
                if (oldIdclienteOfZonaListZona != null) {
                    oldIdclienteOfZonaListZona.getZonaList().remove(zonaListZona);
                    oldIdclienteOfZonaListZona = em.merge(oldIdclienteOfZonaListZona);
                }
            }
            for (Alarme alarmeListAlarme : cliente.getAlarmeList()) {
                Cliente oldIdclienteOfAlarmeListAlarme = alarmeListAlarme.getIdcliente();
                alarmeListAlarme.setIdcliente(cliente);
                alarmeListAlarme = em.merge(alarmeListAlarme);
                if (oldIdclienteOfAlarmeListAlarme != null) {
                    oldIdclienteOfAlarmeListAlarme.getAlarmeList().remove(alarmeListAlarme);
                    oldIdclienteOfAlarmeListAlarme = em.merge(oldIdclienteOfAlarmeListAlarme);
                }
            }
            if( cliente.getAtivo() == null ) 
                cliente.setAtivo("true");
            
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            List<Horario> horarioListOld = persistentCliente.getHorarioList();
            List<Horario> horarioListNew = cliente.getHorarioList();
            List<Contato> contatoListOld = persistentCliente.getContatoList();
            List<Contato> contatoListNew = cliente.getContatoList();
            List<Usuario> usuarioListOld = persistentCliente.getUsuarioList();
            List<Usuario> usuarioListNew = cliente.getUsuarioList();
            List<SinalRouter> sinalRouterListOld = persistentCliente.getSinalRouterList();
            List<SinalRouter> sinalRouterListNew = cliente.getSinalRouterList();
            List<Zona> zonaListOld = persistentCliente.getZonaList();
            List<Zona> zonaListNew = cliente.getZonaList();
            List<Alarme> alarmeListOld = persistentCliente.getAlarmeList();
            List<Alarme> alarmeListNew = cliente.getAlarmeList();
            List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
            for (Horario horarioListNewHorarioToAttach : horarioListNew) {
                horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getIdhorario());
                attachedHorarioListNew.add(horarioListNewHorarioToAttach);
            }
            horarioListNew = attachedHorarioListNew;
            cliente.setHorarioList(horarioListNew);
            List<Contato> attachedContatoListNew = new ArrayList<Contato>();
            for (Contato contatoListNewContatoToAttach : contatoListNew) {
                contatoListNewContatoToAttach = em.getReference(contatoListNewContatoToAttach.getClass(), contatoListNewContatoToAttach.getIdcontatos());
                attachedContatoListNew.add(contatoListNewContatoToAttach);
            }
            contatoListNew = attachedContatoListNew;
            cliente.setContatoList(contatoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdusuarios());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            cliente.setUsuarioList(usuarioListNew);
            List<SinalRouter> attachedSinalRouterListNew = new ArrayList<SinalRouter>();
            for (SinalRouter sinalRouterListNewSinalRouterToAttach : sinalRouterListNew) {
                sinalRouterListNewSinalRouterToAttach = em.getReference(sinalRouterListNewSinalRouterToAttach.getClass(), sinalRouterListNewSinalRouterToAttach.getIdsinalRouter());
                attachedSinalRouterListNew.add(sinalRouterListNewSinalRouterToAttach);
            }
            sinalRouterListNew = attachedSinalRouterListNew;
            cliente.setSinalRouterList(sinalRouterListNew);
            List<Zona> attachedZonaListNew = new ArrayList<Zona>();
            for (Zona zonaListNewZonaToAttach : zonaListNew) {
                zonaListNewZonaToAttach = em.getReference(zonaListNewZonaToAttach.getClass(), zonaListNewZonaToAttach.getIdzonas());
                attachedZonaListNew.add(zonaListNewZonaToAttach);
            }
            zonaListNew = attachedZonaListNew;
            cliente.setZonaList(zonaListNew);
            List<Alarme> attachedAlarmeListNew = new ArrayList<Alarme>();
            for (Alarme alarmeListNewAlarmeToAttach : alarmeListNew) {
                alarmeListNewAlarmeToAttach = em.getReference(alarmeListNewAlarmeToAttach.getClass(), alarmeListNewAlarmeToAttach.getIdalarme());
                attachedAlarmeListNew.add(alarmeListNewAlarmeToAttach);
            }
            alarmeListNew = attachedAlarmeListNew;
            cliente.setAlarmeList(alarmeListNew);
            cliente = em.merge(cliente);
            for (Horario horarioListOldHorario : horarioListOld) {
                if (!horarioListNew.contains(horarioListOldHorario)) {
                    horarioListOldHorario.setIdcliente(null);
                    horarioListOldHorario = em.merge(horarioListOldHorario);
                }
            }
            for (Horario horarioListNewHorario : horarioListNew) {
                if (!horarioListOld.contains(horarioListNewHorario)) {
                    Cliente oldIdclienteOfHorarioListNewHorario = horarioListNewHorario.getIdcliente();
                    horarioListNewHorario.setIdcliente(cliente);
                    horarioListNewHorario = em.merge(horarioListNewHorario);
                    if (oldIdclienteOfHorarioListNewHorario != null && !oldIdclienteOfHorarioListNewHorario.equals(cliente)) {
                        oldIdclienteOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
                        oldIdclienteOfHorarioListNewHorario = em.merge(oldIdclienteOfHorarioListNewHorario);
                    }
                }
            }
            for (Contato contatoListOldContato : contatoListOld) {
                if (!contatoListNew.contains(contatoListOldContato)) {
                    contatoListOldContato.setIdcliente(null);
                    contatoListOldContato = em.merge(contatoListOldContato);
                }
            }
            for (Contato contatoListNewContato : contatoListNew) {
                if (!contatoListOld.contains(contatoListNewContato)) {
                    Cliente oldIdclienteOfContatoListNewContato = contatoListNewContato.getIdcliente();
                    contatoListNewContato.setIdcliente(cliente);
                    contatoListNewContato = em.merge(contatoListNewContato);
                    if (oldIdclienteOfContatoListNewContato != null && !oldIdclienteOfContatoListNewContato.equals(cliente)) {
                        oldIdclienteOfContatoListNewContato.getContatoList().remove(contatoListNewContato);
                        oldIdclienteOfContatoListNewContato = em.merge(oldIdclienteOfContatoListNewContato);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setIdcliente(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Cliente oldIdclienteOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdcliente();
                    usuarioListNewUsuario.setIdcliente(cliente);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdclienteOfUsuarioListNewUsuario != null && !oldIdclienteOfUsuarioListNewUsuario.equals(cliente)) {
                        oldIdclienteOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdclienteOfUsuarioListNewUsuario = em.merge(oldIdclienteOfUsuarioListNewUsuario);
                    }
                }
            }
            for (SinalRouter sinalRouterListOldSinalRouter : sinalRouterListOld) {
                if (!sinalRouterListNew.contains(sinalRouterListOldSinalRouter)) {
                    sinalRouterListOldSinalRouter.setCodCli(null);
                    sinalRouterListOldSinalRouter = em.merge(sinalRouterListOldSinalRouter);
                }
            }
            for (SinalRouter sinalRouterListNewSinalRouter : sinalRouterListNew) {
                if (!sinalRouterListOld.contains(sinalRouterListNewSinalRouter)) {
                    Cliente oldCodCliOfSinalRouterListNewSinalRouter = sinalRouterListNewSinalRouter.getCodCli();
                    sinalRouterListNewSinalRouter.setCodCli(cliente);
                    sinalRouterListNewSinalRouter = em.merge(sinalRouterListNewSinalRouter);
                    if (oldCodCliOfSinalRouterListNewSinalRouter != null && !oldCodCliOfSinalRouterListNewSinalRouter.equals(cliente)) {
                        oldCodCliOfSinalRouterListNewSinalRouter.getSinalRouterList().remove(sinalRouterListNewSinalRouter);
                        oldCodCliOfSinalRouterListNewSinalRouter = em.merge(oldCodCliOfSinalRouterListNewSinalRouter);
                    }
                }
            }
            for (Zona zonaListOldZona : zonaListOld) {
                if (!zonaListNew.contains(zonaListOldZona)) {
                    zonaListOldZona.setIdcliente(null);
                    zonaListOldZona = em.merge(zonaListOldZona);
                }
            }
            for (Zona zonaListNewZona : zonaListNew) {
                if (!zonaListOld.contains(zonaListNewZona)) {
                    Cliente oldIdclienteOfZonaListNewZona = zonaListNewZona.getIdcliente();
                    zonaListNewZona.setIdcliente(cliente);
                    zonaListNewZona = em.merge(zonaListNewZona);
                    if (oldIdclienteOfZonaListNewZona != null && !oldIdclienteOfZonaListNewZona.equals(cliente)) {
                        oldIdclienteOfZonaListNewZona.getZonaList().remove(zonaListNewZona);
                        oldIdclienteOfZonaListNewZona = em.merge(oldIdclienteOfZonaListNewZona);
                    }
                }
            }
            for (Alarme alarmeListOldAlarme : alarmeListOld) {
                if (!alarmeListNew.contains(alarmeListOldAlarme)) {
                    alarmeListOldAlarme.setIdcliente(null);
                    alarmeListOldAlarme = em.merge(alarmeListOldAlarme);
                }
            }
            for (Alarme alarmeListNewAlarme : alarmeListNew) {
                if (!alarmeListOld.contains(alarmeListNewAlarme)) {
                    Cliente oldIdclienteOfAlarmeListNewAlarme = alarmeListNewAlarme.getIdcliente();
                    alarmeListNewAlarme.setIdcliente(cliente);
                    alarmeListNewAlarme = em.merge(alarmeListNewAlarme);
                    if (oldIdclienteOfAlarmeListNewAlarme != null && !oldIdclienteOfAlarmeListNewAlarme.equals(cliente)) {
                        oldIdclienteOfAlarmeListNewAlarme.getAlarmeList().remove(alarmeListNewAlarme);
                        oldIdclienteOfAlarmeListNewAlarme = em.merge(oldIdclienteOfAlarmeListNewAlarme);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Horario> horarioList = cliente.getHorarioList();
            for (Horario horarioListHorario : horarioList) {
                horarioListHorario.setIdcliente(null);
                horarioListHorario = em.merge(horarioListHorario);
            }
            List<Contato> contatoList = cliente.getContatoList();
            for (Contato contatoListContato : contatoList) {
                contatoListContato.setIdcliente(null);
                contatoListContato = em.merge(contatoListContato);
            }
            List<Usuario> usuarioList = cliente.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setIdcliente(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<SinalRouter> sinalRouterList = cliente.getSinalRouterList();
            for (SinalRouter sinalRouterListSinalRouter : sinalRouterList) {
                sinalRouterListSinalRouter.setCodCli(null);
                sinalRouterListSinalRouter = em.merge(sinalRouterListSinalRouter);
            }
            List<Zona> zonaList = cliente.getZonaList();
            for (Zona zonaListZona : zonaList) {
                zonaListZona.setIdcliente(null);
                zonaListZona = em.merge(zonaListZona);
            }
            List<Alarme> alarmeList = cliente.getAlarmeList();
            for (Alarme alarmeListAlarme : alarmeList) {
                alarmeListAlarme.setIdcliente(null);
                alarmeListAlarme = em.merge(alarmeListAlarme);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(String idr) throws Exception {
  
        EntityManager em = getEntityManager();
        try {
       
            Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.codCli = " + idr);
            
            return (Cliente) q.getSingleResult();
                
        } catch(Exception e) {
            throw new Exception("O cliente com ID = " + idr + " não existe.\nErro: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
