package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.ClienteDetalle;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccionalFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            ClienteDetalle detalle = new ClienteDetalle(true, 8000L);
            cliente.addDetalle(detalle);

            em.getTransaction().commit();

            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
