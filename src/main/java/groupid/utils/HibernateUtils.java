package groupid.utils;

import groupid.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    //добавим следующие поля
    //нам нужен класс StandardServiceRegistry, который берет на себя некоторую рутинную работу по конфигурации hibernate
    //это будет константа и будет создавать только один раз.
    //с помощью использования патерна билдер мы вызываем билдер StandardServiceRegistryBuilder конфигурируем и далее получаем результат
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();

    //создадим объект SessionFactory который будет предоставлять доступ для получения и открытия сессии
    private static final SessionFactory sessionFactory = buildSessionFactory();//вызываем метод, который мы напишем

    private static SessionFactory buildSessionFactory() {
        //тут возпользуемся еще одним классом из Hibernate, который нужен нам для создания SessionFactory
        return new MetadataSources(REGISTRY)
                .addAnnotatedClass(Car.class)//загружаем класс машин
                .buildMetadata()//просим построить нам метаданные
                .buildSessionFactory();//вызываем метод получения сессии
    }

    //создадим геттер для получения SessionFactory для того чтобы все потребители нашего класса могли получить доступ
    // для открытия и закрытия сессий
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //нам необходимо озаботиться тем, что нам после использования базы данных, необходимо будет закрыть нашу сессионфабрику
    //для того, чтобы больше никто не смог получить доступ к нашей сессии
    public static void shutdown(){
        getSessionFactory().close();
    }
}
