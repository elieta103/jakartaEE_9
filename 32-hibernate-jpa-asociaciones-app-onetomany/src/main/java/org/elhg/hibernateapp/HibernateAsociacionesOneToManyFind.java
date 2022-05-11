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
            //Un cliente muchas direcciones
            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("vasco de gama", 456);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            //En este caso como cliente ya existe, no se usa persist, se usa merge.
            em.merge(cliente);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
