package com.coadon.almpp.almpp.commands;

import com.coadon.almpp.almpp.ALMPP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commandwarn extends PluginCommand {

    public Commandwarn(ALMPP plugin) {
        super(plugin, "warn");
    }

    @Override
    public void run(@NotNull Server server, @NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull Arguments args) throws Throwable {
        if (args.length() > 1) {
            Player player = getPlayer(args.get(0));
            if (!(player == null)) {
                player.sendMessage(Component.text("[Warning!] ").color(NamedTextColor.RED).append(Component.text(args.getCombinedFrom(1)).color(NamedTextColor.GOLD)));
            } else {
                sender.sendMessage(ChatColor.RED + "Player does not exist or online.");
            }
        } else {
            throw new InvalidCommandArgumentsException();
        }
    }
}
