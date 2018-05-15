package ru.hw13.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.hw13.cache.CacheEngineImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    @Autowired
    private TemplateProcessor templateProcessor;

    @Autowired
    private CacheEngineImpl cacheEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        super.init(config);
    }

    private Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("idleTimeMs", cacheEngine.getIdleTimeMs());
        pageVariables.put("maxElements", cacheEngine.getMaxElements());
        pageVariables.put("lifeTimeMs", cacheEngine.getLifeTimeMs());
        pageVariables.put("isEternal", cacheEngine.isEternal() ? "true" : "false");
        pageVariables.put("HitCount", cacheEngine.getHitCount());
        pageVariables.put("MissCount", cacheEngine.getMissCount());
        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();
        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}