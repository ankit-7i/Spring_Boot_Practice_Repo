package com.example.springcorejdbcpostgresqldemo_8.dao;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.springcorejdbcpostgresqldemo_8.model.Student;

@Component
public class StudentOperations {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE TABLE
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS student (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    marks INT
                )
                """;
        jdbcTemplate.execute(sql);
        System.out.println("Table Created");
    }

    // INSERT
    public void addStudent(Student s) {
        jdbcTemplate.update(
                "INSERT INTO student VALUES (?,?,?)",
                s.getId(), s.getName(), s.getMarks()
        );
        System.out.println("Student Inserted");
    }

    // UPDATE
    public void updateStudent(Student s) {
        jdbcTemplate.update(
                "UPDATE student SET name=?, marks=? WHERE id=?",
                s.getName(), s.getMarks(), s.getId()
        );
        System.out.println("Student Updated");
    }

    // DELETE
    public void deleteStudent(int id) {
        jdbcTemplate.update(
                "DELETE FROM student WHERE id=?",
                id
        );
        System.out.println("Student Deleted");
    }

    // SELECT ONE
    public Student getStudent(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM student WHERE id=?",
                new BeanPropertyRowMapper<>(Student.class),
                id
        );
    }

    // SELECT ALL
    public List<Student> getAllStudents() {
        return jdbcTemplate.query(
                "SELECT * FROM student",
                new BeanPropertyRowMapper<>(Student.class)
        );
    }
}

