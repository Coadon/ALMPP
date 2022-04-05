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

import com.coadon.almpp.AbstractALMPP;
import com.coadon.almpp.config.ConfigOptions;
import com.coadon.almpp.system.ComponentProvider;
import com.coadon.almpp.config.ConfigHandler;
import com.coadon.almpp.system.BanManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.BanList;
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
    protected final AbstractALMPP plugin;
    protected final Logger logger;
    protected final ConfigHandler cfg;
    private final @Nullable Component usage;

    public ALMPPCommand(AbstractALMPP plugin, @Nullable Component usage) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.cfg = plugin.getConfigHandler();
        this.usage = usage;
    }

    public ALMPPCommand(AbstractALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.cfg = plugin.getConfigHandler();
        this.usage = null;
    }


    @Override
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            run(sender.getServer(), sender, command, label, new Arguments(args));
        } catch (InvalidCommandArgumentsException e) {
            if (usage == null)
                return false;

            sender.sendMessage(usage);
            return true;
        } catch (Throwable e) {
            sender.sendMessage(Component.text("An error occurred while performing this command.").color(NamedTextColor.RED));
            if (cfg.getBoolean(ConfigOptions.DEBUG_MODE))
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

    protected final @Nullable Player getPlayer(final String playerName) {
        return plugin.getServer().getPlayer(playerName);
    }

    protected final BanList getBanList(BanList.Type type) {
        return plugin.getServer().getBanList(type);
    }

    protected final BanList getBanList() {
        return plugin.getServer().getBanList(BanList.Type.NAME);
    }

    protected final @Nullable Player getPlayer(final UUID id) {
        return plugin.getServer().getPlayer(id);
    }

    protected final Collection<? extends Player> getOnlinePlayers() {
        return plugin.getServer().getOnlinePlayers();
    }

    protected final Server getServer() {
        return plugin.getServer();
    }

    protected final World getWorld(String name) {
        return plugin.getServer().getWorld(name);
    }

    protected final List<World> getWorlds() {
        return plugin.getServer().getWorlds();
    }

    protected final BukkitScheduler getBukkitScheduler() {
        return plugin.getServer().getScheduler();
    }

    protected final BanManager getBanManager() {
        return plugin.getBanManager();
    }

    protected final ComponentProvider getComponentProvider() {
        return plugin.getComponentProvider();
    }

    protected final List<String> getListOfOnlinePlayers() {
        return getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
