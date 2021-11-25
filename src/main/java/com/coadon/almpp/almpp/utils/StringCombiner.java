/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021  Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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

    public static String combine(Object[] items, String interval) {
        StringBuilder combined = new StringBuilder();
        for (Object item : items) {
            if (item instanceof String || item instanceof Number)
                combined.append(item).append(interval);
            else
                combined.append(item.toString()).append(interval);
        }
        return combined.toString();
    }
}
