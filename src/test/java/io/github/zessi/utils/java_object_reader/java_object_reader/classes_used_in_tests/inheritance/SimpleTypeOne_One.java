package io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance;

public class SimpleTypeOne_One extends SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne_One value1";

    String hiddenFiled2 = "SimpleTypeOne_One value2";

    private String text2;

    public String getText2() {
        return text2;
    }

    public SimpleTypeOne_One setText2(String text2) {
        this.text2 = text2;
        return this;
    }
}
