package org.dbyz.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 翻译帮助类
 */
public class TranslationUtil {
	protected static final String ENCODING = "UTF-8"; // 编码
	
	protected static final String GOOGLE_TRANSLATE_URL_TEMPLATE = "http://translate.google.cn/?langpair={0}&text={1}";
	protected static final String GOOGLE_ID_RESULTBOX = "result_box";// 谷歌页面翻译数据span id
	protected static final String GOOGLE_CHINA = "zh-CN"; // 简体中文
	protected static final String GOOGLE_ENGLISH = "en"; // 英
	protected static final String GOOGLE_JAPAN = "ja"; // 日
	protected static final String GOOGLE_AUTO = "auto"; // google自动判断语言
	
	/**
	 * HTTP 谷歌翻译
	 * 
	 * @param text         文本
	 * @param src_lang     来源语言
	 * @param target_lang  目标语言
	 * @return
	 * @throws Exception
	 */
	public static synchronized String googleTranslate(final String text, final String src_lang,
			final String target_lang){
		Document doc = null;
		Element ele = null;
		String result = "";
		try {
			String url;
				url = MessageFormat.format(GOOGLE_TRANSLATE_URL_TEMPLATE,
						URLEncoder.encode(src_lang + "|" + target_lang, ENCODING),
						URLEncoder.encode(text, ENCODING));
			String resultHtml = postData(url);
			doc = Jsoup.parse(resultHtml);
			ele = doc.getElementById(GOOGLE_ID_RESULTBOX);
			if(ele!=null){
				result = ele.text();
			}
		} catch(UnsupportedEncodingException | URIException e){
			
		}finally {
			doc = null;
			ele = null;
		}
		return result;
	}

	/**
	 * 请求谷歌翻译页面相关
	 */
	protected static final HttpClient client = new HttpClient();
	protected static final PostMethod post = new PostMethod();
	static{
		post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * 20);
		post.setRequestHeader("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
	}
	private static String postData(String url) throws URIException, NullPointerException{
		URI uri = new URI(url,true);
		post.setURI(uri);
		String result = "";
		try {
			client.executeMethod(post);
			result = new String(post.getResponseBody(),ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 英文翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String en2cn(final String text){
		return googleTranslate(text, GOOGLE_ENGLISH, GOOGLE_CHINA);
	}

	/**
	 * 日文翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String jp2cn(final String text){
		return googleTranslate(text, GOOGLE_JAPAN, GOOGLE_CHINA);
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
		return googleTranslate(text, GOOGLE_AUTO, target_lang);
	}
	
	/**
	 * 自动翻译到中文
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String auto2cn(final String text) {
		return googleTranslate(text, GOOGLE_AUTO, GOOGLE_CHINA);
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		try {
			String text = "There are moments in life when you miss someone so much that you just want to pick them from your dreams and hug them for real! Dream what you want to dream;go where you want to go;be what you want to be,because you have only one life and one chance to do all the things you want to do.May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too.The happiest of people don’t necessarily have the best of everything;they just make the most of everything that comes along their way.Happiness lies for those who cry,those who hurt, those who have searched,and those who have tried,for only they can appreciate the importance of people　who have touched their lives.Love begins with a smile,grows with a kiss and ends with a tear.The brightest future will always be based on a forgotten past, you can’t go on well in lifeuntil you let go of your past failures and heartaches.When you were born,you were crying and everyone around you was smiling.Live your life so that when you die,you're the one who is smiling and everyone around you is crying.Please send this message to those people who mean something to you,to those who have touched your life in one way or another,to those who make you smile when you really need it,to those that make you see the brighter side of things when you are really down,to those who you want to let them know that you appreciate their friendship.And if you don’t, don’t worry,nothing bad will happen to you,you will just miss out on the opportunity to brighten someone’s day with this message.";
			System.out.println(auto2cn(text));
			text = "忍ぶ冬…冬でも、寒さに耐えて、葉を落とさないから、こう書くのだそうです。 すいかずらと呼ぶのは、水をよく吸う蔓だからという説、花の根本にある蜜を子供たちが吸ったからという説などがあります。";
			System.out.println(auto2cn(text));
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}