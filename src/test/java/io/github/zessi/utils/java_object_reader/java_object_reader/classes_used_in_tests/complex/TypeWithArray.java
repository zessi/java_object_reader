package io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests.complex;

public class TypeWithArray {

    private TypeTwo[] typeTwos;
    private TypeThree[] typeThrees;
    private TypeFour[] typeFours;

    public TypeWithArray() {
        this.typeTwos = new TypeTwo[10];
        for (int i = 0; i < 10; i++) {
            typeTwos[i] = new TypeTwo();
        }

        this.typeThrees = new TypeThree[10];
        for (int i = 0; i < 10; i++) {
            typeThrees[i] = new TypeThree();
        }

        this.typeFours = new TypeFour[10];
        for (int i = 0; i < 10; i++) {
            typeFours[i] = new TypeFour();
        }
    }
}
