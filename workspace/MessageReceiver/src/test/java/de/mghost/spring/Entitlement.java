package de.mghost.spring;

public class Entitlement implements Visitable {
    private String ent;

    String getEnt(){
        return this.ent;
    }

    Entitlement(String ent){
       this.ent = ent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
