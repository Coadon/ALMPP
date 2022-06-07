/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2022 Coadon
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

package com.coadon.almpp.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PluginConfigHandler {
    private final FileConfiguration cfg;

    public PluginConfigHandler(@NotNull FileConfiguration cfg) {
        this.cfg = cfg;
    }

    public @NotNull String getString(ConfigOptions option) {
        // Get the current value of a specified option
        String value = cfg.getString(option.getPath());

        // If the current value does not exist, get the default one
        if (value == null) {
            value = Objects.requireNonNull(cfg.getDefaults()).getString(option.getPath());
        }

        // Return
        return Objects.requireNonNull(value);
    }

    public @NotNull List<String> getStringList(ConfigOptions option) {
        return cfg.getStringList(option.getPath());
    }

    public boolean getBoolean(ConfigOptions option) {
        return cfg.getBoolean(option.getPath());
    }

    public Configuration cfg() {
        return cfg;
    }
}
