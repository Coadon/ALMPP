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

import com.coadon.almpp.ALMPPInterface;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commandalmpp extends ALMPPCommand {

    public Commandalmpp(ALMPPInterface plugin) {
        super(plugin, Component.text("Usage: /almpp [help|version|reload|terminate]\n" +
                "\t/almpp help : display this help\n" +
                "\t/almpp version : display plugin version\n" +
                "\t/almpp reload : reload config\n")
                .color(NamedTextColor.GOLD));
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Exception {
        if (args.size() == 0 || args.get(0).equals("help")) {
            showVersion(sender);
            showUsage();
        }

        switch (args.get(0)) {
            case "version":
                showVersion(sender);
                break;
            case "reload":
                reloadPlugin(sender);
                break;
            default:
                showUsage();
        }
    }

    private void showUsage() throws InvalidCommandArgumentsException {
        throw new InvalidCommandArgumentsException();
    }

    private void showVersion(@NotNull CommandSender sender) {
        sender.sendMessage(Component.text("")
                .append(Component.text("ALMPP ").color(NamedTextColor.GOLD))
                .append(Component.text("version ").color(NamedTextColor.GRAY))
                .append(Component.text(plugin.getVersion()).color(NamedTextColor.RED)));
    }

    private void reloadPlugin(@NotNull CommandSender sender) {
        sender.sendMessage(Component.text("Reloading config...").color(NamedTextColor.GOLD));
        plugin.reload();
        sender.sendMessage(Component.text("Config reloaded!").color(NamedTextColor.GREEN));
    }
}
