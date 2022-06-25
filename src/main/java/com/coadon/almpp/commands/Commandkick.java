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

import com.coadon.almpp.ALMPP;
import com.coadon.almpp.config.ConfigOptions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class Commandkick extends ALMPPCommand {

    public Commandkick(ALMPP plugin) {
        super(plugin, Component.text("Usage: /kick <player> [reason]").color(NamedTextColor.RED));
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.size() == 0) {
            // Not enough arguments
            throw new InvalidCommandArgumentsException();
        }

        // Get the specified player object
        Player player = getPlayer(args.get(0));
        if (player == null) {
            // Player is null
            sender.sendMessage(ChatColor.RED + "Player '" + args.get(0) + "' does not exist or online.");
            return;
        }

        // See if the player is immune
        if (player.hasPermission("almpp.immune") && cfg.getBoolean(ConfigOptions.ENABLE_IMMUNITY)) {
            // Target is immune
            sender.sendMessage(ChatColor.DARK_RED + "Forbidden! " + ChatColor.RED + "You may not kick this player.");
            if (cfg.getBoolean(ConfigOptions.ENABLE_LOGGING))
                Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "'" + sender.getName() + "' was forbidden to punish immune player '" + player.getName() + "'");
            return;
        }

        String[] rawReason = args.skipGetArray(1);
        boolean broadcast = true;

        // See if the silence flag is present
        if (rawReason.length >= 2 && rawReason[rawReason.length - 1].equals("-s")) {
            rawReason[rawReason.length - 1] = null;
            broadcast = false;
        }

        // Obtaining the reason
        String reason = cfg.getString(ConfigOptions.DEFAULT_PUNISH_REASON);
        if (args.size() > 1 && !(StringUtils.join(rawReason, ' ').trim().equals(cfg.getString(ConfigOptions.NO_REASON_ALT))))
            reason = StringUtils.join(rawReason, ' ').replace("\\ ", " ").trim();

        getBanManager().kickPlayer(player, reason, broadcast);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return getListOfOnlinePlayers();

        if (args.length == 2) {
            List<String> tmp = cfg.getStringList(ConfigOptions.COMMON_PUNISH_REASONS);

            if (!cfg.getString(ConfigOptions.NO_REASON_ALT).equals(""))
                tmp.add(0, cfg.getString(ConfigOptions.NO_REASON_ALT));

            return tmp;
        }

        if (args.length > 2)
            return Collections.singletonList("-s");

        return null;
    }
}
