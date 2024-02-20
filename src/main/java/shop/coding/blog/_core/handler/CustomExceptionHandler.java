package shop.coding.blog._core.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.coding.blog._core.util.Script;

// 응답 에러 컨트롤러 (view(파일) 리턴) // @RestControllerAdvice 이게 데이터를 응답 하지만 @ResponseBody를 붙이면 해결된다.
@ControllerAdvice // 핸들을 만드는 기능
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class) // 모든 에러를 잡아내는 기능을 가지고 있다. // 앞에 기입하는 Exception에 따라 오류가 발생함
    public @ResponseBody String error1(Exception e) {
        return Script.back(e.getMessage());
    }
}
