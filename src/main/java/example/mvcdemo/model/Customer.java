package example.mvcdemo.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "email_address")
    private String email;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday;
}
