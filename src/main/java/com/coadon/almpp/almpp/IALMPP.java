/*
 * ALMPP - a bukkit plugin
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.coadon.almpp.almpp;

import com.coadon.almpp.almpp.system.IComponentGenerator;
import com.coadon.almpp.almpp.system.IPunishmentExecutor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public interface IALMPP {

    void registerCommand(final String commandLabel, final CommandExecutor commandExe);

    void registerListeners(final Listener listener);

    IComponentGenerator getFormatter();

    IPunishmentExecutor getPunisher();

    boolean willBroadcastBan();

    void setWillBroadcastBan(boolean bool);
}
