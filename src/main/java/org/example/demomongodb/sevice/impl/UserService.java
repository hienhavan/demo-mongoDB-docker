package org.example.demomongodb.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.example.demomongodb.model.User;
import org.example.demomongodb.model.UserReq;
import org.example.demomongodb.repo.UserRepository;
import org.example.demomongodb.sevice.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(UserReq req) {
        var user = new User();
        user.setName(req.getName());
        return userRepository.save(user);
    }

    @Override
    public User update(UserReq req) {
        var userOpt = findUserById(req.getId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with id: " + req.getId());
        }
        var user = userOpt.get();
        user.setName(req.getName() != null ? req.getName() : user.getName());
        return userRepository.save(user);
    }

    @Override
    public void delete(String idUser) {
        Optional<User> userOpt = findUserById(idUser);
        userOpt.ifPresent(userRepository::delete);
    }

    @Override
    public Page<User> listUsers(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUser(String idUser) {
        return userRepository.findById(idUser);
    }

    private Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }
}