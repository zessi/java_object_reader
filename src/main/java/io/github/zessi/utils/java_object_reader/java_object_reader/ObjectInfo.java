package io.github.zessi.utils.java_object_reader.java_object_reader;

/**
 * A class representing Object information when displayed as a String.
 */
abstract class ObjectInfo implements StringRepresentable {

    /**
     * The object which will be string represented
     */
    private final Object object;

    /**
     * The class type of the object.
     */
    private final Class type;

    /**
     * The StringRepresentationOptions object which is used to control the behavior of the String representation of this object according to its options.
     */
    private final StringRepresentationOptions options;

    /**
     * The main constructor for this class. It creates a new ObjectInfo object using the provided object and StringRepresentationOptions instances.
     *
     * @param object  The object of which the ObjectInfo instance will be created.
     * @param options The StringRepresentationOptions object that will be used to control the behavior of the String representation of this object according to its options.
     */
    ObjectInfo(Object object, StringRepresentationOptions options) {

        /*
        null check has been propagated to the static factory method ObjectInfo getObjectInfo(Object object, StringRepresentationOptions options, CircularReferencePrevention crp)
        This was done to allow creation of a specific type of ObjectInfo which holds null objects
        */
        /*if (object == null) throw new IllegalArgumentException("null first parameter");*/
        if (options == null) throw new IllegalArgumentException("null second parameter");
        this.object = object;
        if (object != null) {
            this.type = object.getClass();
        } else {
            this.type = null;
        }
        this.options = options;
    }


    /**
     * @return The associated object of this ObjectInfo object.
     */
    Object getObject() {
        return object;
    }

    /**
     * @return The class type of the object of which the String representation will be obtained.
     */
    Class getType() {
        return type;
    }

    /**
     * @return The StringRepresentationOptions object that is being used to control the behavior of the String representation of this object according to its options.
     */
    StringRepresentationOptions getOptions() {
        return options;
    }

    /**
     * @return A String representing the class/type name of the object of which the String representation will be obtained
     */
    public String getTypeName() {
        return (options.isFullTypeName()) ? this.type.getName() : this.type.getSimpleName();
    }

    /**
     * @return A String representing the metadata part of the String representation.
     */
    @Override
    public String getMetaStringRepresentation() {
        return getTypeName();
    }

    /**
     * @return A String containing the separator that is used to separate the metadata and the value parts of the String representation of this object.
     */
    @Override
    public String getMetaValueSeparator() {
        return options.getObjectMetaValueSeparator();
    }

    /**
     * A static factory method that is used to create objects of ObjectInfo type and return them.
     *
     * @param object  The object of which the ObjectInfo instance will be created.
     * @param options The StringRepresentationOptions object that is going to be used in the ObjectInfo instance
     * @return An object of a type implementing the ObjectInfo class which is going to be used in String representation of the input object.
     */
    static ObjectInfo getObjectInfo(Object object, StringRepresentationOptions options, CircularReferencePrevention crp) {
        if (options == null) throw new IllegalArgumentException("null 2nd argument");
        if (crp == null) throw new IllegalArgumentException("null 3rd argument");
        //Handling a null object input with a specific implementation of ObjectInfo that should be used to represent null objects
        if (object == null) return new ObjectInfo(object, options) {
            @Override
            public String getValueStringRepresentation() {
                return "";
            }

            @Override
            public String getFullStringRepresentation() {
                return options.getNullRepresentation();
            }
        };

        //If the input object is of array type, then an ArrayObjectInfo object will be created and returned
        if (StringRepresentableUtils.isArray(object)) return new ArrayObjectInfo(object, options, crp);

        //Checking if the input object is of a primitive type or not.
        if (StringRepresentableUtils.isPrimitive(object)) {
            //If the input object is of a primitive type, then a PrimitiveObjectInfo object will be created and returned
            return new PrimitiveObjectInfo(object, options);
        } else {
            //If the input object is not of a primitive type, then a NonPrimitiveObjectInfo object will be created and returned
            return new NonPrimitiveObjectInfo(object, options, crp);
        }
    }

}
