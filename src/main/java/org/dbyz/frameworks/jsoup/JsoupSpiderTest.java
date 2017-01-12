package org.dbyz.frameworks.jsoup;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 天猫国籍类目爬取
 */
public class JsoupSpiderTest {
	public static final int DEFAULT_TIME_OUT = 15000;
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static void main(String[] args) {
		RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(DEFAULT_TIME_OUT).setSocketTimeout(DEFAULT_TIME_OUT);
        RequestConfig requestConfig = builder.setCookieSpec(CookieSpecs.BEST_MATCH).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpGet get = new HttpGet("https://www.tmall.hk/");
        Document doc = null;
        try {
            HttpResponse response = client.execute(get);
            String html = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
            // Jsoup 解析html
            doc = Jsoup.parse(html);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        // css 选择器
        Elements elements  = doc.select("div.tm-hk-category > dl.J_catagory_item");
        for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			Elements rootCategoryH2 = element.getElementsByTag("h2");
			String rootCategoryName = null;
			if(rootCategoryH2.size()>=1){
				rootCategoryName = rootCategoryH2.get(0).text();
				System.out.println(rootCategoryName);
				Elements secondCategoryH3 = element.getElementsByTag("h3");
				for (int j = 0; j < secondCategoryH3.size(); j++) {
					String secondCategoryName = secondCategoryH3.get(j).text();
					System.out.println("\t"+secondCategoryName);
					Elements thirdCategorySpan = element.getElementsByTag("span");
					if(thirdCategorySpan.size()>=i){
						Elements thirdCategoryA = thirdCategorySpan.get(j).getElementsByTag("a");
						for (int k = 0; k < thirdCategoryA.size(); k++) {
							String thirdCategoryName = thirdCategoryA.get(k).text();
							System.out.println("\t\t"+thirdCategoryName);
						}
					}
				}
			}
		}
	}
}

