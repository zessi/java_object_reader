package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Field;
import java.util.*;

import static io.github.zessi.utils.java_object_reader.java_object_reader.StringRepresentableUtils.*;

/**
 * A class extending the abstract class ObjectInfo <br>
 * This class should be used for String representation of objects that are not primitive objects. <br>
 */
final class NonPrimitiveObjectInfo extends ObjectInfo {
    /**
     * A map (mapped by field name to prevent hidden fields) that contains all (with the exception of hidden fields) the fields declared by this object's class and its ancestors. <br>
     * In case of hidden fields, only the last field in the inheritance hierarchy should remain in the map.
     */
    private final Map<String, FieldInfo> fields = new TreeMap<>();

    /**
     * A variable to hold an object of CircularReferencePrevention which is used and passed recursively to prevent circular references.
     */
    private final CircularReferencePrevention crp;

    /**
     * This constructor creates an instance of this type containing a map of FieldInfo objects
     * (The mapping is done by the String of the field name to avoid duplicate names and hidden fields) that are being used for the String representation of each field.
     *
     * @param object  The object that will be used for the String representation.
     * @param options The StringRepresentationOptions object that will be used to control the behavior of the String representation of this object according to its options.
     */
    NonPrimitiveObjectInfo(Object object, StringRepresentationOptions options, CircularReferencePrevention crp) {
        super(object, options);
        if (StringRepresentableUtils.isPrimitive(object)) throw new IllegalArgumentException("Input object is of a primitive type");
        this.crp = crp;

        /*
        Obtaining all of the participating classes (The class that represents the object's type and all of its parent classes) in the order where the first element is highest class
        in the hierarchy and the last element is the lowest class in the hierarchy (basically the class the represents the type of the object of which the String representation will
        be evaluated).
         */
        final Set<Class> classes = getParticipatingClasses(this.getType());

        /*
        Obtaining the fields declared in each of the classes.
        https://docs.oracle.com/javase/tutorial/java/IandI/hidevariables.html
        In hidden fields case : Because the classes order is from highest hierarchy to lowest hierarchy, the fields that were added last will override the fields that were added first.
        This will allow the String representation of the object to contain the String representation of the most relevant field to it.
         */
        for (Class aClass : classes) {
            for (Field field : aClass.getDeclaredFields()) {
                final FieldInfo fieldInfo = FieldInfo.getFieldInfo(field, aClass, object, this.getOptions().cloneAndAddIndent(), this.crp);
                fields.put(fieldInfo.getName(), fieldInfo);
            }
        }
    }

    /**
     * @return A String holding the separator that is supposed to separate the meta part and the value part in the full String representation of this ObjectInfo instance.
     */
    @Override
    public String getMetaValueSeparator() {
        return this.getOptions().getObjectMetaValueSeparator();
    }

    /**
     * @return A map of FieldInfo objects (The mapping is done by the String of the field name to avoid duplicate names and hidden fields) that are being used for the String representation of each field.
     */
    Map<String, FieldInfo> getFields() {
        return fields;
    }

    /**
     * @return The String representation of the value of this NonPrimitiveObjectInfo object. This String representation should contain all the String representations of the fields
     * related this NonPrimitiveObjectInfo's object be it declared fields or inherited fields.
     */
    @Override
    public String getValueStringRepresentation() {
        if (this.getFields().size() < 1) return "{}";
        //A StringBuilder that should contain the String representation result
        final StringBuilder result = new StringBuilder();

        result.append("{\n");

        //A StringJoiner to join the String representation of all of the fields
        final StringJoiner fieldsJoiner = new StringJoiner("," + this.getOptions().getNewLine());
        for (FieldInfo fieldInfo : fields.values()) {
            //Checks if the fieldInfo's object exists in the crp list or not. If it exists then it shouldn't be processed otherwise, this will result in circular reference and lead to a stack overflow.
            if (!this.crp.checkExists(fieldInfo.getValue())) {
                if (fieldInfo.getValue() == null) {
                    //Representing a field of which the value is null. This should be coherent with the String representation of a FieldInfo object's getFullStringRepresentation
                    fieldsJoiner.add(fieldInfo.getOptions().getIndents() + fieldInfo.getMetaStringRepresentation() + fieldInfo.getMetaValueSeparator() + fieldInfo.getOptions().getNullRepresentation());
                } else {
                    //Adding the fieldInfo's object to the crp list to prevent circular references to it.
                    this.crp.add(fieldInfo.getValue());
                    //Processing the String representation of the current field and adding the result to the joiner.
                    fieldsJoiner.add(fieldInfo.getOptions().getIndents() + fieldInfo.getFullStringRepresentation());
                    //Removing the fieldInfo's object from the crp list.
                    this.crp.remove(fieldInfo.getValue());
                }
            } else {
                //If the fieldInfo's object exists in the crp list then this String representation will be printed instead.
                fieldsJoiner.add(fieldInfo.getOptions().getIndents() + fieldInfo.getMetaStringRepresentation() + fieldInfo.getMetaValueSeparator() + "<Object Processing>");
            }
        }
        result.append(fieldsJoiner.toString());

        result.append("\n" + this.getOptions().getIndents() + "}");
        return result.toString();
    }

}
