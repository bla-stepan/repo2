package groupid;

import groupid.entity.Car;
import groupid.utils.HibernateUtils;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        //открываем сессию для работы с нашей базой данных
        Session session = HibernateUtils.getSessionFactory().openSession();
        //начинаем транзакцию
        session.beginTransaction();

        //создаем новый объект класса Car
        Car car = new Car();
        car.setProducer("Lada");//задаем производителя
        car.setModel("model 25");//задаем модель

        //снова обращаемся к сессии и просим сохранить наш объект
        session.save(car);
        //теперь нам нужна транзакция чтобы сделать коммит - сообщить БД "запиши данные"
        session.getTransaction().commit();
        //теперь надо закрыть сессию
        session.close();

        //теперь попробуем прочитать данные
        //снова открываем сессию для работы с нашей БД
        session = HibernateUtils.getSessionFactory().openSession();
        //начинаем транзакцию
        session.beginTransaction();
        //хотим получить существующую в бд машину у которой ID=1
        Car existedCar = session.get(Car.class, 1L);
        //убедимся что existedCar не равен null
        System.out.println(existedCar!=null);
        //опять получаем транзакцию и сделаем каммит
        session.getTransaction().commit();
        //закроем сессию
        session.close();

        //теперь закроем нашу SessionFactory чтобы никто больше не мог ее использовать
        HibernateUtils.shutdown();
    }
}
