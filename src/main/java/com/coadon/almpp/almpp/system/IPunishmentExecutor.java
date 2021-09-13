package com.coadon.almpp.almpp.system;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IPunishmentExecutor {

    void kickPlayer(final @NotNull Player player, final @NotNull String reason);

    void afkKickPlayer(final @NotNull Player player);

    void permBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source);

    void tempBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source, final @NotNull Date expires);

    void kickAllPlayer(final @NotNull String reason);
}
