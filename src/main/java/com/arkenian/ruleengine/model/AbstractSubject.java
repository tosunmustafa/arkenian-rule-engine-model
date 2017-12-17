package com.arkenian.ruleengine.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"oid"}, callSuper = false)
public abstract class AbstractSubject extends Root {

    @NonNull
    private Long oid;

    public final int getVersion() {
        return 1;
    }
}
