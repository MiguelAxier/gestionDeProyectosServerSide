/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.rest;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxServerSide.ejb.ClienteManagerEJBLocal;
import javafxServerSide.entity.Cliente;
import javafxServerSide.exception.AltaException;
import javafxServerSide.exception.BajaException;
import javafxServerSide.exception.ConsultaException;
import javafxServerSide.exception.ModificarException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miguel Axier Lafuente Peñas
 */
@Path("cliente")
public class ClienteREST{
    //Logger
    private static final Logger LOGGER = 
            Logger.getLogger("javafxserverside");
    //inject ejb
    @EJB
    private ClienteManagerEJBLocal ejb;
    /**
     * This method find a customer using NIF to locate him.
     * @param nif id of te customer
     * @return a Cliente object
     */
    @GET
    @Path("{nif}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente getClienteByNif(@PathParam("nif") String nif) {
        Cliente cliente = null;
        try{
            LOGGER.info("ClienteREST: searching customer by nif");
            cliente=ejb.getClienteByNif(nif);
        }catch(ConsultaException e){
            LOGGER.log(Level.SEVERE,"ClienteREST: Error searching customer by nif",e);
        }
        return cliente;
    }
    /**
     * This method obtains all customers.
     * @return A collection with all customers.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <Cliente> getAllClientes() {
        Collection <Cliente> clientes=null;
        try{
            LOGGER.info("ClienteREST: searching all customers");
            clientes=(Collection <Cliente>) ejb.getAllClientes();
        }catch(ConsultaException e){
            LOGGER.log(Level.SEVERE,"ClienteREST: Error searching all customers",e);
        }
        return clientes;
    }
    /**
     * This methd obtains customers searching by name
     * @param nombre name of the customer
     * @return a collection of customers whose name contains what was sent in the parameter
     */
    @GET
    @Path("findNombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <Cliente> getClienteByNombre(@PathParam ("nombre") String nombre){
        Collection <Cliente> clientes = null;
        try{
            LOGGER.info("ClienteManager: searching customers by name");
            clientes =(Collection <Cliente>) ejb.getClienteByNombre(nombre);
        }catch(ConsultaException e){
             LOGGER.log(Level.SEVERE,"ClienteREST: Error searching customers by name",e);
        }
        return clientes;
    }
    /**
     * This method obtains customers searching by email
     * @param email email of the customer.
     * @return A collection of customers whose email contains what was sent in the parameter
     */    
    @GET
    @Path("findEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <Cliente> getClienteByEmail(@PathParam ("email") String email){
        Collection<Cliente> clientes = null;
        try{
            LOGGER.info("ClienteREST: seathing customers by email");
            clientes = ejb.getClienteByEmail(email);
        }catch(ConsultaException e){
            LOGGER.log(Level.SEVERE,"ClienteREST:Error searching customers by email",e);
        }
        return clientes;
    }
    /**
     * This method create a new customers
     * @param cliente A Cliente object containing customer's data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void newCliente(Cliente cliente) {
        try{
            LOGGER.info("ClienteREST: creating a new customer");
            ejb.newCliente(cliente);
        } catch(AltaException e){
            LOGGER.log(Level.SEVERE,"ClienteREST: Error creating a new customer",e);
        }
    }
    /**
     * This method delete customer's data
     * @param nif identifier of the customer to delete.
     */
    @DELETE
    @Path("{nif}")
    public void deleteCliente(@PathParam("nif") String nif) {
        try{
            LOGGER.info("ClienteREST: deleting customer.");
            ejb.deleteCliente(ejb.getClienteByNif(nif));
        } catch (ConsultaException | BajaException e) {
            LOGGER.log(Level.SEVERE,"ClienteREST: ERROR deleting customer",e);
        }
    }
    /**
     * This method update customer's data
     * @param cliente a Cliente object to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateCliente(Cliente cliente) {
        try{
            LOGGER.info("ClienteREST:updating customer");
            ejb.updateCliente(cliente);
        } catch (ModificarException e){
            LOGGER.log(Level.SEVERE,"ClienteREST: Error updating customer",e);    
        }
    }
}
