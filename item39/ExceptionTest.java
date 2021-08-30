import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 특정 예외를 던져야만 성공하는 테스트를 지원하도록 해보자

/**
 * 명시한 예외를 던져야만 성공하는 테스트 메서드용 애너테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value(); // 이 애너테이션의 매개변수 타입은 Class<? extends Throwable> 이다.
    // 여기서의 와일드카드 타입은 많은 의미를 담고 있다.
    // "Throwable을 확장한 클래스의 Class 객체" 라는 뜻이며, 따라서 모든 예외타입을 다 수용한다.
    // 이는 한정적 타입 토큰의 또 하나의 활용 사례다.
}
