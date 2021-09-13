package com.coadon.almpp.almpp.listeners;

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.system.IComponentGenerator;
import com.coadon.almpp.almpp.system.IPunishmentExecutor;
import org.bukkit.BanList;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class PluginEventListener implements IPluginEventListener, Listener {
    protected final ALMPP plugin;
    protected final Logger logger;

    public PluginEventListener(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
    }

    public void registerItself() {
        plugin.registerListeners(this);
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

    protected IComponentGenerator getFormatter() {
        return plugin.getFormatter();
    }

    /**
     * Get the server ban list.
     *
     * @param type The ban list type.
     * @return The server ban list.
     */
    protected BanList getBanList(BanList.Type type) {
        return plugin.getServer().getBanList(type);
    }

    /**
     * Get the name type server ban list.
     *
     * @return The name type server ban list.
     */
    protected BanList getBanList() {
        return plugin.getServer().getBanList(BanList.Type.NAME);
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
