package ca.uottawa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "emp_id")
    private String empId;

    private String name;
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;
    private String title;
}
