package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        // MultipleBagFetchException es lanzada cuando hay 2 o mas @OneToMany con FetchType=EAGER
        // Por ejemplo cuando se quiere recuperar cliente->direcciones cliente->facturas
        // Con cliente->detalle no hay problema es @OnetoOne
        // Para evitar lo anterior es mejor hacer 2 consultas por separado
        // Una para cliente->direcciones y otra para cliente->facturas
        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones " +
                                                                                    "left outer join fetch c.detalle", Cliente.class).getResultList();

        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));
        em.close();
    }
}
