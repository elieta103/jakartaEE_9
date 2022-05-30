package org.elhg.webapp.jaxws.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    //@XmlTransient    //Omitir el Instructor en el listado de cursos para XML
    //@JsonbTransient  //Omitir el Instructor en el listado de cursos para JSON
    //@JsonIgnore      //Omitir el Instructor en el listado de cursos con =>resteasy-jackson2-provider
    //@JsonIgnoreProperties  Del JSON instructor ignore los cursos, evitar las relaciones ciclicas con el error :
    // Caused by: jakarta.json.bind.JsonbException: Recursive reference has been found in class class org.elhg.webapp.jaxws.models.Curso
    // Evitar la serializacion innecesaria de objetos, Sucede en las relaciones bidireccionales
    @JsonIgnoreProperties({"cursos", "handler", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;

    private Double duracion;

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
}
