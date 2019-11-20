package de.mghost.spring;

public class User  {
    private String name;

    String getName(){
        return this.name;
    }

    User(String name){
       this.name = name;
    }
}
