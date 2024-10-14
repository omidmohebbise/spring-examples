package com.omidmohebbise.aspect.aspect;

import com.omidmohebbise.aspect.domain.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogPersonServiceAspect {


    @Before("execution(* com.omidmohebbise.aspect.service.PersonService.addPerson(String, String))")
    public void logBeforeAddPerson(JoinPoint joinPoint) {
        System.out.print("******** new Person added => ");
        var signiture = joinPoint.getSignature().getName();
        var args = Arrays.stream(joinPoint.getArgs()).map(obj->obj.toString()+"\t").collect(Collectors.joining());
        System.out.println(signiture +"\t"+ args);
    }

    @After("execution(* com.omidmohebbise.aspect.service.PersonService.addPerson(String, String))")
    public void logAfterAddPerson(JoinPoint joinPoint) {
        System.out.println("Person added");
    }

    @AfterReturning(value = "execution(* com.omidmohebbise.aspect.service.PersonService.addPerson(String, String))",returning = "result")
    public void logAfterReturningAddPerson(JoinPoint joinPoint, Object result) {
        Person p = (Person) result;
        if(p != null)
        System.out.printf("After adding new person  (method service: %s)=> %d %s %s" ,joinPoint.getSignature().getName(), p.getId() , p.getName(), p.getFamily() );

    }

    @Pointcut(value = "execution(* com.omidmohebbise.aspect.service.PersonService.addPerson(String, String))",argNames = "name, family")
    public void addPersonPct(){}


    @After("addPersonPct()")
    public void testPointCut(JoinPoint joinPoint){
        System.out.println("test pointcut => target class " + joinPoint.getTarget().getClass().getName());
    }

    @Around("addPersonPct()")
    public Person aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        //System.out.println("## Around ##  " + joinPoint.getArgs());
        //System.out.println("## Around ##  " + joinPoint.proceed());
        return (Person) joinPoint.proceed();
    }
}
