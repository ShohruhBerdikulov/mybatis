package test.pkg.bean;

import lombok.Data;

@Data
public class AppUser {
    private Long id;
    private String forename;
    private String lastname;
    private String email;
    private String phone;
}
