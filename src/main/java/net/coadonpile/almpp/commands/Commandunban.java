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

package net.coadonpile.almpp.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.BanEntry;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class Commandunban extends ALMPPCommand {

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.size() < 1)
            throw new InvalidCommandArgumentsException();

        // Iterate over the arguments, unbanning each valid element.
        args.forEach(name -> {
            // Check is the specified player is unbanned.
            if (!getBanManager().isNameBanned(name)) {
                sender.sendMessage(ChatColor.RED + "Specified player name '" + name + "' does not exist or is not banned.");
                return;
            }

            getBanManager().pardonName(name);
            sender.sendMessage(ChatColor.GREEN + "Specified player '" + name + "' is now unbanned.");
        });
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        return getBanList().getBanEntries().stream().map(BanEntry::getTarget).collect(Collectors.toList());
    }

    @Override
    protected @Nullable Component getUsage() {
        return Component.text("Usage: /unban <player...>").color(NamedTextColor.RED);
    }
}
