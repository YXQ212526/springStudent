import java.util.List;
import java.util.Map;

//import dao.ScoreDao;
//import dao.StudentDao;

import dao.CourseDao;
import dao.ScoreDao;
import dao.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import pojo.Course;
import pojo.StuCourScor;
import pojo.Student;


public class Main {

  public static void main(String[] args) {
    //  dao.StudentDao studentDao = new dao.StudentDao();
    //studentDao.insert("xiaoming");
    // studentDao.select(1);

    // studentDao.update(1,"xiao");
//    StudentDao.insert("jack");
//    Student student=new Student();
//    student.setName("lily");
//    StudentDao.update(student);
    //StudentDao.delete(3);
    // Map<Student,Map<Course, Score>>map=ScoreDao.select(1,2019);
//   Map<Student,Integer>map1= ScoreDao.getTop10();
    // System.out.println(map);
    // CreateConn.close();
    //  studentDao.delete(1);
    //studentDao.close();
    ApplicationContext context = new ClassPathXmlApplicationContext("myxml.xml");
    CourseDao courseDao = (CourseDao) context.getBean("courseDao");
//    // Course course=courseDao.select(1);
//    //System.out.println(course.getName());
////courseDao.update(1,"xx");
//   // courseDao.delete(2);
//    Course course = new Course();
//    course.setId(6);
//    course.setName("movie");
//    courseDao.insert(course);
    ScoreDao scoreDao = context.getBean(ScoreDao.class);
////    //  List<StuCourScor> list=scoreDao.getTop10();
    List<StuCourScor> list = scoreDao.select(2, 2019);
    scoreDao.getTop10();
    System.out.println(list.get(0).getScore().getGoal());
//    StudentDao studentDao = context.getBean(StudentDao.class);
//    Student s = new Student();
//    s.setName("jack");
//    s.setId(5);
//    s.setStatus(0);
//    studentDao.insert(s);

  }
}
