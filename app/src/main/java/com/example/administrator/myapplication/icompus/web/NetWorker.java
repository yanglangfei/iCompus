package com.example.administrator.myapplication.icompus.web;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

public class NetWorker {

/*
    private static DefaultHttpClient getHttpClient() {
        HttpParams httpParams = new BasicHttpParams();
        //连接超时。
        HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
        //Socket超时。
        HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
        //Socket缓存。
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        //重定向
        HttpClientParams.setRedirecting(httpParams, true);
        //User agent
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
        HttpProtocolParams.setUserAgent(httpParams, userAgent);

        return new DefaultHttpClient(httpParams);
    }


    public static String getData(String url, String charset) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }


        String resDate = "";
        HttpClient client = null;
        HttpGet get = null;
        try {
            client = getHttpClient();
            get = new HttpGet(url);
            //发送请求。
            HttpResponse response = client.execute(get);
            //请求失败。
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return "";
            }
            //请求成功。
            resDate = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.getConnectionManager().shutdown();
                } catch (Exception e) {
                }
            }
        }
        return resDate;
    }

    public static String getContent(String url) {
        String result = "";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpUriRequest req = new HttpGet(url);
            HttpResponse res = client.execute(req);
            int status = res.getStatusLine().getStatusCode();
            // 成功
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
                // Encode utf-8 to iso-8859-1
                // result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            }
            client.getConnectionManager().shutdown();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean networkIsAvailable(Context context) {
        ConnectivityManager cManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        if (info.isConnected()) {
            return true;
        }
        return false;
    }


    public static String GetXmlContentFromUrl(String url, String contentType) {
        return getContent(url, contentType).replaceAll("\n|\t|\r", "");
    }


    public static String getContent(String url, String contentType) {
        String result = "";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpUriRequest req = new HttpGet(url);
            HttpResponse res = client.execute(req);
            req.getParams().setParameter("Content-Type", "UTF-8");
            int status = res.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity ent = res.getEntity();
                result = EntityUtils.toString(ent);
                // Encode utf-8 to iso-8859-1
                result = new String(result.getBytes("ISO-8859-1"), contentType);
            }
            client.getConnectionManager().shutdown();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getContent(String url, List<NameValuePair> params) {
        String result = "";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpEntity postEntity = new UrlEncodedFormEntity(params);
            httpPost.setEntity(postEntity);
            HttpResponse res = client.execute(httpPost);
            int responseCode = res.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                result = res.getEntity().toString();
            }
            client.getConnectionManager().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


    public static Drawable loadImageFromUrlWithStore(String folder, String url) {
        try {
            //ע��url���ܰ�?�������Ҫ��?ǰ�ض�
            if (url.indexOf("?") > 0) {
                url = url.substring(0, url.indexOf("?"));
            }
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            String encodeFileName = URLEncoder.encode(fileName);
            URL imageUrl = new URL(url.replace(fileName, encodeFileName));
            byte[] data = readInputStream((InputStream) imageUrl.openStream());
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            String status = Environment.getExternalStorageState();
//			if (status.equals(Environment.MEDIA_MOUNTED)) {
//				FileAccess.MakeDir(folder);
//				String outFilename = folder + fileName;
//				bitmap.compress(CompressFormat.PNG, 100, new FileOutputStream(
//						outFilename));
//				Bitmap bitmapCompress = BitmapFactory.decodeFile(outFilename);
//				Drawable drawable = new BitmapDrawable(bitmapCompress);
//				return drawable;
//			}
        } catch (Exception e) {
            Log.e("download_img_err", e.toString());
        }
        return null;
    }


    public static Drawable loadImageFromUrl(String url) {
        InputStream is = null;
        try {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            String encodeFileName = URLEncoder.encode(fileName);
            URL imageUrl = new URL(url.replace(fileName, encodeFileName));
            is = (InputStream) imageUrl.getContent();
        } catch (Exception e) {
            Log.e("There", e.toString());
        }
        Drawable d = Drawable.createFromStream(is, "src");
        return d;
    }

    private static class HttpClient {
    }

    private static class HttpGet {
    }*/
}
