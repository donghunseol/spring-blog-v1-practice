package shop.coding.blog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 컴퍼넌트 스캔 설정 파일의 역할을 하는 빈
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignore() {
        return w -> w.ignoring().requestMatchers("/board/*", "/static/**", "/h2-console/**"); // 인증이 필요없는 주소 모음
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(a -> {
            a.requestMatchers("/user/updateForm", "/board/**").authenticated()
                    .anyRequest().permitAll();
            // 인증이 되야지만 이 페이지에 출입이 가능하다.
            // "/board/**" 는 /board/ 이후의 모든 것을 다 인증이 되야지 통과되도록 한다. // anyRequest() 이런 형태가 아닌 모든 것들! 이라는 뜻! 또 permitAll() 는 아닌 모든 것들을 허용 하도록 한다.
        });

        http.formLogin(f -> {
            f.loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/").failureUrl("/loginForm");
            // 인증이 필요한 것을 이 곳에 리다이렉션. 시큐리티의 로그인을 사용한다.
            // loginProcessingUrl("/login") post 때림
            // defaultSuccessUrl("/") 로그인 성공시 어디로 갈 것 인지
            // failureForwardUrl("/loginForm") 로그인 실패시 어디로 갈 것 인지
            // 최초의 로그인 화면을 커스터마이징 할 것이다.
        });

        return http.build();
    }
}
