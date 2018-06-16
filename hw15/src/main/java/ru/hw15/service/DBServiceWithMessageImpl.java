package ru.hw15.service;

import ru.hw10.service.DBServiceHibernateImpl;
import ru.hw15.app.DBServiceWithMessage;
import ru.hw15.app.MessageSystemContext;
import ru.hw15.messageSystem.Address;
import ru.hw15.messageSystem.MessageSystem;

public class DBServiceWithMessageImpl extends DBServiceHibernateImpl implements DBServiceWithMessage {
    private final Address address;
    private final MessageSystemContext context;

    public DBServiceWithMessageImpl(Address address, MessageSystemContext context) {
        this.address = address;
        this.context = context;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMS() {
        return this.context.getMessageSystem();
    }

}
