package item26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 로 타입은 사용하지 말라 (List<E>의 로 타입은 List다)
// 제네릭이 도래하기 전 코드와 호환되도록 하기 위한 궁여지책 이다.
// 오류는 가능한 한 발생 즉시, 이상적으로는 컴파일할 때 발견하는 것이 좋다. (런타임 오류는 좋지 않다.)
public class RowType {
    
    // 컴파일러는 list에 string의 인스턴스만 넣어야 함을 컴파일러가 인지하게 된다.
    // 따라서 아무런 경고 없이 컴파일된다면 의도대로 동작할 것임을 보장한다.
    private final List<String> list = new ArrayList<String>();

    // 로 타입을 쓰면 제네릭이 안겨주는 안정성과 표현력을 모두 잃게 된다.
    // 호환성 때문에 만들어 놓음
    // List<Object> 같은 매개변수화 타입을 사용할 때와 달리 List 같은 로 타입을 사용하면 타입 안전성을 읽게 된다.
    private final List rawList = new ArrayList();

    // List<Object>처럼 임의 객체를 허용하는 매개변수화 타입은 괜찮다.
    // 모든 타입을 허용한다는 의사를 컴파일러에 명확히 전달한 것이다.
    private final List<Object> objectList = new ArrayList<Object>(); // new ArrayList<String>() <= 불가능 (제네릭의 하위 타입 규칙 때문 List<Object>의 하위 타입이 아니다)

    public static void main(String[] args) {
        RowType rowType = new RowType();

        // 컴파일 오류가 발생
        rowType.list.add(2);


        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, Integer.valueOf(42));

        String s = strings.get(0); // 컴파일러가 자동으로 형변환 코드를 넣어준다.

        Set<String> s1 = new HashSet<String>();
        Set<String> s2 = new HashSet<String>();

        numElementsInCommon2(s1, s2);
    }

    private static void unsafeAdd(List list, Object o) { // List<Object> list, Object o 로 변경하면 컴파일 오류가 발생하여 미리 알수 있다.
        list.add(o);
    }

    // 이 메서드는 동작은 하지만 로 타입을 사용해 안전하지 않다. 따라서 비한정적 와일드카드 타입을 대신 사용하는 게 좋다.
    // 제네릭 타입을 쓰고 싶지만 실제 타입 매개변수가 무엇인지 신경 쓰고 싶지 않다면 물음표(?)를 사용하자. Set<?>
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }

        return result;
    }

    // 비한정적 와일드카드 타입
    // 결론부터 말하자면 와일드카드 타입은 안전하고, 로 타입은 안전하지 않다.
    // 로 타입 컬렉션에는 아무 원소나 넣을 수 있으니 타입 불변식을 훼손하기 쉽다.
    // 반면, Collection<?>에는 (null 외에는) 어떤 원소도 넣을 수 없다. 다른 원소를 넣으려 하면 컴파일 할 때 다음의 오류 메세지를 보게 된다.
    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }

        return result;
    }

}
