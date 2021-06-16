package item5;

import java.util.List;

// 클래스가 내부적으로 하나 이상의 자원에 의존하고 있고, 그 자원이 클래스 동작에 영향을 준다면
// 싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다. 이 자원들을 클래스가 직접 만들게 해서도 안된다. 
// 대신 필요한 자원을 생성자에 넘겨주자. 의존 객체 주입이라 하는 이 기법은 클래스의 (유연성, 재사용성, 테스트 용이성)을 기막히게 개선해준다.

// 불변을 보장하여 (변경될 일이 없기 때문에) 여러 클라이언트가 의존 객체들을 안심하고 공유할 수 있다.
public class SpellChecker { 

	private final Lexicon dictionary; 

	private SpellChecker(Lexicon dictionary) { 
        this.dictionary = dictionary; 
    }

	public boolean isValid(String word) { return true; } 
	public List<String> suggestions(String typo) { return null; } 

	public static void main(String[] args) {
		Lexicon lexicon = new KoreanDictionary();
		SpellChecker spellChecker = new SpellChecker(lexicon);
		spellChecker.isValid("hello");
	}

}

interface Lexicon{}

class KoreanDictionary implements Lexicon {}
class TestDictionary implements Lexicon {} // test가 가능해짐