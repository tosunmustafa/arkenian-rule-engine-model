package com.arkenian.ruleengine.model;

class ConcreteRoot extends Root {
    private String stringField;
    private Integer integerField;

    ConcreteRoot() {
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Integer getIntegerField() {
        return integerField;
    }

    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }
}
