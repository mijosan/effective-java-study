package item30;

import java.util.HashSet;
import java.util.Set;

// 이왕이면 제네릭 메서드로 만들라 !

// 클래스와 마찬가지로, 메서드로 제네릭으로 만들 수 있다. 매개변수화 타입을
// 받는 정적 유틸리티 메서드는 보통 제네릭이다.
public class GenericMethod {

    // 컴파일은 되지만 타입을 안전하게 만들라는 경고가 두개 발생한다.
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        
        return result;
    }

    // 메서드 선언에서의 세 집합(입력 2개, 반환 1개)의 원소 타입을 타입 매개변수로 명시하고,
    // 메서드 안에서도 이 타입 매개변수만 사용하게 수정하면 된다.
    // 타입 매개변수들을 선언하는 타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다.
    public static <E> Set<E> union2(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        
        return result;
    }

    public static void main(String[] args) {
        Set<String> guys = new HashSet<String>();
        guys.add("톰");
        guys.add("딕");
        guys.add("해리");

        Set<String> stooges = new HashSet<String>();
        guys.add("래리");
        guys.add("모에");
        guys.add("컬리");

        // union2 메서드는 집합 3개(입력 2개, 반환 1개)의 타입이 모두 같아야 한다.
        // 이를 한정적 와일드카드 타입을 사용하여 더 유연하게 개선할 수 있따.
        Set<String> aflCio = union2(guys, stooges);
        System.out.println(aflCio);


        Object obj = "hi";
        String str = (String) obj;
        System.out.println(obj);
    }


}