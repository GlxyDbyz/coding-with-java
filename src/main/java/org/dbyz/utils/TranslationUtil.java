package org.dbyz.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.StringUtil;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

/**
 * 翻译帮助类
 */
public class TranslationUtil {
	private static final Log log = LogFactory.getLog(TranslationUtil.class);
	protected static final String ENCODING = "UTF-8"; // 编码
	
	protected static final String CHINA = "zh"; // 简体中文
	protected static final String ENGLISH = "en"; // 英
	protected static final String JAPAN = "jp"; // 日
	protected static final String AUTO = "auto"; // 自动判断语言
	
	private static final String url = "http://fanyi.baidu.com/v2transapi";
	
	/**
	 * HTTP翻译
	 * 
	 * @param text         文本
	 * @param src_lang     来源语言
	 * @param target_lang  目标语言
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String src_lang,final String target_lang){
		String result = "";
		Map<String, String> param = new HashMap<String,String>(2);
		param.put("from" , src_lang );
		param.put("to" ,  target_lang);
		param.put("query" , text);
		String json = postData(url ,param);
		BaiduResponse response = null;
	    response = JSON.parseObject(json,BaiduResponse.class);
		if (response != null && response.trans_result != null) {
			StringBuffer bf = new StringBuffer();
			 BaiduResult[] transResults = response.trans_result.getData();
			 if(!ArrayUtils.isEmpty(transResults)){
				 for (BaiduResult baiduResult : transResults) {
					 bf.append(baiduResult.getDst()).append("\n");					 
				}
			 }
			 result = bf.toString();
		}
		return result;
	}

	/**
	 * 请求谷歌翻译页面相关
	 */
	private static String postData(String url,Map<String,String> datas) {
		String resultData = "";
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod();
		post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * 8);
		post.setRequestHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
		post.setRequestHeader("Connection","keep-alive");
		post.setRequestHeader("Cookie","_ga=GA1.3.1482014401.1471853424; NID=86=IA1oQzi_3JHfkkpLSAT7XQu6UtKTKTYMgKOuBcjRB26j29LlzZzyTv8uTIoW9sAOAOwimAZ-uXc3pQBYHRZaDoNJSj4yX88GKbz0tcbZdubafsewspRY8kJgHaNQ1bT_");
		post.setRequestHeader("Host","fanyi.baidu.com");
		post.setRequestHeader("Referer","http://fanyi.baidu.com");
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		if (!CollectionUtils.isEmpty(datas)) {
			NameValuePair[] nameValues = new NameValuePair[datas.size()];
			int i = 0;
			for (String key : datas.keySet()) {
				nameValues[i] = new NameValuePair();
				nameValues[i].setName(key);
				nameValues[i].setValue(datas.get(key));
				i++;
			}
			post.setRequestBody(nameValues);
		}
		
		try {
			URI uri = new URI(url,true);
			post.setURI(uri);
			client.executeMethod(post);
			resultData = new String(post.getResponseBody(),ENCODING);
			log.error("TranslationUtil resultData:"+resultData);
		} catch (Exception e) {
			log.error("err:",e);
		}finally{
			post.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(1);
		}
		return resultData;
	}
	
	/**
	 * 英文翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String en2cn(final String text){
		return translate(text, ENGLISH, CHINA);
	}

	/**
	 * 日文翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String jp2cn(final String text){
		return translate(text, JAPAN, CHINA);
	}

	/**
	 * 自动翻译到目标语言
	 * 
	 * @param text        文本
	 * @param target_lang 目标语言
	 * @return
	 * @throws Exception
	 */
	public static String autoTranslate(final String text, final String target_lang){
		return translate(text, AUTO, target_lang);
	}
	
	/**
	 * 自动翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String auto2cn(final String text) {
		return translate(text, AUTO, CHINA);
	}
	
	private static Pattern japanesePattern = Pattern.compile("[\u3040-\u309f]+|[\u30a0-\u30ff]+|[\u31f0-\u31ff]+");
	private static Pattern chinessPattern = Pattern.compile("[\u4e00-\u9fa5]+");
	/**
	 * 是否需要翻译
	 * 
	 * @param words  文本
	 * @param japaneseLimitProportion  日文最低限制比例
	 * @return 中文false &nbsp; 外文true
	 */
	public static boolean needTranslate(String words, Double japaneseLimitProportion){
		if(StringUtil.isBlank(words)){
			return false;
		}
		
		if(japaneseLimitProportion==null || japaneseLimitProportion<0.0D){
			japaneseLimitProportion = 0.0D;
		}
		
		int descriptionLength = words.length();
		Matcher matcher = japanesePattern.matcher(words);
		int japaneseLength = descriptionLength - matcher.replaceAll("").length();
		matcher = chinessPattern.matcher(words);
		if(matcher.find() && (Double.valueOf(japaneseLength) /descriptionLength <= japaneseLimitProportion)){
			// 有中文，日文比例小于限制的最小比例不翻译
			return false;
		}
		return true;
	}
	
	public static boolean isJp(String words){
		int descriptionLength = words.length();
		Matcher matcher = japanesePattern.matcher(words);
		int japaneseLength = descriptionLength - matcher.replaceAll("").length();
		matcher = chinessPattern.matcher(words);
		if(matcher.find()&&(Double.valueOf(japaneseLength) /descriptionLength <= 0.0D)){
			return false;
		}
		return true;
	}
	
	public class BaiduResponse{
		private BaiduTransResult trans_result;
        public BaiduTransResult getTrans_result() {
			return trans_result;
		}

		public void setTrans_result(BaiduTransResult trans_result) {
			this.trans_result = trans_result;
		}
	}
	
	public class BaiduTransResult{
        private String from;
		private String to;
		private BaiduResult[] data;
        public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public BaiduResult[] getData() {
			return data;
		}
		public void setData(BaiduResult[] data) {
			this.data = data;
		}
	}
	
	public class BaiduResult{
		private String src;
		private String dst;
        public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getDst() {
			return dst;
		}
		public void setDst(String dst) {
			this.dst = dst;
		}
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		try {
			String text= "Leather\nDouble chain shoulder straps with leather shoulder pad\nEmbossed interlocking G\nCotton linen interior lining with a zip pocket and two slip pockets\nInterior hook closure\nComes with a dust bag\nThis product was sourced for Gilt by a trusted independent supplier.\n";
			System.out.println(auto2cn(text));
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}