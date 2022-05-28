package reports;

import java.io.PrintWriter;
import java.io.StringWriter;

import static methods.FrameworkSubroutine.*;
import static utilityClasses.DateTimeUtilities.*;

public class ErrorMessageReport {

    private static String message, CURRENT_TEST_STEP = CURRENT_GBL_PARAM.get("GblCurrentTestStepTag").toString();

    public static boolean errorMsgArrayInsert(String ErrorMessage, String ExtraParam) {
        try {
            if (ErrorMessage.equals("DEFAULT")) {
                message = "Error Message:\t" + frmSubGetMethodName(0) + ":: " + CURRENT_GBL_PARAM.getProperty("GblFrmErrorMessage").split("~")[0] + ".\n";
            } else {
                message = "Error Message:\t" + frmSubGetMethodName(0) + ":: " + ErrorMessage + ".\n";
            }
            //Add message to detail log array
            TEST_DETAIL_LOG.add(dateTimeStringFormat("TIMESTAMP", null, null, null, null) + " " + message);

            if(message.toLowerCase().contains("Any common system error indicating message") || message.toLowerCase().contains("org.openqa.selenium.support")){
                CURRENT_GBL_PARAM.setProperty("GblSystemSpecificError", "TRUE");
                message = "Error Message:\t" + frmSubGetMethodName(0) + ":: " +"Object specific system error occurred! \n";
            } else {
                CURRENT_GBL_PARAM.setProperty("GblSystemSpecificError", "FALSE");
            }
            //Add message to report log array
            TEST_RESULT_REPORT.add(CURRENT_TEST_STEP + ": " + message);
            message = "";
            return true;
        } catch (Exception exception) {
            System.out.println("errorMsgArrayInsert method got an exception! The exception message is: " + exception);
            return false;
        }
    }

    public static String errorStackTracePrint(Exception exception) {
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
