package io.github.zessi.utils.java_object_reader.java_object_reader;

/**
 * An immutable class that holds the options information to be used when attempting to represent an entity as String.
 */
public final class StringRepresentationOptions {
    /**
     * A boolean that controls weather the type name of the entity should be represented simply or fully.
     */
    private final boolean fullTypeName;

    /**
     * A String that should be used to separate the meta part and the value part of the String representation of a FieldInfo object
     */
    private final String fieldMetaValueSeparator;

    /**
     * A String that should be used to separate the meta part and the value part of the String representation of an ObjectInfo object
     */
    private final String objectMetaValueSeparator;

    /**
     * A String representing an indent. Should be used for formatting.
     */
    private final String indent;

    /**
     * A String that should be used when separating meta part's components.
     */
    private final String fieldMetaSeparator;

    /**
     * A string used to create new lines.
     */
    private final String newLine;

    /**
     * An int representing how many indents are there.
     */
    private final int indentCount;

    /**
     * A String that should be used to represent a null value
     */
    private final String nullRepresentation;


    /**
     * The main constructor of this class <br>
     * Creates an instance of StringRepresentationOptions which holds information to be used to control the behavior of the String representation of the using objects.
     *
     * @param fullTypeName            A boolean that indicates if class type names should be represented using full representation (canonical name) or simple representation.
     *                                True means full String representation, while false means simple String representation.
     * @param fieldMetaValueSeparator A String containing the text to be used as a separator between the String representation of meta-data and the String representation of the value.
     * @param indent                  A String containing the text to be used as indents
     * @param fieldMetaSeparator      A String containing the text to be used as a separator between different parts of the meta-data
     * @param newLine                 A String containing the text to be used as a new line.
     * @param indentCount             An int indicating how many indents will be used
     */
    public StringRepresentationOptions(boolean fullTypeName, String fieldMetaValueSeparator, String objectMetaValueSeparator, String indent, String fieldMetaSeparator, String newLine, int indentCount, String nullRepresentation) {
        this.fullTypeName = fullTypeName;
        this.fieldMetaValueSeparator = fieldMetaValueSeparator;
        this.objectMetaValueSeparator = objectMetaValueSeparator;
        this.indent = indent;
        this.fieldMetaSeparator = fieldMetaSeparator;
        this.newLine = newLine;
        this.indentCount = indentCount;
        this.nullRepresentation = nullRepresentation;
    }

    /**
     * @return A boolean indicating if full type name will be used or not. <br>
     * Example : with isFullTypeName being true String will be presented as "java.lang.String" and with isFullTypeName returning false, String will be presented as "String"
     */
    public boolean isFullTypeName() {
        return fullTypeName;
    }

    /**
     * @return The String that should is used to separate the meta part and the value part of the String representation of the FieldInfo object
     */
    public String getFieldMetaValueSeparator() {
        return fieldMetaValueSeparator;
    }

    /**
     * @return The String that should is used to separate the meta part and the value part of the String representation of the ObjectInfo object
     */
    public String getObjectMetaValueSeparator() {
        return objectMetaValueSeparator;
    }

    /**
     * @return The String representing an indent.
     */
    public String getIndent() {
        return indent;
    }

