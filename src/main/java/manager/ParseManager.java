package manager;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParseManager {

    public static final String DATE_TIME_FORMAT_STRING = "dd/MM/yyyy HHmm";
    public static final String DATE_TIME_FORMAT_FOR_PRINTING_STRING = "dd MM yyyy HH:mm a";
    public static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING);
    public static DateTimeFormatter dateTimePrintFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_PRINTING_STRING);

    public static void checkValidDateTimeFormat(String dateTimeString) throws DukeException {
        boolean isValidDateTimeFormat;
        try{
            LocalDateTime.parse(dateTimeString, dateTimeformatter);
            isValidDateTimeFormat = true;
        }catch (DateTimeParseException e){
            isValidDateTimeFormat = false;
        }

        if(!isValidDateTimeFormat){
            throw new DukeException("\nWrong format, follow: " + DATE_TIME_FORMAT_FOR_PRINTING_STRING);
        }
    }

    public static String parseDateTimeStringForOutput(String dateTimeString){
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeformatter);
        String formattedDateTimeString = dateTimePrintFormatter.format(dateTime).toString();
        //PrintManager.printNormalMessage("formatted date time: " + formattedDateTimeString);
        return formattedDateTimeString;
    }
}
