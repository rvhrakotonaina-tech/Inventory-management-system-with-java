package com.inventory.servlet;

import com.inventory.model.Item;
import com.inventory.util.XMLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items/add")
public class ItemAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String sku = request.getParameter("sku");
            int reorderLevel = Integer.parseInt(request.getParameter("reorderLevel"));

            Item item = new Item();
            item.setName(name);
            item.setCategory(category);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setDescription(description);
            item.setSku(sku);
            item.setReorderLevel(reorderLevel);

            boolean success = XMLUtility.addItem(item);

            if (success) {
                request.setAttribute("message", "Item added successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to add item!");
                request.setAttribute("messageType", "error");
            }

            response.sendRedirect(request.getContextPath() + "/items");

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format!");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);
        }
    }
}
