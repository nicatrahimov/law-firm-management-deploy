package com.rahimov.lawfirmmanagementdeploy.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @NotBlank
    @Size(min = 2, max = 20)
    String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    String lastName;

    @Email
    String email;

    @Pattern(regexp = "^[0-9]+$", message = "Field should contain only digits")
    String phoneNumber;

    String address;

    @NotBlank
    String city;

    @NotBlank
    String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    Image image;

//    @OneToOne(cascade = CascadeType.ALL)
//            @JoinColumn(name = "lawyer_id",referencedColumnName = "id")
//    Lawyer assignedTo;
}
