package com.coadon.almpp.almpp.utils;

public final class StringCombiner {

    public static String combine(Object[] items) {
        StringBuilder combined = new StringBuilder();
        for (Object item : items) {
            if (item instanceof String || item instanceof Number)
                combined.append(item).append(" ");
            else
                combined.append(item.toString()).append(" ");
        }
        return combined.toString();
    }
}
