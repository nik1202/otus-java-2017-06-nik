package ru.hw11.web;

import ru.hw11.CacheEngineImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    private TemplateProcessor templateProcessor;
    private CacheEngineImpl cacheEngine;

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(CacheEngineImpl cacheEngine) {
        try {
            this.templateProcessor = new TemplateProcessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cacheEngine = cacheEngine;
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
