package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.Factura;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);

            //Factura es la dueña de la relacion
            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);
            System.out.println(factura);

            factura = new Factura("compras de papeleria", 1500L);
            factura.setCliente(cliente);
            em.persist(factura);
            System.out.println(factura);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
