package item52;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 다중정의는 신중히 사용하라 !
// 이름이 같은 메서드가 매개변수의 타입이나 개수만 다르게 갖는 형태를 다중정의(overloading)라고 한다. 이 다중 정의를 사용할 때는 신중해야 한다.
public class Multi {
    
}

// 잘 동작할 것 같은 코드이다. 하지만 이 프로그램은 "그 외"만 3번 출력한다. 다중정의된 classify() 메서드는 컴파일 타임에 어떤 메서드가 호출될 것인지 정해지기 때문이다.
// for문 안에 Collection<?> c는 런타임에 타입이 결정되고 달라진다. 즉, 컴파일 타임에는 항상 Collection <?> 타입이라는 것이다.
// 컴파일 타임에 어떤 classify() 메서드가 호출될 것인지 결정되기 때문에  classify(Collection<?>) 메서드만 3번 호출되는 것이다.
// 이처럼 직관과 어긋나게 동작하는 이유는 재정의한 메서드는 동적으로 선택되고, 다중정의한 메서드는 정적으로 선택되기 때문이다.
class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "집합";
    }

    public static String classify(List<?> lst) {
        return "리스트";
    }

    public static String classify(Collection<?> c) {
        return "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
    }
}

// 다중정의가 혼동을 일으키는 상황을 피하자 !
// 1. API 사용자가 매개변수를 넘길 때, 어떤 다중정의 메서드가 호출될지 모른다면 프로그램은 오작동하기 쉽다.
// 2. 헷갈릴 수 있는 코드는 작성하지 말자. (위의 다중정의 예시처럼)
// 3. 안전하고 보수적으로 가려면 매개변수 수가 같은 다중정의는 만들지 말자.
// 4. 가변 인수를 매개변수로 사용한다면 다중정의는 사용하면 안 된다.
// 5. 이 규칙들만 잘 따르면 다중정의가 혼동을 일으키는 일을 피할 수 있다. 이 외에 다중정의하는 대신 메서드 이름을 다르게 지어주는 방법도 존재한다.

// 이 규칙들만 잘 따르면 다중정의가 혼동을 일으키는 일을 피할 수 있다. 이 외에 다중정의하는 대신 메서드 이름을 다르게 지어주는 방법도 존재한다.

// 생성자 다중정의
// 생성자는 이름을 다르게 지을 수 없으니 두 번째 생성자부터는 무조건 다중정의가 된다. 이러한 상황에 정적 팩터리 메서드가 적절한 대안이 될 수 있다.
// 또한, 생성자는 재정의할 수 없으니 다중정의와 재정의가 혼용될 걱정도 없다. 그래도 여러 생성자가 같은 수의 매개변수를 받아야 하는 경우는 피할 수 없으니 그에 따른 안전 대책을 배워야 한다.




// 이 코드도 마찬가지로 [-3, -2, -1]이 출력되어야 할 것 같다.
// 하지만 전혀 다른 결과가 출력된다. 왜 [-2, 0, 2]가 출력될까?
// 그 이유는 List의 remove가 다중정의되있기 때문이다.
// 위의 코드에서는 remove(Object)가 아닌 remove(int index) 메서드가 선택된다. 따라서 값이 아닌 index의 원소를 제거하기 때문에 [-2, 0, 2]라는 값이 출력되는 것이다.
// Java4까지는 Object와 int가 근본적으로 달라 문제가 없었지만, Java5에 오토 박싱이 도입되면서 이 개념이 흐트러졌다. 즉, 이제는 int와 Integer가 근본적으로 다르지 않다는 것이다. 이 문제는 remove를 호출할 때 매개변수를 Integer로 형 변환해주면 해결된다.
// 위의 예시에서 중요한 점은 제네릭과 오토 박싱(신규 기능)이 추가되면서 기존의 List 인터페이스가 취약해졌다는 것이다. (다중정의에 의해)
// 이 예시만으로도 다중정의를 왜 신중하게 사용해야 하는지에 대한 충분한 근거가 된다.
class SetList {
    public static void main(String[] args) {
       List<Integer> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            list.remove(i);
        }
        System.out.println(list);
    }

}

// 핵심 정리
// 일반적으로 매개변수 수가 같을 때는 다중정의를 피하는 게 좋다.
// 만약 다중정의를 피할 수 없는 상황이라면 헷갈릴 만한 매개변수는 형변환하여 정확한 다중정의 메서드가 선택되도록 하자.