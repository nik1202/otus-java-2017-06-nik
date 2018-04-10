package ru.hw9;

import org.junit.Assert;
import org.junit.Test;
import ru.hw09.model.UserDataSet;
import ru.hw09.service.DBService;
import ru.hw09.service.impl.DBServiceImpl;

import java.util.Arrays;
import java.util.List;

public class DBServiceTest {

    private List<UserDataSet> init(DBService dbService) throws Exception {
        dbService.prepareTables();
        UserDataSet user1 = new UserDataSet("name1", 80);
        UserDataSet user2 = new UserDataSet("name2", 90);
        UserDataSet user3 = new UserDataSet("name3", 100);
        dbService.addUser(user1);
        dbService.addUser(user2);
        dbService.addUser(user3);
        return Arrays.asList(user1, user2, user3);
    }

    @Test
    public void saveAndLoad() {
        try (DBService dbService = new DBServiceImpl()) {
            List<UserDataSet> users = init(dbService);
            UserDataSet u = dbService.getUser(1);
            Assert.assertEquals(u, users.get(0));
            dbService.deleteTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadUserWithNotValidId() {
        try (DBService dbService = new DBServiceImpl()) {
            init(dbService);
            UserDataSet u = dbService.getUser(100500);
            Assert.assertNull(u);
            dbService.deleteTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveAndLoadAll() {
        try (DBService dbService = new DBServiceImpl()) {
            List<UserDataSet> initUsers = init(dbService);
            List<UserDataSet> users = dbService.getAllUsers();
            Assert.assertEquals(users.size(), 3);
            Assert.assertEquals(users, initUsers);
            dbService.deleteTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
