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
import com.coadon.almpp.almpp.system.ComponentProvider;
import com.coadon.almpp.almpp.config.ConfigHandler;
import com.coadon.almpp.almpp.system.PunishmentExecutor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class ALMPPCommand implements IALMPPCommand, TabExecutor {
    protected final ALMPP plugin;
    protected final Logger logger;
    protected final ConfigHandler cfg;

    public ALMPPCommand(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.cfg = plugin.getConfigHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            run(sender.getServer(), sender, command, label, new Arguments(args));
        } catch (InvalidCommandArgumentsException e) {
            return false;
        } catch (Throwable e) {
            sender.sendMessage(Component.text("An error occurred while performing this command.").color(NamedTextColor.RED));
            if (cfg.getDebugMode())
                e.printStackTrace();
        }
        return true;
    }

    @Override
    public abstract void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable;

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

    protected @Nullable Player getPlayer(final String playerName) {
        return plugin.getServer().getPlayer(playerName);
    }

    protected @Nullable Player getPlayer(final UUID id) {
        return plugin.getServer().getPlayer(id);
    }

    protected Collection<? extends Player> getOnlinePlayers() {
        return plugin.getServer().getOnlinePlayers();
    }

    protected Server getServer() {
        return plugin.getServer();
    }

    protected World getWorld(String name) {
        return plugin.getServer().getWorld(name);
    }

    protected List<World> getWorlds() {
        return plugin.getServer().getWorlds();
    }

    protected BukkitScheduler getBukkitScheduler() {
        return plugin.getServer().getScheduler();
    }

    protected PunishmentExecutor getPunisher() {
        return plugin.getPunisher();
    }

    protected ComponentProvider getFormatter() {
        return plugin.getFormatter();
    }

    protected List<String> getListOfOnlinePlayers() {
        return getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
