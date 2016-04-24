coding-with-java
====
一起学习 Java8 新特性、多线程和其他好玩的事情，当然是和Java相关的(持续更新)

# 一、Java新特性
##### Java 8 编程新特性基本更新完成
	org.dbyz.java8.lambda
	org.dbyz.java8.functional_interface
	org.dbyz.java8.some_userful_interface
	org.dbyz.java8.interface_with_defaule_function
	org.dbyz.java8.date_api

##### Java 7 编程特性基本更新完成
	org.dbyz.java7.Java7FeaturesTest.java

# 二、Java 语言相关知识
## 1.多线程相关 
##### 1.1 Thread&Object
	1.新建线程:SimpleThreadTest.java
	
	2.守护线程:DaemonTest.java
	
	3.线程同步:TestSynchronized.java
	
	4.线程等待和唤醒:
		WaitAndNotifyTest.java
		WaitAndNotifyTest2.java
		
	5.线程中断:InterrupteTest.java
	
	6.线程合并:JoinTest.java
	
	7.线程让步:YeildTest.java
	
	更新ing...

##### 1.2 JUC框架
	1.数组和链表实现的阻塞队列 :
		ArrayBlockingQueueTest.java
		LinkedBlockingQueueTest.java
		
	2.普通HashMap和同步的ConcurrentHashMap简单对比:
		ConcurrentHashMapTest.java
		
	3.普通LinkedBlockingQueue和同步的ConcurrentLinkedQueue简单对比:
		ConcurrentLinkedQueueTest.java
		
	4.可重入锁简单例子:
		ReentrantLockTest.java
		
	5.线程信号量简单例子:
		SemaphoreTest.java

## 2.Java中的引用
	1.强引用:StrongReferenceTest.java
	
	2.软引用:SoftReferenceTest.java
	
	3.弱引用:WeakReferenceTest.java
	
	4.虚引用:PhantomReferenceTest.java

## 3.Java 中的自带的数据结构解析【见中文代码注释】
##### package：org.dbyz.java.datastructure (基于Java 1.8.0_31 待更新...ing)
	ArrayList
	LinkedList
	HashMap
	LinkedHashMap
	Hashtable
	HashSet
	LinkedHashSet

## 4.有趣的Java
##### 4.1 语法相关
	一个有关继承的有趣例子：org.dbyz.java.funny.about_inherit


## 5.数据结构
	责任链条模式:org.dbyz.datastructure.filterchain
	
## 6.算法
	待更新

## 7.设计模式
	简单工厂模式:org.dbyz.design_pattern._01SimpleFactory
	外观模式:org.dbyz.design_pattern._02Faced
	适配器模式:org.dbyz.design_pattern._3Adapter
	单例模式:org.dbyz.design_pattern._4singleton
   
# 三、其他(工具、框架)
##### Java反射
	简单反射例子:ObjectToStringUtil.java
	动态代理例子:org.dbyz.java.reflact.dynamic_proxy
	
###### Java 网络编程
	梁飞RPC例子:org.dbyz.java.net.rpc

##### Jax的b使用(自己写的Jaxb简单工具类)
	Jaxb实现对象和XML之间的转换:JaxbUtil.java

##### commons-codec
	1.BASE64、MD2、MD5、SHA1、SHA224、SHA256、SHA384、SHA512算法的Java实现DigestTest.java

##### JMS
	1.ActiveMQ和RabbitMQ 入门例子
		org.dbyz.frameworks.activemq.demo1
		org.dbyz.frameworks.rabbitmq.demo1
	
	2.ActiveMQ和RabbitMQ 简单封装
		org.dbyz.frameworks.activemq.demo2
		org.dbyz.frameworks.rabbitmq.demo2

##### Spring
	1.Spring AOP 简单例子:org.dbyz.frameworks.spring.aop
	
	2.Spring 定时任务简单例子:org.dbyz.frameworks.spring.schedule

##### MongoDB
	1.MongoDB简单例子:org.dbyz.frameworks.mongo.SimpleMongoTest.java
	
	2.MongoDB增删改查例子:org.dbyz.frameworks.mongo.MongoCRUDTest.java

##### POI
	1.excel简单读写操作:
		org.dbyz.frameworks.poi.ReadExcleTest.java
		org.dbyz.frameworks.poi.WriteExcleTest.java
		
	2.word简单读取操作:org.dbyz.frameworks.poi.ReadWordTest.java
	
##### 定时任务
    quartz简单例子:org.dbyz.frameworks.quartz
    
其他待更新...

![Dbyz](https://avatars2.githubusercontent.com/u/6849536?v=3&s=64 "Dbyz") 