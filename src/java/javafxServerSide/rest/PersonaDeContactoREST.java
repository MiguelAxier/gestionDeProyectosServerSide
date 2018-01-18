/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.rest;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ejb.EJB;
import javafxServerSide.ejb.PersonaDeContactoManagerEJBLocal;
import javafxServerSide.entity.PersonaDeContacto;
import javafxServerSide.exception.AltaException;
import javafxServerSide.exception.BajaException;
import javafxServerSide.exception.ConsultaException;
import javafxServerSide.exception.ModificarException;

/**
 * 
 * @author Miguel Axier Lafuente Peñas
 */

@Path("personadecontacto")
public class PersonaDeContactoREST{
    //Logger
    private static final Logger LOGGER = 
            Logger.getLogger("javafxserverside");
    //inject ejb
    @EJB
    private PersonaDeContactoManagerEJBLocal ejb;
    
    /**
     * This method find a contac person using the id to locate him
     * @param id id of the contact person
     * @return a PersonaDeContacto object
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PersonaDeContacto getContactoById(@PathParam("id") Integer id) {
        PersonaDeContacto contacto = null;
        try {
            LOGGER.info("PersonaDeContactoREST: searching contact persons by id");
            contacto = ejb.getContactoById(id);
        } catch (ConsultaException e) {
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: ERROR searching contact persons by id",e);
        }
        return contacto;
    }
    /**
    * This method obtains all contact persons.
    * @return A collection with all contact persons
    */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <PersonaDeContacto> getAllContactos() {
        Collection <PersonaDeContacto> contactos = null;
        try{
            LOGGER.info("PersonaDeContactoREST: searching all contacts.");
            contactos = ejb.getAllContactos();
        } catch (ConsultaException e) {
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: ERROR searching all contacts",e);
        }
        return contactos;
    }
    /**
     * This method obtains contact persons searching by name.
     * @param nombre name of the contact person you want to search
     * @return A collection of contact persons whose name contains what was sent in the parameter
     */
    @GET
    @Path("findNombre/{nombre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <PersonaDeContacto> getContactoByNombre(@PathParam("nombre") String nombre){
        Collection <PersonaDeContacto> contactos = null;
        try{
            LOGGER.info("PersonaDeContactoREST: searching contact persons by name.");
            contactos = ejb.getContactoByNombre(nombre);
        }catch(ConsultaException e){
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: ERROR searching contact persons by name",e);
        }
        return contactos;
    }
    /**
     * This method obtains contact persons searching by email
     * @param email email of the contact person you want to search
     * @return A collection of contact persons whose email contains what was sent in the paramete
     */
    @GET
    @Path("findEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection <PersonaDeContacto> getContactoByEmail(@PathParam("email") String email){
        Collection <PersonaDeContacto> contactos = null;
        try{
            LOGGER.info("PersonaDeContactoREST: searching contact persons by email.");
            contactos = ejb.getContactoByEmail(email);
        }catch(ConsultaException e){
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: ERROR searching contact persons by email",e);
        }
        return contactos;
    }
    /**
     * This method create a new contact person
     * @param personaDeContacto a PersonaDeContacto objetc containig contact person data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void newPersonaDeContacto(PersonaDeContacto personaDeContacto) {
        try{
            LOGGER.info("PersonaDeContactoREST: creating a new contact person");
            ejb.newPersonaDeContacto(personaDeContacto);
        } catch(AltaException e){
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: Error creating a new contact person",e);
        }
    }
    /**
     * This method delete contact person's data
     * @param id identifier of the contact person to delete.
     */
    @DELETE
    @Path("{id}")
    public void deleteContacto(@PathParam("id") Integer id) {
        try{
            LOGGER.info("PersonaDeContactoREST: deleting a contact person.");
            ejb.deleteContacto(ejb.getContactoById(id));
        } catch (ConsultaException | BajaException e) {
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: ERROR deleting a contactperson",e);
        }
    }
    /**
     * this method update contact person's data
     * @param personaDeContacto A PersonaDeContacto object to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateContacto(PersonaDeContacto personaDeContacto) {
        try{
            LOGGER.info("PersonaDeContactoREST:updating a contact person's data");
            ejb.updateContacto(personaDeContacto);
        } catch (ModificarException e){
            LOGGER.log(Level.SEVERE,"PersonaDeContactoREST: Error updating a contact person's data",e);    
        }
    }
}
