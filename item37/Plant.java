package item37;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ordinal 인덱싱 대신 EnumMap을 사용하라

// 정원에 심은 식물들을 배열 하나로 관리하고, 생애주기별로 묶어보자
class Plant{
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

		public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString(){
        return name; 
    }

    public static void main(String[] args) {
        // 배열은 제네릭 호환이 되지 않고, 정확한 정수값을 사용한다는 것을 우리가 보증해야하는 위험이 있다.
        // EnumMap을 사용하자
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant plant : garden) {
            plantsByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
        }

        // 결과 출력 - 레이블을 직접 달아줘야한다.
        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            System.out.printf("%s : %s%n", LifeCycle.values()[i], plantsByLifeCycle[i]);
        }


        ////////////////////////////////////////////
        // 1. 짧고 명료하고 안전하지 않은 형변환을 쓰지 않는다.
        // EnumMap은 key값으로 enum의 상수 객체만을 가질 수 있다. HashMap은 키값에 대한 특별한 제한이 없다. (키가 중복될 염려 X)
        // EnumMap은 해싱작업을 하지 않으므로 해시 충돌이 없다.
        // EnumMap은 키가 순서대로 표시되지만, HashMap은 순서를 보장하지는 못한다.
        // HashMap은 일정 이상의 자료가 들어오면 자체적으로 resizing을 하지만 EnumMap은 enum이 가지고있는 상수 객체의 수가 정해져있기 때문에 크기가 변하거나 하지 않는다.
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle2 = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle2.put(lifeCycle,new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle2.get(plant.lifeCycle).add(plant);
        }

        //결과를 출력할 때 별도의 레이블을 달지 않아도 된다. (EnumMap의 toString)
        System.out.println(plantsByLifeCycle2);
    }

}
