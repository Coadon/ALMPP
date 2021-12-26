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
import com.google.common.collect.ImmutableList;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Commandbrdcstban extends ALMPPCommand {

    public Commandbrdcstban(ALMPP plugin) {
        super(plugin);
    }

    private static final List<String> AVAILABLE_ARGUMENTS = ImmutableList.of("on", "off");

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() == 0) {
            // No arguments
            sender.sendMessage(plugin.willBroadcastBan() ? "Server WILL broadcast punishments." : "Server WILL NOT broadcast punishments.");
            return;
        }

        if (args.get(0).equals("on")) {
            plugin.setWillBroadcastBan(true);
            sender.sendMessage("Server will broadcast punishments");
        } else if (args.get(0).equals("off")) {
            plugin.setWillBroadcastBan(false);
            sender.sendMessage("Server will no longer broadcast punishments");
        } else throw new InvalidCommandArgumentsException();
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return AVAILABLE_ARGUMENTS;

        return null;
    }
}
