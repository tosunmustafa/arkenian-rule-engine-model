package com.arkenian.ruleengine.model;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//TODO design error tolerance and checks
abstract class Root {

    private static final String DELIMITER = "§";

    public static <T extends Root> T fromBuffer(String buffer, Class<T> clazz) {
        if (buffer != null) {
            try {
                T thiz = clazz.newInstance();
                Map<String, String> m = split(buffer);
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
                for (int i = 0; i < pds.length; i++) {
                    PropertyDescriptor pd = pds[i];
                    int modifiers = pd.getReadMethod().getModifiers();
                    if (Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {
                        Method method = pd.getWriteMethod();
                        if (m.containsKey(pd.getName())) {
                            String value = m.get(pd.getName());
                            Object o = method.getParameterTypes()[0].getConstructor(String.class).newInstance(value);
                            method.invoke(thiz, o);
                        }
                    }
                }
                return thiz;
            } catch (Exception e) {
            }
        }
        return null;
    }

    private static Map<String, String> split(String input) {
        Map<String, String> m = new HashMap<>();
        int offset = 0;
        String[] kv = new String[2];
        for (int c = 0; ; c++) {
            int index = input.indexOf(DELIMITER, offset);
            if (index < 0)
                break;
            kv[c % 2 == 0 ? 0 : 1] = input.substring(offset, index);
            offset = index + 1;
            if (c % 2 == 1)
                m.put(kv[0], kv[1]);
        }
        return m;
    }

    public final StringBuilder toBuffer() {
        StringBuilder sb = new StringBuilder();
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(getClass()).getPropertyDescriptors();
            Arrays.sort(pds, new PropertyDescriptorAlphabeticComparator());
            for (int i = 0; i < pds.length; i++) {
                int modifiers = pds[i].getReadMethod().getModifiers();
                if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {
                    Object o = pds[i].getReadMethod().invoke(this);
                    if (o != null) {
                        sb.append(pds[i].getName());
                        sb.append(DELIMITER);
                        sb.append(o.toString());
                        sb.append(DELIMITER);
                    }
                }
            }
        } catch (Exception e) {
        }
        return sb;
    }

    public static class PropertyDescriptorAlphabeticComparator implements Comparator<PropertyDescriptor> {
        @Override
        public int compare(PropertyDescriptor o1, PropertyDescriptor o2) {
            return o1.getReadMethod().getName().compareTo(o2.getName());
        }
    }
}
