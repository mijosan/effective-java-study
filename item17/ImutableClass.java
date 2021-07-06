package item17;

import org.omg.PortableServer.IMPLICIT_ACTIVATION_POLICY_ID;

// 불변 클래스란 인스턴스의 내부 값을 수정할 수 없는 클래스다.
// 그예로는 String, BigInteger, BigDeciaml이 있다.
// 불변 클래스를 만드는 다섯 가지 규칙
// 1. 객체의 상태를 변경하는 메서드를 제공하지 않는다 (setter)
// 2. 클래스를 확장할 수 없도록 (final 클래스)
// 3. 모든 필드를 final로 선언
// 4. 모든 필드를 private으로 선언한다. (필드가 참조하는 가변 객체를 클라이언트에서 직접 접근해 수정하는것 막음)
// (public final로만 선언해도 불변 객체가 되지만, 이렇게 하면 다음 릴리스에서 내부 표현을 바꾸지 못한다.)
// 5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다. (참조를 못얻도록 막아야함 setter(x), private)
// 불변 객체는 근본적으로 스레드 안전하여 따로 동기화할 필요 없다.
// 불변 클래스라면 한번 만든 인스턴스를 최대한 재활용하기를 권장 한다. (메모리를 아낌)
// 정적 팩터리 메서드를 사용하면 자주 사용되는 인스턴스를 캐싱하여 같은 인스턴스를 중복 생성하지 않게 해준다
// (특정 값으로 넘어온 매개변수가 이미 존재한다면 미리 캐싱된 인스턴스를 리턴하면 된다.)
// 불변 객체는 자유롭게 공유할 수 있음은 물론, 불변 객체끼리는 내부 데이터를 공유할 수 있다.
// 불변 객체는 Map의 키와 Set의 원소로 쓰기에 안성맞춤이다. 안에 담긴 값이 바뀌면 불변식이 허물어지는데
// 불변 객체를 사용하면 그런 걱정은 하지 않아도 된다.
class ImutableClass {

    private final double re;
    private final double im;

    private ImutableClass(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // package-private 구현 클래스를 원하는 만큼 만들어 활용할 수 있으니 훨씬 유연하다.
    // 패키지 바깥의 클라이언트에서 바라본 이 불변 객체는 사실상 final이다.
    // 정적 팩터리 방식은 다수의 구혀 클래스를 활요한 유연성을 제공하고, 이에 더해 다음 릴리스에서
    // 객체 캐싱 기능을 추가해 성능을 끌어올릴 수도 있다.
    // 클래스는 꼭 필요한 경우가 아니라면 불변이어야 한다.
    // 불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분은 최소한으로 줄이자.
    // 꼭 변경해야 할 필드를 뺀 나머지 모두를 final로 선언하자.
    // 다른 합당한 이유가 없다면 모든 필드는 private final이어야 한다.
    // 객체를 재활용할 목적으로 상태를 다시 초기화하는 메서드도 안된다. 복잡성만 커지고 성능 이점은 거의 없다.
    public static ImutableClass valueOf(double re, double im) {
        return new ImutableClass(re, im);
    }

}

// 실패 원자성 : 메서드에서 예외가 발생한 후에도 그 객체는 여전히 (메서드 호출 전과 똑같은)
// 유효한 상태여야 한다는 성질이다. 불변 객체의 메서드는 내부 상태를 바꾸지 않으니 이 성질을 만족한다.

// 불변 객체체의 단점 : 값이 다르면 반드시 독립된 객체로 만들어야한다. (값의 가짓수가 많다면 이들을 모두 만드는데 큰 비용을 치러야한다.)
// String의 가변 동반 클래스는 StringBuilder, StringBuffer 가 있다.