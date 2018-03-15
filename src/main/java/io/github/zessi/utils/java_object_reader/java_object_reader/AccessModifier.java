package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Modifier;

/**
 * An enum holding values of the java access modifiers.
 * Each value has a corresponding unique character to represent it.
 */
public enum AccessModifier {
    PRIVATE('i'),
    PACKAGE('c'),
    PROTECTED('o'),
    PUBLIC('b');

    /**
     * A single character that is unique for every AccessModifier enum instance. The character is used to represent its corresponding enum instance.
     */
    private final char representation;

    /**
     * A Constructor that takes a String representation of the access modifier
     *
     * @param representation The String representation of the provided access modifier.
     */
    AccessModifier(char representation) {
        this.representation = representation;
    }

    /**
     * @return The String representation of the associated access modifier instance.
     */
    public char getRepresentation() {
        return representation;
    }

    /**
     * A static factory method that returns the correct AccessModifier according the provided Modifier int value.
     *
     * @param modifiers The int holding the information about the reflection modifiers.
     * @return An AccessModifier enum instance representing the correct access modifier of the provided Modifier int value.
     */
    public static AccessModifier getAccessModifier(int modifiers) {
        if (Modifier.isPublic(modifiers)) return AccessModifier.PUBLIC;
        else if (Modifier.isProtected(modifiers)) return AccessModifier.PROTECTED;
        else if (Modifier.isPrivate(modifiers)) return AccessModifier.PRIVATE;
        else if (Modifier.isPrivate(modifiers)) return AccessModifier.PRIVATE;
        else return AccessModifier.PACKAGE;
    }
}

