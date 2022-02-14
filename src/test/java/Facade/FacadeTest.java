package Facade;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import entities.TeacherSemester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_test");
    Student s1, s2, s3;
    Teacher t1, t2, t3;
    Semester sem1, sem2;
    IFacade f = new Facade(emf);
    /* TODO:
            linktable
            make functions:
                Find the teacher who teaches the most semesters.
                nr 9 + 10
     */

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Student.deleteAll").executeUpdate();
            em.createNamedQuery("Teacher.deleteAll").executeUpdate();
            em.createNamedQuery("Semester.deleteAll").executeUpdate();
            s1 = new Student("Hans", "Hansen");
            s2 = new Student("Hanne", "Hamsun");
            s3 = new Student("Hamud", "Husseini");

            t1 = new Teacher("Tanja", "Telegård");
            t2 = new Teacher("Tobias", "Tormodson");
            t3 = new Teacher("Taiko", "Takamoto");

            sem1 = new Semester("Third sem", "Very interesting semester");
            sem2 = new Semester("Fourth sem", "Amazing learning");

            sem1.setStudent(s1);
            sem1.setStudent(s2);
            sem2.setStudent(s3);

            sem1.setTeachers(t1);
            sem2.setTeachers(t2);
            sem2.setTeachers(t3);
            sem1.setTeachers(t3);

            em.persist(s1);
            em.persist(s2);
            em.persist(s3);
            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.persist(sem1);
            em.persist(sem2);

            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    //Find all Students in the system
    @Test
    void findAllStudents() {
        System.out.println("Find all Students in the system");
        int expected = 3;
        int actual = f.findAllStudents().size();
        assertEquals(expected, actual);
    }

    //Find all Students in the System with the first name Anders
    @Test
    void testfindAllStudentsWithFirstName() {
        System.out.println("Find all Students in the System with the first name Anders");
        Student expected = new Student("Hamud", "Husseini");
        Student actual = f.findAllStudentsWithFirstName("Hamud").iterator().next();
        assertEquals(expected, actual);
    }

    //Insert a new Student into the system
    @Test
    void addStudent() {
        System.out.println("Insert a new Student into the system");
        Student s = f.addStudent(new Student("Inga", "Helgadottir"));
        int expected = 4;
        int actual = f.findAllStudents().size();
        assertEquals(expected, actual);
    }

    //Assign a new student to a semester
    @Test
    void assignStudentToSemester() {
        System.out.println("Assign a new student to a semester");
        Student changed = f.assignStudentToSemester(sem2.getId(), s1.getId());
        String expected = sem2.getName();
        System.out.println(changed.getSemester());
        String actual = changed.getSemester().getName();
        assertEquals(expected, actual);
    }

    //Find (using JPQL) all Students in the system with the last name And
    @Test
    void allStudentsLastName() {
        System.out.println("Find (using JPQL) all Students in the system with the last name And");
        Student expected = new Student("Hamud", "Husseini");
        Student actual = f.allStudentsLastName("Husseini");
        assertEquals(expected, actual);
    }


    //Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.
    @Test
    void nbrOfStudentsInSemester() {
        System.out.println("Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter");
        long expected = 1;
        long actual = f.nbrOfStudentsInSemester("Amazing learning");
        assertEquals(expected, actual);
    }

    //Find (using JPQL) the total number of students in all semesters.
    @Test
    void nbrOfStudentsInAllSemesters() {
        System.out.println("Find (using JPQL) the total number of students in all semesters.");
        long actual = f.nbrOfStudentsInAllSemesters();
        long expected = 3;
        assertEquals(expected, actual);
    }
/*
    //Find (using JPQL) the teacher who teaches the most semesters.
    @Test
    void teacherOfMostSemesters() {
        System.out.println("Find (using JPQL) the teacher who teaches the most semesters.");
        Teacher expected = new Teacher("Taiko", "Takamoto");
        Teacher actual = f.teacherOfMostSemesters();
        assertEquals(expected, actual);
    }*/
}