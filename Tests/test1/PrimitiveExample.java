package test1;


public class PrimitiveExample {
    private int anInt;
    private double aDouble;
    private boolean aBoolean;
    private String aString;
    private short aShort;
    private long aLong;
    private float aFloat;
    private char aChar;
    private byte aByte;
    
    public long getaLong() {
        return aLong;
    }
    
    public byte getaByte() {
        return aByte;
    }
    
    public char getaChar() {
        return aChar;
    }
    
    public float getaFloat() {
        return aFloat;
    }
    
    public short getaShort() {
        return aShort;
    }
    
    public void setaShort(short aShort) {
        this.aShort = aShort;
    }
    
    public void setaByte(byte aByte) {
        this.aByte = aByte;
    }
    
    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }
    
    public void setaChar(char aChar) {
        this.aChar = aChar;
    }
    
    public void setaLong(long aLong) {
        this.aLong = aLong;
    }
    
    public double getaDouble() {
        return aDouble;
    }
    
    public int getAnInt() {
        return anInt;
    }
    
    public String getaString() {
        return aString;
    }
    
    public void setaString(String aString) {
        this.aString = aString;
    }
    
    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
    
    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }
    
    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
    
    public boolean isaBoolean() {
        return aBoolean;
    }
    
    public boolean eq(PrimitiveExample p) {
        return
            p.aBoolean == aBoolean &&
                p.aByte == aByte &&
                p.aChar == aChar &&
                p.aDouble == aDouble &&
                p.aFloat == aFloat &&
                p.aLong==aLong &&
                p.anInt==anInt &&
                p.aShort==aShort &&
                p.aString.equals(aString);
    }
}
