package com.team04.technikon.dto;

import com.team04.technikon.model.PropertyOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyOwnerDto {

    private int id;
    private int vat;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private String role;

    public PropertyOwnerDto(PropertyOwner propertyOwner) {
        this.id = propertyOwner.getId();
        this.vat = propertyOwner.getVat();
        this.name = propertyOwner.getName();
        this.surname = propertyOwner.getSurname();
        this.address = propertyOwner.getAddress();
        this.phoneNumber = propertyOwner.getPhoneNumber();
        this.email = propertyOwner.getEmail();
        this.username = propertyOwner.getUsername();
        this.password = propertyOwner.getPassword();
        this.role = propertyOwner.getRole();

    }

    public PropertyOwner asOwner() {
        PropertyOwner propertyOwner = new PropertyOwner();
        propertyOwner.setId(id);
        propertyOwner.setVat(vat);
        propertyOwner.setName(name);
        propertyOwner.setSurname(surname);
        propertyOwner.setAddress(address);
        propertyOwner.setPhoneNumber(phoneNumber);
        propertyOwner.setEmail(email);
        propertyOwner.setUsername(username);
        propertyOwner.setPassword(password);
        propertyOwner.setRole(role);
        return propertyOwner;

    }

}
