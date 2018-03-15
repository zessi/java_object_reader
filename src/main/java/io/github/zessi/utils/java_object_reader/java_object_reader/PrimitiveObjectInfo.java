package io.github.zessi.utils.java_object_reader.java_object_reader;

/**
 * A class extending the abstract class ObjectInfo <br>
 * This class should be used for String representation of objects that are primitive. Like int/Integer, float/Float, char/Character, String, ... etc. <br>
 */
class PrimitiveObjectInfo extends ObjectInfo {

    /**
     * A constructor that is used to create an instance of PrimitiveObjectInfo.
     *
     * @param object  The object that will be used for the String representation.
     * @param options The StringRepresentationOptions object that will be used to control the behavior of the String representation of this object according to its options.
     */
    PrimitiveObjectInfo(Object object, StringRepresentationOptions options) {
        super(object, options);
        if (!StringRepresentableUtils.isPrimitive(object)) throw new IllegalArgumentException("Input object is not of a primitive type.");
    }

    /**
     * @return A String representing the value of the object associated with this PrimitiveObjectInfo object.
     */
    @Override
    public String getValueStringRepresentation() {
        if (this.getType() == String.class) return "{\"" + this.getObject().toString() + "\"}";
        if ((this.getType() == Character.class) || (this.getType() == char.class)) return "{\'" + this.getObject() + "\'}";
        return "{" + this.getObject().toString() + "}";
    }


}
