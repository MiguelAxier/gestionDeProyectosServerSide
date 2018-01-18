/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity class encapsulates the data for each contact person of a customer
 * <ul>
 *  <li><strong>id:</strong> It's the indentifier of a contact person.</li>
 *  <li><strong>nombre:</strong> It's the contact person's name</li>
 *  <li><strong>telefono:</strong> It's the contact person's phone number</li>
 *  <li><strong>email:</strong> It's the contact person's email address</li>
 * </ul>
 * @author Miguel Axier Lafuente Peñas
 */
@Entity
@Table(name="personaContacto", schema="dindb")
@NamedQueries({
    @NamedQuery(
            name="findAllContactos",
            query="SELECT p FROM PersonaDeContacto p"
    ),
    @NamedQuery(
            name="findContactoByNombre",
            query="SELECT p FROM PersonaDeContacto p WHERE p.nombre like concat(concat('%',:nombre),'%')"
    ),
    @NamedQuery(
            name="findContactoByEmail",
            query="SELECT p FROM PersonaDeContacto p WHERE p.email like concat(concat('%',:email),'%')"
    )
})

@XmlRootElement
public class PersonaDeContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private BigInteger telefono;
    private String email;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Method used to make equal comparison between two objects using the id attribute.
     * @param object Object to compare
     * @return A boolean expression. True if it's the same object and false if it's different.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonaDeContacto)) {
            return false;
        }
        PersonaDeContacto other = (PersonaDeContacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxServerSite.Entity.PersonaDeContacto[ id=" + id + " ]";
    }
    
}
