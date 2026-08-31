package com.inventory.util;

import com.inventory.model.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLUtility {
    private static String XML_FILE_PATH = "src/main/resources/inventory.xml";
    
    public static void setXmlFilePath(String path) {
        XML_FILE_PATH = path;
    }
    
    public static String getXmlFilePath() {
        return XML_FILE_PATH;
    }

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList itemNodes = document.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Node node = itemNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Item item = parseItemFromElement(element);
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Item getItemById(int id) {
        List<Item> items = getAllItems();
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public static boolean addItem(Item item) {
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Generate new ID
            int newId = getNextId(document);
            item.setId(newId);
            item.setLastUpdated(LocalDate.now());

            // Get items element
            NodeList itemsList = document.getElementsByTagName("items");
            Element itemsElement = (Element) itemsList.item(0);

            // Create new item element
            Element itemElement = document.createElement("item");
            itemElement.setAttribute("id", String.valueOf(newId));

            // Add child elements
            createElement(document, itemElement, "name", item.getName());
            createElement(document, itemElement, "category", item.getCategory());
            createElement(document, itemElement, "quantity", String.valueOf(item.getQuantity()));
            createElement(document, itemElement, "price", String.valueOf(item.getPrice()));
            createElement(document, itemElement, "description", item.getDescription());
            createElement(document, itemElement, "sku", item.getSku());
            createElement(document, itemElement, "reorderLevel", String.valueOf(item.getReorderLevel()));
            createElement(document, itemElement, "lastUpdated", item.getLastUpdated().toString());

            itemsElement.appendChild(itemElement);

            // Save to file
            saveDocument(document, xmlFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateItem(Item item) {
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList itemNodes = document.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Node node = itemNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int existingId = Integer.parseInt(element.getAttribute("id"));
                    if (existingId == item.getId()) {
                        // Update the item
                        element.getElementsByTagName("name").item(0).setTextContent(item.getName());
                        element.getElementsByTagName("category").item(0).setTextContent(item.getCategory());
                        element.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(item.getQuantity()));
                        element.getElementsByTagName("price").item(0).setTextContent(String.valueOf(item.getPrice()));
                        element.getElementsByTagName("description").item(0).setTextContent(item.getDescription());
                        element.getElementsByTagName("sku").item(0).setTextContent(item.getSku());
                        element.getElementsByTagName("reorderLevel").item(0).setTextContent(String.valueOf(item.getReorderLevel()));
                        element.getElementsByTagName("lastUpdated").item(0).setTextContent(LocalDate.now().toString());

                        saveDocument(document, xmlFile);
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteItem(int id) {
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList itemNodes = document.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Node node = itemNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int existingId = Integer.parseInt(element.getAttribute("id"));
                    if (existingId == id) {
                        element.getParentNode().removeChild(element);
                        saveDocument(document, xmlFile);
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Item> searchItems(String searchTerm) {
        List<Item> allItems = getAllItems();
        List<Item> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Item item : allItems) {
            if (item.getName().toLowerCase().contains(lowerSearchTerm) ||
                item.getCategory().toLowerCase().contains(lowerSearchTerm) ||
                item.getSku().toLowerCase().contains(lowerSearchTerm) ||
                item.getDescription().toLowerCase().contains(lowerSearchTerm)) {
                results.add(item);
            }
        }
        return results;
    }

    public static List<Item> getLowStockItems() {
        List<Item> allItems = getAllItems();
        List<Item> lowStockItems = new ArrayList<>();

        for (Item item : allItems) {
            if (item.isLowStock()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    private static Item parseItemFromElement(Element element) {
        int id = Integer.parseInt(element.getAttribute("id"));
        String name = getElementText(element, "name");
        String category = getElementText(element, "category");
        int quantity = Integer.parseInt(getElementText(element, "quantity"));
        double price = Double.parseDouble(getElementText(element, "price"));
        String description = getElementText(element, "description");
        String sku = getElementText(element, "sku");
        int reorderLevel = Integer.parseInt(getElementText(element, "reorderLevel"));
        LocalDate lastUpdated = LocalDate.parse(getElementText(element, "lastUpdated"));

        return new Item(id, name, category, quantity, price, description, sku, reorderLevel, lastUpdated);
    }

    private static String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private static void createElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    private static int getNextId(Document document) {
        NodeList itemNodes = document.getElementsByTagName("item");
        int maxId = 0;
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Node node = itemNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getAttribute("id"));
                if (id > maxId) {
                    maxId = id;
                }
            }
        }
        return maxId + 1;
    }

    private static void saveDocument(Document document, File file) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}
