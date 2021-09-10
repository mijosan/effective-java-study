package item60;

// 정확한 답이 필요하다면 float와 double은 피하라

// float와 double 타입은 과학과 공학 계산용으로 설계되었다. 이진 부동소수점 연산에 쓰이며, 넓은 범위의 수를 빠르게 정밀한 "근사치"로 계산하도록 세심하게 설계되었다.
// 따라서 정확한 결과가 필요할 때는 사용하면 안 된다. float와 double타입은 특히 금융 관련 계산과는 맞지 않는다.

// 금융 계산에는 BigDecimal, int 혹은 long을 사용해야 한다.
// BigDecimal의 생성자 중 문자열을 받는 생성자를 사용했음에 주목하자.
// 계산시 부정확한 값이 사용되는 걸 막기 위해 필요한 조치다.

// BigDecimal의 단점 두가지
// 1. 기본 타입보다 쓰기가 훨씬 불편
// 2. 훨씬 느리다

// BigDecimal의 대안으로 int 혹은 long 타입을 쓸 수도 있다. 그럴 경우 다룰수 있는 값의 크기가 제한되고, 소수점을 직접 관리해야 한다.
public class Double {
    public static void main(String[] args) {
        System.out.println(1.03 - 0.42); // 결과 : 0.6100000000000001
    }
}
