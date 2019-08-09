package dao;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pojo.Course;

@Repository
public class CourseDao {

  @Resource
  JdbcTemplate jdbcTemplate;

  public Course select(int id) {

    RowMapper<Course> rowMapper = (var1, num) -> {
      Course course = new Course();
      course.setName(var1.getString("name"));
      course.setId(var1.getInt("id"));
      return course;
    };


    try {
      Course course =jdbcTemplate.queryForObject("select id,name from course where id=?", rowMapper, id);
      return course;
    } catch (DataAccessException e) {
      System.out.println("no result");
    }
    return null;

  }

  public void insert(Course course) {

    jdbcTemplate.update("insert into course(id,name) values(?,?) ", course.getId(), course.getName());
  }

  public int update(int id, String name) {

    return jdbcTemplate.update("update course set name=? where id = ?", name, id);

  }

  public void delete(int id) {

    jdbcTemplate.update("delete from course where id = ?", id);
  }
}
