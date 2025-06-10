package finalmission.reservation.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class CustomerInfo {

    private String name;
    private String phoneNumber;
    private String email;

    public CustomerInfo(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    protected CustomerInfo() {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
