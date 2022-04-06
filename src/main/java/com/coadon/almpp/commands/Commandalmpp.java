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
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Commandalmpp extends ALMPPCommand {

    public Commandalmpp(ALMPPInterface plugin) {
        super(plugin);
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) {
        sender.sendMessage(Component.text("")
                .append(Component.text("ALMPP ").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                .append(Component.text("v" + plugin.getVersion() + "\n").color(NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.BOLD))
                .append(Component.text("Copyright (C) 2020-" + LocalDate.now().getYear() + " Coadon.").color(NamedTextColor.GRAY)));
    }
}
