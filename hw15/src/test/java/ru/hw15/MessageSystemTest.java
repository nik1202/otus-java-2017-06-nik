package ru.hw15;

import org.junit.Assert;
import org.junit.Test;
import ru.hw09.model.AddressDataSet;
import ru.hw09.model.PhoneDataSet;
import ru.hw09.model.UserDataSet;
import ru.hw15.app.DBServiceWithMessage;
import ru.hw15.app.FrontEndService;
import ru.hw15.app.MessageSystemContext;
import ru.hw15.messageSystem.Address;
import ru.hw15.messageSystem.MessageSystem;
import ru.hw15.service.DBServiceWithMessageImpl;
import ru.hw15.service.FrontEndServiceImpl;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MessageSystemTest {

    @Test
    public void test() throws InterruptedException {
        MessageSystem system = new MessageSystem();
        MessageSystemContext context = new MessageSystemContext(system);

        Address wsAddress = new Address("fe");
        context.setFeAddress(wsAddress);

        Address dbAddress = new Address("db");
        context.setDbAddress(dbAddress);

        DBServiceWithMessage dbService = new DBServiceWithMessageImpl(dbAddress, context);
        dbService.init();

        FrontEndService feService = new FrontEndServiceImpl(wsAddress, context);
        feService.init();

        system.start();

        UserDataSet userDataSet = new UserDataSet("Test", 11);
        AddressDataSet addressDataSet = new AddressDataSet("street");
        userDataSet.setAddress(addressDataSet);
        PhoneDataSet phoneDataSet = new PhoneDataSet("123456789");
        userDataSet.setPhones(Collections.singletonList(phoneDataSet));

        feService.addUser(userDataSet);

        TimeUnit.SECONDS.sleep(1);

        UserDataSet resultUserDataSet = ((DBServiceWithMessageImpl) dbService).getUser(1);

        Assert.assertEquals(userDataSet, resultUserDataSet);

        system.dispose();
    }
}
