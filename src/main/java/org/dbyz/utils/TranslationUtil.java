package org.dbyz.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;

/**
 * 翻译帮助类
 */
public class TranslationUtil {
	private static final Log log = LogFactory.getLog(TranslationUtil.class);
	protected static final String ENCODING = "UTF-8"; // 编码
	
	protected static final String GOOGLE_TRANSLATE_URL_TEMPLATE = "http://translate.google.cn";
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
	public static String googleTranslate(final String text, final String src_lang,final String target_lang,Boolean html){
		Document doc = null;
		Element ele = null;
		String result = "";
		try {
			Map<String, String> param = new HashMap<String,String>(2);
			param.put("langpair" , src_lang + "|" + target_lang);
			param.put("text" , text);
			String resultHtml = postData(GOOGLE_TRANSLATE_URL_TEMPLATE,param );
			doc = Jsoup.parse(resultHtml);
			ele = doc.getElementById(GOOGLE_ID_RESULTBOX);
			if(ele!=null){
				if(html!=null && html){
					result = ele.html();
				}else{
					result = ele.text();
				}
			}
		}finally {
			doc = null;
			ele = null;
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
		post.setRequestHeader("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
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
	 * @param html 返回html
	 * @return
	 * @throws Exception
	 */
	public static String en2cn(final String text,Boolean html){
		return googleTranslate(text, GOOGLE_ENGLISH, GOOGLE_CHINA,html);
	}

	/**
	 * 日文翻译到中文
	 * 
	 * @param text
	 * @param html 返回html
	 * @return
	 * @throws Exception
	 */
	public static String jp2cn(final String text,Boolean html){
		return googleTranslate(text, GOOGLE_JAPAN, GOOGLE_CHINA,html);
	}

	/**
	 * 自动翻译到目标语言
	 * 
	 * @param text        文本
	 * @param target_lang 目标语言
	 * @param html        返回html
	 * @return
	 * @throws Exception
	 */
	public static String autoTranslate(final String text, final String target_lang,Boolean html){
		return googleTranslate(text, GOOGLE_AUTO, target_lang,html);
	}
	
	/**
	 * 自动翻译到中文
	 * 
	 * @param text
	 * @param html 返回html
	 * @return
	 * @throws Exception
	 */
	public static String auto2cn(final String text,Boolean html) {
		return googleTranslate(text, GOOGLE_AUTO, GOOGLE_CHINA,html);
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		try {
			String text = "There are moments in life when you miss someone so much that you just want to pick them from "
					+ "your dreams and hug them for real! Dream what you want to dream;go where you want to go;be what "
					+ "you want to be,because you have only one life and one chance to do all the things you want to do."
					+ "May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to "
					+ "keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that "
					+ "it hurts you,it probably hurts the other person, too.The happiest of people don’t necessarily have "
					+ "the best of everything;they just make the most of everything that comes along their way.Happiness "
					+ "lies for those who cry,those who hurt, those who have searched,and those who have tried,for only they"
					+ " can appreciate the importance of people　who have touched their lives.Love begins with a smile,grows"
					+ " with a kiss and ends with a tear.The brightest future will always be based on a forgotten past, you "
					+ "can’t go on well in lifeuntil you let go of your past failures and heartaches.When you were born,you "
					+ "were crying and everyone around you was smiling.Live your life so that when you die,you're the one who "
					+ "is smiling and everyone around you is crying.Please send this message to those people who mean something "
					+ "to you,to those who have touched your life in one way or another,to those who make you smile when you "
					+ "really need it,to those that make you see the brighter side of things when you are really down,to those "
					+ "who you want to let them know that you appreciate their friendship.And if you don’t, don’t worry,nothing"
					+ " bad will happen to you,you will just miss out on the opportunity to brighten someone’s day with this message.";
			System.out.println(auto2cn(text,false));
			text = "生活の文化を笑颜であるし、何もない滞纳私たちは、常にする必要があるので苦い。感谢の気持ちでいっぱいの人生への対処、少なくとも、そ"
					+ "れは私たちの生活を与えている、生き残るために私たちを与えた。笑颜の金持ちと贫しい人々 の生活态度、状况が必ずしも位置ではありま"
					+ "せん。金持ちは常に心配でした、しかし贫しいを缓和することがあります: 障害楽観の穷状容易になります;、スムーズな难色を示す场合が"
					+ "あります、人々 が笑颜... 人の感情的な环境、これはかなり普通ですが、苦い、1 组の kudachoushen 状况に直面し、変更がない、する生"
					+ "活の向上と、笑颜、他のメンバーに、话に前向き场合は逆に、机会の详细になります。Sun の现実を感じるように太阳の光の心臓部のみ] "
					+ "は、しばしば颜、苦しんでいる场合その生活より良いですか？ 人生は我々 のイメージによると、ミラーは泣く、泣く、私たちの生活と笑颜、"
					+ "笑颜で生活。笑颜、心が、どちらも弱いバカも强力にお世辞のであります。あなたの笑颜、お世辞で虚伪スマイル マスクが长く、ないだろう"
					+ "と、机会があるあれば、彼らを明らかに、以下のサイトを対象になります。目的の笑颜、それ、上司はガードのかどうかは、笑颜とは、笑颜"
					+ "他の点である、尊重の生活。笑颜を「复帰」は、関系は物理学力のバランス、何を言うよう他人ためには、他多く笑颜を扱います、他お客様"
					+ "の笑颜详细になります。他のユーザーによって歪められての怒りを选択することができます、笑颜を选択することもできます、笑颜は小さく、"
					+ "感じる开放的な方法で丑いは明らかに、他の心の冲撃されますので通常は笑颜の力をさらに大きなになります。清経、暗い云からであります。"
					+ "时々 あまり说明不要です。故意 vilify 人を知っていたは、人は彼は笑颜を与えたそれの残りの部分がそれを证明する时间だった。その时、"
					+ "どこでも、アインシュタインの理论が间违っていると 100 の科学者を目撃することだったのだ、アインシュタインと言うこのことは、ちょう"
					+ "ど、笑颜のヒントを知って、多くに 100 人、私は本当にですか？ だけ间违って男前方に来た。アインシュタインの论者ビートに笑颜を明らか"
					+ "にしなかったが、时间のテストが行われました。笑颜の心、伪装することはできません。人生は良くなると、「笑颜」考え方をしてください。"
					+ "生活の中で挫折失败した误解に暮らすしたい场合は、まずすべての障害物の明确べきこと、全く正常です。笑颜は爱情、爱の本质であり、平凡"
					+ "なことがある必要があります。笑颜は最高のビジネス カードの生活と人は楽観的な人间の友达に话をしたくはないですか？ 笑颜を自信を持っ"
					+ "て、自分を与えることができますが、また他の人に向上につながる、自信を持って、动机付け潜在的な。笑颜はお友达との间の最高の言语、自"
					+ "発的な笑颜、冲撃は、最初のかどうか互いを知っている、长い时间、お互いの间の距离に笑颜他暖かい感じ。笑颜は达成であり、大きな成果、"
					+ "诚心诚意、笑颜の本质である奨励、暖かいです。本当に、常に简単な他より多くの成功を达成するためには、常に简単に笑颜を知っています。";
			System.out.println(auto2cn(text,false));
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}