/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021  Coadon
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
import com.coadon.almpp.almpp.utils.ExpireDateCalculator;
import com.coadon.almpp.almpp.utils.MalformedDurationFormatException;
import com.google.common.collect.ImmutableList;
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

    public Commandtempban(ALMPP plugin) {
        super(plugin);
    }

    // Common time durations, for use in tab completion
    private static final List<String> COMMON_DURATIONS = ImmutableList.of("1m", "15m", "1h", "3h", "12h", "1d", "1w", "1mo", "3mo", "6mo", "1y");

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() == 2) {
            Player player = getPlayer(args.get(0));
            if (!(player == null)) {
                Date expireDate;
                try {
                    expireDate = ExpireDateCalculator.getExpireDate(args.get(1));
                } catch (MalformedDurationFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Incorrect Argument: Invalid expiration date");
                    return;
                }
                getPunisher().tempBanPlayer(player, plugin.DEFAULT_PUNISH_REASON, sender.getName(), expireDate);
            } else {
                sender.sendMessage(ChatColor.RED + "Player does not exist or online.");
            }
        } else if (args.length() > 2) {
            Player player = getPlayer(args.get(0));
            if (!(player == null)) {
                Date expireDate;
                try {
                    expireDate = ExpireDateCalculator.getExpireDate(args.get(1));
                } catch (MalformedDurationFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Incorrect Argument: Invalid expiration date");
                    return;
                }
                getPunisher().tempBanPlayer(player, args.getCombinedFrom(2), sender.getName(), expireDate);
            } else {
                sender.sendMessage(ChatColor.RED + "Player does not exist or online.");
            }
        } else {
            throw new InvalidCommandArgumentsException();
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return getListOfOnlinePlayers();

        if (args.length == 2)
            return COMMON_DURATIONS;

        return null;
    }
}
