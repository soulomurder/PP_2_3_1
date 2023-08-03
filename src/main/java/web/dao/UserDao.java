package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void editEmail(Long id, String NewEmail);
   void drop(Long id);
}
