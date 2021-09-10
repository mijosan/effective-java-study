package item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// null이 아닌, 빈 컬렉션이나 배열을 반환하라 !

// 따라 하지 말 것!
// 사실 재고가 없다고 해서 특별히 취급할 이유는 없다.
// 그럼에도 이 코드처럼 null을 반환한다면, 클라이언트는 이 null 상황을 처리하는 코드를 추가로 작성해야 한다.
// 컬렉션이나 배열 같은 컨테이너가 비었을 때 null을 반환하는 메서드를 사용할 때면 항시 이와 같은 방어 코드를 넣어줘야 한다.
// 클라이언트에서 방어 코드를 빼먹으면 오류가 발생할 수 있다.
// 한편, null을 반환하려면 반환하는 쪽에서도 이 상황을 특별히 취급해줘야 해서 코드가 더 복잡해진다.

// 때로는 빈 컨테이너를 할당하는 데도 비용이 드니 null을 반환하는 쪽이 낫다는 주장도 있다.
// 하지만 이는 두 가지 면에서 틀린 주장이다.
// 1. 성능 분석 결과 이 할당이 성능 저하의 주범이라고 확인되지 않는 한 이 정도의 성능 차이는 신경 쓸 수준이 못 된다.
// 2. 빈 컬렉션과 배열은 굳이 새로 할당하지 않고도 반환할 수 있다.
public class ReturnClass {
    
    private List<String> cheeseInstock;

    /**
     * @return 매장 안의 모든 치즈 목록을 반환한다.
     * 단, 재고가 하나도 없다면 null을 반환한다.
     */
    public List<String> getString() {
        return cheeseInstock.isEmpty() ? null : new ArrayList<>(cheeseInstock);
    }

    // 다음은 빈 컬렉션을 반환하는 전형적인 코드로, 대부분의 상황에서는 이렇게 하면 된다. (null 예방)
    public List<String> getString2() {
        return new ArrayList<>(cheeseInstock);
    }

    // 최적화 -  빈 컬렉션을 매번 새로 할당하지 않도록 했다.
    // 가능성은 작지만, 사용 패턴에 따라 빈 컬렉션 할당이 성능을 눈에 띄게 떨어 뜨릴 때.
    // 매번 똑같은 빈 "불변" 컬렉션을 반환하는 것이다.
    public List<String> getString3() {
        return cheeseInstock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeseInstock);
    }

    // 배열을 쓸 때도 마찬기지다.
    // 절대 null을 반환하지 말고 길이가 0인 배열을 반환하라.
    // 보통은 단순히 정확한 길이의 배열을 반환하기만 하면 된다.
    // 그 길이가 0일 수도 있을 뿐이다.
    public String[] getString4() {
        return cheeseInstock.toArray(new String[0]);
    }

    // 위의 방식이 성능을 떨어뜨릴 것 같다면 길이 0짜리 배열을 미리 선언해두고 매번 그 배열을 반환하면 된다.
    // 길이 0인 배열은 모두 불변이기 때문이다.
    private static final String[] EMPTY_CHEESE_ARRAY = new String[0];

    public String[] getString5() {
        return cheeseInstock.toArray(EMPTY_CHEESE_ARRAY);
    }

}

// 핵심 정리
// null이 아닌, 빈 배열이나 컬렉션을 반환하라. null을 반환하는 API는 사용하기 어렵고
// 오류 처리 코드도 늘어난다. 그렇다고 성능이 좋은 것도 아니다.