package org.example.demomongodb.sevice;

import org.example.demomongodb.model.User;
import org.example.demomongodb.model.UserReq;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IUserService {
    User saveUser(UserReq req);
    User update(UserReq req);
    void delete(String idUser);
    Page<User> listUsers(int page, int size);
    Optional<User> getUser(String idUser);
}
