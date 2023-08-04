package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
   void createUser(User user);
   List<User> listUsers();
   void editEmail(Long id, String NewEmail);
   void dropUser(Long id);
   User getUserById(Long id);
}
