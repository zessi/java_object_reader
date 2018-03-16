# java_object_reader
java_object_reader is a hacked attempt to create a JSON-like String representation of objects with metadata using recursion and java (Java 1.8) reflection api.

## Usage

### Preparing a StringRepresentationOptions object
```StringRepresentationOptions``` object contains information that are used to control the behavior of the String representation process.  
The simplest way of obtaining a ```StringRepresentationOptions``` object containing the default options is as follows.
```java
StringRepresentationOptions options = StringRepresentationOptions.Builder.build().get();
```
The ```Builder``` is an inner static class that is used to build ```StringRepresentationOptions``` objects.  
The ```build()``` method will return new instance of the ```StringRepresentationOptions.Builder``` class which can be modified using chainable setter method.  
After setting all the options as required (or leaving them as default), simply call ```get()``` method which will return a new immutable instance of ```StringRepresentationOptions```

### Reading Objects 

Reading objects is as simple as calling a single static method providing the object-to-read and the options to be used.

```java
String read(Object object, StringRepresentationOptions options)
```
The method should return a String representing the input object.

#### Examples of Reading Different Types Of Objects 

##### Reading Primitive Type Objects

```java
System.out.println(ObjectReader.read(new Object(), options));
System.out.println(ObjectReader.read("asd", options));
System.out.println(ObjectReader.read(1010, options));
System.out.println(ObjectReader.read(new Integer(10), options));
```
Outputs
```
Object:{}
String:{"asd"}
Integer:{1010}
Integer:{10}
```

##### Reading Objects with Primitive Fields

```java
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
```
```java
public class TypeFive {
    private int number5 = 55555;
    private String text5 = "Five Fives";
}
```

```java
System.out.println(ObjectReader.read(new PrimitivesFieldsObject(), options));
```
Outputs a result similar to
```
PrimitivesFieldsObject:{
	PrimitivesFieldsObject|b|char|"char1"='R',
	PrimitivesFieldsObject|b|Character|"char2"='K',
	PrimitivesFieldsObject|b|boolean|"flag1"=true,
	PrimitivesFieldsObject|b|Boolean|"flag2"=false,
	PrimitivesFieldsObject|b|float|"fraction1"=0.3770591,
	PrimitivesFieldsObject|b|Float|"fraction2"=0.44574523,
	PrimitivesFieldsObject|b|double|"fraction3"=0.24010448035243492,
	PrimitivesFieldsObject|b|Double|"fraction4"=0.20301301006531136,
	PrimitivesFieldsObject|b|byte|"number1"=97,
	PrimitivesFieldsObject|b|Byte|"number2"=44,
	PrimitivesFieldsObject|b|short|"number3"=14942,
	PrimitivesFieldsObject|b|Short|"number4"=17502,
	PrimitivesFieldsObject|b|int|"number5"=-552311405,
	PrimitivesFieldsObject|b|Integer|"number6"=-474129605,
	PrimitivesFieldsObject|b|long|"number7"=4729171233969819320,
	PrimitivesFieldsObject|b|Long|"number8"=-6381542440841004841,
	PrimitivesFieldsObject|iti|String|"text1"="Nb0eh1bkZh",
	PrimitivesFieldsObject|it|String|"text2"="6Z9OvJs5NF",
	PrimitivesFieldsObject|ii|String|"text3"="r6q5Y7C4JA",
	PrimitivesFieldsObject|i|String|"text4"="SuW3pL95Iz",
	PrimitivesFieldsObject|c|String|"text5"="7ENXqAOn49",
	PrimitivesFieldsObject|o|String|"text6"="0AyvW6Sn7o",
	PrimitivesFieldsObject|b|String|"text7"="sWnq9Ayq4W",
	PrimitivesFieldsObject|io|String|"text8"="U7bpLgG5l9",
	PrimitivesFieldsObject|b|String|"text9"="050GYE1SK5"
}
```
```
TypeFive:{
	TypeFive|i|int|"number5"=55555,
	TypeFive|i|String|"text5"="Five Fives"
}
```

##### Reading Objects With Inheritance
```java
public class SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne value1";

    String hiddenFiled2 = "SimpleTypeOne value2";

    private String text1;

    public String getText1() {
        return text1;
    }

    public SimpleTypeOne setText1(String text1) {
        this.text1 = text1;
        return this;
    }
}
```
```java
public class SimpleTypeOne_One extends SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne_One value1";

    String hiddenFiled2 = "SimpleTypeOne_One value2";

    private String text2;

    public String getText2() {
        return text2;
    }

    public SimpleTypeOne_One setText2(String text2) {
        this.text2 = text2;
        return this;
    }
}
```

