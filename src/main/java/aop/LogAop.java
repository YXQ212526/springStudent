package aop;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import pojo.Course;
import pojo.Score;
import pojo.Student;

@Component
@EnableAspectJAutoProxy
@Aspect
public class LogAop {

  Logger logger = LoggerFactory.getLogger(LogAop.class);

  @Pointcut("execution(* dao.*.*(..))")
  public void cut() {}

//  @Before("cut()")
//  public void before(JoinPoint joinPoint) {
//    StringBuilder stringBuilder = new StringBuilder();
//    for (Object obj : joinPoint.getArgs()) {
//      stringBuilder.append(obj.toString());
//      stringBuilder.append(",");
//    }
//    logger.info("before: " + joinPoint.getSignature().getDeclaringType().getName() + " " + joinPoint.getSignature().getName()
//        + " :params:" + stringBuilder);
//  }

  @AfterThrowing(throwing = "ex", pointcut = "cut()")
  public void afterthrow(JoinPoint joinPoint, Throwable ex) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Object obj : joinPoint.getArgs()) {
      stringBuilder.append(obj.toString());
      stringBuilder.append(",");
    }
    logger.info("after: " + joinPoint.getSignature().getDeclaringType().getName() + " " + joinPoint.getSignature().getName()
        + " :params:" + stringBuilder + " throw ex:" + ex.getMessage());

  }

  @Before("cut()")
  public void before(JoinPoint joinPoint) {
    StringBuilder stringBuilder = new StringBuilder();
    if (joinPoint.getArgs().length == 0) {
      stringBuilder.append(" ");
    } else if (joinPoint.getArgs().length > 1) {
      stringBuilder.append(joinPoint.getArgs()[0].toString());
    } else {
      Object obj = joinPoint.getArgs()[0];
      if (obj instanceof Student) {
        Student student = (Student) obj;
        stringBuilder.append(student.getName());
      } else if (obj instanceof Course) {
        Course course = (Course) obj;
        stringBuilder.append(course.getName());
      } else {
        Score score = (Score) obj;
        stringBuilder.append(score.getStudentId());
      }
    }

    logger.info(  joinPoint.getSignature().getDeclaringType().getName()
        +" will "+ joinPoint.getSignature().getName()
        + " :"+stringBuilder);
  }

}
