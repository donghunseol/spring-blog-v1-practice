package shop.coding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.coding.blog._core.util.ApiUtil;
import shop.coding.blog._core.util.Script;

@RequiredArgsConstructor // final 붙은 애들에 대한 생성자 생성
@Controller
public class UserController {

    // 자바는 final 변수는 반드시 초기화 되어야한다.
    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ApiUtil<>(false);
        } else {
            return new ApiUtil<>(true);
        }
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        System.out.println(requestDTO);

        if (requestDTO.getUsername().length() < 3) {
            return "error/400"; // ViewResolver 설정이 되어 있음 (앞 경로, 뒤 경로)
        }

        User user = userRepository.findByUsernameAndPassword(requestDTO);

        if (user == null) { // 조회 안됨 (401)
            return "error/401";
        } else {
            session.setAttribute("sessionUser", user); // 락카에 담음 (StateFul)
        }

        return "redirect:/";
    }

    @PostMapping("/join")
    public @ResponseBody String join(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);

        try {
            userRepository.save(requestDTO); // Request 한 값을 저장 시킨다.
        } catch (Exception e) {
            return Script.back("아이디가 중복되었어요");
        }
        return Script.href("/loginForm");
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 세션을 완전히 삭제. 서랍 비우기.
        return "redirect:/";
    }
}
