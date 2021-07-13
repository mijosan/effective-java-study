package item19;

import java.time.Instant;

public class Sub extends Super {

    private final Instant instant;

    Sub() {
        instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    // 상위 클래스의 생성자가 하위 클래스의 생성자보다 먼저 호출되므로
    // 하위 클래스에서 재정의한 메서드가 하위 클래스의 생성자보다 먼저 호출된다.
    // 이때 재정의한 메서드가 하위 클래스의 생성자에서 초기화하는 값에 의존한다면 의도대로 동작하지 않을것이다.
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }

}
