package com.coadon.almpp.almpp.system;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface IComponentGenerator {

    @NotNull Component generateKickMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateAfkKickMessage(final @NotNull String date);

    @NotNull Component generateKickPermBanMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateKickTempBanMessage(final @NotNull String reason, final @NotNull String date, final @NotNull String expires);

    @NotNull Component generateTerminationAnnouncementMessage(final @NotNull String targetName);

    @NotNull Component generateAfkKickAnnouncementMessage(final @NotNull String targetName);
}
