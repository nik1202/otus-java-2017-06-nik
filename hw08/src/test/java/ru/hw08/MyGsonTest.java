package ru.hw08;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 04/08/17.
 */
public class MyGsonTest {

    private ObjectForSerialize object;

    @Before
    public void init() {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        object = new ObjectForSerialize(7, "test string", new double[]{7., 8.}, l, "transient string");
    }

    @Test
    public void gsonVsMyGson() {
        Gson gson = new Gson();
        IMyGson myGson = new MyGson();

        Assert.assertEquals(gson.toJson(object), myGson.toJson(object));
    }
}
