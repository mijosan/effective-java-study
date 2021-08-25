package item34;

public enum Operation {

    PLUS, MINUS, TIMES, DIVIDE;
    
    public double apply(double x, double y) {
        switch(this) {
            case PLUS: return x + y;
            case MINUS: return x - y;
            case TIMES: return x * y;
            case DIVIDE: return x / y;
        }
        throw new AssertionError("알 수 없는 연산: " + this);
    }

}
// 위 코드는 정상적으로 실행되나 그리 적절한 코드라고 보기는 어렵습니다. 
// 마지막에 선언된 throw 문은 실제로 실행될 경우가 적지만 기술적으로는 도달할 수 있습니다. 그리고 깨지기 쉬운 코드인데요. 
// 예컨대 새로운 상수를 추가하면 해당 case 문장도 추가해야 합니다.
// 그렇다면 상수에서 알맞게 재정의하는 방법을 쓰는 것은 어떨까요?


// 상수별 메서드 구현
enum Operation2 {

    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }
    }
    // apply 메서드가 상수 선언 바로 밑에 있으니 새로운 상수를 추가할 때도 apply 메서드를 항상 재정의해야 한다는 사실을 까먹기 어렵습니다. 
    // 그리고 apply 메서드가 추상 메서드이므로 재정의하지 않았다면 컴파일 오류도 알려줍니다.
    public abstract double apply(double x, double y);

}


// 상수별 메서드 구현을 상수별 데이터와 결합할 수도 있습니다. 
// 예를 들어 아래와 같이 Operation의 toString을 재정의하여 해당 연산을 뜻하는 기호를 반환하도록 해봅시다.
class EffectiveJava34 {
    public static void main(String []args){
        double x = Double.parseDouble("2");
        double y = Double.parseDouble("3");
        for (Operation3 op : Operation3.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}

enum Operation3 {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x * y;
        }
    };

    private final String symbol;

    Operation3(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);
}

// 출력
// 2.000000 + 3.000000 = 5.000000
// 2.000000 - 3.000000 = -1.000000
// 2.000000 * 3.000000 = 6.000000
// 2.000000 / 3.000000 = 6.000000