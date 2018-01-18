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
import javax.ejb.Local;

/**
 * Local interface for ClienteManager EJB
 * @author Miguel Axier Lafuente Peñas
 */

@Local
public interface ClienteManagerEJBLocal {
    public Cliente getClienteByNif(String nif) throws ConsultaException;
    public Collection <Cliente> getClienteByEmail (String email) throws ConsultaException;
    public Collection <Cliente> getClienteByNombre(String nombre) throws ConsultaException;
    public Collection <Cliente> getAllClientes() throws ConsultaException;
    public void newCliente(Cliente cliente) throws AltaException;
    public void deleteCliente(Cliente cliente) throws BajaException;
    public void updateCliente(Cliente cliente) throws ModificarException;
}
