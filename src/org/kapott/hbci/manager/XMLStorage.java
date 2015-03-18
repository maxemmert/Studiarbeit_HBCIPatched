package org.kapott.hbci.manager;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLStorage {

    private static Document document;

    private static HashMap<String,Element> hashMap;
    
    public static Document getDocument() {
        return document;
    }

    public static void setDocument(Document document) {
        XMLStorage.document = document;
    }
    public static Element getNodeWithAttribute(Node root, String attrName, String attrValue)
    {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (n instanceof Element) {
                Element el = (Element) n;
                if (el.getAttribute(attrName).equals(attrValue)) {
                    return el;
                }else{
           el =  getNodeWithAttribute(n, attrName, attrValue); //search recursively
           if(el != null){
            return el;
           }
        }
            }
        }
        return null;
    }

    public static HashMap<String,Element> getHashMap() {
        return hashMap;
    }

    public static void setHashMap(HashMap<String,Element> hashMap) {
        XMLStorage.hashMap = hashMap;
    }
    
}
