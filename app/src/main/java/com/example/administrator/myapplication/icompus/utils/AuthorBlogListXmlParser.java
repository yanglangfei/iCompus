package com.example.administrator.myapplication.icompus.utils;

import java.util.ArrayList;
import java.util.Date;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

import com.example.administrator.myapplication.icompus.entity.Blog;

import org.xml.sax.Attributes;
import org.apache.commons.lang.*;

/**
 * Blog����xml������
 * @author walkingp
 *
 */
public class AuthorBlogListXmlParser extends DefaultHandler {
	final String ENTRY_TAG="entry";//����
	final String ENTRY_ID_TAG="id";//��ű��
	final String ENTRY_TITLE_TAG="title";//������
	final String ENTRY_SUMMARY_TAG="summary";//�����
	final String ENTRY_PUBLISHED_TAG="published";//����ʱ����
	final String ENTRY_UPDATED_TAG="updated";//����ʱ����
	final String ENTRY_AUTHOR_NAME_TAG="name";//���������
	final String ENTRY_AUTHOR_URL_TAG="uri";//��������ҳ
	final String ENTRY_LINK_TAG="link";//ʵ��t�ӵ�ַ
	final String ENTRY_DIGG_TAG="diggs";//�Ƽ����
	final String ENTRY_VIEW_TAG="views";//�鿴����
	final String ENTRY_COMMENTS_TAG="comments";//���۴���
	final String ENTRY_AVATOR_TAG="avatar";//ͷ���ַ
	final String ENTRY_URL_TAG="link";//ʵ����ַ��ǩ
	final String ENTRY_URL_ATTRIBUTE_TAG="href";//��ַ���Ա�ǩ
	
	private ArrayList<Blog> listBlog;//���󼯺�
	private Blog entity;//�������
	private boolean isStartParse;//��ʼ����
	private StringBuilder currentDataBuilder;//��ǰȡ����ֵ
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public AuthorBlogListXmlParser(){
		super();
	}
	/**
	 * ���캯��
	 * @return
	 */
	public AuthorBlogListXmlParser(ArrayList<Blog> list){
		this.listBlog=list;
	}
	/**
	 * ������
	 * @return
	 */
	public ArrayList<Blog> GetBlogList(){
		return listBlog;
	}
	/**
	 * �ĵ���ʼʱ����
	 */
	public void startDocument() throws SAXException{
		Log.i("Blog","�ĵ�����ʼ");
		super.startDocument();
		listBlog=new ArrayList<Blog>();
		currentDataBuilder = new StringBuilder();  	}
	/**
	 * ��ȡ������XML���
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	throws SAXException {
		super.startElement(uri, localName, qName,  attributes);
		if(localName.equalsIgnoreCase(ENTRY_TAG))  
        {  
            entity = new Blog();
            isStartParse = true;   
        }
		if(isStartParse && localName.equalsIgnoreCase(ENTRY_URL_TAG)){
        	entity.SetBlogUrl(attributes.getValue(ENTRY_URL_ATTRIBUTE_TAG));
        }
	}
	/**
	 * ��ȡԪ������
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
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
		if(isStartParse){//����Ŀ��
			String chars=currentDataBuilder.toString();
			Log.i("Blog","���ڽ���" + localName);
    		//����
    		if(localName.equalsIgnoreCase(ENTRY_TITLE_TAG)){//����
    			try{
    				chars=StringEscapeUtils.unescapeHtml(chars);//���б��봦�?�������&gt;����html
    				//chars=AppUtil.HtmlToText(chars);
    			}catch(Exception ex){
    				Log.e("blogXml", "__________������_____________");
    			}
    			entity.SetBlogTitle(chars);
    		}else if(localName.equalsIgnoreCase(ENTRY_SUMMARY_TAG)){//ժҪ
    			try{
    				//chars=URLDecoder.decode(chars);
    				chars=StringEscapeUtils.unescapeHtml(chars);//���б��봦�?�������&gt;����html
    				//chars=AppUtil.HtmlToText(chars);
    			}catch(Exception ex){
    				Log.e("newsXml", "__________������_____________");
    			}
    			entity.SetSummary(chars);
    		}else if(localName.equalsIgnoreCase(ENTRY_ID_TAG)){//���
    			int id=Integer.parseInt(chars);
    			entity.SetBlogId(id);
    		}else if(localName.equalsIgnoreCase(ENTRY_PUBLISHED_TAG)){//����ʱ��    			 
    			Date addTime=AppUtil.ParseUTCDate(chars);
				entity.SetAddTime(addTime);				
    		}else if(localName.equalsIgnoreCase(ENTRY_UPDATED_TAG)){//�޸�ʱ��
    			Date updateTime=AppUtil.ParseUTCDate(chars);
				entity.SetUpdateTime(updateTime);
    		}else if(localName.equalsIgnoreCase(ENTRY_AUTHOR_NAME_TAG)){//�������
    			entity.SetAuthor(chars);
    		}else if(localName.equalsIgnoreCase(ENTRY_DIGG_TAG)){//�Ƽ����
    			entity.SetDiggsNum(Integer.parseInt(chars));
    		}else if(localName.equalsIgnoreCase(ENTRY_VIEW_TAG)){//�鿴����
    			entity.SetViewNum(Integer.parseInt(chars));
    		}else if(localName.equalsIgnoreCase(ENTRY_COMMENTS_TAG)){//���۴���
    			entity.SetCommentNum(Integer.parseInt(chars));
    		}else if(localName.equalsIgnoreCase(ENTRY_AVATOR_TAG)){//�û�ͷ��
    			entity.SetAvator(chars);
    		}else if(localName.equalsIgnoreCase(ENTRY_TAG)){//��ֹ
    			listBlog.add(entity);
    			isStartParse=false;
    		}
		}
		
		currentDataBuilder.setLength(0);
	}
	/**
	 * �ĵ�����ʱ����
	 */
	public void endDocument() throws SAXException{
		Log.i("Blog","�ĵ��������");
		super.endDocument();
	}
}





















