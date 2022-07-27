/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021-2022 Coadon
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

package net.coadonpile.almpp.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A utility class that contains methods for interpreting and compiling durations.
 */
public final class DurationUtil {

    private static final int MINUTE = 60;
    private static final int HOUR = MINUTE * 60;
    private static final int DAY = HOUR * 24;
    private static final int WEEK = DAY * 7;
    private static final int MONTH = DAY * 30;
    private static final int QUARTER = MONTH * 3;
    private static final int YEAR = DAY * 365;

    // A pattern used to find any non-digit character(s) in a token.
    // Since 1.3.0, a decimal point is now considered to be a digit.
    private static final Pattern NON_DIGIT_PATTERN = Pattern.compile("[^\\d.]");

    /**
     * An algorithm that converts a ban duration string into a date object.
     * Used in temporary punish.
     *
     * @param duration the ban duration string
     * @return the converted date object
     * @throws MalformedDurationFormatException if the duration is malformed
     */
    public static Date compileExpireDate(final @NotNull String duration) throws MalformedDurationFormatException {
        double seconds = 0;
        String[] tokens = duration.split(",");
        for (String token : tokens) {
            if (token.endsWith("y")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (YEAR * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("q")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (QUARTER * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("mo")) {
                String slice = token.substring(0, token.length() - 2);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (MONTH * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("w")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (WEEK * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }

            } else if (token.endsWith("d")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (DAY * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("h")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (HOUR * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("m")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + (MINUTE * provided);
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (token.endsWith("s")) {
                String slice = token.substring(0, token.length() - 1);
                try {
                    double provided = Double.parseDouble(slice);
                    seconds = seconds + provided;
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else if (!(NON_DIGIT_PATTERN.matcher(token).find())) {
                // There is no time-unit suffix, therefore default to second.
                // No need to strip the suffix, since there is none.
                try {
                    double provided = Double.parseDouble(token);
                    seconds = seconds + provided;
                } catch (NumberFormatException e) {
                    throw new MalformedDurationFormatException(e);
                }
            } else {
                throw new MalformedDurationFormatException("Unidentified time-unit suffix.");
            }
        }
        return new Date(System.currentTimeMillis() + ((long) (seconds * 1000L)));
    }

    /**
     * An algorithm that converts a date to a String of duration in compare to the time when the method is called.
     *
     * @param date the date.
     * @return the duration string.
     */
    public static String compileDurationString(final @NotNull Date date) {
        // The difference between the current time and the date in seconds
        int diff = (int) ((date.getTime() - System.currentTimeMillis()) / 1000);

        int days;
        int hours;
        int minutes;
        int seconds;

        // ============================================================
        // Calculate
        // ============================================================

        // Calculate days
        int[] dyAr = modularDivide(diff, DAY);
        if (dyAr[0] == 0 && dyAr[1] == 0)
            return "this instant"; // No difference
        days = dyAr[0];

        // Calculate hours
        int[] hrAr = modularDivide(dyAr[1], HOUR);
        hours = hrAr[0];

        // Calculate minutes
        int[] miAr = modularDivide(hrAr[1], MINUTE);
        minutes = miAr[0];

        // Consider the remaining as seconds
        seconds = miAr[1];

        // ============================================================
        // Generate the String
        // ============================================================

        List<String> buffer = new ArrayList<>();

        if (days != 0 )
            buffer.add(days + "d");

        if (hours != 0 )
            buffer.add(hours + "h");

        if (minutes != 0 )
            buffer.add(minutes + "m");

        if (seconds != 0 )
            buffer.add(seconds + "s");

        StringBuilder sb = new StringBuilder();

        buffer.forEach(s -> sb.append(s).append(" "));

        // Return
        return sb.toString().trim();
    }

    /**
     * Modular divide 2 numbers.
     *
     * @param dividend the dividend
     * @param divider the divider
     * @return An array of integers. The first one is the quotient, and the second one is the remainder.
     */
    private static int[] modularDivide(int dividend, int divider) {
        int remainder = dividend % divider;
        if (remainder == 0) {
            return new int[] {dividend / divider, 0};
        } else {
            return new int[] {(dividend - remainder) / divider, remainder};
        }
    }
}
