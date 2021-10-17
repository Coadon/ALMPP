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

package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.utils.StringCombiner;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;

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

    public String getCombined() {
        return StringCombiner.combine(content);
    }

    public String getCombinedFrom(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > content.length)
            throw new IndexOutOfBoundsException();
        return StringCombiner.combine(Arrays.stream(content).skip(skipCount).toArray());
    }

    public int length() {
        return content.length;
    }
}
