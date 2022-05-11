package org.aguzman.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        //Tipo de dato del resultado = Cliente.class
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        // Tabla principal es el from
        Root<Cliente> cliente = query.from(Cliente.class);

        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);
        query.select(cliente).distinct(true);
        List<Cliente> clientes = em.createQuery(query).getResultList();

        clientes.forEach(c -> System.out.println(c.getNombre() + ", detalle: " + c.getDetalle()
                + ", direcciones: " + c.getDirecciones()));
        em.close();
    }
}
