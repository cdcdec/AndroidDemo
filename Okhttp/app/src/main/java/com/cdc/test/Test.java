package com.cdc.test;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    String[] strTest={"java","c##","python","c","html"};
    private volatile Set<String> headersToRedact = Collections.emptySet();
    public void redactHeader(String name) {
        Set<String> newHeadersToRedact = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        newHeadersToRedact.addAll(headersToRedact);
        newHeadersToRedact.add(name);
        headersToRedact = newHeadersToRedact;
    }

    public void tt(){
        for(int i=0;i<strTest.length;i++){
            log(i);
        }
        System.out.println("set长度=="+headersToRedact.size());
    }

    private void log(int i){
        String value = headersToRedact.contains(strTest[i]) ? "██" :strTest[i];
        System.out.println("value="+value);
    }



    public static void main(String[] strings){
        Test test=new Test();
        //test.redactHeader("c##");
        test.tt();
    }
}
