package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 인코딩 ==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1); // ASCII 확장(LATIN-1)
        test("A", US_ASCII, EUC_KR); // ASCII 포함
        test("A", US_ASCII, MS_949); // ASCII 포함
        test("A", US_ASCII, UTF_8); // ASCII 포함
        test("A", US_ASCII, UTF_16BE); // (UTF_16 디코딩 실패) A -> [US-ASCII] 인코딩 -> [65] 1byte -> [UTF-16BE] 디코딩 -> � ( � : 모르겠다는 뜻 )

        System.out.println("== 한글 인코딩 - 기본 ==");
                                            //     한글도 글자 이므로 일단 인코딩을 하기는 한다. => 63 => (한글을 ASCII 로 인코딩 했으니..) 디코딩 하려고 보니 => ? ( 뭐지? 의 뜻 )
        test("가", US_ASCII, US_ASCII); // (X) 가 -> [US-ASCII] 인코딩 -> [63] 1byte -> [US-ASCII] 디코딩 -> ?
        test("가", ISO_8859_1, ISO_8859_1); // (X)
        test("가", EUC_KR, EUC_KR);
        test("가", MS_949, MS_949);
        test("가", UTF_8, UTF_8);
        test("가", UTF_16, UTF_16);

        System.out.println("== 한글 인코딩 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR); // (X) 자주 쓰는 한글만 인코딩 하므로
        test("뷁", MS_949, MS_949);
        test("뷁", UTF_8, UTF_8);
        test("뷁", UTF_16BE, UTF_16BE);

        System.out.println("== 한글 인코딩 - 디코딩이 다른 경우 ==");
        test("가", EUC_KR, MS_949);
        test("뷁", MS_949, EUC_KR); // 인코딩 가능 (모든 한글 인코딩 지원), 디코딩 X (자주 쓰는 한글만 인코딩 하므로)
        test("가", EUC_KR, UTF_8); // (X)
        test("가", MS_949, UTF_8); // (X)
        test("가", UTF_8, MS_949); // (X) 한글 윈도우에서 작성한 텍스트 파일을, 다른 운영체제에서 열었을 때 깨짐 현상 종종 있음.

        System.out.println("== 영문 인코딩 - 디코딩이 다른 경우");
        test("A", EUC_KR, UTF_8);
        test("A", MS_949, UTF_8);
        test("A", UTF_8, MS_949);
        test("A", UTF_8, UTF_16BE); // (X) UTF_16 : ASCII 지원 안되므로.
    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n",
                text, encodingCharset, Arrays.toString(encoded), encoded.length,
                decodingCharset, decoded);
    }
}
