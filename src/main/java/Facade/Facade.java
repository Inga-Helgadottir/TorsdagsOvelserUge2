package Facade;

import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Facade implements IFacade{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args){

    }

    //Find all Students in the system
    public List<Student> findAllStudents(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s", Student.class);
            return tq.getResultList();
        }finally {
            em.close();
        }
    }

    //Find all Students in the System with the first name Anders
    public List<Student> findAllStudentsWithFirstName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :FIRSTNAME", Student.class);
            tq.setParameter("FIRSTNAME", name);
            return tq.getResultList();
        }finally {
            em.close();
        }
    }

    //Insert a new Student into the system
    public int addStudent(Student s){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("A new student has been added, with the name " + s.getFirstname());
        return findAllStudents().size();
    }

    //Assign a new student to a semester
    public Student assignStudentToSemester(Student s){
        return null;
    }

    //Find (using JPQL) all Students in the system with the last name And
    public List<Student> allStudentsLastName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s WHERE s.lastname = :LASTNAME", Student.class);
            tq.setParameter("LASTNAME", name);
            return tq.getResultList();
        }finally {
            em.close();
        }
    }

    //Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.
    public int nbrOfStudentsInSemester(String semester){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Long> tq = em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.semester.name = :sem", Long.class);
            tq.setParameter("sem", semester);
            return tq.getSingleResult().intValue();
        }finally {
            em.close();
        }
    }

    //Find (using JPQL) the total number of students in all semesters.
    public int nbrOfStudentsInAllSemesters(){
        return 0;
    }

    //Find (using JPQL) the teacher who teaches the most semesters.
    public Teacher teacherOfMostSemesters(){
        return null;
    }
}