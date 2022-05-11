package org.elhg.hibernateapp;

import jakarta.persistence.EntityManager;
import org.elhg.hibernateapp.entity.Alumno;
import org.elhg.hibernateapp.entity.Curso;
import org.elhg.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y Jpa", "Andres");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            // Crea  tabla alumnos_cursos (Alumno_id, cursos_id)
            // Alumno es la principal, maneja el cascade
            // Unidireccional
            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
