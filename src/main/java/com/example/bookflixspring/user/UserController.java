// package com.example.bookflixspring.user;

// import java.net.URI;
// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.util.UriComponentsBuilder;

// @CrossOrigin("*")
// @RestController
// @RequestMapping("/users")
// public class UserController {

//     private UserRepository userRepository;

//     public UserController(UserRepository userRepository){
//         this.userRepository = userRepository;
//     }
    
//     @PostMapping("/register")
//     private ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
//         User savedUser = userRepository.save(user);

//         URI locationOfNewUser = ucb
//                     .path("/users/{id}")
//                     .buildAndExpand(savedUser.getId())
//                     .toUri();
        
//         return ResponseEntity.created(locationOfNewUser).build();
//     }

//     @PostMapping("/login")
//     private ResponseEntity<Optional<User>> login(@RequestBody User user) {
//         Optional<User> newUser = userRepository.findByEmail(user.getUsername());
//         User extractedUser = newUser.orElse(null);
//         if (extractedUser != null && extractedUser.getPassword().equals(user.getPassword()) && extractedUser.getId() != null) {
//             return ResponseEntity.ok(newUser);
//         }
//         return ResponseEntity.notFound().build();
//     }
// }
