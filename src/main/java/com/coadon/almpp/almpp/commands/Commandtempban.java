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

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.utils.BanDurationInterpreter;
import com.coadon.almpp.almpp.utils.MalformedDurationFormatException;
import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

public class Commandtempban extends ALMPPCommand {

    // Common time durations, for use in tab completion
    private static final List<String> COMMON_DURATIONS = ImmutableList.of("1m", "15m", "1h", "3h", "12h", "1d", "1w", "1mo", "3mo", "6mo", "1y");

    public Commandtempban(ALMPP plugin) {
        super(plugin, Component.text("Usage: /ban <player> <duration> [reason]").color(NamedTextColor.RED));
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() < 2) {
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
        if (player.hasPermission("almpp.immune")) {
            // Target is immune
            sender.sendMessage(ChatColor.DARK_RED + "Forbidden! " + ChatColor.RED + "You may not ban this player.");
            logger.info("'" + sender.getName() + "' was forbidden to punish immune player '" + player.getName() + "'");
            return;
        }

        // Interprets the ban duration
        Date expireDate;
        try {
            expireDate = BanDurationInterpreter.getExpireDate(args.get(1));
        } catch (MalformedDurationFormatException e) {
            // Malformed ban duration
            sender.sendMessage(ChatColor.RED + "Error: Invalid ban duration");
            return;
        }

        // Obtaining the reason
        String reason = cfg.getDefaultPunishReason();
        if (args.length() > 2 && !(args.getCombinedFrom(2).equals(cfg.getNoReasonAlt())))
            reason = args.getCombinedFrom(2);

        getBanManager().tempBanPlayer(player, reason, sender.getName(), expireDate, true);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return getListOfOnlinePlayers();

        if (args.length == 2)
            return COMMON_DURATIONS;

        if (args.length == 3) {
            List<String> tmp = cfg.getCommonPunishReasons();

            if (!cfg.getNoReasonAlt().equals(""))
                tmp.add(0, cfg.getNoReasonAlt());

            return tmp;
        }

        return null;
    }
}
