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
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Commandkickall extends ALMPPCommand {

    public Commandkickall(ALMPP plugin) {
        super(plugin, Component.text("Usage: /kickall [reason]").color(NamedTextColor.RED));
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Exception {
        // Obtaining the reason
        String reason = cfg.getString(ConfigOptions.DEFAULT_PUNISH_REASON);
        if (args.size() > 0 && !(args.getCombined().equals(cfg.getString(ConfigOptions.NO_REASON_ALT))))
            reason = args.getCombined();

        getBanManager().kickAllPlayer(reason);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        List<String> tmp = cfg.getStringList(ConfigOptions.COMMON_PUNISH_REASONS);

        if (!cfg.getString(ConfigOptions.NO_REASON_ALT).equals(""))
            tmp.add(0, cfg.getString(ConfigOptions.NO_REASON_ALT));

        return tmp;
    }
}
