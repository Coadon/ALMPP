/*
 * ALMPP - a bukkit plugin
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
