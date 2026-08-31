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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Item> allItems = XMLUtility.getAllItems();
        List<Item> lowStockItems = XMLUtility.getLowStockItems();

        // Calculate statistics
        int totalItems = allItems.size();
        int totalQuantity = allItems.stream().mapToInt(Item::getQuantity).sum();
        double totalValue = allItems.stream().mapToDouble(Item::getTotalValue).sum();
        int lowStockCount = lowStockItems.size();

        request.setAttribute("totalItems", totalItems);
        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("totalValue", totalValue);
        request.setAttribute("lowStockCount", lowStockCount);
        request.setAttribute("lowStockItems", lowStockItems);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }
}
