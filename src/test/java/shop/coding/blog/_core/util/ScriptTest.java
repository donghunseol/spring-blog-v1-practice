package shop.coding.blog._core.util;

import org.junit.jupiter.api.Test;

public class ScriptTest {
    @Test
    public void back_test() { // test는 언더스코어기법 사용한다.
        String result = Script.back("권한이 없어요");
        System.out.println(result);
    }
}
