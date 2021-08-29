package item38;

import java.util.Arrays;
import java.util.Collection;

// 이처럼 다른 연산 타입이 필요하다면 우리가 해야할 일은 Operation 인터페이스를 구현한 다른 열거 타입을 정의하는 것뿐이다. 
// 새로 정의한 연산은 기존 연산(BasicOperation)을 사용하던 곳이면 어디에든 사용할 수 있다.
public enum ExtendedOperation implements Operation {

    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;
    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override 
    public String toString() {
        return symbol;
    }

    // 확장된 열거 타입의 원소 모두를 사용하게 하는 방법
    // 확장된 열거 타입의 원소 모두를 사용하게 하는 방법은 두 가지가 있다.

    // Class 객체 대신 한정적 와일드카드 타입을 넘기는 방법
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);

        test2(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    // Class 객체에 한정적 타입 토큰을 넘기는 방법
    // <T extends Enum<T> & Operation> 의미
    // Class 객체가 열거 타입인 동시에 구현한 인터페이스의 하위 타입이어야 한다.
    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    // 두 번째 대안인 Class 객체 대신 한정적 와일드카드 타입을 넘기는 방법은 다음과 같다.
    // 코드가 덜 복잡하고 test 메서드가 여러 구현 타입의 연산을 조합해 호출할 수 있게 되어 조금 더 유연해졌다. 
    // 하지만 특정 연산에서 EnumSet과 EnumMap을 사용하지 못한다.
    private static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }


    // 확장 가능한 열거 타입을 흉내 내는 방식의 문제점!
    // 열거 타입끼리 구현을 상속할 수 없다는 사소한 단점이 존재한다. 
    // 이 단점은 아무 상태에도 의존하지 않는 경우 인터페이스에 디폴트 메서드를 정의해 극복할 수 있다. 
    // 반면, 앞서 살펴본 Operation 예제에서는 연산 기호를 저장하고 찾는 로직이 각각 들어가야 한다. 
    // 이 예제에서는 중복량이 적어 문제 되지 않지만 공유하는 기능이 많다면 별도의 도우미 클래스나 정적 도우미 메서드로 분리해서 중복을 줄여야 한다.

}
