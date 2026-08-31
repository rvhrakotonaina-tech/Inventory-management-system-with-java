<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>404 - Page Not Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error-container {
            text-align: center;
            padding: 4rem 2rem;
        }
        .error-code {
            font-size: 6rem;
            font-weight: bold;
            color: #667eea;
        }
        .error-message {
            font-size: 1.5rem;
            margin: 1rem 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-container">
            <div class="error-code">404</div>
            <h1 class="error-message">Page Not Found</h1>
            <p>The page you are looking for doesn't exist or has been moved.</p>
            <br>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Return to Dashboard</a>
        </div>
    </div>
</body>
</html>
