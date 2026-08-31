package com.inventory.servlet;

import com.inventory.model.Item;
import com.inventory.util.XMLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/items")
public class ItemListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        List<Item> items;

        if ("search".equals(action)) {
            String searchTerm = request.getParameter("searchTerm");
            items = XMLUtility.searchItems(searchTerm);
            request.setAttribute("searchTerm", searchTerm);
        } else if ("lowStock".equals(action)) {
            items = XMLUtility.getLowStockItems();
            request.setAttribute("showLowStock", true);
        } else {
            items = XMLUtility.getAllItems();
        }

        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);
    }
}
