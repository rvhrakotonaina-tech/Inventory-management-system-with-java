<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <%@ include file="header.jsp" %>
        
        <main>
            <div class="page-header">
                <h1>Inventory Items</h1>
                <a href="${pageContext.request.contextPath}/items/add" class="btn btn-success">Add New Item</a>
            </div>

            <div class="search-filters">
                <form action="${pageContext.request.contextPath}/items" method="get" class="search-form">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="searchTerm" placeholder="Search by name, category, SKU..." 
                           value="${searchTerm}" class="search-input">
                    <button type="submit" class="btn btn-primary">Search</button>
                    <a href="${pageContext.request.contextPath}/items" class="btn btn-secondary">Clear</a>
                </form>
                
                <div class="filter-buttons">
                    <a href="${pageContext.request.contextPath}/items" class="btn ${showLowStock != true ? 'btn-primary' : 'btn-secondary'}">All Items</a>
                    <a href="${pageContext.request.contextPath}/items?action=lowStock" class="btn ${showLowStock == true ? 'btn-warning' : 'btn-secondary'}">Low Stock Only</a>
                </div>
            </div>

            <c:if test="${not empty message}">
                <div class="alert alert-${messageType}">
                    ${message}
                </div>
            </c:if>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>SKU</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total Value</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty items and not empty items}">
                                <c:forEach var="item" items="${items}">
                                    <tr class="${item.lowStock ? 'low-stock' : ''}">
                                        <td>${item.id}</td>
                                        <td>
                                            <strong>${item.name}</strong>
                                            <br><small class="text-muted">${item.description}</small>
                                        </td>
                                        <td>${item.category}</td>
                                        <td>${item.sku}</td>
                                        <td>${item.quantity}</td>
                                        <td>$<fmt:formatNumber type="number" pattern="#,##0.00" value="${item.price}"/></td>
                                        <td>$<fmt:formatNumber type="number" pattern="#,##0.00" value="${item.totalValue}"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.lowStock}">
                                                    <span class="badge badge-warning">Low Stock</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-success">In Stock</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <div class="action-buttons">
                                                <a href="${pageContext.request.contextPath}/items/edit?id=${item.id}" 
                                                   class="btn btn-sm btn-primary">Edit</a>
                                                <a href="${pageContext.request.contextPath}/items/delete?id=${item.id}" 
                                                   class="btn btn-sm btn-danger" 
                                                   onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="9" class="text-center">
                                        <p class="no-data">No items found. <a href="${pageContext.request.contextPath}/items/add">Add your first item</a></p>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <c:if test="${not empty items and not empty items}">
                <div class="table-footer">
                    <p>Total: ${items.size()} items</p>
                </div>
            </c:if>
        </main>

        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
