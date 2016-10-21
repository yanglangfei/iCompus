package com.example.administrator.myapplication.icompus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import android.R.integer;
import android.net.Uri;
import android.os.Environment;

public class FileUtil {
	public static final String TAG = FileUtil.class.getSimpleName();
	
	public static final String ROOT = Environment.getExternalStorageDirectory().getPath();
	public static final String PATH_ICAMPUS = ROOT + "/icampus";
	public static final String PATH_BOOK = PATH_ICAMPUS + "/books";
	public static final String PATH_IMAGES = PATH_ICAMPUS + "/img";

	public static boolean initDir(){
		boolean b = false;
		File dirBook = new File(PATH_BOOK);
		if (!dirBook.exists()) {
			b = dirBook.mkdirs();
		}
		
		File dirImg = new File(PATH_IMAGES);
		if (!dirImg.exists()) {
			b = dirImg.mkdir();
		}
		return b;
	}
	
	public static File[] getBooks(){
		File dirBook = new File(PATH_BOOK);
		File[] files = dirBook.listFiles();
		return files;
	}
	
	public static String readfile(String path){
		String content = "";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			int size = fis.available();
			byte[] buf = new byte[size];
			fis.read(buf);
			content = EncodingUtils.getString(buf, "UTF-8");
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	
}
