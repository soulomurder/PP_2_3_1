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
   private EntityManager em;

   @Autowired
   private PlatformTransactionManager transactionManager;

   @Transactional(readOnly = true)
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = em.createQuery("from User", User.class);
      return (List<User>) query.getResultList();
   }

   @Transactional
   @Override
   public void createUser(User user) {
      TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
      TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
      try {
         em.persist(user);
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
         em.find(User.class, id).setEmail(newEmail);
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
         em.remove(em.find(User.class, id));
         transactionManager.commit(transactionStatus);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserById(Long id) {
      return em.find(User.class, id);
   }
}
