package courier;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthorizationParameter {
    private String login;
    private String password;
}