```java
public class SimpleTypeOne_One_One extends SimpleTypeOne_One {

    private String hiddenFiled1 = "SimpleTypeOne_One_One value1";

    String hiddenFiled2 = "SimpleTypeOne_One_One value2";

    private String text3;

    public String getText3() {
        return text3;
    }

    public SimpleTypeOne_One_One setText3(String text3) {
        this.text3 = text3;
        return this;
    }
}
```
```java
System.out.println(ObjectReader.read(new SimpleTypeOne().setText1("1"), options));
System.out.println(ObjectReader.read(new SimpleTypeOne_One().setText2("22").setText1("11"), options));
System.out.println(ObjectReader.read(new SimpleTypeOne_One_One().setText3("333").setText2("222").setText1("111"), options));
```
Outputs
```
SimpleTypeOne:{
	SimpleTypeOne|i|String|"hiddenFiled1"="SimpleTypeOne value1",
	SimpleTypeOne|c|String|"hiddenFiled2"="SimpleTypeOne value2",
	SimpleTypeOne|i|String|"text1"="1"
}
SimpleTypeOne_One:{
	SimpleTypeOne_One|i|String|"hiddenFiled1"="SimpleTypeOne_One value1",
	SimpleTypeOne_One|c|String|"hiddenFiled2"="SimpleTypeOne_One value2",
	SimpleTypeOne|i|String|"text1"="11",
	SimpleTypeOne_One|i|String|"text2"="22"
}
SimpleTypeOne_One_One:{
	SimpleTypeOne_One_One|i|String|"hiddenFiled1"="SimpleTypeOne_One_One value1",
	SimpleTypeOne_One_One|c|String|"hiddenFiled2"="SimpleTypeOne_One_One value2",
	SimpleTypeOne|i|String|"text1"="111",
	SimpleTypeOne_One|i|String|"text2"="222",
	SimpleTypeOne_One_One|i|String|"text3"="333"
}
```

##### Reading nulls

```java
public class NullsObject {

    private String text1;
    private Object object1;
    private Integer number1;

}
```
```java
System.out.println(ObjectReader.read(new NullsObject(), options));
System.out.println(ObjectReader.read(null, options));
System.out.println(ObjectReader.read(new String[]{"1111", "2222", null}));
```
Outputs
```
NullsObject:{
	NullsObject|i|Integer|"number1"=<null>,
	NullsObject|i|Object|"object1"=<null>,
	NullsObject|i|String|"text1"=<null>
}
<null>
String[]:[
	String:{"1111"},
	String:{"2222"},
	<null>
]
```
##### Reading Arrays and Array-related Objects
```java
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
```
```java
public class TypeFive {
    private int number5 = 55555;
    private String text5 = "Five Fives";
}
```
```java
final TypeWithArray typeWithArray = new TypeWithArray();
final TypeFive[] fives = new TypeFive[10];
for (int i = 0; i < fives.length; i++) {
    fives[i] = new TypeFive();
}

System.out.println(ObjectReader.read(new String[]{"1111", "2222", "3333", "4444"}, options));
System.out.println(ObjectReader.read(fives, options));
System.out.println(ObjectReader.read(typeWithArray));
```
Outputs
```
String[]:[
	String:{"1111"},
	String:{"2222"},
	String:{"3333"},
	String:{"4444"}
]
TypeFive[]:[
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	},
	TypeFive:{
		TypeFive|i|int|"number5"=55555,
		TypeFive|i|String|"text5"="Five Fives"
	}
]
TypeWithArray:{
	TypeWithArray|i|TypeFour[]|"typeFours"=TypeFour[]:[
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeFour:{
			TypeFour|i|String|"text4"="some text 4",
			TypeFour|i|TypeThree|"typeThree"=TypeThree:{
				TypeThree|i|String|"text3"="some other text3",
				TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
					TypeTwo|i|int|"number"=1234,
					TypeTwo|i|String|"text"="some text"
				}
			},
			TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		}
	],
	TypeWithArray|i|TypeThree[]|"typeThrees"=TypeThree[]:[
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		},
		TypeThree:{
			TypeThree|i|String|"text3"="some other text3",
			TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
				TypeTwo|i|int|"number"=1234,
				TypeTwo|i|String|"text"="some text"
			}
		}
	],
	TypeWithArray|i|TypeTwo[]|"typeTwos"=TypeTwo[]:[
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		},
		TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		}
	]
}
```
##### Testing Complex Objects (Objects containing non-String reference-type fields)

