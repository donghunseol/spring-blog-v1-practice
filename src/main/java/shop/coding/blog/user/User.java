package shop.coding.blog.user;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 어노테이션
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    // 카멜 표기법으로 만들면 DB는 created_at 언더스코어기법으로 만들어진다.
    private LocalDateTime createdAt;
}
