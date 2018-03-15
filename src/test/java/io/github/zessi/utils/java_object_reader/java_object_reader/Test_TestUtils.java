package io.github.zessi.utils.java_object_reader.java_object_reader;

import org.junit.Test;

public class Test_TestUtils {

    @Test
    public void test_getRandomLettersAndNumbers() {
        for (int i = 0; i < 10; i++) {
            final String random = TestUtils.getRandomLettersAndNumbers(7);
            System.out.println("random.length() = " + random.length());
            System.out.println("random = " + random);
        }
    }


}
