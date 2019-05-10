package SyntacseForDeserializing;

import java.io.FileInputStream;

public interface DeserializationSyntacseWithoutParse extends DeserializingSyntacse{
    void removeHeader();
    void setStream(FileInputStream stream);
    void readObject(Object o, String type);
}
