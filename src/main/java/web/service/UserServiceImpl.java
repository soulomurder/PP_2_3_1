package web.service;

import web.dao.UserDao;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   private final UserDao userDao;

   @Autowired
   public UserServiceImpl(UserDao userDao) {
      this.userDao = userDao;
   }

   @Override
   public void createUser(User user) {
      userDao.createUser(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void editEmail(Long id, String newEmail) {
      userDao.editEmail(id, newEmail);
   }

   @Override
   public void dropUser(Long id) {
      userDao.dropUser(id);
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }
}
