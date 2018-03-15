package io.github.zessi.utils.java_object_reader.java_object_reader;

/**
 * An interface to be implemented by types that are going to be used to represent certain objects as strings while also representing the some of the object's runtime meta-data.
 */
public interface StringRepresentable {

    /**
     * @return A String representing the meta-data of this String representable entity <br>
     */
    String getMetaStringRepresentation();

    /**
     * @return A String representing the value of this entity.
     */
    String getValueStringRepresentation();

    /**
     * @return A String (preferably short) holding the separator that is supposed to separate the meta part and the value part in the full String representation of this entity.
     */
    String getMetaValueSeparator();

    /**
     * @return A String representing both parts of this entity (Meta and Value) separated by the separator returned from getMetValueSeparator().
     */
    default String getFullStringRepresentation() {
        return getMetaStringRepresentation() + getMetaValueSeparator() + getValueStringRepresentation();
    }

}
