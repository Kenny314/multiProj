package org.kenny.exception;

public class MainClass {
    public void methodA() throws ExceptionA{
        System.out.println(MainClass.class + "----" + "methodA()");
    }

    public void methodB(){
        System.out.println(MainClass.class + "----" + "methodB()");
    }
}
