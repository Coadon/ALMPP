/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021 Coadon
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

package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.utils.StringCombiner;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;

public final class Arguments {
    private final String[] content;

    public Arguments(@Nullable String[] args) {
        this.content = args;
    }

    public String[] getContent() {
        return content;
    }

    public String get(int index) throws ArrayIndexOutOfBoundsException {
        return (String) Array.get(content, index);
    }

    public String[] getFrom(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > content.length)
            throw new IndexOutOfBoundsException();
        return Arrays.stream(content).skip(skipCount).toArray(String[]::new);
    }

    public String getCombined() {
        return StringCombiner.combine(content);
    }

    public String getCombinedFrom(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > content.length)
            throw new IndexOutOfBoundsException();
        return StringCombiner.combine(Arrays.stream(content).skip(skipCount).toArray());
    }

    public List<String> getContentAsList() {
        return Arrays.asList(content);
    }

    public Set<String> getContentAsSet() {
        return new HashSet<>(Arrays.asList(content));
    }

    public int length() {
        return content.length;
    }
}