//美妆个护
//	美妆护肤
//		保湿补水
//		洁面
//		乳液/面霜
//		化妆水/爽肤水
//		面部护理套装
//		面部精华
//		眼部护理
//		男士护理
//		精油芳疗
//		身体护理
//		手部护理
//		胸部护理
//		润唇膏
//		防晒
//		面膜
//		磨砂/去角质/按摩
//	美妆护肤热门品牌
//		欧莱雅
//		契尔氏
//		The history of whoo/后
//		九朵云
//		SK-II
//		雪花秀
//		it‘s skin
//		Avene/雅漾
//		Fresh/馥蕾诗
//		LEADERS/丽得姿
//		L’occitane/欧舒丹
//		Albion/澳尔滨
//	彩妆/香水
//		香水
//		口红/唇膏
//		睫毛膏
//		指甲油
//		BB霜
//		腮红
//		蜜粉散粉
//		粉饼
//		遮瑕
//		眼影
//		眼线
//		粉底
//		彩妆套装
//	彩妆香水热门品牌
//		Dior/迪奥
//		Etude/爱丽
//		3 CONCEPT EYES
//		iope
//		Missha
//		MAC/魅可
//		stylenanda
//		YSL/圣罗兰
//		The Face Shop
//		Benefit
//		OPI
//		KISS ME/奇士美
//		CANMAKE/井田
//	个人护理/美容工具
//		身体乳
//		止汗露
//		身体按摩
//		护手霜
//		洗发水
//		护发素
//		沐浴露
//		洗护套装
//		烫发染发
//		头发造型
//		口腔护理
//		润唇膏
//		清油芳疗
//		清洁美容工具
//		美容仪器
//	个人护理/美容工具热门品牌
//		贵爱娘
//		吕
//		ReEn/睿嫣
//		LUSH
//		REVEUR
//		花王
//		力士
//		kai/贝印
//		小林制药
//		资生堂
//		Aussie袋鼠
//		UNICHARM/尤妮佳
//食品保健
//	国际最新营养理念
//		抗氧化－虾青素
//		贫血－铁元
//		降血脂－大蒜提取物
//		女性内分泌－月见草
//		更年期－黑升麻
//		男性前列腺－锯棕榈
//	进口食品
//		美国味道
//		泰国风味
//		韩国零食
//		果蔬干
//		坚果
//		特色糖果
//		巧克力
//		日本零食
//		肉干/海味
//		牛轧糖
//		台湾小食
//		方便面
//		健康橄榄油
//	膳食保健品国际大荟萃
//		澳大利亚－降血脂－BLACKMORES鱼油
//		日本－排毒养颜－生酵素
//		美国－关节养护－SCHIFF move free
//		德国－抵抗亚健康－Doppelherz双心
//		新西兰－基础营养补充－Thompson’s
//		英国－草本营养－holland&barrett
//	进口饮料
//		夏日果蔬汁
//		成人奶粉
//		功能饮品
//		速溶咖啡
//		酒
//		冲饮麦片
//		花草茶
//		星巴克咖啡
//	功效分类
//		基础营养补充
//		增强抵抗力
//		美容养颜
//		肝肠胃
//		心脑血管
//		体重管理
//		骨骼关节养护
//		脑力智力
//		泌尿生殖
//		改善睡眠
//		眼部保养
//		运动营养
//		内分泌
//		孕妇营养
//		儿童营养
//		补血
//		酵素排毒
//		氨糖软骨素
//		减肥奶昔
//	热门畅销品牌
//		GNC
//		Swisse
//		普丽普莱
//		自然之宝
//		Schiff
//		康宝莱
//		Nature Made
//		山本汉方
//		善存
//		康维他
//		美瑞克斯
//		生酵素
//		Nature‘s Care
//		USANA
//		Kingpower
//		糖村
//		Costco
//		holland&barrett
//母婴用品
//	宝宝食品
//		牛奶粉
//		羊奶粉
//		米粉
//		牛初乳
//		豆奶粉
//		1段奶粉
//		2段奶粉
//		3段奶粉
//		4段奶粉
//		DHA
//		钙铁锌
//		鱼油
//		维生素
//		益生菌
//		DHA/核桃油
//		磨牙饼干
//		果泥/肉泥
//		果肉条
//		奶酪
//		奶片
//	宝宝用品
//		纸尿裤
//		耳温枪
//		奶瓶
//		奶嘴
//		奶瓶刷/奶嘴刷
//		水杯
//		辅食料理机
//		宝宝餐具
//		婴儿湿巾
//		食物咬咬袋
//		推车/床
//		背带/背袋
//		安全座椅
//		喂药器
//		退烧贴
//	宝宝洗护
//		驱蚊防虫
//		爽身防痱
//		宝贝防晒
//		宝贝护肤
//		洗发沐浴
//		护臀膏
//		宝宝润肤乳
//		按摩油
//		牙膏牙刷
//		宝宝洗手液
//		宝宝洗衣液
//	孕产妇用品
//		妊娠纹护理
//		淡疤护理
//		乳房护理（新）
//		孕妇DHA
//		孕产妇奶粉
//		孕产妇维生素
//		催奶发奶
//		吸奶器
//		防溢乳垫
//		产妇卫生巾
//		孕妇装
//	宝宝服饰/玩具
//		亲子装
//		连身衣/连爬服
//		裤子
//		外套
//		帽子
//		益智类玩具
//		书包
//		宝宝玩具集合
//	热门畅销品牌
//		诺优能
//		可瑞康
//		花王
//		大王
//		爱他美
//		加州宝宝
//		Hero Baby
//		moony
//		贝亲
//		hipp
//		小蜜蜂
//服饰鞋包
//	运动户外
//		运动鞋
//		跑步鞋
//		休闲板鞋
//		篮球鞋
//		运动服
//		运动用品
//		户外鞋服/装备
//	箱包/鞋靴
//		时尚女包
//		女士钱包
//		精品男包
//		男士钱包
//		旅行箱包
//		时尚女鞋
//		单鞋
//		浅口鞋
//		流行男鞋
//		休闲皮鞋
//		板鞋
//	饰品/手表
//		施华洛世奇
//		珠宝
//		饰品
//		钻石
//		珍珠
//		项链/吊坠
//		手链/手镯
//		戒指
//		耳环
//		手表
//		瑞士腕表
//		机械表
//		石英表
//		电子表
//		皮带
//		帽子/围巾/手套
//		眼镜
//	女装/女士内衣
//		2016新品
//		连衣裙
//		针织衫
//		短外套
//		小西装
//		风衣
//		卫衣
//		牛仔裤
//		毛衣
//		羽绒服
//		呢大衣
//		棉衣/棉服
//		皮衣皮草
//		文胸/文胸套装
//		内裤
//		丝袜
//		袜子
//		睡衣/家居服
//	男装/男士内衣
//		2016新品
//		夹克
//		卫衣
//		风衣
//		皮衣
//		针织衫/毛衣
//		羽绒服
//		毛呢外套
//		棉衣
//		男士睡衣/家居服
//		内裤
//		袜子
//		背心
//	热门品牌
//		New Balance
//		Nike
//		Longines
//		Tissot
//		PAZZO
//		Nike Air Jordan
//		Swarovski
//		Casio
//		Coach
//		Cherrykoko
//		Clarks
//		Samsonite
//		Adidas
//		Tommy Hilfiger
//		Ecco
//		Gucci
//生活/数码
//	家用电器
//		净水器
//		净水壶
//		空气净化器
//		扫地机
//		吸尘器
//		咖啡机
//		美容仪
//		生活电器
//		料理机
//		电吹风
//	厨房餐具/烹饪用具
//		厨房锅具
//		滤水壶
//		保温杯
//		炒锅
//		刀具
//		家庭清洁
//	数码音响
//		数码相机
//		耳机
//		CD
//		手机壳
//	收藏
//		邮票
//	家居生活
//		卫生巾/棉条/护垫
//		蒸汽眼膜
//		保温壶
//		防霾口罩
//		洗衣液
//		避孕套
//		床上用品
//		宠物用品
//	热门畅销品牌
//		索尼
//		BRITA/碧然德
//		dyson/戴森
//		膳魔师
//		花王
//		RECARO
//		WMF
//		nespresso
//		Bosch/博世
//		KISS YOU
//		Tescom