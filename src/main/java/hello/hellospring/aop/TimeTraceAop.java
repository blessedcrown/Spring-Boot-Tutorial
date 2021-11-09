package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*AOP가 필요한 상황
모든 매소드의 호출 시각을 찍어봐야 한다고 가정하자.
다음과 같이 구현을 해야한다. 100개의 매소드면 100번 반복해야 하는 상황
이러한 상황이 발생하면 다음과 같은 문제가 발생한다
    - 시간을 측정하는 로직은 공통관심 사항이다
    - 시간을 측정하는 로직과 핵심 비지니스 로직이 섞여 유지보수가 어렵다
    - 시간을 측정하는 로직을 변경할때 모든 로직을 찾아가면 변경해야한다
    - 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다
*/
@Aspect
@Component
public class TimeTraceAop {

    //이 공통 매소드를 어디에 적용시킬지를 정하는 애노테이션
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.print("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