    /**
     * @return A String containing multiple (0 or more) indents to be used as indentation. The number of multiplication is affected by indentCount field.
     * To get the number of indents, invoke the method StringRepresentationOptions#getIndentCount().
     */
    public String getIndents() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.getIndentCount(); i++) {
            builder.append(getIndent());
        }
        return builder.toString();
    }

    /**
     * @return The String that is used as a separator between different meta-data components of the meta-data String representation.
     */
    public String getFieldMetaSeparator() {
        return fieldMetaSeparator;
    }

    /**
     * @return The string used to create new lines.
     */
    public String getNewLine() {
        return newLine;
    }

    /**
     * @return The int representing how many indents are there.
     */
    public int getIndentCount() {
        return indentCount;
    }

    /**
     * @return A String that should be used to represent a null value
     */
    public String getNullRepresentation() {
        return nullRepresentation;
    }

    /**
     * @return A method that clones the current StringRepresentationOptions into a new StringRepresentationOptions object with an indentCount incremented by 1.
     */
    public StringRepresentationOptions cloneAndAddIndent() {
        return new StringRepresentationOptions(this.isFullTypeName(), this.getFieldMetaValueSeparator(), this.getObjectMetaValueSeparator(), this.getIndent(), this.getFieldMetaSeparator(), this.getNewLine(), this.getIndentCount() + 1, this.getNullRepresentation());
    }

    /**
     * A mutable builder class that is used to build StringRepresentationOptions instances. <br>
     * The class's setter methods support chaining. <br>
     * The class has fields that correspond to similar fields in StringRepresentationOptions which are going to be used when creating the StringRepresentationOptions object.
     * Example <br>
     * StringRepresentationOptions.Builder.build().setFullTypeName(true).get(); <br>
     * That will return an instance of StringRepresentationOptions using the default values except for fullTypeName which will be true.
     */
    public static class Builder {
        /*Those values are counterparts of the values of the class StringRepresentationOptions*/
        private boolean fullTypeName = false;
        private String fieldMetaValueSeparator = "=";
        private String objectMetaValueSeparator = ":";
        private String indent = "\t";
        private String fieldMetaSeparator = "|";
        private String newLine = "\n";
        private int indentCount = 0;
        private String nullRepresentation = "<null>";

        /**
         * A private constructor. cause, why not?
         */
        private Builder() {
        }

        /**
         * A method that returns a Builder instance using default values.
         *
         * @return Builder instance using default values
         */
        public static Builder build() {
            return new Builder();
        }

        /**
         * @return the builder attribute of "fullTypeName" that will be used when constructing a StringRepresentationOptions object
         */
        public boolean isFullTypeName() {
            return fullTypeName;
        }

        /**
         * Sets the the builder attribute of "fullTypeName" that will be used when constructing a StringRepresentationOptions object
         *
         * @param fullTypeName the value that will be used ot set the attribute.
         * @return this object after changing the value.
         */
        public Builder setFullTypeName(boolean fullTypeName) {
            this.fullTypeName = fullTypeName;
            return this;
        }

        /**
         * @return the builder attribute of "fieldMetaValueSeparator" that will be used when constructing a StringRepresentationOptions object
         */
        public String getFieldMetaValueSeparator() {
            return fieldMetaValueSeparator;
        }

        /**
         * Sets the builder attribute of "fieldMetaValueSeparator" that will be used when constructing a StringRepresentationOptions object
         *
         * @param fieldMetaValueSeparator the value that will be used to set the attribute
         * @return this object after changing the value.
         */
        public Builder setFieldMetaValueSeparator(String fieldMetaValueSeparator) {
            this.fieldMetaValueSeparator = fieldMetaValueSeparator;
            return this;
        }

        /**
         * @return the builder attribute of "objectMetaValueSeparator" that will be used when constructing a StringRepresentationOptions object
         */
        public String getObjectMetaValueSeparator() {
            return objectMetaValueSeparator;
        }

        /**
         * Sets the builder attribute of "objectMetaValueSeparator" that will be used when constructing a StringRepresentationOptions object.
         *
         * @param objectMetaValueSeparator the value that will be used to set the attribute
         * @return this object after changing the value.
         */
        public Builder setObjectMetaValueSeparator(String objectMetaValueSeparator) {
            this.objectMetaValueSeparator = objectMetaValueSeparator;
            return this;
        }

        /**
         * @return the builder attribute of "indent" that will be used when constructing a StringRepresentationOptions object
         */
        public String getIndent() {
            return indent;
        }

        /**
         * Sets the builder attribute of "indent" that will be used when constructing a StringRepresentationOptions object
         *
         * @param indent the value that will be used to set the attribute
         * @return this object after changing the value.
         */
        public Builder setIndent(String indent) {
            this.indent = indent;
            return this;
        }

        /**
         * @return the builder attribute of "fieldMetaSeparator" that will be used when constructing a StringRepresentationOptions object
         */
        public String getFieldMetaSeparator() {
            return fieldMetaSeparator;
        }

        /**
         * Sets the builder attribute of "fieldMetaSeparator" that will be used when constructing a StringRepresentationOptions object
         *
         * @param fieldMetaSeparator the value that will be used to set the attribute
         * @return this object after changing the value.
         */
        public Builder setFieldMetaSeparator(String fieldMetaSeparator) {
            this.fieldMetaSeparator = fieldMetaSeparator;
            return this;
        }

        /**
         * @return the builder attribute of "newLine" that will be used when constructing a StringRepresentationOptions object
         */
        public String getNewLine() {
            return newLine;
        }

        /**
         * Sets the builder attribute of "newLine" that will be used when constructing a StringRepresentationOptions object
         *
         * @param newLine the value that will be used to set the attribute.
         * @return this object changing the value
         */
        public Builder setNewLine(String newLine) {
            this.newLine = newLine;
            return this;
        }

        /**
         * @return the builder attribute of "indentCount" that will be used when constructing a StringRepresentationOptions object
         */
        public int getIndentCount() {
            return indentCount;
        }

        /**
         * sets the builder attribute of "indentCount" that will be used when constructing a StringRepresentationOptions object
         *
         * @param indentCount The value that will be used to set the attribute
         * @return this object after changing the value
         */
        public Builder setIndentCount(int indentCount) {
            this.indentCount = indentCount;
            return this;
        }

        /**
         * @return the builder attribute of "nullRepresentation" that will be used when constructing a StringRepresentationOptions object
         */
        public String getNullRepresentation() {
            return nullRepresentation;
        }

        /**
         * Sets the builder attribute of "nullRepresentation" that will be used when constructing a StringRepresentationOptions object.
         *
         * @param nullRepresentation The value that will be used to set the attribute
         * @return this object after changing the value
         */
        public Builder setNullRepresentation(String nullRepresentation) {
            this.nullRepresentation = nullRepresentation;
            return this;
        }

        /**
         * A method that is used to construct a new StringRepresentationOptions object using the current values of this Builder instance.
         *
         * @return a new StringRepresentationOptions object using the values of this Builder instance.
         */
        public StringRepresentationOptions get() {
            return new StringRepresentationOptions(Builder.this.isFullTypeName(), Builder.this.getFieldMetaValueSeparator(), Builder.this.getObjectMetaValueSeparator(), Builder.this.getIndent(), Builder.this.getFieldMetaSeparator(), Builder.this.getNewLine(), Builder.this.getIndentCount(), Builder.this.getNullRepresentation());
        }
    }

}
