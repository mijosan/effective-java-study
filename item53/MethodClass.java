package item53;

// 가변인수는 신중히 사용하라 !

// 가변인수 메서드는 명시한 타입의 인수를 0개 이상 받을 수 있따.
public class MethodClass {
    
    static int sum(int... args) {
        int sum = 0;

        for (int arg : args) {
            sum += arg;
        }

        return sum;
    }

    // 인수가 1개 이상이어야 하는 가변인수 메서드 - 잘못 구현한 예 !
    // 인수 개수는 런타임에 (자동 생성된) 배열의 길이로 알 수 있다.
    // 이 방식에는 문제가 몇 개 있다.
    // 1. 가장 심각한 문제는 인수를 0개만 넣어 호출하면 런타임에 실패한다는 점이다. 코드도 지저분하다.
    // 2. args 유효성 검사를 명시적으로 해야함, min의 초기값을 Integer.MAX_VALUE로 설정하지 않고는 for-each 문도 사용할 수 없다.
    static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }

        int min = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }

        return min;
    }

    // 인수가 1개 이상이어야 할 때 가변인수를 제대로 사용하는 방법
    static int min2(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }

        return min;
    }

}

// 가변인수는 인수 개수가 정해지지 않았을 때 아주 유용하다.

// 단점
// 1. 가변인수 메서드는 호출될 때마다 배열을 새로 하나 할당하고 초기화한다. 다행히, 이 비용을 감당할 수는 없지만 가변인수의 유연성이 필요할 때 선택할 수 있는 멋진 패턴이 있다.

// 예를 들어 메서드 호출의 95%가 인수를 3개 이하로 사용한다고 해보자
// 마지막 다중저으이 메서드가 인수 4개 이상인 5%의 호출을 담당하는 것이다.
// public void foo() {}
// public void foo(int a1) {}
// public void foo(int a1, int a2, int a3) {}
// public void foo(int a1, int a2, int a3, int... rest) {}

// 핵심정리 !
// 인수 개수가 일정하지 않은 메서드를 정의해야 한다면 가변인수가 반드시 필요하다. 메서드를
// 정의할 때 필수 매개변수는 가변인수 앞에 두고, 가변인수를 사용할 때는 성능 문제까지 고려하자.
