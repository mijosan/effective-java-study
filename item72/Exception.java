package item72;

// 표준 예외를 사용하라 !

// 장점 
// 1. 다른 사람이 익히고 사용하기 쉬워진다는 것이다.
// 2. 예외 클래스 수가 적을수록 메모리 사용량도 줄고 클래스를 적재하는 시간도 적게 걸린다.

// Exception, RuntimeException, Throwable, Error는 직접 재사용하지 말자.
// 이 클래스들은 추상 클래스라고 생각하길 바란다. 이 예외들은 다른 예외들의 상위 클래스이므로,
// 즉 여러 성격의 예외들을 포괄하는 클래스이므로 안정적으로 테스트할 수 없다.

// IllegalArgumentException : 허용하지 않는 값이 인수로 건네졌을 때 (null은 따로 NPE으로 처리)
// IllegalStateException : 객체가 메서드를 수행하기에 적절하지 않은 상태일 때
// NullPointerException : null을 허용하지 않는 메서드에 null을 건넸을 때
// IndexOutOfBoundsException : 인덱스가 범위를 넘어섰을 때
// ConcurrentModificationException : 허용하지 않는 동시 수정이 발견됐을 때
// UnsupportedOperationException : 호출한 메서드를 지원하지 않을 때

// 상황에 부합한다면 항상 표준 예외를 재사용하자.
public class Exception {
    
}
