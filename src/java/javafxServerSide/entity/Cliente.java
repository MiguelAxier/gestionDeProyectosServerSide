/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This entity class encapsulates the data of each customer.
 * <ul>
 *  <li><strong>nif:</strong> It's the tax identification number of a customer. It's the identifier</li>
 *  <li><strong>nombre:</strong> It's the customer's name</li>
 *  <li><strong>direccion:</strong> It's the customer's address</li>
 *  <li><strong>telefono:</strong> It's the customer's phone number</li>
 *  <li><strong>email:</strong> It's the customer's email address</li>
 *  <li><strong>web:</strong> It's the customer's website</li>
 *  <li><strong>contacto:</strong> Object of the PersonaDeContacto class that contains 
 *                                  the data of the customer's contact person</li>
 *  <li><strong>facturas:</strong> Collection of objects of the Factura class that 
 *                                  contains the data of the invoices</li>
 *  <li><strong>proyectos:</strong> Collection of objects of the Proyecto class that
 *                                  contains the data of the projects</li>
 * </ul>
 * @author Miguel Axier Lafuente Peñas
 */
@Entity
@Table(name="cliente", schema="dindb")
@NamedQueries({
    @NamedQuery(
            name="findAllClientes",
            query="SELECT c FROM Cliente c"
    ),
    @NamedQuery(
            name="findClienteByEmail",
            query="SELECT c FROM Cliente c WHERE c.email like concat(concat('%',:email),'%')"
    ),
    @NamedQuery(
            name="findClienteByNombre",
            query="SELECT c FROM Cliente c WHERE c.nombre like concat(concat('%',:nombre),'%')"
    )
})
@XmlRootElement
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nif;
    private String nombre;
    private String direccion;
    private BigInteger telefono;
    private String email;
    private String web;
    @OneToOne
    private PersonaDeContacto contacto;
    @OneToMany(mappedBy="cliente")
    private Collection<Factura> facturas;
    @OneToMany(mappedBy="cliente")
    private Collection<Proyecto> proyectos;

    
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    public PersonaDeContacto getContacto() {
        return contacto;
    }

    public void setContacto(PersonaDeContacto contacto) {
        this.contacto = contacto;
    }

    @XmlTransient
    public Collection<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Collection<Factura> facturas) {
        this.facturas = facturas;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Collection<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }
    /**
     * Method used to make equal comparison between two objects using the nif attribute.
     * @param object Object to compare
     * @return A boolean expression. True if it's the same object and false if it's different.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxServerSite.Entity.Cliente[ id=" + nif + " ]";
    }
    
}
