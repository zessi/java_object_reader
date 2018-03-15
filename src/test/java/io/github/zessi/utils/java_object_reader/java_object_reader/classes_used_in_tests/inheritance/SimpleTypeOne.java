package io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance;

public class SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne value1";

    String hiddenFiled2 = "SimpleTypeOne value2";

    private String text1;

    public String getText1() {
        return text1;
    }

    public SimpleTypeOne setText1(String text1) {
        this.text1 = text1;
        return this;
    }
}
