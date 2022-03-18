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

package com.coadon.almpp.almpp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ConfigHandlerImpl implements ConfigHandler {
    private final FileConfiguration cfg;

    public ConfigHandlerImpl(@NotNull FileConfiguration cfg) {
        this.cfg = cfg;
    }

    @Override
    public @NotNull String getDefaultPunishReason() {
        return getNoNullString("default-punish-reason");
    }

    @Override
    public @NotNull List<String> getTerminationMessage() {
        return cfg.getStringList("broadcast-ban-message.termination");
    }

    @Override
    public @NotNull List<String> getRemovalMessage() {
        return cfg.getStringList("broadcast-ban-message.removal");
    }

    @Override
    public @NotNull List<String> getCommonPunishReasons() {
        return cfg.getStringList("common-punish-reasons");
    }

    @Override
    public @NotNull String getNoReasonAlt() {
        return getNoNullString("no-reason-alt");
    }

    // Ban Screens

    @Override
    public @NotNull List<String> getPermanentTerminationScreen() {
        return cfg.getStringList("ban-screens.permanent-termination");
    }

    @Override
    public @NotNull List<String> getTemporaryTerminationScreen() {
        return cfg.getStringList("ban-screens.temporary-termination");
    }

    @Override
    public @NotNull List<String> getRemovalScreen() {
        return cfg.getStringList("ban-screens.removal");
    }

    // Debug mode
    @Override
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode");
    }

    // ------------
    // Other Things
    // ------------

    // Get FileConfiguration

    @Override
    public @NotNull FileConfiguration getFileCfg() {
        return cfg;
    }

    // NotNull Utilities

    private String getNoNullString(String path) {
        return noNull(
                ifNullThen(cfg.getString(path),
                        noNull(cfg.getDefaults(), "Configuration source does not exist.").getString(path)),
                "Missing default configuration option: '" + path + "'.");
    }

    private <T> T ifNullThen(T object, T fallback) {
        if (object == null)
            return fallback;
        return object;
    }

    private <T> T noNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    private <T> T noNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }
}
