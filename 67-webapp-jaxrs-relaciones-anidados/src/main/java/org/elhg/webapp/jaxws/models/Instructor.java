package org.elhg.webapp.jaxws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructores")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonIgnoreProperties  Del JSON curso ignore el atributo instructor, evitar las relaciones ciclicas con el error :
    // Caused by: jakarta.json.bind.JsonbException: Recursive reference has been found in class class org.elhg.webapp.jaxws.models.Instructor
    // "handler", "hibernateLazyInitializer", evita la basura que queda en el proxy
    // Evitar la serializacion innecesaria de objetos, Sucede en las relaciones bidireccionales
    @JsonIgnoreProperties({"instructor", "handler", "hibernateLazyInitializer"})
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Curso> cursos;

    private String nombre;
    private String apellido;

    public Instructor() {
        this.cursos = new ArrayList<>();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
