package item9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// try-finally의 2가지 단점이 있다.
// 첫 번째 단점은 가독성이 안좋다는 것이다.
// 두 번째 단점은 스택 추적이 어려울 수 있다는 것이다.
// 위의 firstLineOfFile 메소드를 통해 좀 더 자세히 살펴보자. 
// 예외는 try 블록과 finally 블록 모두에서 발생할 수 있다. 
// 예를 들어 기기에 물리적인 문제가 생긴다면 firstLineOfFile 메서드 내의 
// readLine 메서드가 예외를 던지고, 같은 이유로 close 메서드도 실패한다.
// 예외가 두개가 생겨버리고 close 예외가 readLine 예외를 덮어버린다.
public class TryFinally {

    public static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[3];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

}
