/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2022 Coadon
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

package com.coadon.almpp;

import com.coadon.almpp.config.ConfigHandler;
import com.coadon.almpp.services.BanManager;
import com.coadon.almpp.services.ComponentProvider;
import org.bukkit.plugin.Plugin;

public interface ALMPPInterface extends Plugin {

    ComponentProvider getComponentProvider();

    BanManager getBanManager();

    ConfigHandler getConfigHandler();

    String getVersion();

    void terminate();
}
