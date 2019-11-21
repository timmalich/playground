package de.mghost.spring;

public interface Visitor {
    void visit(Authorization authorization);
    void visit(Entitlement entitlement);
}