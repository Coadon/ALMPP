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

public enum ConfigOptions {
    DEFAULT_PUNISH_REASON("default-punish-reason"),
    ANNOUNCE_TERMINATION("punish-announcement.termination"),
    ANNOUNCE_REMOVAL("punish-announcement.removal"),
    COMMON_PUNISH_REASONS("common-punish-reasons"),
    NO_REASON_ALT("no-reason-alt"),
    SCREEN_PERM_TERM("ban-screens.permanent-termination"),
    SCREEN_TEMP_TERM("ban-screens.temporary-termination"),
    SCREEN_REMOVAL("ban-screens.removal"),
    DEBUG_MODE("debug-mode");

    private final String path;

    ConfigOptions(String node) {
        this.path = node;
    }

    public String getPath() {
        return path;
    }
}
