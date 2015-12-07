package com.bbs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Xml {

    private String filePath = "";
    private String strXml = "";

    public Xml(String _filePath) {
	this.filePath = _filePath;
    }

    public void setFilePath(String _filePath) {
	this.filePath = _filePath;
    }

    public String getFilePath() {
	return this.filePath;
    }

    public void setXmlString(String strXml) {
	this.strXml = strXml;
    }

    public String getRootValueFromXml(String nodeAttribute) {
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    if (this.filePath.equals(""))
		return "";

	    Document doc = builder.build(new FileReader(this.filePath));
	    Element root = doc.getRootElement();

	    String nodeAtb = root.getAttributeValue(nodeAttribute);
	    return nodeAtb;

	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}
    }

    public String getRootValueFromXmlString(String nodeAttribute) {

	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    Document doc = builder.build(new StringReader(this.strXml));
	    Element root = doc.getRootElement();
	    String nodeAtb = root.getAttributeValue(nodeAttribute);
	    return nodeAtb;

	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}
    }

    public String getValueFromXml(String nodeName, String nodeAttribute) {

	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    if (this.filePath.equals(""))
		return "";

	    Document doc = builder.build(new FileReader(this.filePath));
	    Element root = doc.getRootElement();

	    Element nd = root.getChild(nodeName);
	    String nodeAtb = nd.getAttributeValue(nodeAttribute);
	    return nodeAtb;

	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}

    }

    public String getValueFromXmlString(String nodeName, String nodeAttribute) {

	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    Document doc = builder.build(new StringReader(this.strXml));
	    Element root = doc.getRootElement();

	    Element nd = root.getChild(nodeName);
	    String nodeAtb = nd.getAttributeValue(nodeAttribute);
	    return nodeAtb;

	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}
    }

    public String[] getValueFromXmlByID(String strPhylum, String nodeName, String sid) {
	String[] arg = new String[3];
	List cnd = null;
	List lnd = null;
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    if (this.filePath.equals(""))
		return null;

	    Document doc = builder.build(new FileReader(this.filePath));
	    Element root = doc.getRootElement();
	    cnd = root.getChildren();
	    Iterator ic = cnd.iterator();
	    while (ic.hasNext()) {
		Element ndl = (Element) ic.next();
		if (ndl.getName().equals("language") && ndl.getAttributeValue("type").equals(strPhylum)) {
		    lnd = ndl.getChildren();

		    Iterator i = lnd.iterator();
		    while (i.hasNext()) {
			Element nd = (Element) i.next();

			if (nd.getAttributeValue("id").equals(sid)) {
			    arg[0] = nd.getAttributeValue("version");
			    arg[1] = nd.getAttributeValue("fileName");
			    arg[2] = nd.getAttributeValue("md5");
			    return arg;
			}
		    }
		}

	    }

	    return null;

	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }
    
    /**
	 * 根据xml文件 路径 和 节点名获得节点的值
	 */
	public static String getXmlTagValue(String xmlFileName,String tagName) throws JDOMException, IOException{
		String s=null;
		try{
			File f=new File(xmlFileName);
			InputStream ins=new FileInputStream(f);
			SAXBuilder builder = new SAXBuilder(false);
			
			Document doc=builder.build(ins);
			
			Element root=doc.getRootElement();
			
			s=root.getChildText(tagName);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (JDOMException e) {
			throw e;
		}catch (IOException e) {
			throw e;
		}
		return s;
	}



    public String[] getValueFromXmlStringByID(String strPhylum, String nodeName, String sid) {
	String[] arg = new String[3];
	List cnd = null;
	List lnd = null;
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    Document doc = builder.build(new StringReader(this.strXml));
	    Element root = doc.getRootElement();

	    cnd = root.getChildren();
	    Iterator ic = cnd.iterator();
	    while (ic.hasNext()) {
		Element ndl = (Element) ic.next();
		if (ndl.getName().equals("language") && ndl.getAttributeValue("type").equals(strPhylum)) {
		    lnd = ndl.getChildren();

		    Iterator i = lnd.iterator();
		    while (i.hasNext()) {
			Element nd = (Element) i.next();

			if (nd.getAttributeValue("id").equals(sid)) {
			    arg[0] = nd.getAttributeValue("version");
			    arg[1] = nd.getAttributeValue("fileName");
			    arg[2] = nd.getAttributeValue("md5");
			    return arg;
			}
		    }
		}

	    }

	    return null;

	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public ArrayList getNodeListXML(String strPhylum) {
	ArrayList ndlist = new ArrayList();
	List cnd = null;
	List lnd = null;
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    if (this.filePath.equals(""))
		return null;

	    Document doc = builder.build(new FileReader(this.filePath));
	    Element root = doc.getRootElement();

	    cnd = root.getChildren();
	    Iterator ic = cnd.iterator();
	    while (ic.hasNext()) {
		Element ndl = (Element) ic.next();
		if (ndl.getName().equals("language") && ndl.getAttributeValue("type").equals(strPhylum)) {
		    lnd = ndl.getChildren();

		    Iterator i = lnd.iterator();
		    while (i.hasNext()) {
			Element nd = (Element) i.next();
			ndlist.add(nd.getAttributeValue("id"));
		    }
		}
	    }

	    return ndlist;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    public ArrayList getNodeListXMLString(String strPhylum) {
	ArrayList ndlist = new ArrayList();
	List cnd = null;
	List lnd = null;
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    Document doc = builder.build(new StringReader(this.strXml));
	    Element root = doc.getRootElement();

	    cnd = root.getChildren();
	    Iterator ic = cnd.iterator();
	    while (ic.hasNext()) {
		Element ndl = (Element) ic.next();
		if (ndl.getName().equals("language") && ndl.getAttributeValue("type").equals(strPhylum)) {
		    lnd = ndl.getChildren();

		    Iterator i = lnd.iterator();
		    while (i.hasNext()) {
			Element nd = (Element) i.next();
			ndlist.add(nd.getAttributeValue("id"));
		    }
		}
	    }

	    return ndlist;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    public boolean savetoFileXMLString(String filePath) {
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    Document doc = builder.build(new StringReader(this.strXml));
	    XMLOutputter fmt = new XMLOutputter();
	    FileWriter writer = new FileWriter(filePath);
	    Format f = Format.getPrettyFormat();
	    fmt.setFormat(f);
	    fmt.output(doc, writer);
	    writer.close();
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean savetoFileXML(String newfilePath) {
	try {
	    SAXBuilder builder = new SAXBuilder(false);
	    if (this.filePath.equals(""))
		return false;
	    Document doc = builder.build(new FileReader(this.filePath));
	    XMLOutputter fmt = new XMLOutputter();
	    FileWriter writer = new FileWriter(newfilePath);
	    Format f = Format.getPrettyFormat();
	    fmt.setFormat(f);
	    fmt.output(doc, writer);
	    writer.close();

	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

}
