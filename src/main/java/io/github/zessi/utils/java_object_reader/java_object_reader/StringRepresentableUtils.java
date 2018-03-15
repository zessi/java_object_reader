package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.util.*;

/**
 * A class with useful utility methods.
 */
public class StringRepresentableUtils {

    /**
     * A method to check if the object is of a primitive type
     *
     * @param o The object to check
     * @return true if the object is primitive, false otherwise.
     */
    public static boolean isPrimitive(Object o) {
        if (o == null) throw new IllegalArgumentException("null input");

        if (o.getClass() == Boolean.class) return true;
        if (o.getClass() == Byte.class) return true;
        if (o.getClass() == Short.class) return true;
        if (o.getClass() == Integer.class) return true;
        if (o.getClass() == Long.class) return true;
        if (o.getClass() == Float.class) return true;
        if (o.getClass() == Double.class) return true;
        if (o.getClass() == Character.class) return true;
        if (o.getClass() == String.class) return true;

        return false;
    }

    /**
     * A method to check if a class is of a primitive type.
     *
     * @param aClass The class to check
     * @return true if the class is primitive, false otherwise.
     */
    public static boolean isPrimitive(Class aClass) {
        if (aClass == Boolean.class) return true;
        if (aClass == boolean.class) return true;
        if (aClass == Byte.class) return true;
        if (aClass == byte.class) return true;
        if (aClass == Short.class) return true;
        if (aClass == short.class) return true;
        if (aClass == Integer.class) return true;
        if (aClass == int.class) return true;
        if (aClass == Long.class) return true;
        if (aClass == long.class) return true;
        if (aClass == Float.class) return true;
        if (aClass == float.class) return true;
        if (aClass == Double.class) return true;
        if (aClass == double.class) return true;
        if (aClass == Character.class) return true;
        if (aClass == char.class) return true;
        if (aClass == String.class) return true;

        return false;
    }

    /**
     * A method to check if the object is of an array type.
     *
     * @param o The object to check
     * @return true if the object is of an array type, false otherwise.
     */
    public static boolean isArray(Object o) {
        return o.getClass().isArray();
    }

    /**
     * A method that is used to return an ordered set of classes including the input class and all of its parent classes ordered from the most parent to the last child.
     *
     * @param inputClass The class of which the parent classes will be returned
     * @return A set containing the parent classes of the input class.
     */
    public static Set<Class> getParticipatingClasses(Class inputClass) {
        if (inputClass.isInterface()) throw new IllegalArgumentException("inputClass is an interface");
        final List<Class> result = new ArrayList<>();

        Class currentClass = inputClass;
        //Adding the input class
        result.add(currentClass);
        //Adding all the parent classes from the button up
        while ((currentClass = currentClass.getSuperclass()) != null) {
            result.add(currentClass);
        }

        //Reversing the order of the list so that the first element is the highest hierarchy parent and the last element is the input class (inputClass).
        Collections.reverse(result);

        //Returning the result as a set that retains insertion order to ensure that the order remains.
        return new LinkedHashSet<>(result);
    }
}
