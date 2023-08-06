package web;

import web.config.AppConfig;
import web.model.User;
import web.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      userService.createUser(new User("maxov", "max", "gmail.com"));
      userService.editEmail(1L, "new_email@gmail.com");

      context.close();
   }
}
