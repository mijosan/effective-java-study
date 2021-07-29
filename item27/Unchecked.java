package item27;

import java.util.HashSet;
import java.util.Set;

// 비검사 경고를 제거하라 !

// 할 수 있는 한 모든 비검사 경고를 제거하라. 모두 제거한다면 그 코드는 타입 안전성이 보장된다.
// 즉, 런타임에 ClassCastException이 발생할 일이 없고, 여러분이 의도한 대로 잘 동작하리라 확신할 수 있다.
// 경고를 제거할 수는 없지만 타입 안전하다고 확신할 수 있다면 @SuppressWarnings("unchecked") 애너테이션을 달아 경고를 숨기자.
// 단, 타입 안전함을 검증하지 않은 채 경고를 숨기면 스스로에게 잘못된 보안 인식을 심어주는 꼴이다.
// 한편, 안전하다고 검증된 비검사 경고를 (숨기지 않고) 그대로 두면, 진짜 문제를 알리는 새로운 경고가 나와도 눈치채지 못할 수 있다.
// 제거하지 않은 수많은 거짓 경고 속에 새로운 경고가 파묻힐 것이기 때문이다.
// @SuppressWarnings 애너테이션은 항상 가능한 한 좁은 범위에 적용하자. (절대 클래스 전체에 적용해서는 안 된다.)
public class Unchecked {
    
    Set<String> exaltation = new HashSet(); // 다이아몬드 연산자(<>) 만으로 해결할 수 있다. (String)이라고 추론해준다.

    // 범위를 더좁게 해야함
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    // 이 코드는 깔금하게 컴파일되고 비검사 경고를 숨기는 범위도 최소로 좁혔다.
    // @SuppressWarnings("unchecked") 애너테이션을 사용할 때면 그 경고를 무시해도 안전한 이유를 항상 주석으로 남겨야 한다.
    // 다른 사람이 그 코드를 이해하는 데 도움이 되며, 더 중요하게는 다른 사람이 그 코드를 잘못 수정하여 타입 안정성을 잃는 상황을 줄여준다.

    // 코드가 안전한 근거가 쉽게 떠오르지 않더라도 끝까지 포기하지 말자. 근거를 차즌 중에 그 코드가 사실은 안전하지 않다는 걸 발견할 수도 있으니 말이다.
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Arrays.copyOf(elementData, size, a.getClass());
            return result;
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

}
