package com.xml.jdom;

import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class DocumentWriter {

	public void writer(Document document,OutputStream os){
		try {
			new XMLOutputter().output(document,os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writer(Element element,OutputStream os){
		try {
			new XMLOutputter().output(element,os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
