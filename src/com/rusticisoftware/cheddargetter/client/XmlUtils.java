package com.rusticisoftware.cheddargetter.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlUtils {
	
	public static String getXmlString (Document xmlDoc) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(xmlDoc);
		transformer.transform(source, result);
		
		return result.getWriter().toString();
	}
    
    public static String getXmlString (Node xmlNode) throws TransformerFactoryConfigurationError, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(xmlNode);
        transformer.transform(source, result);
        
        return result.getWriter().toString();
    }
    

    public static Document parseXmlString (String xmlString) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        return docBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
    }
    
    public static String xmlEncode (String str)
    {
        return str.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace("<", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&apos;");
    }
    
    /// <summary>
    /// Utility function to retrieve typed value of first elem with tag elementName, or defaultVal if not found
    /// </summary>
    /// <param name="parent"></param>
    /// <param name="elementName"></param>
    /// <returns></returns>
    public static Object getNamedElemValue(Element parent, String elementName, Class basicType, Object defaultVal)
    {
        String val = getNamedElemValue(parent, elementName);
        if(val == null){
        	return defaultVal;
        }
        
        try {
        	if(Boolean.class.equals(basicType)){
        		return Boolean.parseBoolean(val);
        	}
        	else if(Integer.class.equals(basicType)){
	        	return Integer.parseInt(val);
	        }
	        else if (Float.class.equals(basicType)){
	        	return Float.parseFloat(val);
	        }
	        else if (Double.class.equals(basicType)){
	        	return Double.parseDouble(val);
	        }
	        else
	        	return val;
        } 
        catch (Exception e){
        	return defaultVal;
        }
    }
    
    /// <summary>
    /// Utility function to retrieve inner text of first elem with tag elementName, or null if not found
    /// </summary>
    /// <param name="parent"></param>
    /// <param name="elementName"></param>
    /// <returns></returns>
    public static String getNamedElemValue(Element parent, String elementName)
    {
        String val = null;
        NodeList list = parent.getElementsByTagName(elementName);
        if (list.getLength() > 0) {
            val = ((Element)list.item(0)).getTextContent();
        }
        return val;
    }

    /// <summary>
    /// Utility function to retrieve inner text of first elem with tag elementName, or null if not found
    /// </summary>
    /// <param name="parent"></param>
    /// <param name="elementName"></param>
    /// <returns></returns>
    public static String getNamedElemXml(Element parent, String elementName) throws Exception
    {
        String val = null;
        NodeList list = parent.getElementsByTagName(elementName);
        if (list.getLength() > 0) {
            val = getXmlString(list.item(0));
        }
        return val;
    }
    
  /// <summary>
    /// Utility function to retrieve inner text of first elem with tag elementName, or null if not found
    /// </summary>
    /// <param name="parent"></param>
    /// <param name="elementName"></param>
    /// <returns></returns>
    public static String getChildElemText(Element parent, String tagName)
    {
        String val = null;
        Element childElem = getFirstChildByTagName(parent, tagName);
        if(childElem != null){
            val = childElem.getTextContent();
        }
        return val;
    }
    
    public static Element getFirstChildByTagName(Node parent, String tagName){
        List<Element> children = getChildrenByTagName(parent, tagName);
        return (children.size() == 0) ? null : children.get(0);
    }
    
    public static List<Element> getChildrenByTagName(Node parent, String tagName){
        ArrayList<Element> elements = new ArrayList<Element>();
        NodeList children = parent.getChildNodes();
        for(int i = 0; i < children.getLength(); i++){
            Node child = children.item(i);
            if(child instanceof Element){
                Element elem = (Element)children.item(i);
                if(tagName.equals(elem.getTagName())){
                    elements.add(elem);
                }
            }
        }
        return elements;
    }

  //Copy inStream to outStream using Java's built in buffering, closing outStream when complete
    public static boolean bufferedCopyStream(InputStream inStream, OutputStream outStream) throws Exception {
        return bufferedCopyStream(inStream, outStream, true);
    }
    
    //Copy inStream to outStream using Java's built in buffering
    public static boolean bufferedCopyStream(InputStream inStream, OutputStream outStream, boolean closeStream) throws Exception {
        BufferedInputStream bis = new BufferedInputStream( inStream );
        BufferedOutputStream bos = new BufferedOutputStream ( outStream );
        while(true){
            int data = bis.read();
            if (data == -1){
                break;
            }
            bos.write(data);
        }
        bos.flush();
        if(closeStream){
            bos.close();
        }
        return true;
    }
}
