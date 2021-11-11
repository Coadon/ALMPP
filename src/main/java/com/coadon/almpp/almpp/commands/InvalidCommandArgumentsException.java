/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft
 * Copyright (C) 2021 Coadon
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

package com.coadon.almpp.almpp.commands;

public class InvalidCommandArgumentsException extends Exception {

    public InvalidCommandArgumentsException() {
        super();
    }

    public InvalidCommandArgumentsException(String message) {
        super(message);
    }

    public InvalidCommandArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandArgumentsException(Throwable cause) {
        super(cause);
    }
}
