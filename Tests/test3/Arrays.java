package test3;

public class Arrays {
    private int[] anInt;
    private double[] aDouble;
    private boolean[] aBoolean;
    private String[] aString;
    private short[] aShort;
    private long[] aLong;
    private float[] aFloat;
    private char[] aChar;
    private byte[] aByte;
    
    public boolean eq(Arrays p) {
        return
            java.util.Arrays.equals(p.aBoolean, aBoolean) &&
                java.util.Arrays.equals(p.aByte, aByte) &&
                java.util.Arrays.equals(p.aChar, aChar) &&
                java.util.Arrays.equals(p.aDouble, aDouble) &&
                java.util.Arrays.equals(p.aFloat, aFloat) &&
                java.util.Arrays.equals(p.aLong, aLong) &&
                java.util.Arrays.equals(p.anInt, anInt) &&
                java.util.Arrays.equals(p.aShort, aShort) &&
                java.util.Arrays.equals(p.aString, aString);
    }
    
    public void setAnInt(int[] anInt) {
        this.anInt = anInt;
    }
    
    public void setaDouble(double[] aDouble) {
        this.aDouble = aDouble;
    }
    
    public void setaLong(long[] aLong) {
        this.aLong = aLong;
    }
    
    public void setaShort(short[] aShort) {
        this.aShort = aShort;
    }
    
    public void setaBoolean(boolean[] aBoolean) {
        this.aBoolean = aBoolean;
    }
    
    public boolean[] getaBoolean() {
        return aBoolean;
    }
    
    public void setaChar(char[] aChar) {
        this.aChar = aChar;
    }
    
    public void setaString(String[] aString) {
        this.aString = aString;
    }
    
    public double[] getaDouble() {
        return aDouble;
    }
    
    public int[] getAnInt() {
        return anInt;
    }
    
    public String[] getaString() {
        return aString;
    }
    
    public void setaFloat(float[] aFloat) {
        this.aFloat = aFloat;
    }
    
    public short[] getaShort() {
        return aShort;
    }
    
    public void setaByte(byte[] aByte) {
        this.aByte = aByte;
    }
    
    public byte[] getaByte() {
        return aByte;
    }
    
    public char[] getaChar() {
        return aChar;
    }
    
    public float[] getaFloat() {
        return aFloat;
    }
    
    public long[] getaLong() {
        return aLong;
    }
}


