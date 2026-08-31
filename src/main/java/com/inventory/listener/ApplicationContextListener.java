package com.inventory.listener;

import com.inventory.util.XMLUtility;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Set the XML file path based on the servlet context
        String realPath = sce.getServletContext().getRealPath("/WEB-INF/classes/inventory.xml");
        if (realPath != null) {
            File xmlFile = new File(realPath);
            if (xmlFile.exists()) {
                XMLUtility.setXmlFilePath(realPath);
                System.out.println("XML file path set to: " + realPath);
            } else {
                // Fallback to development path
                String devPath = "src/main/resources/inventory.xml";
                File devFile = new File(devPath);
                if (devFile.exists()) {
                    XMLUtility.setXmlFilePath(devPath);
                    System.out.println("XML file path set to development path: " + devPath);
                }
            }
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}
