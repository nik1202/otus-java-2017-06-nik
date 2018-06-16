package ru.hw15.app;

import ru.hw15.messageSystem.Address;
import ru.hw15.messageSystem.MessageSystem;

public class MessageSystemContext {
    private final MessageSystem messageSystem;

    private Address feAddress;
    private Address dbAddress;

    public MessageSystemContext(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFeAddress() {
        return feAddress;
    }

    public void setFeAddress(Address feAddress) {
        this.feAddress = feAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }
}
