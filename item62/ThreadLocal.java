package item62;

import java.util.ArrayList;
import java.util.List;

// 다른 타입이 적절하다면 문자열 사용을 피하라 !

// 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
// 문자열은 열거 타입을 대신하기에 적합하지 않다.
// 문자열은 흔한 타입을 대신하기에 적합하지 않다.
public class ThreadLocal {
    
    // 이는 단점이 많은 방식이다. 혹여라도 두 요소를 구분해주는 문자 #이 두 요소중 하나에서 쓰였다면 혼란스러운 결과를 초래한다.
    // 각 요소를 개별로 접근하려면 문자열을 파싱해야 해서 느리고, 귀찮고, 오류 가능성도 커진다.
    // 이런 클래스는 보통 private 정적 멤버 클래스로 선언한다.
    public static void main(String[] args) {
        List<Test> list = new ArrayList<Test>();
        Test test = new Test();

        list.add(test);

        List list2 = list;
        list2.add("hello");

        ((Test)list2.get(0)).test();
    }


}

// 핵심정리
// 더 적합한 데이터 타입이 있거나 새로 작성할 수 있다면, 문자열을 쓰고 싶은 유혹을 뿌리쳐라.
// 문자열은 잘못 사용하면 번거롭고, 덜 유연하고, 느리고, 오류 가능성도 크다.
// 문자열을 잘못 사용하는 흔한 예로는 기본 타입, 열거타입, 혼합 타입이 있다.
