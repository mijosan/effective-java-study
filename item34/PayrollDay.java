package item34;

// 그런데, 이런 상수별 메서드에도 단점은 있습니다. 열거 타입 상수끼리 코드를 공유하기가 어려운 점인데요. 예를 들어서 살펴봅시다.
// 휴가와 같이 새로운 값을 열거 타입에 추가하려면 그 값을 처리하는 case 문을 넣어야 합니다. 그렇지 않으면 휴가 기간에 일해도 평일과 똑같은 임금을 받게 되겠죠… 그럼 어떻게 해야 할까요?
public enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
	SATURDAY, SUNDAY;

	private static final int MINS_PER_SHIFT = 8 * 60; // 하루 8시간

	int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;

		int overtimePay;
		switch(this) {
			case SATURDAY: case SUNDAY: // 주말
				overtimePay = basePay / 2;
				break;
			default: // 주중
				if (minutesWorked <= MINS_PER_SHIFT) {
					overtimePay = 0	;
				} else {
					overtimePay = (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
				}
		}
		return basePay + overtimePay;
	}

}

// 전략 열거 타입 패턴
// 안전성과 유연함을 고려한다면 전략 열거 타입 패턴을 고려해볼 수 있습니다. switch 문이나 상수별 메서드 구현이 필요 없어지지요. 
// 새로운 상수를 추가할 때마다 잔업수당 전략을 선택하도록 하는 것입니다.
enum PayrollDay2 {
	MONDAY(), TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
	SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

	private final PayType payType;

	PayrollDay2() {
		this(PayType.WEEKDAY);
	}

	PayrollDay2(PayType payType) {
		this.payType = payType;
	}

	enum PayType {
		WEEKDAY {
			int overtimePay(int minsWorked, int payRate) {
				int overtimePay;
				if (minsWorked <= MINS_PER_SHIFT) {
					overtimePay = 0;
				} else {
					overtimePay = (minsWorked - MINS_PER_SHIFT) * payRate / 2;
				}
				return overtimePay;
			}
		},
		WEEKEND {
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked * payRate / 2;
			}
		};

		abstract int overtimePay(int mins, int payRate);

		private static final int MINS_PER_SHIFT = 8 * 60; // 하루 8시간

		int pay(int minutesWorked, int payRate) {
			int basePay = minutesWorked * payRate;
			return basePay + overtimePay(minutesWorked, payRate);
		}
	}

}

// 열거 타입은 확실히 정수 상수보다 효율적입니다. 읽기도 쉽고 강력합니다. 
// 물론 메서드도 쓸 수 있고요. 필요한 원소를 컴파일 타임에 모두다 알 수 있는 상수의 집합이라면 
// 열거 타입을 강력히 추천합니다. 바이너리 수준에서 호환되도록 설계되었기 때문에 열거 타입에 정의된 
// 상수 개수가 영원히 고정 불변일 필요도 없습니다.