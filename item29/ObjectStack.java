package item29;

import java.util.Arrays;
import java.util.EmptyStackException;

// 현재 버전을 사용하는 클라이언트에는 아무런 해가 없다 (제네릭으로 바꿔도)
// 지금 상태에서의 클라이언트는 스택에서 꺼낸 객체를 형변환해야 하는데, 이때 런타임 오류가 날 위험이 있다.
// 기존 클라이언트에는 아무런 영향을 주지 않으면서, 새로운 사용자를 훨씬 편하게 해주는 길이다.
public class ObjectStack {
    
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public ObjectStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if(size == 0){
            throw new EmptyStackException();
        }
        
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void ensureCapacity() {
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}
// 제네릭 클래스로 만들기
class Stack<E> { // 첫 단계는 클래스 선언에 타입 매개변수를 추가, 이때 타입 이름으로는 보통 E를 사용한다.
    private E[] elements; // Object를 적절한 타입 매개변수로 바꾸고 컴파일하자
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        // elements = new E[DEFAULT_INITIAL_CAPACITY]; E와 같은 실체화 불가 타입으로는 배열을 만들 수 없다.
        // 첫번째 방법
        @SuppressWarnings("unchecked")
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY]; // 이 배열의 런타임 타입은 E[] 가 아닌 Object[]다. 하지만 안전하다.
    }

    public void push(E e) { // push 메서드를 통해 전다로디는 원소의 타입은 항상 E다. 따라서 이 비검사 형변환은 확실히 안전한다.
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }

        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

class Stack2<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack2(){
        // 두번째 방법
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }

        @SuppressWarnings("unchecked") // 비검사 수행문 push에서 E 타입만 허용하므로 이 형변환은 잔전하다.
        E result = (E) elements[--size]; // E는 실체화 불가 타입이므로 컴파일러는 런타임에 이뤄지는 형변환이 안전한지 증명할 방법이 없다.
        elements[size] = null; // 형변환을 배열에서 원소를 읽을 떄마다 해줘야 하기 때문에 현업에서는 첫 번쨰 방법을 선호한다.
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

// 지금까지 설명한 Stack 예는 "배열보다는 리스트를 우선하라"는 아이템 28과 모순돼 보인다.
// 사실 제네릭 타입 안에서 리스트를 사용하는 게 항상 가능하지도, 꼭 더 좋은 것도 아니다.
// 자바가 리스트를 기본 타입으로 제공하지 않으므로 ArrayList 같은 제네릭 타입은 성능을 높일 목적으로 배열을 사용하기도 한다.
