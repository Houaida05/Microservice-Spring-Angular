package org.ms.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
}
