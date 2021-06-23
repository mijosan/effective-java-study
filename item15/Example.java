package item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 접근제한자를 활용하여 클래스와 멤버의 접근성을 최대한 좁히고, 독자적인 컴포넌트를 만드는 것이 정보은닉인 것이다.
// public static final 필드는 기본 타입이나 불변 객체를 참조해야 한다.
// 하지만 public static final 배열 필드를 두거나 이를 반환하는 접근자 메서드는 두면 안된다.
// 다른 객체를 참조하도록 바꿀 수는 없지만 참조된 객체 자체가 수정될 수는 있다.
// 길이가 0이 아닌 배열은 모두 변경 가능하다.
class Example {
    public static final Integer[] SOME_VALUES = {1, 2, 3};

    // 이런 경우는 public 으로 선언한 배열을 private 접근 지정자로 변경하고 변경 불가능한 public 리스트로 만드는 방법이 있다.
    public static final List<Integer> VALUES = Collections.unmodifiableList(Arrays.asList(SOME_VALUES));

    // 아니면 배열은 private으로 선언하고 해당 배열을 복사해서 반환하는 public 메서드를 추가할 수도 있다.
    public static final Integer[] values() {
        return SOME_VALUES.clone();
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(Example.SOME_VALUES[0]); // 1
        Example.SOME_VALUES[0] = 5;
        System.out.println(Example.SOME_VALUES[0]); // 5
    }
}

// public 클래스의 인스턴스 필드가 public 이 되어서는 안되는 이유
// 1. 변경에 매우 취약하다.

// 2. 스레드 안전하지 않다.
// 인스턴스 변수는 힙에 할당되며 공유자원이다. 즉, 모든 스레드가 이 공유자원에 접근 할 수 있다.
// public 인스턴스 변수는 Foo.resource와 같이 접근하기 때문에 다른 작업을 할 수 없다. 책의 예시처럼 Lock 획득 같은 
// "thread safe" 하도록 부가적인 작업을 할 수 없기 때문에 사용해서는 안된다.

// 4. 유일하게 허용되는 public static final 필드
// 추상 개념을 완성하는데 꼭 필요한 "상수"
// 관용적으로 대문자알파벳과 _ 의 조합으로 이루어져있으며 기본타입 또는 불변객체를 참조해야한다.