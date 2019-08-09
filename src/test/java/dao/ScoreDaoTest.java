package dao;

import javax.annotation.Resource;


import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pojo.Score;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:myxml.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ScoreDaoTest {


  @Resource
  ScoreDao scoreDao;

  @Resource
  StudentDaoTest studentDaoTest;

  @Resource
  CourseDaoTest courseDaoTest;

  public void insert() {

    studentDaoTest.insert();

    courseDaoTest.insert();

    Score score1 = new Score();
    score1.setCourseId(1);
    score1.setGoal(80);
    score1.setStudentId(1);
    score1.setYear(2019);
    Score score2 = new Score();
    score2.setCourseId(2);
    score2.setGoal(70);
    score2.setStudentId(1);
    score2.setYear(2019);
    Score score3 = new Score();
    score3.setCourseId(3);
    score3.setGoal(20);
    score3.setStudentId(2);
    score3.setYear(2019);
    Score score4 = new Score();
    score4.setCourseId(2);
    score4.setGoal(50);
    score4.setStudentId(3);
    score4.setYear(2019);

    scoreDao.insert(score1);
    scoreDao.insert(score2);
    scoreDao.insert(score3);
    scoreDao.insert(score4);
  }


  @Test
  public void select() {
    Assert.assertEquals(scoreDao.select(1, 2019).size(), 2);
    Assert.assertEquals(scoreDao.select(1, 2019).get(0).getStudent().getName(), "jim");
    Assert.assertEquals(scoreDao.select(1, 2019).get(1).getCourse().getId(), 2);
    Assert.assertEquals(scoreDao.select(1, 2019).get(1).getScore().getGoal(), 70);
  }

  @Test
  public void getTop10() {

    Assert.assertEquals(scoreDao.getTop10().size(), 3);
    Assert.assertEquals(scoreDao.getTop10().get(0).getStudent().getName(), "jim");
    Assert.assertEquals(scoreDao.getTop10().get(1).getTotal(), 50);

  }

  @Test
  public void getGPA() {
    insert();
    Assert.assertEquals(scoreDao.GPA().size(), 3);
    Assert.assertEquals(scoreDao.GPA().get(0).getStudent().getName(), "jim");
    Assert.assertEquals(scoreDao.GPA().get(1).getStudent().getId(), 2);
    Assert.assertTrue(scoreDao.GPA().get(2).getGpa() == 2);

  }
}
