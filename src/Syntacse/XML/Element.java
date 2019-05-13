package Syntacse.XML;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is just a map with 2 methods set and get
 */
class Element {

    private Map<String,String> map= new HashMap<>();

    String getValue(String s){
        return map.get(s);
    }
    void setValue(String k, String v){
        map.put(k,v);
    }
}