```java
public class TypeTwo {

    private int number = 1234;
    private String text = "some text";

}
```
```java
public class TypeThree {

    private String text3 = "some other text3";
    private TypeTwo typeTwo = new TypeTwo();

}
```
```java
public class TypeFour {

    private String text4 = "some text 4";
    private TypeTwo typeTwo = new TypeTwo();
    private TypeThree typeThree = new TypeThree();

}
```
```java
System.out.println(ObjectReader.read(new TypeFour(), options));
```
Outputs
```
TypeFour:{
	TypeFour|i|String|"text4"="some text 4",
	TypeFour|i|TypeThree|"typeThree"=TypeThree:{
		TypeThree|i|String|"text3"="some other text3",
		TypeThree|i|TypeTwo|"typeTwo"=TypeTwo:{
			TypeTwo|i|int|"number"=1234,
			TypeTwo|i|String|"text"="some text"
		}
	},
	TypeFour|i|TypeTwo|"typeTwo"=TypeTwo:{
		TypeTwo|i|int|"number"=1234,
		TypeTwo|i|String|"text"="some text"
	}
}
```
##### Handling Objects With Circular References 
```java
public class MyCircularTypeOne {

    public String text = "some text";
    public Object innerObject;

}
```
```java
public class CircularObjectHolder {

    public MyCircularTypeOne myCircularTypeOne1;
    public MyCircularTypeOne myCircularTypeOne2;

    public CircularObjectHolder() {
        this.myCircularTypeOne1 = new MyCircularTypeOne();
        this.myCircularTypeOne2 = new MyCircularTypeOne();
        this.myCircularTypeOne1.innerObject = myCircularTypeOne2;
        this.myCircularTypeOne2.innerObject = myCircularTypeOne1;
    }
}
```
```java
System.out.println(ObjectReader.read(new CircularObjectHolder(), options));
```
Outputs
```
CircularObjectHolder:{
	CircularObjectHolder|b|MyCircularTypeOne|"myCircularTypeOne1"=MyCircularTypeOne:{
		MyCircularTypeOne|b|Object|"innerObject"=MyCircularTypeOne:{
			MyCircularTypeOne|b|Object|"innerObject"=<Object Processing>,
			MyCircularTypeOne|b|String|"text"="some text"
		},
		MyCircularTypeOne|b|String|"text"="some text"
	},
	CircularObjectHolder|b|MyCircularTypeOne|"myCircularTypeOne2"=MyCircularTypeOne:{
		MyCircularTypeOne|b|Object|"innerObject"=MyCircularTypeOne:{
			MyCircularTypeOne|b|Object|"innerObject"=<Object Processing>,
			MyCircularTypeOne|b|String|"text"="some text"
		},
		MyCircularTypeOne|b|String|"text"="some text"
	}
}
```
##### Reading Objects With Hidden Fields
[docs.oracle.com | The Java™ Tutorials | Hiding Fields](https://docs.oracle.com/javase/tutorial/java/IandI/hidevariables.html)

```java
public class SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne value1";

    String hiddenFiled2 = "SimpleTypeOne value2";

    private String text1;

    public String getText1() {
        return text1;
    }

    public SimpleTypeOne setText1(String text1) {
        this.text1 = text1;
        return this;
    }
}
```
```java
public class SimpleTypeOne_One extends SimpleTypeOne {

    private String hiddenFiled1 = "SimpleTypeOne_One value1";

    String hiddenFiled2 = "SimpleTypeOne_One value2";

    private String text2;

    public String getText2() {
        return text2;
    }

    public SimpleTypeOne_One setText2(String text2) {
        this.text2 = text2;
        return this;
    }
}
```
```java
public class SimpleTypeOne_One_One extends SimpleTypeOne_One {

    private String hiddenFiled1 = "SimpleTypeOne_One_One value1";

    String hiddenFiled2 = "SimpleTypeOne_One_One value2";

    private String text3;

    public String getText3() {
        return text3;
    }

    public SimpleTypeOne_One_One setText3(String text3) {
        this.text3 = text3;
        return this;
    }
}
```
```java
System.out.println(ObjectReader.read(new SimpleTypeOne(), options));
System.out.println(ObjectReader.read(new SimpleTypeOne_One(), options));
System.out.println(ObjectReader.read(new SimpleTypeOne_One_One(), options));
```
Outputs
```
SimpleTypeOne:{
	SimpleTypeOne|i|String|"hiddenFiled1"="SimpleTypeOne value1",
	SimpleTypeOne|c|String|"hiddenFiled2"="SimpleTypeOne value2",
	SimpleTypeOne|i|String|"text1"=<null>
}
SimpleTypeOne_One:{
	SimpleTypeOne_One|i|String|"hiddenFiled1"="SimpleTypeOne_One value1",
	SimpleTypeOne_One|c|String|"hiddenFiled2"="SimpleTypeOne_One value2",
	SimpleTypeOne|i|String|"text1"=<null>,
	SimpleTypeOne_One|i|String|"text2"=<null>
}
SimpleTypeOne_One_One:{
	SimpleTypeOne_One_One|i|String|"hiddenFiled1"="SimpleTypeOne_One_One value1",
	SimpleTypeOne_One_One|c|String|"hiddenFiled2"="SimpleTypeOne_One_One value2",
	SimpleTypeOne|i|String|"text1"=<null>,
	SimpleTypeOne_One|i|String|"text2"=<null>,
	SimpleTypeOne_One_One|i|String|"text3"=<null>
}
```

















