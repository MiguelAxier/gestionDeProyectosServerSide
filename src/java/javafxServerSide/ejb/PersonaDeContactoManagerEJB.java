/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.ejb;

import java.util.Collection;
import javafxServerSide.entity.PersonaDeContacto;
import javafxServerSide.exception.AltaException;
import javafxServerSide.exception.BajaException;
import javafxServerSide.exception.ConsultaException;
import javafxServerSide.exception.ModificarException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.logging.Logger;

/**
 * This class encapsulates method for consult, modify, create and delete contact persons.
 * @author Miguel Axier Lafuente Peñas
 */
@Stateless
public class PersonaDeContactoManagerEJB implements PersonaDeContactoManagerEJBLocal {
    //Logger
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");

    //Persistence context for using entities
    @PersistenceContext
    private EntityManager em;
    /**
     * This method find a contac person using the id to locate him
     * @param id id of the contact person
     * @return a PersonaDeContacto object
     * @throws ConsultaException if there is an error in the query.
     */
    @Override
    public PersonaDeContacto getContactoById(Integer id) throws ConsultaException {
        LOGGER.info("PersonaDeContactoManager: searching contact persons by id");
        return em.find(PersonaDeContacto.class, id);
    }
    /**
     * This method obtains all contact persons.
     * @return A collection with all contact persons
     * @throws ConsultaException if there is an error in the query.
     */
    @Override
    public Collection<PersonaDeContacto> getAllContactos() throws ConsultaException {
        LOGGER.info("PersonaDeContactoManager: searching all contacts");
        return em.createNamedQuery("findAllContactos").getResultList();
    }
    /**
     * This method obtains contact persons searching by name.
     * @param nombre name of the contact person you want to search
     * @return A collection of contact persons whose name contains what was sent in the parameter 
     * @throws ConsultaException if there is an error in the query.
     */
    @Override
    public Collection<PersonaDeContacto> getContactoByNombre(String nombre) throws ConsultaException {
        LOGGER.info("PersonaDeContactoManager: searching contact persons by name");
        return em.createNamedQuery("findContactoByNombre").setParameter("nombre", nombre).getResultList();
    }
    /**
     * This method obtains contact persons searching by email
     * @param email email of the contact person you want to search
     * @return A collection of contact persons whose email contains what was sent in the parameter
     * @throws ConsultaException if there is an error in the query.
     */
    @Override
    public Collection<PersonaDeContacto> getContactoByEmail(String email) throws ConsultaException {
        LOGGER.info("PersonaDeContactoManager: searching contact persons by email");
        return em.createNamedQuery("findContactoByEmail").setParameter("email", email).getResultList();
    }
    /**
     * This method create a new contact person
     * @param personaDeContacto a PersonaDeContacto objetc containig contact person data
     * @throws AltaException A Exception indicating error during registration processing.
     */
    @Override
    public void newPersonaDeContacto(PersonaDeContacto personaDeContacto) throws AltaException {
        LOGGER.info("PersonaDeContactoManager: creating a new contact person");
        try{
            em.persist(personaDeContacto);
        }catch(Exception e){
            LOGGER.log(Logger.Level.FATAL, "PersonaDeContactoManager: Error creating a contact person",e);
            throw new AltaException();
        }
        LOGGER.info("PersonaDeContactoManager: contact person created correctly.");
    }
    /**
     * This method delete contact person's data
     * @param personaDeContacto A PersonaDeContacto object to delete.
     * @throws BajaException is there was any error during the process
     */
    @Override
    public void deleteContacto(PersonaDeContacto personaDeContacto) throws BajaException{
        LOGGER.info("PersonaDeContactoManager: deleting a contact person");
        try{
            personaDeContacto=em.merge(personaDeContacto);
            em.remove(personaDeContacto);
        }catch(Exception e){
            LOGGER.log(Logger.Level.FATAL, "PersonaDeContactoManager: Error deleting a contact person",e);
            throw new BajaException(e.getMessage());
        }
        LOGGER.info("PersonaDeContactoManager: deleted successfully");
    }
    /**
     * this method update contact person's data
     * @param personaDeContacto A PersonaDeContacto object to update
     * @throws ModificarException if there was any error
     */
    @Override
    public void updateContacto(PersonaDeContacto personaDeContacto) throws ModificarException {
        LOGGER.info("PersonaDeContactoManager: updating a contact person's data");
        try{
            if(!em.contains(personaDeContacto)){
                em.merge(personaDeContacto);
            }
        }catch (Exception e){
            LOGGER.log(Logger.Level.FATAL, "PersonaDeContactoManager: Error updating a contact person's data",e);
            throw new ModificarException(e.getMessage());
        }
        LOGGER.info("ClienteManager: update contact person.");
    }
    
}
