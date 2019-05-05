package test4;

import test3.Arrays;

public class ArraysInArrays {
    private int[][] anInt;
    private double[][] aDouble;
    private boolean[][] aBoolean;
    private String[][] aString;
    private short[][] aShort;
    private long[][] aLong;
    private float[][] aFloat;
    private char[][] aChar;
    private byte[][] aByte;
    
    public boolean eq(ArraysInArrays p) {
        return
            java.util.Arrays.deepEquals(p.aBoolean, aBoolean) &&
                java.util.Arrays.deepEquals(p.aByte, aByte) &&
                java.util.Arrays.deepEquals(p.aChar, aChar) &&
                java.util.Arrays.deepEquals(p.aDouble, aDouble) &&
                java.util.Arrays.deepEquals(p.aFloat, aFloat) &&
                java.util.Arrays.deepEquals(p.aLong, aLong) &&
                java.util.Arrays.deepEquals(p.anInt, anInt) &&
                java.util.Arrays.deepEquals(p.aShort, aShort) &&
                java.util.Arrays.deepEquals(p.aString, aString);
    }
    
    public void setaString(String[][] aString) {
        this.aString = aString;
    }
    
    public void setaDouble(double[][] aDouble) {
        this.aDouble = aDouble;
    }
    
    public void setaFloat(float[][] aFloat) {
        this.aFloat = aFloat;
    }
    
    public void setaChar(char[][] aChar) {
        this.aChar = aChar;
    }
    
    public void setaByte(byte[][] aByte) {
        this.aByte = aByte;
    }
    
    public void setaBoolean(boolean[][] aBoolean) {
        this.aBoolean = aBoolean;
    }
    
    public void setaShort(short[][] aShort) {
        this.aShort = aShort;
    }
    
    public void setaLong(long[][] aLong) {
        this.aLong = aLong;
    }
    
    public void setAnInt(int[][] anInt) {
        this.anInt = anInt;
    }
    
    public boolean[][] getaBoolean() {
        return aBoolean;
    }
    
    public byte[][] getaByte() {
        return aByte;
    }
    
    public char[][] getaChar() {
        return aChar;
    }
    
    public double[][] getaDouble() {
        return aDouble;
    }
    
    public float[][] getaFloat() {
        return aFloat;
    }
    
    public int[][] getAnInt() {
        return anInt;
    }
    
    public long[][] getaLong() {
        return aLong;
    }
    
    public short[][] getaShort() {
        return aShort;
    }
    
    public String[][] getaString() {
        return aString;
    }
}
