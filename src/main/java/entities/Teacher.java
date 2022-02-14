package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
        @NamedQuery(name = "Teacher.deleteAll", query = "DELETE FROM Teacher t")
})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    private String firstname;
    private String lastname;
    @ManyToMany
    @JoinColumn(name = "teachers_ID", referencedColumnName = "id")
    private Set<Semester> semesters = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(Semester semesters) {
        this.semesters.add(semesters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getFirstname().equals(teacher.getFirstname()) && getLastname().equals(teacher.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname());
    }

    public long intValue() {
        return this.getId();
    }
}
