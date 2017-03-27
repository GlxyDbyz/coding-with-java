package org.dbyz.java._static;

import org.dbyz.java._static.StaticInnerClassTest.InnerClass;
import org.dbyz.java._static.StaticInnerClassTest.StaticInnerClass;

public class StaticInnerClassTest {

    static String outerClassStaticStr = "outer";
    String outerClassStr = "outer";

    static class StaticInnerClass {
        static String staticInnerClasSstr = "inner";

        // 静态内部类的非静态成员可以访问外部类的静态变量，而不可访问外部类的非静态变量；
        String test = "test" + outerClassStaticStr;
        // String test2 = "test" + outerClassStr; // err

    }

    class InnerClass {
        // 静态内部类可以有静态成员，而非静态内部类则不能有静态成员。
        // static String str = ""; // err
        static final String innerClasSstr = ""; // ok

        // 非静态内部类的非静态成员可以访问外部类的非静态变量。
        String test2 = "test" + outerClassStr;
    }
}

class Test {
    public static void main(String[] args) {
        // 静态内部类可以不需要外部类对象的情况下直接创建,而非静态内部类则不行
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        // InnerClass innerClass = new InnerClass(); // err
        InnerClass innerClass = new StaticInnerClassTest().new InnerClass();
    }
}