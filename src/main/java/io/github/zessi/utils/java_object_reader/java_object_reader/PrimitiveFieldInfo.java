package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Field;

/**
 * An immutable class that is used for the String representation of a java field of a primitive type <br>
 * This class extends {@link FieldInfo}
 */
final class PrimitiveFieldInfo extends FieldInfo {

    /**
     * The main constructor of this class
     *
     * @param field            The field that will be String represented
     * @param definingClass    The class that defines/declares this field
     * @param associatedObject The context object of the class that this field is a part of. If the field is static, then this object should be null
     * @param options          The StringRepresentationOptions object that will be used to control the behavior of the String representation of this field
     */
    PrimitiveFieldInfo(Field field, Class definingClass, Object associatedObject, StringRepresentationOptions options) {
        super(field, definingClass, associatedObject, options);
        if (!StringRepresentableUtils.isPrimitive(this.getFieldType())) throw new IllegalArgumentException("The field's data type is not of a primitive type.");
    }

    /**
     * @return String representation of this field's value
     */
    @Override
    public String getValueStringRepresentation() {
        if (this.getValue() == null) return this.getOptions().getNullRepresentation();
        if (this.getFieldType() == String.class) return "\"" + this.getValue().toString() + "\"";
        if ((this.getFieldType() == Character.class) || (this.getFieldType() == char.class)) return "\'" + this.getValue().toString() + "\'";
        return this.getValue().toString();
    }
}
