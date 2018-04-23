package ru.hw10;

import org.junit.Assert;
import org.junit.Test;
import ru.hw09.model.AddressDataSet;
import ru.hw09.model.PhoneDataSet;
import ru.hw09.model.UserDataSet;
import ru.hw10.service.DBServiceHibernateImpl;

import java.util.ArrayList;
import java.util.List;

public class DBServiceHibernateTest {

    private UserDataSet makeUserInstance(String name, int age, String address, String phone, String ... phones) {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        user.setAge(age);
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet(address);
        user.setAddress(addressDataSet);
        List<PhoneDataSet> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneDataSet(phone));
        if (phones.length > 0) {
            for (String number : phones) {
                phoneNumbers.add(new PhoneDataSet(number));
            }
        }
        user.setPhones(phoneNumbers);
        return user;
    }

    @Test
    public void addAndGetOne() {
        try(DBServiceHibernateImpl service = new DBServiceHibernateImpl()) {
            UserDataSet user1 = makeUserInstance("Vasya", 25, "Lenina", "111111", "2222222");
            UserDataSet user2 = makeUserInstance("Petya", 35, "Kolkhoznaya", "333333", "444444");

            service.addUser(user1);
            service.addUser(user2);

            UserDataSet testUser = service.getUser(1);
            Assert.assertEquals(testUser, user1);

            testUser = service.getUser(2);
            Assert.assertEquals(testUser, user2);
        }
    }

    @Test
    public void addAndGetAll() {
        try(DBServiceHibernateImpl service = new DBServiceHibernateImpl()) {
            UserDataSet user1 = makeUserInstance("Vasya", 25, "Lenina", "111111", "2222222");
            UserDataSet user2 = makeUserInstance("Petya", 35, "Kolkhoznaya", "333333", "444444");
            UserDataSet user3 = makeUserInstance("Nik", 32, "Hrusheva", "555555", "666666");

            service.addUser(user1);
            service.addUser(user2);
            service.addUser(user3);

            List<UserDataSet> testUsers = service.getAllUsers();

            Assert.assertEquals(testUsers.size(), 3);
            Assert.assertTrue(testUsers.stream().anyMatch(user -> user.equals(user1)));
            Assert.assertTrue(testUsers.stream().anyMatch(user -> user.equals(user2)));
            Assert.assertTrue(testUsers.stream().anyMatch(user -> user.equals(user3)));
        }
    }
}
