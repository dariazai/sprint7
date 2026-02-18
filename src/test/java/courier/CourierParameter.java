package courier;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CourierParameter {
    private String login;
    private String password;
    private String firstName;

    public CourierParameter(String login, String password) {
        this.login = login;
        this.password = password;
    }
}