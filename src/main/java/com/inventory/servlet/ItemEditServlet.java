package com.inventory.servlet;

import com.inventory.model.Item;
import com.inventory.util.XMLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items/edit")
public class ItemEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Item item = XMLUtility.getItemById(id);
            if (item != null) {
                request.setAttribute("item", item);
                request.setAttribute("isEdit", true);
                request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/items");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String sku = request.getParameter("sku");
            int reorderLevel = Integer.parseInt(request.getParameter("reorderLevel"));

            Item item = new Item();
            item.setId(id);
            item.setName(name);
            item.setCategory(category);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setDescription(description);
            item.setSku(sku);
            item.setReorderLevel(reorderLevel);

            boolean success = XMLUtility.updateItem(item);

            if (success) {
                request.setAttribute("message", "Item updated successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to update item!");
                request.setAttribute("messageType", "error");
            }

            response.sendRedirect(request.getContextPath() + "/items");

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format!");
            request.setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/items");
        }
    }
}
