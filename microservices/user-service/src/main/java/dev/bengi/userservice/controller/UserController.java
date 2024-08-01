package dev.bengi.userservice.controller;

import dev.bengi.userservice.dto.UserCreatedDTO;
import dev.bengi.userservice.dto.UserRetrieveDTO;
import dev.bengi.userservice.model.User;
import dev.bengi.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import dev.bengi.userservice.enumeration.RoleEnum;
import java.net.URI;

/**
 * @author bengi
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param keyword
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @GetMapping
    public Page<User> getUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int size,
            @RequestParam(required = true) String sortField,
            @RequestParam(required = true) String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return userService.getUsersByFirstName(keyword, pageable);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserRetrieveDTO> getUserById(int id) {
        return ResponseEntity.ok(userService.getUserDTObyId(id));
    }

    /**
     * Endpoint for creating a new user.
     *
     * @param dto The user data transfer object.
     * @param currentUserId The ID of the user making the request.
     * @param newUserRole The role of the new user.
     * @return The created user.
     */
    @PostMapping("/create")
    public ResponseEntity<UserRetrieveDTO> createUser(
            @RequestBody @Validated UserCreatedDTO dto,
            @RequestParam int currentUserId,
            @RequestParam RoleEnum newUserRole) {

        // Create the new user.
        var newUser = userService.createUser(dto, currentUserId, newUserRole);

        // Generate the location of the new user.
        var location = String.format("http://localhost/api/v1/users/%d", newUser.id());

        // Return the created user with the location.
        return ResponseEntity.created(URI.create(location)).body(newUser);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserRetrieveDTO> updateUser(@PathVariable Integer id, @RequestBody @Validated UserCreatedDTO dto) {
//        var result = userService.updateUser(id, dto);
//        return ResponseEntity.ok(result);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
//        userService.deleteUserById(id);
//        logger.info("UserId: {} has been deleted", id);
//        return ResponseEntity.ok(true);
//    }



}
