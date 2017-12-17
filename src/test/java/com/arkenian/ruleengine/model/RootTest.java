package com.arkenian.ruleengine.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(BlockJUnit4ClassRunner.class)
public class RootTest {

    private ConcreteRoot dummy;

    @Before
    public void setUp() throws Exception {
        dummy = new ConcreteRoot();
        dummy.setStringField("Something");
        dummy.setIntegerField(123);
    }

    @Test
    public void toBuffer() throws Exception {
        final String expected = "stringField§Something§integerField§123§";
        assertThat("Result buffer should be same as expected buffer.", dummy.toBuffer().toString(), is(equalTo(expected)));
    }

    @Test
    public void fromBuffer() throws Exception {
        final String buffer = "stringField§Something§integerField§123§";
        ConcreteRoot obj = ConcreteRoot.fromBuffer(buffer, ConcreteRoot.class);
        assertThat("Field stringField should be equal", obj.getStringField(), is(equalTo(dummy.getStringField())));
        assertThat("Field integerField should be equal", obj.getIntegerField(), is(equalTo(dummy.getIntegerField())));
    }
}