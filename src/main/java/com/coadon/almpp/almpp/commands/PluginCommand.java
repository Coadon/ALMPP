/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.system.IComponentProvider;
import com.coadon.almpp.almpp.system.IPunishmentExecutor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class PluginCommand implements IPluginCommand, CommandExecutor {
    protected final ALMPP plugin;
    protected final Logger logger;
    protected final String cmdLabel;

    public PluginCommand(ALMPP plugin, String commandLabel) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.cmdLabel = commandLabel;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            run(sender.getServer(), sender, command, label, new Arguments(args));
        } catch (InvalidCommandArgumentsException e) {
            return false;
        } catch (Throwable e) {
            showError(sender, e);
            return false;
        }
        return true;
    }

    @Override
    public abstract void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable;

    @Override
    public void showError(@NotNull final CommandSender sender, final Throwable throwable) {
        Component output = Component.text()
                .append(Component.text("An error occurred while performing this command").color(NamedTextColor.RED))
                .append(Component.text(throwable != null ? (throwable.getMessage() != null ? ":\n" + throwable.getMessage() : ".") : ".").color(NamedTextColor.RED))
                .build();
        sender.sendMessage(output);
    }

    public void registerItself() {
        plugin.registerCommand(cmdLabel, this);
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

    protected IPunishmentExecutor getPunisher() {
        return plugin.getPunisher();
    }

    protected IComponentProvider getFormatter() {
        return plugin.getFormatter();
    }

    /**
     * Lists all plugin names.
     *
     * @param server Server instance
     * @return List of plugin names
     */
    protected final List<String> getPlugins(final Server server) {
        final List<String> plugins = new ArrayList<>();
        for (final Plugin p : server.getPluginManager().getPlugins()) {
            plugins.add(p.getName());
        }
        return plugins;
    }
}
