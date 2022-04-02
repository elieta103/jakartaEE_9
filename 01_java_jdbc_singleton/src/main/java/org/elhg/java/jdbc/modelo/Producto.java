package org.elhg.java.jdbc.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable{
    private static final long serialVersionUID = 8799656478674716638L;

    private Long id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;
}
