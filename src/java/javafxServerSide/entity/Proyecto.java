/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The Project class encapsulates the data of each project: 
 * <ul>
 *  <li><stron>id</strong> is the identifier of the Proyecto:</li>
 *  <li><stron>fechaEstimada</strong> is the expected date of completion of the project.</li> 
 *  <li><stron>fechaFinal</strong> is the date when the project ends.</li>
 *  <li><stron>concepto</strong> is the description of the project.</li>
 *  <li><stron>importeEstimado</strong> is the price estimated to cost the project.</li>
 *  <li><stron>importeFinal</strong> is the final price of the project.</li>
 *  <li><stron>horasEstimadas</strong> are the estimated hours it takes to do the project.</li>
 *  <li><stron>horasFinales</strong> are the hours that have taken to make the project.</li>
 *  <li><stron>cliente</strong> are all cliente data.</li>
 *  <li><stron>servicios</strong> is the collection of servicios and the corresponding data of each servicio</li>
 * </ul>
 * 
 * 
 * @author Iker Jon Mediavilla
 */
@Entity
@Table(name="proyecto", schema="dindb")
    @NamedQueries({
        @NamedQuery(
            name="findAllProyectos",
            query="select p from Proyecto p order by p.id"
        ),
        @NamedQuery(
            name="findProyectosSinFinalizar",
            query="select p from Proyecto p where p.fechaFinal is null or p.importeFinal=0 or p.horasFinales=0 order by p.id"
        ),  
        @NamedQuery(
            name="findProyectosFinalizados",
            query="select p from Proyecto p where p.fechaFinal is not null or p.importeFinal!=0 or p.horasFinales!=0 order by p.id"
        ),
        @NamedQuery(
            name="findProyectosCIF",
            query="select p from Proyecto p where p.cliente like :cif order by p.id"
        ),
        @NamedQuery(
            name="findProyectosSinFinalizarCIF",
            query="select p from Proyecto p where p.fechaFinal is null or p.importeFinal=0 or p.horasFinales=0 and p.cliente like :cif order by p.id"
        ),
        @NamedQuery(
            name="findProyectosFinalizadosCIF",
            query="select p from Proyecto p where p.fechaFinal is not null or p.importeFinal!=0 or p.horasFinales!=0 and p.cliente like :cif order by p.id"
        )
    })

@XmlRootElement
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date fechaEstimada;
    private Date fechaFinal;
    private String concepto;
    private Float importeEstimado;
    private Float importeFinal;
    private Integer horasEstimadas;
    private Integer horasFinales;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany(mappedBy="proyectos")
    private Collection<Servicio> servicios;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Float getImporteEstimado() {
        return importeEstimado;
    }

    public void setImporteEstimado(Float importeEstimado) {
        this.importeEstimado = importeEstimado;
    }

    public Float getImporteFinal() {
        return importeFinal;
    }

    public void setImporteFinal(Float importeFinal) {
        this.importeFinal = importeFinal;
    }

    public Integer getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Integer horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public Integer getHorasFinales() {
        return horasFinales;
    }

    public void setHorasFinales(Integer horasFinales) {
        this.horasFinales = horasFinales;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public Collection<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Collection<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Method used to make equal comparison between two ids.
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxServerSite.Entity.Proyecto[ id=" + id + " ]";
    }
    
}