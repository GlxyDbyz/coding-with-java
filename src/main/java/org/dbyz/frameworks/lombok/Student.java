package org.dbyz.frameworks.lombok;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Student {
    private String name;
    private int age;
}