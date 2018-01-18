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


/**
 * Local interface for PersonaDeContacto EJB
 * @author Miguel Axier Lafuente Peñas
 */
public interface PersonaDeContactoManagerEJBLocal {
    public void newPersonaDeContacto (PersonaDeContacto personaDeContacto) throws AltaException;
    public void deleteContacto (PersonaDeContacto personaDeContacto) throws BajaException;
    public void updateContacto(PersonaDeContacto personaDeContacto) throws ModificarException;
    public Collection<PersonaDeContacto> getAllContactos() throws ConsultaException;
    public Collection<PersonaDeContacto> getContactoByNombre(String nombre) throws ConsultaException;
    public Collection<PersonaDeContacto> getContactoByEmail(String email) throws ConsultaException;
    public PersonaDeContacto getContactoById (Integer id) throws ConsultaException;
}
