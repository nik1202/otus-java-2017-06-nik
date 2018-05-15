package ru.hw13.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateProcessor {
    private static final String HTML_DIR = "/template/";

    private final Configuration configuration;

    public TemplateProcessor() {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), HTML_DIR);
        configuration.setDefaultEncoding("UTF-8");
    }

    String getPage(String filename, Map<String, Object> data) {
        try (Writer stream = new StringWriter();) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}