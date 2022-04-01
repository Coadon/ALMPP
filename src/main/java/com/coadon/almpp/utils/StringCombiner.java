/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021-2022 Coadon
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

package com.coadon.almpp.utils;

public final class StringCombiner {

    /**
     * Combines an array of strings into a single string object with a space in between two strings.
     * All spaces leading a backslash ("\ ") will be replaced with a single space character (" ").
     *
     * @param strings the array of strings to be combined
     * @return the combined string
     */
    public static String combine(Object[] strings) {
        StringBuilder combined = new StringBuilder();
        for (Object s : strings) {
            if (s instanceof String || s instanceof Number)
                combined.append(s).append(" ");
            else
                combined.append(s.toString()).append(" ");
        }
        return combined.toString().replace("\\ ", " ").trim();
    }

    /**
     * Combines an array of strings into a single string object with a custom string in between two strings.
     * All spaces leading a backslash ("\ ") will be replaced with a single space character (" ").
     *
     * @param strings the array of strings to be combined
     * @param inBetween the custom string to but between two strings
     * @return the combined string
     */
    public static String combine(Object[] strings, String inBetween) {
        StringBuilder combined = new StringBuilder();
        for (Object s : strings) {
            if (s instanceof String || s instanceof Number)
                combined.append(s).append(inBetween);
            else
                combined.append(s.toString()).append(inBetween);
        }
        return combined.toString().replace("\\ ", " ").trim();
    }
}
