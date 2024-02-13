package shop.coding.blog._core.config.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.coding.blog.user.User;
import shop.coding.blog.user.UserRepository;

// 이 코드가 사용될 조건!
// POST, /login, x-www-form-urlencoded, 키 값이 username, passowrd
@RequiredArgsConstructor
@Service // 컴포넌트 스캔 (다시 덮어 씌움으로서 기존의 UserDetailsService를 무력화 시킨다, IoC에 덮어 씌운다)
public class MyLoginService implements UserDetailsService { // implements UserDetailsService 하는 이유

    private final UserRepository userRepository;
    private final HttpSession session;

    // 리턴을 받는 이유는 session 에 setAtribute 하려고! UserDetails 로 형태를 맞춰야 한다.
    // 리턴 값이 있으면 session에 저장 받기 위해서 그런 것 이다.
    // 왜 password를 안받고 UserDetails로 받을까? 이걸 해결하면 의도가 파악이 된다!
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername : " + username);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("user는 null");
            return null;
        } else {
            System.out.println("user를 찾았어요");

            // 1. 의존성 주입
            // 2. 세션에 대한 개념
            // 3. 로그인 시스템에 대한 개념
            // 4. 시큐리티 세션이 어디에 저장되어 있는지 알아야한다.
            // 5. 데이터가 어떻게 저장되는지도 알아야한다.
            session.setAttribute("sessionUser", user); // 머스태치에서 사용하기 위해 만듬 (머스태치에서만 가져오자!)
            return new MyLoginUser(user);
        }
    }
}
