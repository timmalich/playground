package de.mghost.spring;

public class Authorization implements Visitable {
    private String auth;

    String getAuth(){
        return this.auth;
    }

    Authorization(String auth){
       this.auth = auth;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
