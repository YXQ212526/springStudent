package dao;

import javax.annotation.Resource;

import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:myxml.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Component
@ComponentScan(basePackageClasses = CourseDaoTest.class)
public class CourseDaoTest {

  @Resource
  CourseDao courseDao;


  public void insert() {
    Course course1 = new Course();
    course1.setId(1);
    course1.setName("math");
    Course course2 = new Course();
    course2.setId(2);
    course2.setName("english");
    Course course3 = new Course();
    course3.setId(3);
    course3.setName("chinese");
    courseDao.insert(course1);
    courseDao.insert(course2);
    courseDao.insert(course3);
  }


  @Test
  public void select() {
    Assert.assertEquals(courseDao.select(1).getName(), "math");
    Assert.assertNull(courseDao.select(0));
  }

  @Test
  public void update() {

    Assert.assertSame(courseDao.update(1, "chinese"), 1);
    Assert.assertSame(courseDao.update(0, "chinese"), 0);
  }

  @Test
  public void delete() {
    insert();
    courseDao.delete(2);
    Assert.assertNull(courseDao.select(2));
  }
}
