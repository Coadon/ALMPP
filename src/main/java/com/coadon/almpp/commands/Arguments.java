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

package com.coadon.almpp.commands;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public final class Arguments extends ArrayList<String> {

    public Arguments(@Nullable String[] args) {
        super(Arrays.asList(args));
    }

    public Arguments(@NotNull Collection<String> args) {
        super(args);
    }

    public String[] getContent() {
        return this.toArray(new String[0]);
    }

    public String getCombined() {
        return StringUtils.join(this.toArray(), ' ');
    }

    public String getSkipCombined(int skipCount) throws IndexOutOfBoundsException{
        return StringUtils.join(skipGetArray(skipCount), ' ');
    }

    public List<String> skipGet(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > this.size())
            throw new IndexOutOfBoundsException();
        return this.stream().skip(skipCount).collect(Collectors.toList());
    }

    public String[] skipGetArray(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > this.size())
            throw new IndexOutOfBoundsException();
        return this.stream().skip(skipCount).toArray(String[]::new);
    }

    public Arguments skipGetArgs(int skipCount) throws IndexOutOfBoundsException{
        if (skipCount > this.size())
            throw new IndexOutOfBoundsException();
        return new Arguments(this.stream().skip(skipCount).toArray(String[]::new));
    }

    public Set<String> getAsSet() {
        return new HashSet<>(this);
    }
}
