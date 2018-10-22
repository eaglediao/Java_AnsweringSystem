package com.eaglediao.qa.startup;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QAsystemMain {
    private static final int WEB_PORT = 8088;
    private static final String DEFAULT_WEBAPP = "/";
    private static final String DEFAULT_WEBXML = "/WEB-INF/web.xml";
    private static final String DEFAULT_RESOURCE_BASE = "src/main/webapp";

    private static Logger log = LoggerFactory.getLogger(QAsystemMain.class);

    public static void main(String[] args) throws Exception {
            startJetty();
    }

    private static void startJetty() throws Exception {
        Server server = new Server(WEB_PORT);
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault(server);
        classlist.clear();
        classlist.add("org.eclipse.jetty.webapp.WebXmlConfiguration");
        classlist.add("org.eclipse.jetty.annotations.AnnotationConfiguration");
        server.setHandler(getServletContextHandler());
        addRuntimeShutdownHook(server);
        server.start();
        server.join();

    }

    private static Handler getServletContextHandler() {
        WebAppContext contextHandler = new WebAppContext();
        contextHandler.setContextPath(DEFAULT_WEBAPP);
        contextHandler.setDescriptor(DEFAULT_WEBXML);
        contextHandler.setResourceBase(DEFAULT_RESOURCE_BASE);
        contextHandler.setClassLoader(Thread.currentThread().getContextClassLoader());
        contextHandler.setParentLoaderPriority(true);
        return contextHandler;
    }

    private static void addRuntimeShutdownHook(final Server server) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (server.isStarted()) {
                server.setStopAtShutdown(true);
                try {
                    server.stop();
                } catch (Exception e) {
                    log.error("Error while stopping jetty server:" , e.getMessage());
                }
            }
        }));
    }
}
