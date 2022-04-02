package org.elhg.java.jdbc.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {

    private Long id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;
    private Categoria categoria;
    private String sku;

}
