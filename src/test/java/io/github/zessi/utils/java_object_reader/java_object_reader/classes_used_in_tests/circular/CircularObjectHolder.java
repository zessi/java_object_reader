package io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.circular;

public class CircularObjectHolder {

    public MyCircularTypeOne myCircularTypeOne1;
    public MyCircularTypeOne myCircularTypeOne2;

    public CircularObjectHolder() {
        this.myCircularTypeOne1 = new MyCircularTypeOne();
        this.myCircularTypeOne2 = new MyCircularTypeOne();
        this.myCircularTypeOne1.innerObject = myCircularTypeOne2;
        this.myCircularTypeOne2.innerObject = myCircularTypeOne1;
    }
}
