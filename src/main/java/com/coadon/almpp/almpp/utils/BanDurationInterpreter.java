/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
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

package com.coadon.almpp.almpp.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.regex.Pattern;

public final class BanDurationInterpreter {

    // A pattern used to find any non-digit character(s) in a token.
    private static final Pattern pattern = Pattern.compile("\\D");

    /**
     * Converts a ban duration string into a date object.
     * Used in temporary punish.
     *
     * @param duration the ban duration string
     * @return the converted date object
     * @throws MalformedDurationFormatException if the duration is malformed
     */
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
            } else if (!(pattern.matcher(token).find())) {
                // There is no time-unit suffix, therefore default to second.
                // No need to strip the suffix, since there is none.
                try {
                    int provided = Integer.parseInt(token);
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
