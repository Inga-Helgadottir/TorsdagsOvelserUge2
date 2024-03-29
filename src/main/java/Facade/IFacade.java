package Facade;

import entities.Semester;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface IFacade {
    //Find all Students in the system
    public List<Student> findAllStudents();

    //Find all Students in the System with the first name Anders
    public List<Student> findAllStudentsWithFirstName(String name);

    //Insert a new Student into the system
    public Student addStudent(Student s);

    //Assign a new student to a semester

    Student assignStudentToSemester(long semId, long studentId2);

    //Find (using JPQL) all Students in the system with the last name And
    public Student allStudentsLastName(String name);

    //Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.
    public long nbrOfStudentsInSemester(String semester);

    //Find (using JPQL) the total number of students in all semesters.
    public long nbrOfStudentsInAllSemesters();

    //Find (using JPQL) the teacher who teaches the most semesters.
    public Teacher teacherOfMostSemesters();

    long findStudentId(Student s);

    public long findTeacherId(Teacher t);

    public long findSemesterId(Semester s);
}