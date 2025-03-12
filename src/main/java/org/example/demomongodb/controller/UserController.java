package org.example.demomongodb.controller;

import lombok.RequiredArgsConstructor;
import org.example.demomongodb.model.User;
import org.example.demomongodb.model.UserReq;
import org.example.demomongodb.sevice.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserReq req) {
        return service.saveUser(req);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody UserReq req) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing or invalid user ID");
        }
        req.setId(id);
        return service.update(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping
    public Page<User> listUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid pagination parameters");
        }
        return service.listUsers(page, size);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return service.getUser(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
