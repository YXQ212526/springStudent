package dao;


import java.util.List;


import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pojo.Course;
import pojo.Score;
import pojo.StuCourScor;
import pojo.Student;

@Repository
public class ScoreDao {

  @Resource
  JdbcTemplate jdbcTemplate;

  public List<StuCourScor> select(int studentId, int year) {

    RowMapper<StuCourScor> rowMapper = (var1, row) -> {
      Student student = new Student();
      student.setName(var1.getString("sname"));
      student.setId(var1.getInt("sid"));
      Course course;
      course = new Course();
      course.setId(var1.getInt("cid"));
      course.setName(var1.getString("cname"));
      Score score = new Score();
      score.setGoal(var1.getInt("goal"));
      StuCourScor stuCourScor = new StuCourScor();
      stuCourScor.setCourse(course);
      stuCourScor.setScore(score);
      stuCourScor.setStudent(student);
      return stuCourScor;
    };
    List<StuCourScor> list = jdbcTemplate
        .query("select student.id as sid,student.name as sname,course.id as cid,course.name as cname, goal "
            + "from student,score,course where student.id=? and year=? "
            + "and student.id=score.student_id and course.id=score.course_id", rowMapper, studentId, year);

    return list;
  }

  public List<StuCourScor> getTop10() {
    RowMapper<StuCourScor> rowMapper = (var1, row) -> {
      Student student = new Student();
      student.setName(var1.getString("sname"));
      student.setId(var1.getInt("sid"));
      StuCourScor stuCourScor = new StuCourScor();
      stuCourScor.setStudent(student);
      stuCourScor.setTotal(var1.getInt("total"));
      return stuCourScor;
    };

    List<StuCourScor> list = jdbcTemplate.query("select student.id as sid,student.name as sname,"
        + "sum(score.goal) as total "
        + "from student inner join score on "
        + "student.id=score.student_id "
        + "group by student.id "
        + "order by total desc limit 10 ", rowMapper);
    return list;
  }

  public List<StuCourScor> GPA() {

    RowMapper<StuCourScor> rowMapper = (var1, row) -> {
      Student student = new Student();
      student.setName(var1.getString("sname"));
      student.setId(var1.getInt("sid"));
      StuCourScor stuCourScor = new StuCourScor();
      stuCourScor.setStudent(student);
      stuCourScor.setGpa(var1.getInt("val") * 4 / 100);
      return stuCourScor;
    };
    List<StuCourScor> list = jdbcTemplate.query("select student.id as sid,student.name as sname,avg(goal) as val from"
        + " student,score where student.id=score.student_id "
        + "group by student.id", rowMapper);
    return list;
  }

  public void insert(Score score) {

    jdbcTemplate.update("insert into score(course_id,goal,student_id,year) values(?,?,?,?)",
        score.getCourseId(), score.getGoal(), score.getStudentId(), score.getYear());

  }


}
