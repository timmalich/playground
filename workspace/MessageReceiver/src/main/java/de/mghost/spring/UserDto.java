package de.mghost.spring;

import java.util.ArrayList;

public class UserDto {
    String name;
    ArrayList<String> authorizations;

    public String toString(){
        StringBuilder auths = new StringBuilder();
        for (String authorization : authorizations) {
            auths.append(authorization).append("\n");
        }
        return name + ":\n" + auths.toString();
    }
}
