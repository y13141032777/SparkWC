package test;

import org.apache.commons.lang3.Validate;

public class test {
    public static void main(String[] args) {
        String a= "asd";
        System.out.println(a.substring(0,2));
//        new test(a);
    }


    test(String a){
        Validate.notEmpty(a, "topic must be not empty");
    }
}
