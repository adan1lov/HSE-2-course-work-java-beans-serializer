package SyntacseForDeserializing;

import java.io.FileReader;
import java.io.IOException;

public abstract class DeserializationSyntacse {
    //public abstract void RemoveHeader(FileReader fileReader) throws IOException;
    //public abstract void RemoveEnd(FileReader fileReader);
    public abstract Object readPrimitive(FileReader fileReader, String name);
    
    public DeserializationSyntacse(String path) {}
}
