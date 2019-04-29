package JavaSerializer;

import java.io.InputStream;
import java.lang.reflect.Type;
import sun.misc.SharedSecrets;

public class JavaDeserializer {
    Type typeOfObject;
    InputStream stream;
    public JavaDeserializer(Type typeOfObject){
        this.typeOfObject=typeOfObject;
    }
    public Object Make(InputStream stream)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.stream=stream;
        String someClassName = "com.javainterviewpoint.Test";
        Class clasz = Class.forName(someClassName);
        clasz.newInstance();

    }
}
