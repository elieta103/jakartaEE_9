package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.entity.Factura;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {

            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("compras de supermercado", 5000L);
            Factura f2 = new Factura("compras de tecnologia", 7000L);
            // AGREGA RELACION BIDIRECCIONAL
            cliente.addFactura(f1)
                    .addFactura(f2);

            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);

            System.out.println("\n");
            System.out.println("Eliminando registros relacionados .....");
            em.getTransaction().begin();
            // Dentro contexto de persistencia para eliminar
            // Factura f3 = em.find(Factura.class, 1L);

            //Con este enfoque requiere que factura implemente equals()
            // Fuera contexto de persistencia para eliminar
            Factura f3 = new Factura("compras de supermercado", 5000L);
            f3.setId(1L);
            // REMUEVE RELACION BIDIRECCIONAL
            cliente.removeFactura(f3);
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
