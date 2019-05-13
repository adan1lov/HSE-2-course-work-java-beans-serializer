package SyntacseForDeserializing;

import java.io.IOException;

public interface DeserializationSyntacseWithParse extends DeserializingSyntacse {

    Object Parse(String path) throws IOException;
}
