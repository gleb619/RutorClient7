package org.team619.rutor.converter;

import org.team619.rutor.core.Logger;
import org.team619.rutor.model.Caption;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BORIS on 07.08.2016.
 */
public class DefaultConverter {

    protected Logger logger;

    public DefaultConverter(Logger logger) {
        this.logger = logger;
    }

    public String parseString(String input) {
        if (input == null) {
            input = "";
        }
        input = input.replace(Patterns.HTML_SPACE, "");

        return input;
    }

    public String parseStringClear(String input) {
        if (input == null) {
            input = "";
        }
        input = input.replace(Patterns.HTML_SPACE, "")
                .replaceAll("^" + Patterns.ALL_SPACES, "")
                .replaceAll(Patterns.ALL_SPACES + "$", "");

        return input;
    }

    public Integer parseInteger(String input) {
        Integer output = 0;
        if (input == null) {
            input = "";
        }
        input = input.replaceAll(Patterns.ONLY_NUMBERS, "");
        if (input.length() > 0) {
            try {
                output = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                logger.error("ERROR: " + e.getMessage());
            }
        }

        return output;
    }

    public Caption parseCaption(String input) {
        Caption output;
        if (input == null) {
            input = "";
        }

        try {
            Matcher m = Patterns.PATTERN_CAPTION.matcher(input);
            int index = 1;
            if (m.find()) {
                output = new Caption(
                        parseStringClear(m.group(index++))
                        , parseStringClear(m.group(index++))
                        , parseStringClear(m.group(index++))
                );
            } else {
                output = new Caption(parseStringClear(input));
            }
        } catch (Exception e) {
            logger.error(e, "ERROR: ");
            output = new Caption(input);
        }

        return output;
    }

    public String parseTorrentName(String input) {
        String output = "";
        if (input == null) {
            input = "";
        }

        try {
            Matcher m = Patterns.PATTERN_TORRENT_NAME.matcher(input);
            if (m.find()) {
                output = m.group(3);
            } else {
                output = parseStringClear(input);
            }
        } catch (Exception e) {
            logger.error(e, "ERROR: ");
            output = input;
        }

        return output;
    }

    public int parseId(String input) {
        int output = 0;
        if (input == null) {
            input = "";
        }

        Pattern pattern = Pattern.compile(Patterns.ID);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String idInString = matcher.group(1);
            if (idInString != null) {
                try {
                    output = new Integer(idInString.replaceAll(Patterns.ONLY_NUMBERS, ""));
                } catch (NumberFormatException e) {
                    output = -1;
                }
            }
        } else {
            throw new IllegalArgumentException(new StringBuilder("Id couldn't be parsed from input: ")
                    .append(input)
                    .toString());
        }

        return Math.abs(output);
    }

    public String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public String compare(final String text) {
        return text.replaceAll(Patterns.ALL_SPACES, "").toLowerCase();
    }

    private interface Patterns extends Serializable {

        String ID = "[http|https]+:..d.+rutor\\.\\w+.download.(.*)";
        String CAPTION = "(.*)\\((.*)\\)(.*)";
        String TORRENT_NAME = "\\/(.*)\\/(.*)\\/(.*)";
        String ONLY_NUMBERS = "[^0-9.]";
        String HTML_SPACE = "&nbsp;";
        String ALL_SPACES = "\\s+";

        Pattern PATTERN_CAPTION = Pattern.compile(CAPTION);
        Pattern PATTERN_TORRENT_NAME = Pattern.compile(TORRENT_NAME);

    }

}
