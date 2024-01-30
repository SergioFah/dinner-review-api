package fah.sergio.dinnerreview.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", unique = true, nullable = false)
    private String name;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private Integer zipcode;

    @Column(name = "EGG_ALLERGY")
    private boolean eggAllergy;

    @Column(name = "PEANUT_ALLERGY")
    private boolean peanutAllergy;

    @Column(name = "DAIRY_ALLERGY")
    private boolean dairyAllergy;

}
