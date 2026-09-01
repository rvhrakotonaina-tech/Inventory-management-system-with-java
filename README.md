
# Inventory Management System

A comprehensive web-based inventory management system built with Java, Servlets, JSP, and XML. This system enables efficient inventory tracking, management, and reporting with a clean, modern interface.

## Features

- **Dashboard**: Real-time overview of inventory statistics
- **Item Management**: Full CRUD operations for inventory items
- **Search & Filter**: Search items by name, category, SKU, or description
- **Low Stock Alerts**: Automatic alerts when items fall below reorder level
- **Category Management**: Organize items by categories
- **XML Data Storage**: Persistent data storage using XML files
- **Responsive Design**: Mobile-friendly interface
- **Real-time Statistics**: Track total items, quantity, value, and stock status

## Technology Stack

- **Backend**: Java 11, Servlets 4.0
- **Frontend**: JSP, JSTL, CSS3
- **Data Storage**: XML (DOM parsing)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 9.x or higher

 <img width="1321" height="971" alt="Screenshot 2026-09-01 100618" src="https://github.com/user-attachments/assets/aa8069aa-c343-4447-9797-5578bb3fe4f4" />
<img width="1020" height="970" alt="Screenshot 2026-09-01 100651" src="https://github.com/user-attachments/assets/241d263f-ccba-459e-ad1a-858b5d483c5e" />

 


## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6 or higher
- Apache Tomcat 9.x or higher
- IDE (IntelliJ IDEA, Eclipse, or NetBeans) - optional

## Installation & Setup

### 1. Clone or Download the Project

```bash
cd "c:/Users/Venceslas Hyacinthe/Documents/5th Semster folders/Project based learing with Java/Inventory Management System"
```

### 2. Build the Project with Maven

```bash
mvn clean package
```

This will compile the project and create a WAR file in the `target/` directory.

### 3. Deploy to Tomcat

#### Option A: Manual Deployment
1. Copy the generated WAR file (`target/inventory-management.war`)
2. Paste it into Tomcat's `webapps` directory
3. Start Tomcat server

#### Option B: IDE Deployment (IntelliJ IDEA)
1. Open the project in IntelliJ IDEA
2. Configure Tomcat server in Run/Debug Configurations
3. Deploy the WAR artifact to Tomcat
4. Run the server

#### Option C: IDE Deployment (Eclipse)
1. Import the project as a Maven project
2. Configure Tomcat server in Eclipse
3. Add the project to the server
4. Run the server

### 4. Access the Application

Open your browser and navigate to:
```
http://localhost:8080/inventory-management/
```

## Usage Guide

### Dashboard
- View overall inventory statistics
- See low stock alerts
- Quick access to common actions

### Managing Items

#### Add New Item
1. Click "Add New Item" button
2. Fill in the item details:
   - Name (required)
   - Category (required)
   - SKU (required)
   - Quantity (required)
   - Price (required)
   - Reorder Level (required)
   - Description (optional)
3. Click "Add Item"

#### Edit Item
1. Go to Items list
2. Click "Edit" button for the desired item
3. Modify the details
4. Click "Update Item"

#### Delete Item
1. Go to Items list
2. Click "Delete" button for the desired item
3. Confirm the deletion

#### Search Items
1. Use the search bar to find items by:
   - Name
   - Category
   - SKU
   - Description

#### Filter Low Stock Items
1. Click "Low Stock Only" button
2. View items that need restocking

## Data Model

### Item Properties
- **id**: Unique identifier (auto-generated)
- **name**: Item name
- **category**: Item category
- **quantity**: Current stock quantity
- **price**: Unit price
- **description**: Item description
- **sku**: Stock Keeping Unit
- **reorderLevel**: Minimum stock before reorder alert
- **lastUpdated**: Last modification date

### Categories
- Electronics
- Furniture
- Office Supplies
- Clothing
- Food & Beverages
- Other

## XML Data Storage

The system uses XML files for data persistence. The main data file is located at:
```
src/main/resources/inventory.xml
```

The XML structure includes all item details and is automatically updated when you perform CRUD operations.

## API Endpoints

- `GET /` - Redirects to dashboard
- `GET /dashboard` - Dashboard view
- `GET /items` - List all items (supports search and filtering)
- `GET /items/add` - Add new item form
- `POST /items/add` - Process new item
- `GET /items/edit?id={id}` - Edit item form
- `POST /items/edit` - Process item update
- `GET /items/delete?id={id}` - Delete item

## Configuration

### Port Configuration
Default Tomcat port: 8080

To change the port, modify Tomcat's `server.xml` configuration file.

### XML File Path
The XML file path is configured in `XMLUtility.java`:
```java
private static final String XML_FILE_PATH = "src/main/resources/inventory.xml";
```

## Troubleshooting

### Common Issues

**Issue**: "404 Not Found" error
- **Solution**: Ensure the WAR file is deployed correctly and the context path matches

**Issue**: XML parsing errors
- **Solution**: Verify the XML file exists and has valid structure

**Issue**: Character encoding issues
- **Solution**: The application uses UTF-8 encoding by default

**Issue**: Permission denied when writing to XML
- **Solution**: Ensure the application has write permissions to the resources directory

## Development

### Adding New Features

1. **Add new servlet**: Create in `src/main/java/com/inventory/servlet/`
2. **Add new JSP view**: Create in `src/main/webapp/WEB-INF/views/`
3. **Modify data model**: Update classes in `src/main/java/com/inventory/model/`
4. **Update XML utility**: Modify `XMLUtility.java` for new data operations

### Testing

Test the application by:
1. Adding sample items
2. Performing CRUD operations
3. Testing search functionality
4. Verifying low stock alerts
5. Checking responsive design on different screen sizes

## Future Enhancements

Potential improvements:
- User authentication and authorization
- Multiple warehouse support
- Barcode/QR code scanning
- Export to Excel/PDF
- Email notifications for low stock
- Advanced reporting and analytics
- REST API for mobile app integration
- Database migration (MySQL/PostgreSQL)

## License

This project is created for educational purposes.

## Support

For issues or questions, please refer to the project documentation or contact the development team.

---

**Version**: 1.0.0  
**Last Updated**: 2024
