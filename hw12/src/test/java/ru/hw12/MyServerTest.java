package ru.hw12;

import org.junit.Assert;
import org.junit.Test;
import ru.hw11.CacheEngineImpl;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyServerTest {

    @Test
    public void test() throws Exception {
        CacheEngineImpl cacheEngine = new CacheEngineImpl(100, 10, 10, false);
        MyServer server = new MyServer(cacheEngine);
        server.start();

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8090/login").openConnection();
        connection.setRequestMethod("POST");

        String params = "login=admin&password=admin";
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        InputStream is = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        char[] buffer = new char[256];
        int rc;

        StringBuilder sb = new StringBuilder();
        while ((rc = reader.read(buffer)) != -1)
            sb.append(buffer, 0, rc);
        reader.close();

        String result = sb.toString();
        Assert.assertTrue(result.contains("idleTimeMs: 10"));
        Assert.assertTrue(result.contains("maxElements: 100"));
        Assert.assertTrue(result.contains("lifeTimeMs: 10"));
        Assert.assertTrue(result.contains("isEternal: false"));
        Assert.assertTrue(result.contains("HitCount: 0"));
        Assert.assertTrue(result.contains("MissCount: 0"));

        System.out.println(sb);
    }
}
