package order;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class OrderParameter {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
}