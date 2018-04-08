package ru.hw09.model;

public abstract class DataSet {

    private long id;

    public DataSet() {
    }

    public DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
