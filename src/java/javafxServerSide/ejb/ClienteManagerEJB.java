/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.ejb;

import java.util.Collection;
import javafxServerSide.entity.Cliente;
import javafxServerSide.exception.AltaException;
import javafxServerSide.exception.BajaException;
import javafxServerSide.exception.ConsultaException;
import javafxServerSide.exception.ModificarException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.logging.Logger;

/**
 * This class encapsulates methos for consult, modify, create and delete customers
 * @author Miguel Axier Lafuente Peñas
 */
@Stateless
public class ClienteManagerEJB implements ClienteManagerEJBLocal{
    //Logger
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    //Persistence context for using entities
    @PersistenceContext
    private EntityManager em;
    /**
     * This method find a customer using NIF to locate him.
     * @param nif id of te customer
     * @return a Cliente object
     * @throws ConsultaException if there is an error in the query
     */
    @Override
    public Cliente getClienteByNif(String nif) throws ConsultaException {
        LOGGER.info("ClienteManager: searching customers by nif");
        return em.find(Cliente.class, nif);
    }
    /**
     * This method obtains all customers.
     * @return A collection with all customers.
     * @throws ConsultaException is there is an error in the query
     */
    @Override
    public Collection <Cliente> getAllClientes() throws ConsultaException {
        LOGGER.info("ClienteManager: searching all customers");
        return em.createNamedQuery("findAllClientes").getResultList();
    }
    /**
     * This methd obtains customers searching by name
     * @param nombre name of the customer
     * @return a collection of customers whose name contains what was sent in the parameter
     * @throws ConsultaException if there is an error in the query
     */
    @Override
    public Collection getClienteByNombre(String nombre) throws ConsultaException{
        LOGGER.info("ClienteManager: searching customers by name");
        return em.createNamedQuery("findClienteByNombre").setParameter("nombre",nombre).getResultList();
    }
    /**
     * This method obtains customers searching by email
     * @param email email of the customer.
     * @return A collection of customers whose email contains what was sent in the parameter
     * @throws ConsultaException id there is an error in the query.
     */
    @Override
    public Collection <Cliente> getClienteByEmail(String email) throws ConsultaException {
        LOGGER.info("ClienteManager: searching customers by email");
        return (Collection<Cliente>) (Cliente) em.createNamedQuery("findClienteByEmail").setParameter("email",email).getResultList();
    }
    
    /**
     * This method create a new customers
     * @param cliente A Cliente object containing customer's data
     * @throws AltaException A exception indicating error during registration processing.
     */
    @Override
    public void newCliente(Cliente cliente) throws AltaException {
        LOGGER.info("ClienteManager: Creating a new customer");
        try{
            em.persist(cliente);
        } catch (Exception e){
            LOGGER.log(Logger.Level.FATAL, "ClienteManager: Error creating a customer",e);
            throw new AltaException(e.getMessage());
        }
        LOGGER.info("ClienteManager: customer created correctly.");
    }
    /**
     * This method delete customer's data
     * @param cliente A Cliente object to delete.
     * @throws BajaException is there was any error during the process
     */
    @Override
    public void deleteCliente(Cliente cliente) throws BajaException {
        LOGGER.info("ClienteManager: deleting customer");
        try{
            cliente = em.merge(cliente);
            em.remove(cliente);
        }catch(Exception e){
            LOGGER.log(Logger.Level.FATAL, "ClienteManager: Error deleting a customer",e);
            throw new BajaException(e.getMessage());
        }
        LOGGER.info("ClienteManager: deleted successfully");
    }
    /**
     * This method update customer's data
     * @param cliente a Cliente object to update
     * @throws ModificarException  if there was any error
     */
    @Override
    public void updateCliente(Cliente cliente) throws ModificarException {
       LOGGER.info("ClienteManager: updating a customer's data");
       try{
           if(!em.contains(cliente)){
                em.merge(cliente);
           }
       }catch(Exception e){
           LOGGER.log(Logger.Level.FATAL, "ClienteManager: Error updating customer's data",e);
           throw new ModificarException(e.getMessage());
       }
       LOGGER.info("ClienteManager: update customer.");
    }    
}
