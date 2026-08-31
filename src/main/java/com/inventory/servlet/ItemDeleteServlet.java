package com.inventory.servlet;

import com.inventory.util.XMLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items/delete")
public class ItemDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            boolean success = XMLUtility.deleteItem(id);

            if (success) {
                request.setAttribute("message", "Item deleted successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to delete item!");
                request.setAttribute("messageType", "error");
            }
        }

        response.sendRedirect(request.getContextPath() + "/items");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
