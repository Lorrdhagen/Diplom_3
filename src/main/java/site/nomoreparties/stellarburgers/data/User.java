package site.nomoreparties.stellarburgers.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;

    public static User getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(10) + "@test.ru";
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(8);
        return new User(email, password, name);
    }
}
