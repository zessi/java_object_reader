package io.github.zessi.utils.java_object_reader.java_object_reader;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.*;

public class TestUtils {

    public static String getRandomLettersAndNumbers(int size) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            final byte randomChoice = (byte) new SecureRandom().nextInt(3);
            switch (randomChoice) {
                case 1: {
                    /*Generates a random character from A to Z*/
                    builder.append((char) (new SecureRandom().nextInt(26) + 65));
                    break;
                }
                case 2: {
                    /*Generates a random character form a to z*/
                    builder.append((char) (new SecureRandom().nextInt(26) + 97));
                    break;
                }
                default: {
                    /*Generates a random digit from 0 to 9*/
                    builder.append((char) (new SecureRandom().nextInt(10) + 48));
                    break;
                }
            }
        }

        return builder.toString();
    }

    public static <E> LinkedHashSet<E> getLinkedHashSetWithArrayElements(E... elements) {
        return new LinkedHashSet<E>(Arrays.asList(elements));
    }
}
