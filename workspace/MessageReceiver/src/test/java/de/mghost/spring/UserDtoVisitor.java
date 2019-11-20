package de.mghost.spring;

import java.util.ArrayList;

public class UserDtoVisitor implements Visitor {
    private UserDto userDto;

    UserDtoVisitor(){
        this.userDto = new UserDto();
        this.userDto.authorizations = new ArrayList<>();
    }

    @Override
    public void visit(Authorization authorization) {
        this.userDto.authorizations.add("A-" + authorization.getAuth());
    }

    @Override
    public void visit(Entitlement entitlement) {
        this.userDto.authorizations.add("E-" + entitlement.getEnt());

    }

    UserDto getUserDto(User user){
        userDto.name = user.getName();
        return userDto;
    }
}
