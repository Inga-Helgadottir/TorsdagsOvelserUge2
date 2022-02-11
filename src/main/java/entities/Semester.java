package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Semester {
    @Id
    @Column(name = "ID")
    private long id;
    private String description;
    private String name;
    @OneToMany(mappedBy = "semester")
    private List<Student> s = new ArrayList<>();

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
        return id == semester.id && Objects.equals(description, semester.description) && Objects.equals(name, semester.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name);
    }
}
