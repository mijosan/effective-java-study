package item70;

// 복구할 수 있는 상황에는 검사 예외를, 프로그래밍 오류에는 런타임 예외를 사용하라

// 자바는 문제 상황을 알리는 타입(throwable)으로 검사 예외, 런타임 예외, 에러, 이렇게 세 가지를 제공하는데
// 언제 무엇을 사용해야 하는지 헷갈려 하는 프로그래머들이 종종 있다.
// 언제나 100% 명확한 건 아니지만 이럴 때 참고하면 좋은 멋진 지침들이 있으니 함께 살펴보자

// 1. 호출하는 쪽에서 복구하리라 여겨지는 상황이라면 검사 예외를 사용하라.
// 검사 예외를 던지면 호출자가 그 예외를 catch로 잡아 처리하거나 더 바깥으로 전파하도록 강제하게 된다.
// 따라서 메서드 선언된 검사 예외 각각은 그 메서드를 호출했을 때 발생할 수 있는 유력한 결과임을 API 사용자에게 알려주는 것이다.
// 달리 말하면, API 설계자는 API 사용자에게 검사 예외를 던져주어 그 상황에서 회복해내라고 요구한 것이다.
// 물론 사용자는 예외를 잡기만 하고 별다른 조치를 취하지 않을 수도 있지만, 이는 보통 좋지 않은 생각이다.


// 비검사 throwable은 두 가지로, 바로 런타임 예외와 에러다. 둘 다 동작 측면에서는 다르지 않다.
// 프로그램에서 비검사 예외나 에러를 던졌다는 것은 복구가 불가능하거나 더 실행해봐야 득보다는 실이 많다는 뜻이다.

// 여러분이 구현하는 비검사 throwable은 모두 RuntimeException의 하위 클래스여야 한다.
public class Exception {
    
}

// 핵심 정리
// 복구할 수 있는 상황이면 검사 예외를, 프로그래밍 오류라면 비검사 예외를 던지자. 확실 하지 않다면
// 비검사 예외를 던지자. 검사 예외도 아니고 런타임 예외도 아닌 throwable은 정의하지도 말자
// 검사 예외라면 복구에 필요한 정보를 알려주는 메서드도 제공하자.
