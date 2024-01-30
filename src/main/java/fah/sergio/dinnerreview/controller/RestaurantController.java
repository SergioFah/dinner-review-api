package fah.sergio.dinnerreview.controller;

//I want to submit a new restaurant entry. Should a restaurant with the same name and with the same zip code already exist, I will see a failure.
//I want to fetch the details of a restaurant, given its unique Id.
//I want to fetch restaurants that match a given zip code

import fah.sergio.dinnerreview.model.Restaurant;
import fah.sergio.dinnerreview.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant){
        if(this.restaurantRepository.existsByName(restaurant.getName())&&this.restaurantRepository.existsByZipcode(restaurant.getZipcode())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is a Restaurant with this name and Zipcode already...");
        }
        return this.restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        if (this.restaurantRepository.findById(id).isPresent()){
            return this.restaurantRepository.findById(id).get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no restaurant with this Id...");
        }
    }
    @GetMapping("search/{id}")
    public Iterable<Restaurant> getByZipcode(@PathVariable String zip) {
        return this.restaurantRepository.findByZipcode(zip);
    }


}
