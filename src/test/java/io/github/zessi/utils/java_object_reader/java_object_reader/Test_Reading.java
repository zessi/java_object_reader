package io.github.zessi.utils.java_object_reader.java_object_reader;

import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.NullsObject;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.ObjectWithLargeString;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.PrimitivesFieldsObject;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.TypeFive;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.circular.CircularObjectHolder;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.complex.TypeFour;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.complex.TypeWithArray;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne_One;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne_One_One;
import org.junit.Test;

public class Test_Reading {
    private static final StringRepresentationOptions options = StringRepresentationOptions.Builder.build().get();

    @Test
    public void test_primitivesAndBasics() {
        System.out.println(ObjectReader.read(new Object(), options));
        System.out.println(ObjectReader.read("asd", options));
        System.out.println(ObjectReader.read(1010, options));
        System.out.println(ObjectReader.read(new Integer(10), options));
        System.out.println(ObjectReader.read(new PrimitivesFieldsObject(), options));
        System.out.println(ObjectReader.read(new TypeFive(), options));
        System.out.println(ObjectReader.read(new ObjectWithLargeString()));
    }

    @Test
    public void test_inheritance() {
        System.out.println(ObjectReader.read(new SimpleTypeOne().setText1("1"), options));
        System.out.println(ObjectReader.read(new SimpleTypeOne_One().setText2("22").setText1("11"), options));
        System.out.println(ObjectReader.read(new SimpleTypeOne_One_One().setText3("333").setText2("222").setText1("111"), options));
    }

    @Test
    public void test_nulls() {
        System.out.println(ObjectReader.read(new NullsObject(), options));
        System.out.println(ObjectReader.read(null, options));
        System.out.println(ObjectReader.read(new String[]{"1111", "2222", null}));
    }

    @Test
    public void test_arrays() {
        final TypeWithArray typeWithArray = new TypeWithArray();
        final TypeFive[] fives = new TypeFive[10];
        for (int i = 0; i < fives.length; i++) {
            fives[i] = new TypeFive();
        }

        System.out.println(ObjectReader.read(new String[]{"1111", "2222", "3333", "4444"}, options));
        System.out.println(ObjectReader.read(fives, options));
        System.out.println(ObjectReader.read(typeWithArray));
    }

    @Test
    public void test_complex() {
        System.out.println(ObjectReader.read(new TypeFour(), options));
    }

    @Test
    public void test_circular() {
        System.out.println(ObjectReader.read(new CircularObjectHolder(), options));
    }

    @Test
    public void test_inheritanceWithHiddenFields() {
        System.out.println(ObjectReader.read(new SimpleTypeOne(), options));
        System.out.println(ObjectReader.read(new SimpleTypeOne_One(), options));
        System.out.println(ObjectReader.read(new SimpleTypeOne_One_One(), options));
    }
}
