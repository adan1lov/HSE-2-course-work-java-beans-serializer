package Syntacse.XML;

import java.util.HashMap;
import java.util.Map;

public class Element {
    private Map<String,String> map= new HashMap<>();
    public String getValue(String s){
        return map.get(s);
    }
    public void setValue(String k, String v){
        map.put(k,v);
    }
}
