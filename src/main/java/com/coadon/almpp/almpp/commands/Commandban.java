package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.ALMPP;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commandban extends PluginCommand {

    public Commandban(ALMPP plugin) {
        super(plugin, "ban");
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() == 1) {
            Player player = getPlayer(args.get(0));
            if (!(player == null)) {
                getPunisher().permBanPlayer(player, plugin.DEFAULT_PUNISH_REASON, sender.getName());
            } else {
                sender.sendMessage(ChatColor.RED + "Player does not exist or online.");
            }
        } else if (args.length() > 1) {
            Player player = getPlayer(args.get(0));
            if (!(player == null)) {
                getPunisher().permBanPlayer(
                        player, args.getCombinedFrom(1), sender.getName());
            } else {
                sender.sendMessage(ChatColor.RED + "Player does not exist or online.");
            }
        } else {
            throw new InvalidCommandArgumentsException();
        }
    }
}
