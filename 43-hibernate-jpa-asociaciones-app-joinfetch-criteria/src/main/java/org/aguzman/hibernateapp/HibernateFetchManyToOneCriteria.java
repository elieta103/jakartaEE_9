package org.aguzman.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        //Tipo de dato del resultado = Factura.class
        CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
        // Tabla principal es el from
        Root<Factura> factura = query.from(Factura.class);

        // cliente es el atributo de la relacion
        // Si no se coloca nada, por defecto es inner join
        Join<Factura, Cliente> cliente = (Join) factura.fetch("cliente", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);

        query.select(factura).where(cb.equal(cliente.get("id"), 1L));
        List<Factura> facturas = em.createQuery(query).getResultList();
        facturas.forEach(f-> System.out.println(f.getDescripcion() + ", cliente: " + f.getCliente().getNombre()));
        em.close();
    }
}
