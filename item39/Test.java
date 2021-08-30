import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 테스트 메서드임을 선언하는 애너테이션이다.
 * 매개변수 없는 정적 메서드 전용이다.
*/
// 메타애너테이션
@Retention(RetentionPolicy.RUNTIME) // @Test가 런타임에도 유지되어야 한다는 표시다. (이 메타애너테이션을 생략하면 테스트 도구는 @Test를 인식할 수 없다.)
@Target(ElementType.METHOD) // 이 메타애너테이션은 @Test가 반드시 메서드 선언에서만 사용돼야 한다고 알려준다.
public @interface Test { // 이와 같은 애너테이션을 아무 매개변수 없이 단순히 대상에 마킹(marking)한다는 뜻에서 마커 애너테이션이라 한다.
                  // 이 애너테이션을 사용하면 프로그래머가 Test이름에 오타를 내거나 메서드 선언 외의 프로그램 요소에 달면 컴파일 오류를 내준다.
}