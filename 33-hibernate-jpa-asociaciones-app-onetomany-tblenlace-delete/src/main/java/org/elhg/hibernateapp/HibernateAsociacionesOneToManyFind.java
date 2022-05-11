package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.Direccion;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("vasco de gama", 456);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            em.merge(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            // En una nueva trx, se elimina la direccion
            // orphanRemoval = true , elimina el registro en la direccion y la tbl_clientes_direccion
            // d1, d2  NO ESTAN en el contexto de persistencia(NO Traen  id), Es necesario buscarlas para hacer el delete
            System.out.println("REMOVIENDO DIRECCION 1 .............");
            em.getTransaction().begin();
            d1 = em.find(Direccion.class, 1L);
            cliente.getDirecciones().remove(d1);
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
