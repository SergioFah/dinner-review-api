package fah.sergio.dinnerreview.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Data
@Entity
@Table(name = "REVIEW")
public class Review {

//    who submitted, represented by their unique display name (String)
//    the restaurant, represented by its Id (Long)
//    an optional peanut score, on a scale of 1-5
//    an optional egg score, on a scale of 1-5
//    an optional dairy score, on a scale of 1-5
//    an optional commentary

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REVIEWED_BY")
    private String reviewedBy;

    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;

    @Column(name = "PEANUT_SCORE")
    private Integer peanutScore;

    @Column(name = "EGG_SCORE")
    private Integer eggScore;

    @Column(name = "DAIRY_SCORE")
    private Integer dairyScore;

    @Column(name = "COMMENTARY")
    private String commentary;

    @Column(name = "REVIEW_STATUS")
    @Enumerated(EnumType.STRING)
    private AdminReviewStatus reviewStatus;
}
