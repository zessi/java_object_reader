package io.github.zessi.utils.java_object_reader.java_object_reader.classes_used_in_tests;

import java.security.SecureRandom;

import static io.github.zessi.utils.java_object_reader.java_object_reader.TestUtils.*;

public class PrimitivesFieldsObject {

    private static final String text1 = getRandomLettersAndNumbers(10);
    private static String text2 = getRandomLettersAndNumbers(10);

    private final String text3 = getRandomLettersAndNumbers(10);
    private String text4 = getRandomLettersAndNumbers(10);
    String text5 = getRandomLettersAndNumbers(10);
    protected String text6 = getRandomLettersAndNumbers(10);
    public String text7 = getRandomLettersAndNumbers(10);

    private volatile String text8 = getRandomLettersAndNumbers(10);

    public boolean flag1 = new SecureRandom().nextBoolean();
    public Boolean flag2 = new SecureRandom().nextBoolean();
    public byte number1 = (byte) new SecureRandom().nextInt(Byte.MAX_VALUE);
    public Byte number2 = (byte) new SecureRandom().nextInt(Byte.MAX_VALUE);
    public short number3 = (short) new SecureRandom().nextInt(Short.MAX_VALUE);
    public Short number4 = (short) new SecureRandom().nextInt(Short.MAX_VALUE);
    public int number5 = new SecureRandom().nextInt();
    public Integer number6 = new SecureRandom().nextInt();
    public long number7 = new SecureRandom().nextLong();
    public Long number8 = new SecureRandom().nextLong();
    public float fraction1 = new SecureRandom().nextFloat();
    public Float fraction2 = new SecureRandom().nextFloat();
    public double fraction3 = new SecureRandom().nextDouble();
    public Double fraction4 = new SecureRandom().nextDouble();
    public char char1 = (char) (new SecureRandom().nextInt(26) + 65);
    public Character char2 = (char) (new SecureRandom().nextInt(26) + 65);
    public String text9 = getRandomLettersAndNumbers(10);

}
