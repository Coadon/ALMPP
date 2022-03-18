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

package com.coadon.almpp.almpp.system;

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.config.ConfigHandler;
import com.coadon.almpp.almpp.config.ConfigOptions;
import com.coadon.almpp.almpp.utils.StringCombiner;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public final class ComponentProviderImpl implements ComponentProvider {
    private final ConfigHandler cfg;
    private final MiniMessage mm;

    public ComponentProviderImpl(ALMPP plugin) {
        this.mm = MiniMessage.miniMessage();
        this.cfg = plugin.getConfigHandler();
    }

    /**
     * Generates a kick message component with provided arguments.
     *
     * @param reason the text to be displayed as the reason
     * @param date the text to be displayed as the date
     * @return the combined and generated component
     */
    @Override
    public @NotNull Component generateKickMessage(@NotNull String reason, @NotNull String date) {
        String source = StringCombiner.combine(cfg.getStringList(ConfigOptions.SCREEN_REMOVAL).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date);
        return mm.deserialize(source);
    }

    /**
     * Generates a permanently banned message component with provided arguments.
     *
     * @param reason the text to be displayed as the reason
     * @param date the text to be displayed as the date
     * @return the combined and generated component
     */
    @Override
    public @NotNull Component generateKickPermBanMessage(@NotNull String reason, @NotNull String date) {
        String source = StringCombiner.combine(cfg.getStringList(ConfigOptions.SCREEN_PERM_TERM).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date);
        return mm.deserialize(source);
    }

    /**
     * Generates a temporarily banned message component with provided arguments.
     *
     * @param reason the text to be displayed as the reason
     * @param date the text to be displayed as the date
     * @param expiry the text to be displayed as the expires date
     * @return the combined and generated component
     */
    @Override
    public @NotNull Component generateKickTempBanMessage(@NotNull String reason, @NotNull String date, @NotNull String expiry) {
        String source = StringCombiner.combine(cfg.getStringList(ConfigOptions.SCREEN_TEMP_TERM).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[expiry]", expiry);
        source = source.replaceAll("\\[date]", date);
        return mm.deserialize(source);
    }

    /**
     * Generates a player termination announcement message component.
     *
     * @param targetName the name to be displayed as the target
     * @return the combined and generated component
     */
    @Override
    public @NotNull String getTerminationAnnouncementMessage(@NotNull String targetName) {
        String output = StringCombiner.combine(cfg.getStringList(ConfigOptions.ANNOUNCE_TERMINATION).toArray(), "\n");
        output = output.replaceAll("\\[player]", targetName);
        output = ChatColor.translateAlternateColorCodes('&', output);
        return output;
    }

    /**
     * Generates a player removal announcement message component.
     *
     * @param targetName the name to be displayed as the target
     * @return the combined and generated component
     */
    @Override
    public @NotNull String getRemovalAnnouncementMessage(@NotNull String targetName) {
        String output = StringCombiner.combine(cfg.getStringList(ConfigOptions.ANNOUNCE_REMOVAL).toArray(), "\n");
        output = output.replaceAll("\\[player]", targetName);
        output = ChatColor.translateAlternateColorCodes('&', output);
        return output;
    }
}
