package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TEACHER_SEMESTER", schema = "ExamPreparationJPQL", catalog = "")
@IdClass(TeacherSemesterPK.class)
public class TeacherSemester {
    private long teachingId;
    private long teachersId;

    @Id
    @Column(name = "teaching_ID")
    public long getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(long teachingId) {
        this.teachingId = teachingId;
    }

    @Id
    @Column(name = "teachers_ID")
    public long getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(long teachersId) {
        this.teachersId = teachersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherSemester that = (TeacherSemester) o;
        return teachingId == that.teachingId && teachersId == that.teachersId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingId, teachersId);
    }
}
