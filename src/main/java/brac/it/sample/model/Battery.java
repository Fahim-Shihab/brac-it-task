package brac.it.sample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name", unique = true, nullable = false)
    private String name;
    @Column(name="post_code", length = 10)
    private String postCode;
    @Column(name = "watt_capacity", nullable = false)
    private Integer wattCapacity;
}
