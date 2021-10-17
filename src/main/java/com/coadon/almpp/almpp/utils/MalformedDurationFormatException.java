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

package com.coadon.almpp.almpp.utils;

public class MalformedDurationFormatException extends Exception {

    public MalformedDurationFormatException() {
        super();
    }

    public MalformedDurationFormatException(String message) {
        super(message);
    }

    public MalformedDurationFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedDurationFormatException(Throwable cause) {
        super(cause);
    }
}
