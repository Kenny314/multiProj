package org.kenny.exception;

public class Test {
    public static void main(String[] args){
        MainClass mainClass = new MainClass();

        try {
            mainClass.methodA();
        } catch (ExceptionA e) {
            throw new RuntimeException(e);
        }

        mainClass.methodB();
    }
}
