package fah.sergio.dinnerreview.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", unique = true)
    private String name;

    @Column(name="ZIPCODE", unique = true)
    private String zipcode;

    @Column(name="CUISINE")
    private String cuisine;

}
