package shop.coding.blog._core.util;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

    // $2a$10$QIDSIsb5N61t4a9tM0vQQu
    // $2a$10$4Tm5k4ZGzQz0G5o8mkGRE.
    @Test
    public void gensalt_test() {
        String salt = BCrypt.gensalt();
        System.out.println(salt);
    }

    // $2a$10$aRxOHca/njKKruRN7je5iOqJzz7V5twh7ClkKs6JmIAIGZqWhq4/.
    // $2a$10$cfe1W5BklWEIB8umwQIVs.C12pThqL2tmPAfdTZnit5ehbFSWAdUu
    @Test
    public void hashpw_test() {
        String rawPassword = "1234";
        String encPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        System.out.println(encPassword);
    }
}
