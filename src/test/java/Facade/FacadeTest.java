package Facade;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_test");
    Student s1, s2, s3;
    Teacher t1, t2, t3;
    Semester sem1, sem2;
    IFacade f = new Facade(emf);
    List<Student> expected;
    /* TODO:
            look over screenshots
            fix all functions after change
            connect tables for functions:
                Assign a new student to a semester
                Find the total number of students in all semesters.
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

            sem1.addStudent(s1);
            sem1.addStudent(s2);
            sem2.addStudent(s3);

            sem1.addTeacher(t1);
            sem2.addTeacher(t2);
            sem2.addTeacher(t3);
            sem1.addTeacher(t3);

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
//        expected = new ArrayList<>();
//        Student s = new Student("Jens", "Jensen");
//        Student s2 = new Student("Hans", "Hansen");
//        Student s3 = new Student("John", "Doe");
//        Student s4 = new Student("Jane", "Doe");
//        Student s5 = new Student("Andersine", "And");
//        Student s6 = new Student("Anders", "And");
//        expected.add(s);
//        expected.add(s2);
//        expected.add(s3);
//        expected.add(s4);
//        expected.add(s5);
//        expected.add(s6);
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
//        List<Student> actual = f.findAllStudents();
//        assertEquals(expected, actual);
    }

    //Find all Students in the System with the first name Anders
    @Test
    void testfindAllStudentsWithFirstName() {
        System.out.println("Find all Students in the System with the first name Anders");
        Student expected = new Student("Hamud", "Husseini");
        Student actual = f.findAllStudentsWithFirstName("Hamud").iterator().next();
        System.out.println("here-------------");
        System.out.println(actual.equals(expected));
        System.out.println(actual.getFirstname() + " - " + expected.getFirstname());
        System.out.println(actual.getLastname() + " - " + expected.getLastname());
        assertEquals(expected, actual);
        /*
        List<Student> expected2 = new ArrayList<>();
        String checkForName = "Anders";
        for (int i = 0; i < expected.size(); i++) {
            if(expected.get(i).getFirstname().equals(checkForName)){
                expected2.add(expected.get(i));
            }
        }
        List<Student> actual = f.findAllStudentsWithFirstName("Anders");
        assertEquals(expected2, actual);*/
    }

    //Insert a new Student into the system
    @Test
    void addStudent() {
        System.out.println("Insert a new Student into the system");
        int expected2 = 6;
        int actual2 = f.findAllStudents().size();
        assertEquals(expected2, actual2);

        Student s = new Student("Inga", "Helgadottir");
        expected.add(s);
        int expected = 7;
        int actual = f.addStudent(s);
        assertEquals(expected, actual);
    }
/*
    //Assign a new student to a semester
    @Test
    void assignStudentToSemester() {
        System.out.println("Assign a new student to a semester");
//        assertEquals(expected, actual);
    }*/

    //Find (using JPQL) all Students in the system with the last name And
    @Test
    void allStudentsLastName() {
        System.out.println("Find (using JPQL) all Students in the system with the last name And");
        List<Student> expected2 = new ArrayList<>();
        String checkForName = "And";
        for (int i = 0; i < expected.size(); i++) {
            if(expected.get(i).getLastname().equals(checkForName)){
                expected2.add(expected.get(i));
            }
        }
        List<Student> actual = f.allStudentsLastName("And");
        assertEquals(expected2, actual);
    }


    //Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.
    @Test
    void nbrOfStudentsInSemester() {
        System.out.println("Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter");
        int expected = 2;
        int actual = f.nbrOfStudentsInSemester("CLdat-a14e");
        assertEquals(expected, actual);
    }
/*
    //Find (using JPQL) the total number of students in all semesters.
    @Test
    void nbrOfStudentsInAllSemesters() {
        System.out.println("Find (using JPQL) the total number of students in all semesters.");
//        assertEquals(expected, actual);
    }

    //Find (using JPQL) the teacher who teaches the most semesters.
    @Test
    void teacherOfMostSemesters() {
        System.out.println("Find (using JPQL) the teacher who teaches the most semesters.");
//        assertEquals(expected, actual);
    }*/
}