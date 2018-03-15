package io.github.zessi.utils.java_object_reader.java_object_reader;

import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne_One;
import io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.inheritance.SimpleTypeOne_One_One;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static io.github.zessi.utils.java_object_reader.java_object_reader.StringRepresentableUtils.*;
import static org.junit.Assert.*;
import static io.github.zessi.utils.java_object_reader.java_object_reader.TestUtils.*;

@SuppressWarnings("Duplicates")
public class Test_StringRepresentableUtils {

    @Test
    public void test_isPrimitive_OnObjectInput() throws Exception {
        final List<Object> inputs = new ArrayList<>();
        final List<Boolean> results = new ArrayList<>();

        inputs.add(true);
        results.add(true);

        inputs.add(new Byte((byte) 10));
        results.add(true);

        inputs.add(new Short((short) 10));
        results.add(true);

        inputs.add(10);
        results.add(true);

        inputs.add(10l);
        results.add(true);

        inputs.add(10.5f);
        results.add(true);

        inputs.add(10.5d);
        results.add(true);

        inputs.add('c');
        results.add(true);

        inputs.add("text");
        results.add(true);

        inputs.add(Instant.now());
        results.add(false);

        inputs.add(new BigDecimal(10));
        results.add(false);

        inputs.add(new Object());
        results.add(false);

        if (inputs.size() != results.size()) throw new Exception("inputs and results do not have the same size");

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println("inputs.get(i) = " + inputs.get(i));
            System.out.println("inputs.get(i).getClass() = " + inputs.get(i).getClass());
            System.out.println("results.get(i) = " + results.get(i));
            assertEquals(results.get(i), isPrimitive(inputs.get(i)));
            System.out.println();
        }

    }

    @Test
    public void test_isPrimitive_OnClassInput() throws Exception {
        final List<Class> inputs = new ArrayList<>();
        final List<Boolean> results = new ArrayList<>();

        inputs.add(Boolean.class);
        results.add(true);

        inputs.add(Byte.class);
        results.add(true);

        inputs.add(Short.class);
        results.add(true);

        inputs.add(Integer.class);
        results.add(true);

        inputs.add(Long.class);
        results.add(true);

        inputs.add(Float.class);
        results.add(true);

        inputs.add(Double.class);
        results.add(true);

        inputs.add(Character.class);
        results.add(true);

        inputs.add(String.class);
        results.add(true);

        inputs.add(BigDecimal.class);
        results.add(false);

        inputs.add(Instant.class);
        results.add(false);

        inputs.add(Date.class);
        results.add(false);


        if (inputs.size() != results.size()) throw new Exception("inputs and results do not have the same size");

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println("inputs.get(i) = " + inputs.get(i));
            System.out.println("inputs.get(i).getClass() = " + inputs.get(i).getClass());
            System.out.println("results.get(i) = " + results.get(i));
            assertEquals(results.get(i), isPrimitive(inputs.get(i)));
            System.out.println();
        }
    }

    @Test
    public void test_getParticipatingClasses() {
        final Map<Class, Set<Class>> actualParentsMap = new HashMap<>();
        final Map<Class, Set<Class>> returnedParentsMap = new HashMap<>();

        actualParentsMap.put(Object.class, getLinkedHashSetWithArrayElements(Object.class));
        returnedParentsMap.put(Object.class, getParticipatingClasses(Object.class));

        actualParentsMap.put(SimpleTypeOne_One.class, getLinkedHashSetWithArrayElements(Object.class, SimpleTypeOne.class, SimpleTypeOne_One.class));
        returnedParentsMap.put(SimpleTypeOne_One.class, getParticipatingClasses(SimpleTypeOne_One.class));

        actualParentsMap.put(SimpleTypeOne_One_One.class, getLinkedHashSetWithArrayElements(Object.class, SimpleTypeOne.class, SimpleTypeOne_One.class, SimpleTypeOne_One_One.class));
        returnedParentsMap.put(SimpleTypeOne_One_One.class, getParticipatingClasses(SimpleTypeOne_One_One.class));

        for (Class aClass : returnedParentsMap.keySet()) {
            final Class[] actual = actualParentsMap.get(aClass).toArray(new Class[]{});
            final Class[] returned = returnedParentsMap.get(aClass).toArray(new Class[]{});
            { /*Printing*/
                System.out.println("aClass = " + aClass);
                final String actualString = Arrays.stream(actual)
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(" , ", "{ ", " }"));
                final String returnedString = Arrays.stream(returned)
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(" , ", "{ ", " }"));
                System.out.println("actualString = " + actualString);
                System.out.println("returnedString = " + returnedString);
                System.out.println();
            }
            assertArrayEquals(actual, returned);
        }

    }

}
