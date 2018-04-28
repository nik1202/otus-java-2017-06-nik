package ru.hw11.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.hw11.CacheEngineImpl;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class MyServer {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public";
    private Server server;

    public MyServer(CacheEngineImpl cacheEngine) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
        context.addServlet(new ServletHolder(new AdminServlet(cacheEngine)), "/admin");
        context.addFilter(AuthFilter.class, "*", EnumSet.of(DispatcherType.REQUEST));

        server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
    }

    public void start() {
        try {
            server.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}