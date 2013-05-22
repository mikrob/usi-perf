package org.henri.cocktailfactory.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author Henri Tremblay
 */
public class VersionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/plain");

        String version = getClass().getPackage().getImplementationVersion();
        if (version==null) {
            Properties prop = new Properties();
            try(InputStream in = getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")) {
                prop.load(in);
                version = prop.getProperty("Implementation-Version");
            }
        }
        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println(version);
    }
}
