package de.mghost.spring;

import java.util.ArrayList;

public class UserEntitlementShoppingCart {
    public static void main(String[] args){
        ArrayList<Visitable> visitableItems = new ArrayList<>();
        visitableItems.add(new Authorization("AccessAuth1"));
        visitableItems.add(new Authorization("AccessAuth2"));
        visitableItems.add(new Entitlement("SuperAdmin"));
        visitableItems.add(new Entitlement("HeroMitGiro"));

        UserDtoVisitor userDtoVisitor = new UserDtoVisitor();
        for (Visitable item : visitableItems) {
            item.accept(userDtoVisitor);
        }

        System.out.println(userDtoVisitor.getUserDto(new User("Hans Mainer von Mainland")));

    }
}
