package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.dominio.ClienteDto;
import org.elhg.hibernateapp.entity.Cliente;
import org.elhg.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("=========== TODOS ==========");
        List<Cliente> clientes = em
                                .createQuery("select c from Cliente c", Cliente.class)
                                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("");
        System.out.println("=========== POR ID ===========");
        Cliente cliente = em
                            .createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                            .setParameter("id", 1L)
                            .getSingleResult();
        System.out.println(cliente);

        System.out.println("");
        System.out.println("========== SOLO NOMBRE POR ID ==========");
        String nombreCliente = em
                                .createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                                .setParameter("id", 2L)
                                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("");
        System.out.println("========== CAMPOS PERSONALIZADOS ===========");
        Object[] objetoCliente = em
                        .createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                        .setParameter("id", 1L)
                        .getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id=" + id + ",nombre=" + nombre + ",apellido=" + apellido);

        System.out.println("");
        System.out.println("========== LISTA CAMPOS PERSONALIZADOS ===========");
        List<Object[]> registros = em
                        .createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                        .getResultList();
        //for (Object[] reg : registros) {
        registros.forEach( reg -> {
                    Long idCli = (Long) reg[0];
                    String nombreCli = (String) reg[1];
                    String apellidoCli = (String) reg[2];
                    System.out.println("id=" + idCli + ",nombre=" + nombreCli + ",apellido=" + apellidoCli);
                });
        //}

        System.out.println("");
        System.out.println("=========== POR CLIENTE Y FORMA DE PAGO==========");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente)reg[0];
            String formaPago = (String) reg[1];
            System.out.println("formaPago=" + formaPago + "," + c);
        });

        System.out.println("");
        System.out.println("========== CREA OBJETO ENTITY DE CLASE PERSONALIZADA ==========");
        clientes = em
                    .createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                    .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== CREA Y DEVUELVE OBJETO DTO PERSONALIZADO ==========");
        List<ClienteDto> clientesDto = em
                        .createQuery("select new org.elhg.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                        .getResultList();
        clientesDto.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== LISTA NOMBRES DE CLIENTES ===========");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== DISTINCT NOMBRES ===========");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== DISTINCT FORMA PAGO ===========");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                        .getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== COUNT DISTINCT FORMA PAGO ===========");
        Long totalFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                .getSingleResult();
        System.out.println(totalFormasPago);

        System.out.println("");
        System.out.println("========== CONCAT NOMBRE Y APELLIDOS ===========");
//        nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class)
//                        .getResultList();

        nombres = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== CONCAT UPPER_CASE NOMBRE Y APELLIDO ===========");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== LIKE UPPER_CASE NOMBRE ===========");
        String param = "PE";
        clientes = em.createQuery("select c from Cliente c where upper(c.nombre) like upper(:parametro)", Cliente.class)
                .setParameter("parametro", "%" + param + "%")
                        .getResultList();
        // LOS % para que busque en cualquier posicion la cadena izq, der.
        clientes.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== BETWEEN ==========");
//        clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'Q'", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("");
        System.out.println("========== ORDER BY ASC ==========");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c.apellido asc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("");
        System.out.println("=========== COUNT ===========");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        System.out.println("");
        System.out.println("========== MIN ==========");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class).getSingleResult();
        System.out.println(minId);

        System.out.println("");
        System.out.println("========== MAX ==========");
        Long maxId = em.createQuery("select max(c.id) as maximo from Cliente c", Long.class).getSingleResult();
        System.out.println(maxId);

        System.out.println("");
        System.out.println("=========== LENGTH NOMBRE ==========");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registros.forEach(reg ->{
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre=" + nom + ", largo=" + largo);
        });

        System.out.println("");
        System.out.println("========== LENGTH NOMBRE MAS CORTO ==========");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        System.out.println("");
        System.out.println("========== LENGTH NOMBRE MAS LARGO ==========");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        System.out.println("");
        System.out.println("========== RESUMEN FUNCIONES AGREGADAS COUNT MIN MAX AVG SUM ==========");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                        .getSingleResult();
        Long min = (Long) estadisticas[0];
        Long max = (Long) estadisticas[1];
        Long sum = (Long) estadisticas[2];
        Long count = (Long) estadisticas[3];
        Double avg = (Double) estadisticas[4];
        System.out.println("min=" + min + ", max=" + max + ", sum=" + sum + ", count=" + count + ", avg=" + avg);

        System.out.println("");
        System.out.println("========== SUBQUERY NOMBRE MAS CORTO ==========");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre=" + nom + ", largo=" + largo);
        });

        System.out.println("");
        System.out.println("========== SUBQUERY ULTIMO REGISTRO ==========");
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                        .getSingleResult();
        System.out.println(ultimoCliente);

        System.out.println("");
        System.out.println("========== IN  =========");
        clientes = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L, 2L, 10L, 6L))
                .getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}
