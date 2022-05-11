package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.Factura;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {

            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnologia", 7000L);

            //addFactura,  asigna
            cliente.addFactura(f1)
                    .addFactura(f2);

            // En ManyToOne se guarda factura.
            // En OneToMany se guarda cliente.
            // Merge Opcional, ya que cliente entra en el contexto de persistencia con el find
            // y al hacer el commit registra los cambios.
            em.merge(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
