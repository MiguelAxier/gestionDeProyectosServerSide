/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Project class encapsulates the data of each project:  
 * <ul>
 *  <li><stron>id</strong> is the identifier of the Servicio:</li>
 * </ul>
 * 
 * @author Iker Jon Mediavilla
 */

@Entity
@Table(name="servicio", schema="dindb")
    @NamedQueries({
        @NamedQuery(
            name="findAllServicios",
            query="select s from Servicio s order by s.id"
        ),  
        @NamedQuery(
            name="findServiciosId",
            query="select s from Servicio s where s.id like :id order by s.id"
        ),
        @NamedQuery(
            name="findServiciosNombre",
            query="select s from Servicio s where s.nombre like :nombre order by s.id"
        ),
        @NamedQuery(
            name="findServiciosIdNombre",
            query="select s from Servicio s where s.nombre like :nombre and s.id like :id order by s.id"
        )
    })

@XmlRootElement
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String descripcion;
    @ManyToMany
    @JoinTable(name="proyecto_servicio",schema="dindb")
    private Collection<Proyecto> proyectos;
    

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Collection<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxServerSite.Entity.Servicio[ id=" + id + " ]";
    }
    
}