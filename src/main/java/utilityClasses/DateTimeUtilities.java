package utilityClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import static methods.FrameworkSubroutine.*;
import static reports.ErrorMessageReport.*;

public class DateTimeUtilities {
    public static String currentDateAndTimeGet(String PatternOfDateTime){
        DateFormat dateFormat = new SimpleDateFormat(PatternOfDateTime);
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String dateTimeStringFormat(String FormatOutputMask, String FormatInputMask, String InputDateTimeString, String OutDateTimeString, String ExtraParam) throws Exception {
        try {
            LocalDateTime dateTimeObject = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter;
            String dateTime = null;

            if (FormatOutputMask.equals("DATETIMESTAMP")) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(CURRENT_GBL_PARAM.getProperty("GblDateTimeFormat").split("~")[0]);
            } else if (FormatOutputMask.equals("TIMESTAMP")) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(CURRENT_GBL_PARAM.getProperty("GblTimeFormat").split("~")[0]);
            } else if (FormatOutputMask.equals("DATESTAMP")) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(CURRENT_GBL_PARAM.getProperty("GblDateFormat").split("~")[0]);
            } else {
                dateTimeFormatter = DateTimeFormatter.ofPattern(FormatOutputMask);
            }

            dateTime = dateTimeObject.format(dateTimeFormatter);

            if(OutDateTimeString != null && dateTime != null){
                if(array.get("TestParams") == null) {
                    array.put("TestParams", new HashMap<>());
                }
                if (array.get("TestParams").containsKey(OutDateTimeString)) {
                    array.get("TestParams").replace(OutDateTimeString, dateTime);
                } else {
                    array.get("TestParams").put(OutDateTimeString, dateTime);
                }
            }

            return dateTime;

        } catch (Exception e) {
            errorMsgArrayInsert(errorStackTracePrint(e), null);
            throw new Exception();
        }
    }
}
