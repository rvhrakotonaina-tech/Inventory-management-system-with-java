<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${isEdit ? 'Edit Item' : 'Add New Item'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <%@ include file="header.jsp" %>
        
        <main>
            <div class="page-header">
                <h1>${isEdit ? 'Edit Item' : 'Add New Item'}</h1>
                <a href="${pageContext.request.contextPath}/items" class="btn btn-secondary">Back to List</a>
            </div>

            <c:if test="${not empty message}">
                <div class="alert alert-${messageType}">
                    ${message}
                </div>
            </c:if>

            <div class="form-container">
                <form action="${pageContext.request.contextPath}/items/${isEdit ? 'edit' : 'add'}" 
                      method="post" class="item-form">
                    
                    <c:if test="${isEdit}">
                        <input type="hidden" name="id" value="${item.id}">
                    </c:if>

                    <div class="form-group">
                        <label for="name">Item Name *</label>
                        <input type="text" id="name" name="name" required
                               value="${isEdit ? item.name : ''}" 
                               placeholder="Enter item name">
                    </div>

                    <div class="form-group">
                        <label for="category">Category *</label>
                        <select id="category" name="category" required>
                            <option value="">Select Category</option>
                            <option value="Electronics" ${isEdit && item.category == 'Electronics' ? 'selected' : ''}>Electronics</option>
                            <option value="Furniture" ${isEdit && item.category == 'Furniture' ? 'selected' : ''}>Furniture</option>
                            <option value="Office Supplies" ${isEdit && item.category == 'Office Supplies' ? 'selected' : ''}>Office Supplies</option>
                            <option value="Clothing" ${isEdit && item.category == 'Clothing' ? 'selected' : ''}>Clothing</option>
                            <option value="Food & Beverages" ${isEdit && item.category == 'Food & Beverages' ? 'selected' : ''}>Food & Beverages</option>
                            <option value="Other" ${isEdit && item.category == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="sku">SKU (Stock Keeping Unit) *</label>
                        <input type="text" id="sku" name="sku" required
                               value="${isEdit ? item.sku : ''}" 
                               placeholder="Enter SKU (e.g., PROD-001)">
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="quantity">Quantity *</label>
                            <input type="number" id="quantity" name="quantity" required min="0"
                                   value="${isEdit ? item.quantity : ''}" 
                                   placeholder="0">
                        </div>

                        <div class="form-group">
                            <label for="price">Price ($) *</label>
                            <input type="number" id="price" name="price" required min="0" step="0.01"
                                   value="${isEdit ? item.price : ''}" 
                                   placeholder="0.00">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reorderLevel">Reorder Level *</label>
                        <input type="number" id="reorderLevel" name="reorderLevel" required min="0"
                               value="${isEdit ? item.reorderLevel : ''}" 
                               placeholder="Minimum stock before reorder">
                        <small class="form-help">Alert when stock falls below this level</small>
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" rows="3"
                                  placeholder="Enter item description">${isEdit ? item.description : ''}</textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${isEdit ? 'Update Item' : 'Add Item'}
                        </button>
                        <a href="${pageContext.request.contextPath}/items" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </main>

        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
