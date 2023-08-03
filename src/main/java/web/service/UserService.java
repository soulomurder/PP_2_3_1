package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void editEmail(Long id, String NewEmail);
    void drop(Long id);
}