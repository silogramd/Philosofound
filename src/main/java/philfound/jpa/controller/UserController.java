package philfound.jpa.controller;

import org.springframework.data.domain.Page;
import philfound.jpa.model.User;
import philfound.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import philfound.jpa.exception.ResourceNotFoundException;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // @PutMapping("/users/{userId}")
    // public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
    //     return userRepository.findById(userId).map(user -> {
    //         user.setUser(userRequest.getUser());
    //         return userRepository.save(user);
    //
    //     }).orElseThrow(() -> new ResourceNotFoundException("UserID " + userId + " not found"));
    // }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

}
