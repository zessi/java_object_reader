package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * An immutable class that is used for the String representation of a java field <br>
 * The class holds the following information about a field
 * <ul>
 * <li>
 * Field field; The associated java field
 * </li>
 * <li>
 * Class definingClass; The class that defines/declares this field. This is important to show inherited fields.
 * </li>
 * <li>
 * Object associatedObject; The context object of the class that this field is a part of. If the field is static, then this object is null
 * </li>
 * <li>
 * String name; The name of the field
 * </li>
 * <li>
 * Object value; The value of the field's data
 * </li>
 * <li>
 * int modifiers; An int representing the java modifiers applied to this field
 * </li>
 * <li>
 * Class fieldType; A class representing the type supported by the field
 * </li>
 * <li>
 * StringRepresentationOptions options; The StringRepresentationOptions object that is used to control the behavior of the String representation of this field
 * </li>
 * </ul>
 */
abstract class FieldInfo implements StringRepresentable {

    /**
     * The associated java field
     */
    private final Field field;

    /**
     * The class that defines/declares this field. This is important to show inherited fields.
     */
    private final Class definingClass;

    /**
     * The context object of the class that this field is a part of. If the field is static, then this object is null
     */
    private final Object associatedObject;

    /**
     * The name of the field
     */
    private final String name;

    /**
     * The value of the field's data
     */
    private Object value;

    /**
     * An int representing the java modifiers applied to this field
     */
    private final int modifiers;

    /**
     * A class representing the type supported by the field
     */
    private final Class fieldType;

    /**
     * The StringRepresentationOptions object that is used to control the behavior of the String representation of this field
     */
    private final StringRepresentationOptions options;

    /**
     * A constructor that calls the main constructor <br>
     * this(field, definingClass, null, new StringRepresentationOptions())
     *
     * @param field         The field that will be String represented
     * @param definingClass The class that defines/declares this field
     */
    FieldInfo(Field field, Class definingClass) {
        this(field, definingClass, null, StringRepresentationOptions.Builder.build().get());
    }

    /**
     * A constructor that calls the main constructor<br>
     * this(field, definingClass, null, options)
     *
     * @param field         The field that will be String represented
     * @param definingClass The class that defines/declares this field
     * @param options       The StringRepresentationOptions object that will be used to control the behavior of the String representation of this field
     */
    FieldInfo(Field field, Class definingClass, StringRepresentationOptions options) {
        this(field, definingClass, null, options);
    }

