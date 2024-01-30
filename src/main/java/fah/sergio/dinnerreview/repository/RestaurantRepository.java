package fah.sergio.dinnerreview.repository;

import fah.sergio.dinnerreview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    boolean existsByZipcode(String zipcode);
    boolean existsByName(String name);

    Iterable<Restaurant> findByZipcode(String zipcode);

}
