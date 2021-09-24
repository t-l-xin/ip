package manager;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents ParseManager. Manages parsing of any string in the program.
 */
public class ParseManager {

    private static final String DATE_TIME_FORMAT_STRING = "dd/MM/yyyy HHmm";
    private static final String DATE_TIME_FORMAT_FOR_PRINTING_STRING = "MMM dd yyyy HH:mm a";
    private static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING);
    private static DateTimeFormatter dateTimePrintFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_PRINTING_STRING);

    /**
     * Checks if the date and time string follows the program input format.
     *
     * @param dateTimeString The string that contains date and time.
     * @throws DukeException If the string does not follow the program input format.
     */
    public static void checkValidDateTimeFormat(String dateTimeString) throws DukeException {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeformatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("\nDate time wrong format, follow: " + DATE_TIME_FORMAT_STRING);
        }
    }

    /**
     * Parses date and time string, and reformats string for printing.
     *
     * @param dateTimeString The string that contains date and time.
     * @return A date and time string format for printing.
     */
    public static String parseDateTimeStringForOutput(String dateTimeString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeformatter);
        String formattedDateTimeString = dateTimePrintFormatter.format(dateTime).toString();
        return formattedDateTimeString;
    }


}
