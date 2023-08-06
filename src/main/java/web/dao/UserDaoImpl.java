package web.dao;

import org.springframework.transaction.TransactionDefinition;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

   @PersistenceContext
   @Autowired
   private EntityManager entityManager;

   @Autowired
   private PlatformTransactionManager transactionManager;
   /* рекомендуют, но не упоминают, что если так сделать, то будет ошибка
   HTTP Status 500 – Internal Server Error
   Type Exception Report
   Message Servlet.init() для сервлета [dispatcher] выбросил исключение
   Description The server encountered an unexpected condition that prevented it from fulfilling the request. */

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = entityManager.createQuery("from User", User.class);
      return (List<User>) query.getResultList();
   }

   @Transactional
   @Override
   public void createUser(User user) {
      TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
      TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
      try {
         entityManager.persist(user);
         transactionManager.commit(transactionStatus);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Transactional
   @Override
   public void editEmail(Long id, String newEmail) {
      TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
      TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
      try {
         entityManager.find(User.class, id).setEmail(newEmail);
         transactionManager.commit(transactionStatus);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Transactional
   @Override
   public void dropUser(Long id) {
      TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
      TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
      try {
         entityManager.remove(entityManager.find(User.class, id));
         transactionManager.commit(transactionStatus);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Override
   public User getUserById(Long id) {
      return entityManager.find(User.class, id);
   }
}
