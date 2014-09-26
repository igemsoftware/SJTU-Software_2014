package data_center; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;

@SuppressWarnings("deprecation")
public class OfficialUploadFiller
{
	
	@SuppressWarnings({ "resource", "unused" })
	public static void login() throws Exception
	{
		//初始化
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    // 这一行必须要加，否则服务器无法获取登陆状态
	    HttpClientParams.setCookiePolicy(httpclient.getParams(),CookiePolicy.BROWSER_COMPATIBILITY);
	
	    // 第一次访问
	    String url = "http://www.baidu.com";
	    HttpGet httpget = new HttpGet(url);
	    HttpResponse response;// = httpclient.execute(httpget);
	    //System.out.println("Length1::" + response.getEntity().getContentLength());
	    HttpEntity entity;// = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	    
	    // 登陆【使用POST方式登录】
	    // 如果要直接执行，麻烦去申请个百度的帐号
	    // 不好意思，给百度做广告了
	    HttpPost httpost = new HttpPost("http://passport.baidu.com/?login");
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("username", "梦泽牧马人"));
	    nvps.add(new BasicNameValuePair("password", "wtgwst0901"));
	    httpost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
	    response = httpclient.execute(httpost);
	
	    // 第二次访问
	    System.out.println("\n----------------------------------------");
	    System.out.println(response.getStatusLine());
	    List<Cookie> cookies = httpclient.getCookieStore().getCookies();
	    entity = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	
	    System.out.println("\n----------------------------------------");
	
	    cookies = httpclient.getCookieStore().getCookies();
	    
	    System.out.println("cookies" + cookies.size());
	    httpget = new HttpGet(url);
	    // httpget.setr
	    // httpget.setHeader(name, value)
	    //response = httpclient.execute(httpget);
	    System.out.println("Length2::"
	            + response.getEntity().getContentLength());
	    entity = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	}
	
	
	
	
	public static void httpGet() throws Exception
	{	
		
		
		Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider>create()
				.register(CookieSpecs.BEST_MATCH,
				new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY,
				new BrowserCompatSpecFactory())
				.build();
		
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("username", "DustInMonteriggioni"));
	    nvps.add(new BasicNameValuePair("password", "********"));
	    
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, 
				new UsernamePasswordCredentials("DustInMonteriggioni", "*********"));
		
		HttpClientContext context = HttpClientContext.create();
		CookieStore cookieStore = new BasicCookieStore();
		context.setCredentialsProvider(credsProvider);
		context.setCookieStore(cookieStore);
		
		CloseableHttpClient httpclient = 
				HttpClients.custom().setDefaultCookieSpecRegistry(r)
				.setDefaultCookieStore(cookieStore).build();
		
		//HttpGet httpget = new HttpGet("https://igem.org/Login2");
		HttpPost httpost = new HttpPost("http://igem.org/Login2");
		
		httpost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		
		CloseableHttpResponse response = httpclient.execute(httpost, context);
		System.out.println(response.getStatusLine());
		
		
		System.out.println("\n\n\n********\n");
		Header[] headers = response.getAllHeaders();
		for (Header header : headers)
			System.out.println(header);
		
		
		HttpEntity entity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
		String line;
		while ( (line = reader.readLine()) != null )
			System.out.println(line);
		reader.close();
		
		List<Cookie> cookies = context.getCookieStore().getCookies();
		
		System.out.println("\n\n\n" + cookies.size());
		
		for (Cookie outCookie : cookies)
			System.out.println(outCookie);
		
		response.close();
	}
	
	
	/**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet()//String url, String param)
    {
        String result = "";
        BufferedReader in = null;
        try
        {	//String urlNameString = url + "?" + param;
        	String urlNameString = "http://www.baidu.com/";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            //connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("user-agent",
                    //"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet())
                System.out.println(key + "--->" + map.get(key));
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null)
                result += line + "\n";
            
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally
        {	try {
                if (in != null) 
                    in.close();
            } catch (Exception e2) {e2.printStackTrace();}
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent",
                    //"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
  
}
