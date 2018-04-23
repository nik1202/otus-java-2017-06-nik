package ru.hw10.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.hw09.model.AddressDataSet;
import ru.hw09.model.PhoneDataSet;
import ru.hw09.model.UserDataSet;
import ru.hw09.service.DBService;
import ru.hw10.dao.UserDataSetDAO;
import ru.hw11.CacheEngine;
import ru.hw11.CacheEngineImpl;
import ru.hw11.MyElement;

import javax.transaction.NotSupportedException;
import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private CacheEngine<Long, UserDataSet> cache;

    public DBServiceHibernateImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
        cache = new CacheEngineImpl<>(10, 0, 1000, false);
    }

    public DBServiceHibernateImpl(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
        cache = new CacheEngineImpl<>(10, 0, 1000, false);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public void prepareTables() throws Exception {
        throw new NotSupportedException("Method for JDBC impl.");
    }

    @Override
    public void addUser(UserDataSet userDataSet) {
        runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(userDataSet);
            if (userDataSet.getId() != 0) {
                cache.put(new MyElement<>(userDataSet.getId(), userDataSet));
            }
            return userDataSet;
        });
    }

    @Override
    public UserDataSet getUser(int id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            MyElement<Long, UserDataSet> myElement = cache.get(Integer.toUnsignedLong(id));
            if (myElement != null) {
                return myElement.getValue();
            } else {
                UserDataSet userDataSet = dao.read(id);
                if (userDataSet != null) {
                    cache.put(new MyElement<>(userDataSet.getId(), userDataSet));
                }
                return userDataSet;
            }
        });
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            List<UserDataSet> userDataSetList = dao.readAll();
            if (userDataSetList != null) {
                userDataSetList.forEach(user -> cache.put(new MyElement<>(user.getId(), user)));
            }
            return userDataSetList;
        });
    }

    @Override
    public void deleteTables() throws Exception {
        throw new NotSupportedException("Method for JDBC impl.");
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}