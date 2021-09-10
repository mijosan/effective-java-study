package item58;

// 전통적인 for 문보다는 for-each 문을 사용하라 !

// 반복자와 인덱스 변수를 사용하지 않으니 코드가 깔끔해지고 오류가 날 일도 없다.
// 하나의 관용구로 컬렉션과 배열을 모두 처리할 수 있어서 어떤 컨테이너를 다루는지는 신경 쓰지 않아도 된다.
public class ForEach {
    
    for (Element e : elements) { // 여기서 콜론은 "안의(in)"라고 읽으면 된다. 따라서 이 반복문은 "elements 안의 각 원소 e에 대해"라고 읽는다.

    }

    // 안타깝게도 for-each 문을 사용할 수 없는 상황이 세 가지 존재한다.
    // 1. 파괴적인 필터링 - 컬렉션을 순회하면서 선택된 원소를 제거해야 한다면 반복자의 remove 메서드를 호출해야 한다.
    // 2. 변형 - 리스트나 배열을 순회하면서 그 원소의 값 일부 혹은 전체를 교체해야 한다면 리스트의 반복자나 배열의 인덱스를 사용해야 한다.
    // 3. 병렬 반복 - 여러 컬렉션을 병렬로 순회해야 한다면 각각의 반복자와 인덱스 변수를 사용해 엄격하고 명시적으로 제어해야 한다.

    // for-each 문은 컬렉션과 배열은 물론 Iterable 인터페이스를 구현한 객체라면 무엇이든 순회할 수 있다.
    // Iterable 인터페이스는 다음과 같이 메서드가 단 하나 뿐이다.

    interface Iterable<E> {
        // 이 객체의 원소들을 순회하는 반복자를 반환한다.
        Iterator<E> iterator();
    }
}

// 핵심 정리
// 전통적인 for문과 비교했을 때 for-each 문은 명료하고, 유연하고, 버그를 예방해준다.
// 성능 저하도 없다. 가능한 모든 곳에서 for문이 아닌 for-each 문을 사용하자.
