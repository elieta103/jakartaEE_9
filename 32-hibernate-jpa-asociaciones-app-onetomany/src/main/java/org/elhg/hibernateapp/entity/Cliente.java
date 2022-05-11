package org.elhg.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name="forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria audit = new Auditoria();

    // Cada vez que se guarda o crea el cliente tambien se crean los hijos
    // En el delete remueve los registros relacionados,  orphanRemoval = true
    // Sin  @JoinColumn, crea una tabla de clientes_direcciones ahi guarda (cliente_id, direcciones_id)
    // Con  @JoinColumn, no crea la tabla clientes_direcciones, solo agrega la FK id_cliente en direcciones
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_cliente")
    private List<Direccion> direcciones;

    public Cliente() {
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        LocalDateTime creado = this.audit != null? audit.getCreadoEn():null;
        LocalDateTime editado = this.audit != null? audit.getEditadoEn(): null;
        return "{" + "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago+ '\'' +
                ", creadoEn='" + creado + '\'' +
                ", editadoEn='" + editado + '\'' +
                ", direcciones='" + direcciones +  '\'' +
                '}';
    }
}
