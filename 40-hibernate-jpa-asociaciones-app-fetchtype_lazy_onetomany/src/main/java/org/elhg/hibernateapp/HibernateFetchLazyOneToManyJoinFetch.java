package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        // LEFT OUTER JOIN : TODOS LOS CLIENTES TENGAN O NO DIRECCIONES
        // INNER JOIN      : SOLO CLIENTES CON DIRECCION
        // RIGHT OUTER JOIN: TODAS LAS DIRECCIONES TENGAN O NO CLIENTE
        // FETCH ES PARA POBLAR DIRECCIONES EN EL OBJETO CLIENTE
        // OUTER ES OPCIONAL
        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones" +
                                                                   " left join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones());
        System.out.println(cliente.getNombre() + ", detalle: " + cliente.getDetalle());
        em.close();
    }
}
