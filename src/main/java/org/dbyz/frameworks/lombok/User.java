package org.dbyz.frameworks.lombok;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
/**
1.添加lombok.jar到 eclipse.ini OR myeclipse.ini 目录
2.添加如下eclipse启动参数到eclipse.ini 最后

-Xbootclasspath/a:lombok.jar
-javaagent:lombok.jar

https://projectlombok.org/features/index.html

 */
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class User {
	@NonNull
    private final String name;
    @Setter(AccessLevel.PACKAGE)
    @NonNull
    private Integer age;
    private double score;
    private String[] tags;

    @ToString(includeFieldNames = true)
    @Data(staticConstructor = "of")
    public static class Exercise<T> {
        private final String name;
        private T value;
    }
}