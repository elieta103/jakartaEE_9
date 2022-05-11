package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Alumno;
import org.elhg.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToManyResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a left outer join fetch a.cursos", Alumno.class).getResultList();
        alumnos.forEach(a -> System.out.println(a.getNombre() + ", cursos: " + a.getCursos()));
        em.close();
    }
}
