package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.ALMPP;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commandkickall extends PluginCommand {

    public Commandkickall(ALMPP plugin) {
        super(plugin, "kickall");
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) {
        if (args.length() > 0) {
            getPunisher().kickAllPlayer(args.getCombined());
        } else {
            getPunisher().kickAllPlayer(plugin.DEFAULT_PUNISH_REASON);
        }
    }
}
