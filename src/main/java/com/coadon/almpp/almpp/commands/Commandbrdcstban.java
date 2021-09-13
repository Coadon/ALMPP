package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.ALMPP;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commandbrdcstban extends PluginCommand {

    public Commandbrdcstban(ALMPP plugin) {
        super(plugin, "brdcstban");
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() > 0) {
            if (args.get(0).equals("on")) {
                plugin.setWillBroadcastBan(true);
                sender.sendMessage("Server will broadcast punishments");
            } else if (args.get(0).equals("off")) {
                plugin.setWillBroadcastBan(false);
                sender.sendMessage("Server will no longer broadcast punishments");
            } else throw new InvalidCommandArgumentsException();
        } else {
            sender.sendMessage("Server will broadcast punishments: " + plugin.willBroadcastBan());
        }
    }
}
