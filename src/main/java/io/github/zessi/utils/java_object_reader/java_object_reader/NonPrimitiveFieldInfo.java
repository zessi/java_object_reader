package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Field;

/**
 * An immutable class that is used for the String representation of a java field of a non-primitive type <br>
 * This class extends {@link FieldInfo}
 */
final class NonPrimitiveFieldInfo extends FieldInfo {
    /**
     * A variable to hold an object of CircularReferencePrevention which is used and passed recursively to prevent circular references.
     */
    private final CircularReferencePrevention crp;

    /**
     * A Constructor that creates an instance of NonPrimitiveObjectInfo using the provided arguments
     *
     * @param field            The field that will be String represented
     * @param definingClass    The class that defines/declares this field
     * @param associatedObject The context object of the class that this field is a part of. If the field is static, then this object should be null
     * @param options          The StringRepresentationOptions object that will be used to control the behavior of the String representation of this field
     */
    NonPrimitiveFieldInfo(Field field, Class definingClass, Object associatedObject, StringRepresentationOptions options, CircularReferencePrevention crp) {
        super(field, definingClass, associatedObject, options);
        if (StringRepresentableUtils.isPrimitive(this.getFieldType())) throw new IllegalArgumentException("The field's data type is of a primitive type.");
        this.crp = crp;
    }

    /**
     * @return String representation of this field's value. which is basically the String representation of this field's object
     */
    @Override
    public String getValueStringRepresentation() {
        return ObjectInfo.getObjectInfo(this.getValue(), this.getOptions(), this.crp).getFullStringRepresentation();
    }
}
