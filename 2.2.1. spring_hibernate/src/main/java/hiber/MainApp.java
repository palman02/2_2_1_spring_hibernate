package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      Car car1 = new Car(123, "mark1");
      Car car2 = new Car(456, "mark2");
      Car car3 = new Car(798, "silvia");
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car2);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      userService.add(car1);
      userService.add(car2);
      userService.add(car3);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      List<Car> cars = userService.listCars();
      System.out.println();
      for (Car car: cars) {
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println();
      }


      User user = userService.getUser("mark2", 456);
      System.out.println("--------------------------------------------------------------------------------------------");
      if (user == null) System.out.println("Машиной никто не владеет");
      else System.out.println("Машиной из запроса владеет: " + user);

      context.close();
   }
}
