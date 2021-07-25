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

    public static void main(String[] args) {
        System.out.println(Calculator.Operator.PLUS);
    }

}

// 구문상의 차이는 static이 붙어 있는가의 차이지만, 의미상의 차이는 비정적 멤버 클래스의 인스턴스가 바깥 클래스의 인스턴스와 연결된다는 점에 있다.
// 그래서 다음과 같이 정규화된 this를 이용하여 바깥 인스턴스의 인스턴스 메서드를 호출 할 수 있다.
// 개념상 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면 정적 멤버 클래스로 만들어야한다.
// 비정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.
class NestedNonStaticExample {

    private final String name;

    public NestedNonStaticExample(String name) {
        this.name = name;
    }

    public String getName() {
        // 비정적 멤버 클래스와 바깥 클래스의 관계가 확립되는 부분
        // 보통 생성자로 만들어지지만, 드물게는 직접 바깥에서 직접 생성하기도 한다(new OuterClass().new Inner())
        NonStaticClass nonStaticClass = new NonStaticClass("nonStatic : ");
        return nonStaticClass.getNameWithOuter();
    }

    // 문제점 : 이 관계 정보는 비정적 멤버 클래스의 인스턴스 안에 만들어져 메모리 공간을 차지하며, 생성 시간도 더 걸린다.
    // 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자
    // static을 생략하면 바깥 인스턴스로의 숨은 외부 참조를 갖게 된다.
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
