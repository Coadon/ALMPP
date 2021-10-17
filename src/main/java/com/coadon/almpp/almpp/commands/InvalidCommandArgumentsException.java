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
