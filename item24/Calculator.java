package item24;

public class Calculator {

    // 정적 멤버 클래스는 흔히 바깥 클래스와 함꼐 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.
    // Operation 열거 타입은 Calculator 클래스의 public 정적 멤버 클래스가 되어야 한다.
    // ex) Calculator.Operation.PLUS
    public enum Operator {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y);

        private final String token;
        private final Strategy strategy;

        Operator(String token, Strategy strategy) {
            this.token = token;
            this.strategy = strategy;
        }

        public double operate(double x, double y) {
            return this.strategy.operate(x, y);
        }

        private interface Strategy {
            double operate(double x, double y);
        }
    }

}

// 구문상의 차이는 static이 붙어 있는가의 차이지만, 의미상의 차이는 비정적 멤버 클래스의 인스턴스가 바깥 클래스의 인스턴스와 연결된다는 점에 있다.
// 그래서 다음과 같이 정규화된 this를 이용하여 바깥 인스턴스의 인스턴스 메서드를 호출 할 수 있다.
class NestedNonStaticExample {

    private final String name;

    public NestedNonStaticExample(String name) {
        this.name = name;
    }

    public String getName() {
        // 비정적 멤버 클래스와 바깥 클래스의 관계가 확립되는 부분
        NonStaticClass nonStaticClass = new NonStaticClass("nonStatic : ");
        return nonStaticClass.getNameWithOuter();
    }

    private class NonStaticClass {
        private final String nonStaticName;

        public NonStaticClass(String nonStaticName) {
            this.nonStaticName = nonStaticName;
        }

        public String getNameWithOuter() {
            // 정규화된 this 를 이용해서 바깥 클래스의 인스턴스 메서드를 사용할 수 있다.
            return nonStaticName + NestedNonStaticExample.this.getName();
        }
    }
}
