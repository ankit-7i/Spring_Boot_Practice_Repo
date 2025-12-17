package com.example.springcorejdbcmysqldemo_7;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        JdbcTemplate jdbcTemplate = con.getBean(JdbcTemplate.class);

        // ---------- INSERT ----------
        String insertSQL =
                "insert into student (sid, sname, saddress, marks) values (?,?,?,?)";
        int insertCount = jdbcTemplate.update(
                insertSQL, 101, "Venkat", "Hyderabad", 85.5);
        System.out.println("Inserted Rows : " + insertCount);

        // ---------- UPDATE ----------
        String updateSQL =
                "update student set sname=?, saddress=?, marks=? where sid=?";
        int updateCount = jdbcTemplate.update(
                updateSQL, "Venkat R", "Bangalore", 90.0, 101);
        System.out.println("Updated Rows : " + updateCount);

        // ---------- DELETE ----------
        /* 
        String deleteSQL = "delete from student where sid=?";
        int deleteCount = jdbcTemplate.update(deleteSQL, 101);
        System.out.println("Deleted Rows : " + deleteCount);
*/
        // ---------- SELECT ALL ----------
        String selectSQL = "select * from student";

        List<Student> list = jdbcTemplate.query(selectSQL, (rs, rowNum) -> {
            Student s = new Student();
            s.setSid(rs.getInt("sid"));
            s.setSname(rs.getString("sname"));
            s.setSaddress(rs.getString("saddress"));
            s.setMarks(rs.getDouble("marks"));
            return s;
        });

        for (Student student : list) {
            System.out.println(student);
        }

        con.close();
    }
}
