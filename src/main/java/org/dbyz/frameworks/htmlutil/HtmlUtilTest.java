package org.dbyz.frameworks.htmlutil;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * HtmlUti 的使用
 *
 * @ClassName: HtmlUtilTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class HtmlUtilTest {
    @Test
    public void test01() throws Exception{
        String str;
        //使用FireFox读取网页
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = webClient.getPage("http://www.baidu.com/");
        str = page.getTitleText();
        System.out.println(str);
        //关闭webclient
        webClient.close();
    }
    @Test
    public void homePage() throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
        final HtmlPage page = webClient.getPage("http://yangshangchuan.iteye.com");
        Assert.assertEquals("杨尚川的博客 - ITeye技术网站", page.getTitleText());
        final String pageAsXml = page.asXml();
        Assert.assertTrue(pageAsXml.contains("杨尚川，系统架构设计师，系统分析师，2013年度优秀开源项目APDPlat发起人，资深Nutch搜索引擎专家。多年专业的软件研发经验，从事过管理信息系统(MIS)开发、移动智能终端(Win CE、Android、Java ME)开发、搜索引擎(nutch、lucene、solr、elasticsearch)开发、大数据分析处理(Hadoop、Hbase、Pig、Hive)等工作。目前为独立咨询顾问，专注于大数据、搜索引擎等相关技术，为客户提供Nutch、Lucene、Hadoop、Solr、ElasticSearch、HBase、Pig、Hive、Gora等框架的解决方案、技术支持、技术咨询以及培训等服务。"));
        final String pageAsText = page.asText();
        Assert.assertTrue(pageAsText.contains("[置顶] 国内首套免费的《Nutch相关框架视频教程》(1-20)"));
        webClient.close();
    }
    @Test
    public void homePage_Firefox() throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
        final HtmlPage page = webClient.getPage("http://yangshangchuan.iteye.com");        
        Assert.assertEquals("杨尚川的博客 - ITeye技术网站", page.getTitleText());
        webClient.close();
    }
    @Test
    public void getElements() throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        final HtmlPage page = webClient.getPage("http://yangshangchuan.iteye.com");
        final HtmlDivision div = page.getHtmlElementById("blog_actions");
        //获取子元素
        Iterator<DomElement> iter = div.getChildElements().iterator();
        while(iter.hasNext()){
            System.out.println(iter.next().getTextContent());
        }
        //获取所有输出链接
        for(HtmlAnchor anchor : page.getAnchors()){
            System.out.println(anchor.getTextContent()+" : "+anchor.getAttribute("href"));
        }
        webClient.close();
    }
    @Test
    public void xpath() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://yangshangchuan.iteye.com");
        //获取所有博文标题
        final List<HtmlAnchor> titles = (List<HtmlAnchor>)page.getByXPath("/html/body/div[2]/div[2]/div/div[16]/div/h3/a");
        for(HtmlAnchor title : titles){
            System.out.println(title.getTextContent()+" : "+title.getAttribute("href"));
        }
        //获取博主信息
        final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@id='blog_owner_name']").get(0);
        System.out.println(div.getTextContent());
        webClient.close();
    }
    @Test
    public void submittingForm() throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
        final HtmlPage page = webClient.getPage("http://www.oschina.net");
        // Form没有name和id属性
        final HtmlForm form = page.getForms().get(0);
        final HtmlTextInput textField = form.getInputByName("q");
        final HtmlButton button = form.getButtonByName("");
        textField.setValueAttribute("APDPlat");
        final HtmlPage resultPage = button.click();
        final String pageAsText = resultPage.asText();
        Assert.assertTrue(pageAsText.contains("找到约"));
        Assert.assertTrue(pageAsText.contains("条结果"));        
        webClient.close();
    }
}
