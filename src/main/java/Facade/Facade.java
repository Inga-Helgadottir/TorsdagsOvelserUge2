package Facade;

import entities.Semester;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class Facade implements IFacade{
    EntityManagerFactory emf;// = Persistence.createEntityManagerFactory("pu");

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
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
    public Student addStudent(Student s){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        }finally {
            em.close();
        }
    }

    //Assign a new student to a semester
    public Student assignStudentToSemester(long semId, long studentId){
        EntityManager em = emf.createEntityManager();
        Semester sem = em.find(Semester.class, semId);
        Student stu = em.find(Student.class, studentId);
        sem.setStudent(stu);
        try{
            em.getTransaction().begin();
            Student merged = em.merge(stu);
            em.merge(sem);
            em.getTransaction().commit();
            return merged;
        }finally {
            em.close();
        }
    }

    //Find (using JPQL) all Students in the system with the last name And
    public Student allStudentsLastName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s WHERE s.lastname = :LASTNAME", Student.class);
            tq.setParameter("LASTNAME", name);
            return tq.getResultList().get(0);
        }finally {
            em.close();
        }
    }

    //Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.
    public long nbrOfStudentsInSemester(String semester){
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
    public long nbrOfStudentsInAllSemesters(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Long> tq = em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.semester != null", Long.class);
            return tq.getSingleResult().intValue();
        }finally {
            em.close();
        }
    }

    //Find (using JPQL) the teacher who teaches the most semesters.
    public Teacher teacherOfMostSemesters(){
//        EntityManager em = emf.createEntityManager();
//        try{
//
//        }finally {
//            em.close();
//        }
        return null;
    }

    public long findTeacherId(Teacher t){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Teacher> tq = em.createQuery("SELECT t FROM Teacher t WHERE t.firstname = :fn AND t.lastname = :ln", Teacher.class);
            tq.setParameter("fn", t.getFirstname());
            tq.setParameter("ln", t.getLastname());
            return tq.getSingleResult().getId();
        }finally {
            em.close();
        }
    }

    public long findSemesterId(Semester semester){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Semester> tq = em.createQuery("SELECT s FROM Semester s WHERE s.name = :semName AND s.description = :des", Semester.class);
            tq.setParameter("semName", semester.getName());
            tq.setParameter("des", semester.getDescription());
            return tq.getSingleResult().getId();
        }finally {
            em.close();
        }
    }

    public long findStudentId(Student student){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :fn AND s.lastname = :ln", Student.class);
            tq.setParameter("fn", student.getFirstname());
            tq.setParameter("ln", student.getLastname());
            return tq.getSingleResult().getId();
        }finally {
            em.close();
        }
    }
}