package vaadin.servlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import vaadin.ui.MyUI;
import vaadin.ui.NavigatorUI;

import javax.servlet.annotation.WebServlet;

/**
 * @author Vitaly Moskalik
 *         created on 13.07.2016
 */
@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
public class MyUIServlet extends VaadinServlet {
}
