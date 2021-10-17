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

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class ExpireDateCalculator {

    public static Date getExpireDate(final @NotNull String duration) throws MalformedDurationFormatException {
        int seconds = 0;
        String[] tokens = duration.split(",");
        for (String token : tokens) {
            if (token.endsWith("y")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (31_536_000 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("mo")) {
                String slice = token.substring(0, token.length() - 2);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (2_592_000 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("w")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (604_800 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }

            } else if (token.endsWith("d")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (86_400 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("h")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (3_600 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("m")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + (60 * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("s")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    int provided = Integer.parseInt(slice);
                    seconds = seconds + provided;
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else {
                throw new MalformedDurationFormatException("Unidentified time-unit suffix.");
            }
        }
        return new Date(System.currentTimeMillis() + (seconds * 1000L));
    }
}
