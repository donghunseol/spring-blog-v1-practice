package shop.coding.blog.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {
    @Test
    public void encode_test() {
        BCryptPasswordEncoder en = new BCryptPasswordEncoder();
        String rawPassword = "1234";

        String encPassword = en.encode(rawPassword);
        System.out.println(encPassword);

        // $2a$10$Cci8iv5/P9H.j6rTS3iF9OH1UJ6A.XttT6VIBqpdN5sIg6VS2SllW
    }
}
