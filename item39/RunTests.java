import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName("Sample2"); // 정규화된 클래스 이름을 받음
        // for (Method m : testClass.getDeclaredMethods()) {
        //     if (m.isAnnotationPresent(Test.class)) {
        //         tests++;
        //         try {
        //             m.invoke(null);
        //             passed++;
        //         } catch (InvocationTargetException wrappedExc) { // 테스트 메서드가 예외를 던지면 리플렉션 메커니즘이 InvocationTargetException으로 감싸서 다시 던진다.
        //             Throwable exc = wrappedExc.getCause();
        //             System.out.println(m + " 실패: " + exc);
        //         } catch (Exception exc) { // InvocationTargetException외의 예외가 발생한다면 @Test 애너테이션을 잘못 사용했다는 뜻이다. (인스턴스 메서드, 매개변수가 있는 메서드, 호출할 수 없는 메서드 등에 달았을 것)
        //             System.out.println("잘못 사용한 @Test: " + m);
        //         }
        //     }
        // }
        // System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
                } catch (InvocationTargetException wrappedExc) { // 테스트 메서드가 예외를 던지면 리플렉션 메커니즘이 InvocationTargetException으로 감싸서 다시 던진다.
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외 %s%n", m, excType.getName(), exc);
                    }
                } catch (Exception exc) {
                    System.out.printf("잘못 사용한 @ExceptionTest: " + m);
                }
            }
        }
    }

}
