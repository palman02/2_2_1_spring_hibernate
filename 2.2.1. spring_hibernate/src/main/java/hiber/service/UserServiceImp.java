package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   private final CarDao carDao;

   @Autowired
   public UserServiceImp(UserDao userDao, CarDao carDao) {
      this.userDao = userDao;
      this.carDao = carDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.getListUsers();
   }

   @Transactional
   @Override
   public void add(Car car) {
      carDao.add(car);
   }

   @Transactional(readOnly = true)
   @Override
   public List<Car> listCars() {
      return carDao.listCar();
   }

   @Override
   @Transactional
   public User getUser(String model, int series) {
      return userDao.getUser(model, series);
   }
}
