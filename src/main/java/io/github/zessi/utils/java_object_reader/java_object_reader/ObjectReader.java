package io.github.zessi.utils.java_object_reader.java_object_reader;

/**
 * A class that has methods working as an interface to Java Object Reader functionality
 */
public class ObjectReader {

    /**
     * A method that returns a String representing the input java object. <br>
     * This method calls the other method "static String read(Object object, StringRepresentationOptions options)" using default StringRepresentationOptions
     *
     * @param object The object of which the String representation should be returned
     * @return The String representation of the input object according to the default StringRepresentationOptions settings.
     */
    public static String read(Object object) {
        return read(object, StringRepresentationOptions.Builder.build().get());
    }

    /**
     * A method that returns a String representing the input java object <br>
     *
     * @param object  The object of which the String representation should be returned
     * @param options The StringRepresentationOptions object that should be used control the behavior of the String representation of the input object.
     * @return The String representation of the input object according the input StringRepresentationOptions object.
     */
    public static String read(Object object, StringRepresentationOptions options) {
        //Creating a CircularReferencePrevention object associated with the input object's String representation
        final CircularReferencePrevention circularReferencePrevention = new CircularReferencePrevention();
        return ObjectInfo.getObjectInfo(object, options, circularReferencePrevention).getFullStringRepresentation();
    }

    /**
     * A convenient method that is used to return a StringRepresentationOptions object using default options
     *
     * @return A StringRepresentationOptions object using the default settings.
     */
    public static StringRepresentationOptions getDefaultOptions() {
        return StringRepresentationOptions.Builder.build().get();
    }

}
