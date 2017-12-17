package com.arkenian.ruleengine.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(BlockJUnit4ClassRunner.class)
public class AbstractSubjectTest {
    private ConcreteSubject cs;

    @Before
    public void setUp() throws Exception {
        cs = new ConcreteSubject();
        cs.setOid(111L);
    }

    @Test
    public void getVersion() throws Exception {
        assertThat("Version should be equal", cs.getVersion(), is(equalTo(1)));
    }

    @Test
    public void equals() throws Exception {
        ConcreteSubject different = new ConcreteSubject();
        different.setOid(112L);
        assertThat("Oid should be equal", cs.getOid(), is(equalTo(111L)));
        assertThat("Objects should be different", is(not(different.equals(cs))));
    }

}