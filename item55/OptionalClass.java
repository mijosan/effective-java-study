package item55;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

// 옵셔널 반환은 신중히 하라

// 자바 8 전에는 메서드가 특정 조건에서 값을 반환할 수 없을 때 취할 수 있는 선택지가 두가지 있었다.
// 예외를 던지거나, null을 반환하는 것이다.

// 두가지 모두 허점이 있다.
// 예외는 진짜 예외적인 상황에서만 사용해야 하며 예외를 생성할 때 스택 추적 전체를 캡처하므로 비용도 만만치 않다.
// null을 반환하면 이런 문제가 생기지 않지만, item54에서 보았다 싶이 문제가 있다.

// 자바 버전이 8로 올라가면서 또 하나의 선택지가 생겼다.
// 그 주인공인 Optional<T>는 null이 아닌 T타입 참조를 하나 담거나, 혹은 아무것도 담지 않을 수 있다.
// 아무것도 담지 않은 옵셔널은 "비었다"고 말한다. 반대로 어떤 값을 담은 옵셔널은 "비지 않았다"고 한다.
// 옵셔널은 원소를 최대 1개 가질 수 있는 "불변" 컬렉션이다. Optional<T>가 Collection<T>를 구현하지는 않았지만, 원칙적으로 그렇다는 말이다.

// 보통은 T를 반환해야 하지만 특정 조건에서는 아무것도 반환하지 않아야 할 때 T 대신 Optional<T>를 반환하도록 선언하면 된다.
// 그러면 유효한 반환값이 없을 때는 빈 결과를 반환하는 메서드가 만들어진다.
// 옵셔널을 반환하는 메서드는 예외를 던지는 메서드보다 유연하고 사용하기 쉬우며, null을 반환하는 메서드보다 오류 가능성이 적다.
public class OptionalClass {
    
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("빈 컬렉션");
        }

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    // Optional<E>로 수정
    // 옵셔널은 검사 예외와 취지가 비슷하다. 즉, 반환 값이 없을 수도 있음을
    // API사용자에게 명확히 알려준다. 비검사 예외를 던지거나 null을 반환한다면 API사용자가 
    // 그 사실을 인지하지 못해 끔찍한 결과로 이어질 수 있다.
    // 하지만 검사 예외를 던지면 클라이언트에서는 반드시 이에 대처하는 코드를 작성해넣어야 한다.
    public static <E extends Comparable<E>> Optional<E> max2(Collection<E> c) {
        if (c.isEmpty()) {
            // 정적 팩터리 메서드
            return Optional.empty();
        }

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        // 적절한 정적 팩터리를 사용해 옵셔널을 생성해주기만 하면 된다.
        // 정적 팩터리 메서드
        // value에 null을 넣으면 NPE를 던지니 주의하자.
        return Optional.of(result);

        // null 값도 허용하는 옵셔널을 만들기
        // 옵셔널을 반환하는 메서드에서는 절대 null을 반환하지 말자.
        // 옵셔널을 도입한 취지를 완전히 무시하는 행위다.
        // return Optional.ofNullable(result)
    }

    // 기본값을 정해둘 수 있다.
    // 비슷하게, 메서드가 옵셔널을 반환한다면 클라이언트는 값을 받지 못했을 때 취할 행동을 선택해야 한다.
    // 그중 하나는 기본값을 설정하는 방법이다.
    String lastWordInLexicon = max2(new ArrayList<>()).orElse("단어 없음...");

    // 원하는 예외를 던질 수 있다.
    // 또는 상황에 맞는 예외를 던질 수 있다. 다음 코드에서 실제 예외가 아니라 예외 팩터리를 건넷 것에 주목하자.
    // 이렇게 하면 예외가 실제로 발생하지 않는한 예외 생성 비용은 들지 않는다.
    String lastWordInLexicon2 = max2(new ArrayList<>()).orElseThrow(NullPointerException::new);

    // 항상 값이 채워져 있다고 가정한다.
    // 옵셔널에 항상 값이 채워져 있다고 확신한다면 그냥 곧바로 값을 꺼내 사용하는 선택지도 있다.
    // 다만 잘못 판단한 것이라면 NoSuchElementException이 발생할 것이다.
    String lastWordInLexicon3 = max2(new ArrayList<>()).get();

    // 반환값으로 옵셔널을 사용한다고 해서 무조건 득이 되는 건 아니다.
    // 컬렉션, 스트림, 배열, 옵셔널 같은 컨테이너 타입은 옵셔널로 감싸면 안 된다.
    // 빈 Optional<List<T>>를 반환하기보다는 빈 List<T>를 반환하는 게 좋다(아이템 54)
    // 빈 컨테이너를 그대로 반환하면 클라이언트에 옵셔널 처리 코드를 넣지 않아도 된다.

    // 그렇다면 어떤 경우에 메서드 반환 타입을 T 대신 Optional<T>로 선언해야 할까 ?
    // 기본 규칙은 이렇다. 결과가 없을 수 있으며, 클라이언트가 이 상황을 특별하게 처리해야 한다면 Optional<T>를 반환한다.
    // 박싱된 기본 타입을 담는 옵셔널은 기본 타입 자체보다 무거울 수밖에 없다. 값을 두 겹이나 감싸기 때문이다.

    // 박싱된 기본 타입을 담은 옵셔널을 반환하는 일은 없도록 하자. (OptionalInt, OptionalLong 사용 하면 됨)

    // 옵셔널은 맵의 값으로 사용하면 절대 안 된다. 만약 그리 한다면 맵 안에 키가 없다는 사실을 나타내는 방법이 두 가지가 된다.
    // 하나는 키자체가 없는 경우고, 다른 하나는 키가 있지만 그 키가 속이 빈 옵셔널인 경우다.
}

// 핵심 정리
// 값을 반환하지 못할 가능성이 있고, 호출할 때마다 반환값이 없을 가능성을 염두에 둬야 하는 메서드라면
// 옵셔널을 반환해야 할 상황일 수 있다. 하지만 옵셔널 반환에는 성능 저하가 뒤따르니,
// 성능에 민감한 메서드라면 null을 반환하거나 예외를 던지는 편이 나을 수 있다.
// 그리고 옵셔널 반환값 이외의 용도로 쓰는 경우는 매우 드물다.