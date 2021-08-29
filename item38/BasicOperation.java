package item38;

// 열거 타입은 거의 모든 상황에서 타입 안전 열거 패턴(typesafe enum pattern)보다 우수하다. 
// 단, 예외가 있다. 열거 타입은 확장을 할 수가 없다는 점이다. 
// 즉, 타입 안전 열거 패턴은 확장을 통해 다음 값을 추가하고 다른 목적으로 사용할 수 있는 반면, 열거 타입은 그렇게 할 수없다.
// 하지만 대부분 상황에서 열거 타입을 확장하는 것은 좋지 않은 생각이다.

// 일반적으로 열거 타입 확장이 안 좋은 이유
// 확장한 타입의 원소는 기반 타입의 원소로 취급하지만 그 반대는 아니다.
// 기반 타입과 확장된 타입들의 원소 모두를 순회할 방법도 마땅치 않다.
// 확장성을 높이려면 고려할 요소가 늘어나 설계와 구현이 복잡해진다.

// 확장할 수 있는 열거 타입은 연산 코드에 잘 어울린다. 
// API가 제공하는 기본 연산 외에 사용자 확장 연산을 추가할 수 있도록 열어줄 때 유용하게 사용할 수 있다. 열거 타입으로도 이 효과를 구현해낼 수 있다. 바로 열거 타입이 인터페이스를 구현할 수 있다는 점을 이용하는 것이다.
public enum BasicOperation implements Operation {

    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override 
    public String toString() {
        return symbol;
    }

}
