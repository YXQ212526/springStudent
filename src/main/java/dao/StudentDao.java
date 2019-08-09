package dao;


import javax.annotation.Resource;


import enums.Status;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pojo.Student;

@Repository
public class StudentDao {

  @Resource
  JdbcTemplate jdbcTemplate;

  public Student select(int id) {

    RowMapper<Student> rowMapper = (var1, num) -> {
      Student student = new Student();
      student.setName(var1.getString("name"));
      student.setId(var1.getInt("id"));
      student.setStatus(Status.getType(var1.getString("status")));
      return student;
    };
    try {
      Student student = jdbcTemplate.queryForObject("select id,name,status from student where id=?", rowMapper, id);
      return student;
    } catch (DataAccessException e) {
      System.out.println("no result");
    }
    return null;
  }

  public void insert(Student student) {


    jdbcTemplate.update("insert into student(id,name,status) values(?,?,?)",
        student.getId(), student.getName(), student.getStatus());
  }

  public int update(int id, int status) {

    return jdbcTemplate.update("update student set status=? where id = ?", status, id);
  }

  public void delete(int id) {

    jdbcTemplate.update("delete from student where id = ?", id);
  }

}