<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <%@ include file="header.jsp" %>
        
        <main>
            <h1>Dashboard</h1>
            
            <div class="stats-grid">
                <div class="stat-card">
                    <h3>Total Items</h3>
                    <p class="stat-number">${totalItems}</p>
                </div>
                <div class="stat-card">
                    <h3>Total Quantity</h3>
                    <p class="stat-number">${totalQuantity}</p>
                </div>
                <div class="stat-card">
                    <h3>Total Value</h3>
                    <p class="stat-number">$<fmt:formatNumber type="number" pattern="#,##0.00" value="${totalValue}"/></p>
                </div>
                <div class="stat-card ${lowStockCount > 0 ? 'warning' : ''}">
                    <h3>Low Stock Items</h3>
                    <p class="stat-number">${lowStockCount}</p>
                </div>
            </div>

            <c:if test="${lowStockCount > 0}">
                <div class="alert alert-warning">
                    <h3>⚠️ Low Stock Alert</h3>
                    <p>The following items are running low on stock:</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>SKU</th>
                                <th>Current Stock</th>
                                <th>Reorder Level</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${lowStockItems}">
                                <tr class="low-stock">
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.sku}</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.reorderLevel}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/items/edit?id=${item.id}" class="btn btn-sm btn-primary">Restock</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="quick-actions">
                <h3>Quick Actions</h3>
                <div class="action-buttons">
                    <a href="${pageContext.request.contextPath}/items" class="btn btn-primary">View All Items</a>
                    <a href="${pageContext.request.contextPath}/items/add" class="btn btn-success">Add New Item</a>
                    <a href="${pageContext.request.contextPath}/items?action=lowStock" class="btn btn-warning">View Low Stock</a>
                </div>
            </div>
        </main>

        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
