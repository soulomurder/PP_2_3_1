package web.dao;

import web.config.AppConfig;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
public class UserDaoImp implements UserDao {

   @PersistenceContext
   @Autowired
   private EntityManager em;

   @Transactional
   @Override
   public void add(User user) {
      PlatformTransactionManager transactionManager = new AnnotationConfigApplicationContext(AppConfig.class).
              getBean(PlatformTransactionManager.class);
      TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
      try {
         em.persist(user);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = em.createQuery("from User", User.class);
      return (List<User>) query.getResultList();
   }

   @Transactional
   @Override
   public void editEmail(Long id, String newEmail) {
      PlatformTransactionManager transactionManager = new AnnotationConfigApplicationContext(AppConfig.class).
              getBean(PlatformTransactionManager.class);
      TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
      try {
         em.find(User.class, id).setEmail(newEmail);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }

   @Transactional
   @Override
   public void drop(Long id) {
      PlatformTransactionManager transactionManager = new AnnotationConfigApplicationContext(AppConfig.class).
              getBean(PlatformTransactionManager.class);
      TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
      try {
         em.remove(em.find(User.class, id));
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }
   }
}
