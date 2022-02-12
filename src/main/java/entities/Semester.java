package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Semester.findAll", query = "SELECT s FROM Semester s"),
        @NamedQuery(name = "Semester.deleteAll", query = "DELETE FROM Semester s")
})
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    private String description;
    private String name;
    @ManyToMany(mappedBy = "semesters")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "semester")
    private Set<Student> s = new HashSet<>();

    public Semester() {
    }

    public Semester(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher t) {
        this.teachers.add(t);
    }

    public Set<Student> getStudent() {
        return s;
    }

    public void addStudent(Student s) {
        this.s.add(s);
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(description, semester.description) && Objects.equals(name, semester.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name);
    }
}