    /**
     * The main constructor of this class<br>
     * The constructor creates a new instance FieldInfo type using the provided input. Upon creation, the object becomes immutable
     *
     * @param field            The field that will be String represented
     * @param definingClass    The class that defines/declares this field
     * @param associatedObject The context object of the class that this field is a part of. If the field is static, then this object should be null
     * @param options          The StringRepresentationOptions object that will be used to control the behavior of the String representation of this field
     */
    FieldInfo(Field field, Class definingClass, Object associatedObject, StringRepresentationOptions options) {
        if (field == null) throw new IllegalArgumentException("null first parameter");
        if (definingClass == null) throw new IllegalArgumentException("null second parameter");
        //A null associatedObject is only allowed for static fields.
        if ((associatedObject == null) && (!Modifier.isStatic(field.getModifiers())))
            throw new IllegalArgumentException("null third parameter (associatedObject) with non static field");
        if (options == null) throw new IllegalArgumentException("null fourth parameter");

        this.field = field;
        this.definingClass = definingClass;
        this.name = field.getName();
        this.fieldType = field.getType();
        this.modifiers = field.getModifiers();
        this.associatedObject = associatedObject;
        this.options = options;

        //Reading the value of the field even if it was inaccessible.
        synchronized (this.field) {
            final boolean originalAccessibility = this.field.isAccessible();
            this.field.setAccessible(true);
            try {
                this.value = this.field.get(associatedObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            this.field.setAccessible(originalAccessibility);
        }
    }

    /**
     * @return The StringRepresentationOptions object that is used to control the behavior of the String representation of this field
     */
    StringRepresentationOptions getOptions() {
        return options;
    }

    /**
     * @return The associated java field
     */
    Field getField() {
        return field;
    }

    /**
     * @return The context object of the class that this field is a part of. If the field is static, then null is returned
     */
    Object getAssociatedObject() {
        return associatedObject;
    }

    /**
     * @return The name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * @return The class that defines/declares this field. This is important to show inherited fields.
     */
    Class getDefiningClass() {
        return definingClass;
    }

    /**
     * @return A String representing the class name of the class representing the type of the field's value. The returned String is either short/simple or full, that depends on the
     * StringRepresentationOptions#isFullTypeName value
     */
    public String getTypeName() {
        return (this.options.isFullTypeName()) ? this.fieldType.getName() : this.fieldType.getSimpleName();
    }

    /**
     * @return A String representing the meta part of the String representation of this field
     */
    @Override
    public String getMetaStringRepresentation() {
        return ((options.isFullTypeName()) ? getDefiningClass().getCanonicalName() : getDefiningClass().getSimpleName()) + options.getFieldMetaSeparator()
                + getModifiersRepresentation() + options.getFieldMetaSeparator() + getTypeName() + options.getFieldMetaSeparator() + "\"" + getName() + "\"";
    }

    /**
     * @return A String that is used to separate between the meta part and the value part of the String representation of this field
     */
    @Override
    public String getMetaValueSeparator() {
        return options.getFieldMetaValueSeparator();
    }

    /**
     * @return The value of the field's data
     */
    Object getValue() {
        return value;
    }

    /**
     * @return An int representing the java modifiers applied to this field
     */
    int getModifiers() {
        return modifiers;
    }

    /**
     * @return A class representing the type supported by the field
     */
    Class getFieldType() {
        return fieldType;
    }

    /**
     * @return true if the field is static, false otherwise
     */
    boolean isStatic() {
        return Modifier.isStatic(this.modifiers);
    }

    /**
     * @return true if the field is final, false otherwise
     */
    boolean isFinal() {
        return Modifier.isFinal(this.modifiers);
    }

    /**
     * @return true if the field has private access modifier, false otherwise
     */
    boolean isPrivate() {
        return Modifier.isPrivate(this.modifiers);
    }

    /**
     * @return true if the field has protected access modifier, false otherwise
     */
    boolean isProtected() {
        return Modifier.isProtected(this.modifiers);
    }

    /**
     * @return true if the field has access modifier, false otherwise
     */
    boolean isPublic() {
        return Modifier.isPublic(this.modifiers);
    }

    /**
     * @return true if the field is strictfp, false otherwise
     */
    boolean isStrict() {
        return Modifier.isStrict(this.modifiers);
    }

    /**
     * @return true if the field is transient, false otherwise
     */
    boolean isTransient() {
        return Modifier.isTransient(this.modifiers);
    }

    /**
     * @return true if the field is volatile, false otherwise
     */
    boolean isVolatile() {
        return Modifier.isVolatile(this.modifiers);
    }

    /**
     * A method that evaluates this fields modifiers and returns a string representing the modifiers of this field. <br>
     * The method uses the following format. <br>
     * The format is like this &quot;[part_1][part_2]&quot;
     * <ol>
     * <li>
     * part_1 :
     * <ul>
     * <li>
     * This part has only one character which always represents the access modifier.
     * </li>
     * <li>
     * This part's character is never omitted even if the access modifier is package-private/NA.
     * </li>
     * </ul>
     * </li>
     * <li>
     * part_2 :
     * <ul>
     * <li>
     * This part has characters representing the rest of the field's modifiers (Non-access modifiers).
     * </li>
     * <li>
     * Each modifier is represented by a unique character. Basically, the second character of its word except for strictfp.
     * <ul>
     * <li>
     * static : 't'
     * </li>
     * <li>
     * final : 'i'
     * </li>
     * <li>
     * volatile : 'o'
     * </li>
     * <li>
     * transient : 'r'
     * </li>
     * <li>
     * strictfp : 'f'
     * </li>
     * </ul>
     * </li>
     * <li>
     * If a modifier is applicable to this field, then the modifier's character is appended to the String representation, otherwise, the character is omitted.
     * </li>
     * <li>
     * This essentially means that the lack of the character in the representation String means that the field does not have the corresponding modifier. And the existence
     * of the character in the representation String means that the character's modifier is applied to this field.
     * </li>
     * </ul>
     * </li>
     * </ol>
     *
     * @return The string representation of this field's modifiers.
     */
    String getModifiersRepresentation() {
        StringBuilder rep = new StringBuilder();
        //Variables were used to make the code more readable/understandable
        final char access = AccessModifier.getAccessModifier(this.modifiers).getRepresentation();
        final String isStatic = (isStatic()) ? "t" : "";
        final String isFinal = (isFinal()) ? "i" : "";
        final String isVolatile = (isVolatile()) ? "o" : "";
        final String isTransient = (isTransient()) ? "r" : "";
        final String isStrictfp = (isStrict()) ? "f" : "";

        rep.append(access);
        rep.append(isStatic);
        rep.append(isFinal);
        rep.append(isVolatile);
        rep.append(isTransient);
        rep.append(isStrictfp);

        return rep.toString();
    }

    /**
     * A static factory method that returns an implementation of this class depending on the field type being provided
     *
     * @param field            The field that will be String represented
     * @param definingClass    The class that defines/declares this field. This is important to show inherited fields.
     * @param associatedObject The context object of the class that this field is a part of. If the field is static, then this object should be null
     * @param options          The StringRepresentationOptions object that will be used to control the behavior of the String representation of this field
     * @return
     */
    static FieldInfo getFieldInfo(Field field, Class definingClass, Object associatedObject, StringRepresentationOptions options, CircularReferencePrevention crp) {
        //The method returns the appropriate FieldInfo type according the field's type.
        return (StringRepresentableUtils.isPrimitive(field.getType())) ? new PrimitiveFieldInfo(field, definingClass, associatedObject, options) : new NonPrimitiveFieldInfo(field, definingClass, associatedObject, options, crp);
    }
}
