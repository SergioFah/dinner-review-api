package fah.sergio.dinnerreview.controller;

import fah.sergio.dinnerreview.model.User;
import fah.sergio.dinnerreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//As an unregistered user, I want to create my user profile using a display name that’s unique only to me.
//As a registered user, I want to update my user profile. I cannot modify my unique display name.
//As an application experience, I want to fetch the user profile belonging to a given display name.
//As part of the backend process that validates a user-submitted dining review, I want to verify that the user exists, based on the user display name associated with the dining review.

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User create(@RequestBody User user){
        return this.userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        Optional<User> userToBeUpdatedOptional = this.userRepository.findById(id);
        if(userToBeUpdatedOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist");
        }
        User userToBeUpdated = userToBeUpdatedOptional.get();

        userToBeUpdated.setDairyAllergy(user.isDairyAllergy());
        userToBeUpdated.setEggAllergy(user.isEggAllergy());
        userToBeUpdated.setPeanutAllergy(user.isPeanutAllergy());

        if(user.getCity() != null){
            userToBeUpdated.setCity(user.getCity());
        }
        if(user.getZipcode() != null){
            userToBeUpdated.setZipcode(user.getZipcode());
        }
        if(user.getState() != null) {
            userToBeUpdated.setState(user.getState());
        }
        return this.userRepository.save(userToBeUpdated);
    }

    @GetMapping("/{name}")
    public User getByName(@PathVariable String name){
        return this.userRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This use does not exist"));
    }


}
