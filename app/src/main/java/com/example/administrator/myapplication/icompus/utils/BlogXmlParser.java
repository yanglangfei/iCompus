package com.example.administrator.myapplication.icompus.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
/**
 * ʵ����ת��
 * 
 * @author walkingp
 * 
 */
public class BlogXmlParser extends DefaultHandler {
	final String ENTRY_TAG = "string";// ����

	private String blogContent;// �������
	private boolean isStartParse;// ��ʼ����
	private StringBuilder currentDataBuilder;// ��ǰȡ����ֵ
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public BlogXmlParser() {
		super();
	}
	/**
	 * ���캯��
	 * 
	 * @return
	 */
	public BlogXmlParser(String content) {
		this.blogContent = content;
	}
	/**
	 * ������
	 * 
	 * @return
	 */
	public String GetBlogContent() {
		return blogContent;
	}
	/**
	 * �ĵ���ʼʱ����
	 */
	public void startDocument() throws SAXException {
		super.startDocument();
		currentDataBuilder = new StringBuilder();
	}
	/**
	 * ��ȡ������XML���
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase(ENTRY_TAG)) {
			blogContent = "";
			isStartParse = true;
		}
	}
	/**
	 * ��ȡԪ������
	 * 
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		currentDataBuilder.append(ch, start, length);
	}
	/**
	 * Ԫ�ؽ���ʱ����
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (isStartParse) {// ����Ŀ��
			String chars = currentDataBuilder.toString();
			Log.i("Blog", "���ڽ���" + localName);
			// ����
			if (localName.equalsIgnoreCase(ENTRY_TAG)) {// ����
				blogContent = chars;
				isStartParse = false;
			}
		}

		currentDataBuilder.setLength(0);
	}
	/**
	 * �ĵ�����ʱ����
	 */
	public void endDocument() throws SAXException {
		Log.i("Blog", "�ĵ��������");
		super.endDocument();
	}
}
