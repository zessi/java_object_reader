package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Array;
import java.util.StringJoiner;

/**
 * A class extending the abstract class ObjectInfo <br>
 * This class should be used for String representation of array type objects.
 */
final class ArrayObjectInfo extends ObjectInfo {
    private final CircularReferencePrevention crp;

    /**
     * A constructor that creates and instance of ArrayObjectInfo using the provided arguments
     *
     * @param object  The array object that will be used for the String representation.
     * @param options The StringRepresentationOptions object that will be used to control the behavior of the String representation of this object according to its options.
     */
    ArrayObjectInfo(Object object, StringRepresentationOptions options, CircularReferencePrevention crp) {
        super(object, options);
        if (!object.getClass().isArray()) throw new IllegalArgumentException("First input parameter is not an array object");
        this.crp = crp;
    }

    /**
     * @return A String containing the String representation of the elements of this array.
     */
    @Override
    public String getValueStringRepresentation() {
        final StringJoiner joiner = new StringJoiner(",\n", "[\n", "\n" + this.getOptions().getIndents() + "]");
        final int arrayLength = Array.getLength(this.getObject());
        for (int i = 0; i < arrayLength; i++) {
            final Object element = Array.get(this.getObject(), i);
            //An ObjectInfo is obtained for each element of the array and its String representation is then added to the StringJoiner
            final ObjectInfo elementObjectInfo = ObjectInfo.getObjectInfo(element, this.getOptions().cloneAndAddIndent(), crp);
            joiner.add(elementObjectInfo.getOptions().getIndents() + elementObjectInfo.getFullStringRepresentation());
        }
        return joiner.toString();
    }
}
