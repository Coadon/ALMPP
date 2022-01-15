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

package com.coadon.almpp.almpp.system;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

public class ConfigHandlerImpl implements ConfigHandler {
    private final @NotNull String DEFAULT_PUNISH_REASON;
    private final @NotNull List<String> TERMINATION_BROADCAST_MSG;
    private final @NotNull List<String> AFKKICK_BROADCAST_MSG;

    public ConfigHandlerImpl(FileConfiguration cfg, Logger logger) {
        this.DEFAULT_PUNISH_REASON = cfg.getString("default-punish-reason") == null ? noNull(getDefaults(cfg).getString("default-punish-reason")) : "";
        this.TERMINATION_BROADCAST_MSG = cfg.getStringList("broadcast-ban-message.termination");
        this.AFKKICK_BROADCAST_MSG = cfg.getStringList("broadcast-ban-message.afk-kick");
    }

    @Override
    public @NotNull String getDefaultPunishReason() {
        return DEFAULT_PUNISH_REASON;
    }

    @Override
    public @NotNull List<String> getTerminationMessage() {
        return TERMINATION_BROADCAST_MSG;
    }

    @Override
    public @NotNull List<String> getAfkKickMessage() {
        return AFKKICK_BROADCAST_MSG;
    }

    private Configuration getDefaults(FileConfiguration cfg) {
        return noNull(cfg.getDefaults());
    }

    /**
     * A shorter and more convenient way of doing Objects.requireNonNull();
     *
     * @param obj the object reference to check for nullity
     * @return obj if not null
     * @throws NullPointerException – if obj is null
     */
    private <T> T noNull(T obj) {
        return Objects.requireNonNull(obj);
    }
}
