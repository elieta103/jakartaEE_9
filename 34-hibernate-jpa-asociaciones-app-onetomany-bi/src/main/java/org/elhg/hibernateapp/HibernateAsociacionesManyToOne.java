package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.Factura;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToOne {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("credito");
            em.persist(cliente);

            Factura factura1 = new Factura("compras de oficina", 1000L);
            factura1.setCliente(cliente);
            em.persist(factura1);

            Factura factura2 = new Factura("compras de papeleria", 1500L);
            factura2.setCliente(cliente);
            em.persist(factura2);

            System.out.println(factura1.getCliente());
            System.out.println(factura2.getCliente());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
