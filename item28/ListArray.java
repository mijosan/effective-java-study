package item28;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// 배열보다는 리스트를 사용하라

// 이유1 : 배열은 공변이다. (Sub가 Super의 하위 타입이라면 배열 Sub[]는 배열 Super[]의 하위 타입이 된다.)
// 반면 제네릭은 불공변 이다. 즉, 서로 다른 타입 Type1과 Type2가 있을 때, List<Type1>은 List<Type2>의 하위 타입도 아니고 상위 타입도 아니다.
// 어느 쪽이든 Long용 저장소에 String을 넣을 수는 없다. 다만 배열에서는 그 실수를 런타임에야 알게 되지만, 리스트를 사용하면 컴파일할 때 바로 알 수 있다.
// 이유2 : 배열은 실체화(reify)된다. 배열은 런타임에도 자신이 담기로 한 원소의 타입을 인지하고 확인한다.
// 그래서 String을 넣으려 하면 ArrayStoreException 이 발생한다. 반면, 앞서 이야기했듯 제네릭은 타입 정보가 런타임에는 소거(erasure)된다.
// 원소 타입을 컴파일 타임에만 검사하며 런타임에는 알 수조차 없다는 뜻이다.
// * 소거는 제네릭이 지원되기 전의 레거시 코드와 제네릭 타입을 함께 사용할 수 있게 해주는 메커니즘으로,
// 자바 5가 제네릭으로 순조롭게 전환될 수 있도록 해줬다.
// 이러한 이유는 런타임에 ClassCastException이 발생하는 일을 막아주겠다는 제네릭 타입 시스템의 취지다.
public class ListArray {
    
    public static void main(String[] args) {
        // 런타임에 실패한다.
        Object[] objectArray = new Long[1]; // 공변
        objectArray[0] = "타입이 달라서 넣을 수 없다."; // ArrayStoreException을 던진다.

        // 컴파일되지 않는다!
        List<Object> ol = new ArrayList<Long>(); // 호환되지 않는 타입이다.
        ol.add("타입이 달라 넣을 수 없다.");

        // 제네릭 배열 생성을 허용하지 않는 이유는 ClassCastException이 발생하기 떄문,
        // 즉 제네릭 타입 시스템의 취지에 어긋나는 것이다.
        List<String>[] stringLists = new List<String>[1]; // 컴파일이 허용 된다고 가정
        List<Integer> intList = List.of(42); // 원소 하나 생성
        Object[] objects = stringLists; // 배열은 공변이기 때문에 Object 배열에 할당됨
        objects[0] = intList; // 제네릭은 런타임시 타입이 소거되기 때문에 성공함 (ArrayStoreException을 일으키지 않음)
        String s = stringLists[0].get(0); // 컴파일러는 꺼낸 원소를 자동으로 String으로 형변환하는데, 이 원소는 Integer이므로 런타임에 ClassCastException이 발생한다.
                                         // 이런 일을 방지하려면 (제네릭 배열이 생성되지 않도록) (1)에서 컴파일 오류를 내야 한다.
        // E, List<E>, List<String> 같은 타입을 실체화 불가 타입이라 한다. (쉽게 말해, 실체화되지 않아서 런타임에는 컴파일타임보다 타입 정보를 적게 가지는 타입이다.)
    }

}

class Chooser {

    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        choiceArray = choices.toArray();
    }

    // 이 메서드를 호출 할때마다 반환된 Object를 원하는 타입으로 형변환해야 한다.
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }

}

class Chooser2<T> {
    
    // private final T[] choiceArray;
    private final List<T> choiceList;

    public Chooser2(Collection<T> choices) {
        // choiceArray = (T[]) choices.toArray(); // T가 무슨 타입인지 알 수 없으니 컴파일러는 이 형변환이 런타임에도 안전한지 보장할 수 없다는 메시지다.
        choiceList = new ArrayList<>(choices);
    }

    // 코드양이 조금 늘었고 아마도 조금 더 느릴 테지만, 런타임에 ClassCastException을 만날 일은 없으니 그만한 가치가 있다.
    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

}

// 핵심 정리

// 배열과 제네릭에는 매우 다른 타입 규칙이 적용된다. 배열은 공변이고 실체화되는 반면, 제네릭은
// 불공변이고 타입 정보가 소거된다. 그 결과 배열은 런타임에는 타입 안전하지 만 컴파일타임에는 그렇지 않다.
// 제네릭은 반대다. 그래서 둘을 섞어 쓰기란 쉽지 않다. 둘을 섞어 쓰다가 컴파일 오류나 경고를 만나면,
// 가장 먼저 배열을 리스트로 대체하는 방법을 적용해보자
