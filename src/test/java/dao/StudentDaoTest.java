package dao;


import javax.annotation.Resource;

import enums.Status;

import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:myxml.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Component
public class StudentDaoTest {

  @Resource
  StudentDao studentDao;

  public void insert() {
    Student student1 = new Student();
    student1.setId(1);
    student1.setName("jim");
    Student student2 = new Student();
    student2.setId(2);
    student2.setName("alice");
    Student student3 = new Student();
    student3.setId(3);
    student3.setName("bob");

    studentDao.insert(student1);
    studentDao.insert(student2);
    studentDao.insert(student3);


  }


  @Test
  public void select() {

    Assert.assertEquals(studentDao.select(1).getName(), "jim");
    Assert.assertEquals(studentDao.select(2).getStatus(), "正常");
    Assert.assertNull(studentDao.select(0));
  }


  @Test
  public void update() {

    int result = studentDao.update(1, 10);
    int result1 = studentDao.update(2, 1);
    int result2 = studentDao.update(10, 1);
    Assert.assertEquals(studentDao.select(2).getStatus(), Status.get(1));
    Assert.assertEquals(studentDao.select(1).getStatus(), null);
    Assert.assertSame(1, result);
    Assert.assertSame(1, result1);
    Assert.assertSame(0, result2);

  }

  @Test
  public void delete() {
    insert();
    studentDao.delete(3);
    Assert.assertNull(studentDao.select(3));
  }

}
