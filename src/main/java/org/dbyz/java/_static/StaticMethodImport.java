package org.dbyz.java._static;

import static org.dbyz.java._static.StaticMethodImport.$.sayBye;

public class StaticMethodImport {
    static class $ {
        public static String sayHello() {
            return "hello";
        }

        public static String sayBye() {
            return "bye";
        }
    }

    public static void main(String[] args) {
        // type 1
        $.sayHello();
        
        // type 2
        // import static org.dbyz.java._static.StaticMethodImport.$.sayBye;
        sayBye();
    }
}
