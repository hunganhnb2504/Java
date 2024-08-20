package com.example.studentinformationsystem.models;

import jakarta.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "student_score_t")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentScoreId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "score1")
    private Double score1;

    @Column(name = "score2")
    private Double score2;

    // Tính toán điểm tổng hợp
    public Double getGrade() {
        return 0.3 * score1 + 0.7 * score2;
    }

    // Phân loại điểm số thành chữ cái
    public String getGradeLetter() {
        Double grade = getGrade();
        if (grade >= 8.0) {
            return "A";
        } else if (grade >= 6.0) {
            return "B";
        } else if (grade >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }

    // Định dạng điểm số
    public String getFormattedGrade() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(getGrade());
    }

    // Getters và setters
    public Integer getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }
}
