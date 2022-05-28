package reports;

import java.io.PrintWriter;
import java.io.StringWriter;

import static methods.FrameworkSubroutine.*;
import static utilityClasses.DateTimeUtilities.*;

public class ErrorMessageReport {

    private static String message;

    public static boolean errorMsgArrayInsert(String ErrorMessage, String ExtraParam) {
        try {
            if (ErrorMessage.equals("DEFAULT")) {
                message = "Error Message:\t" + Thread.currentThread().getStackTrace()[2].getMethodName() + ":: " + CURRENT_GBL_PARAM.getProperty("GblFrmErrorMessage").split("~")[0] + ".\n";
            } else if(Thread.currentThread().getStackTrace()[2].getMethodName().equalsIgnoreCase("UtlProcessCpuUsageStabilitySynchronize")){
                message = "CPU Usage Message:\t" + Thread.currentThread().getStackTrace()[2].getMethodName() + ":: " + ErrorMessage + ".\n";
            } else {
                if(Thread.currentThread().getStackTrace()[2].getMethodName().equals("main")){
                    message = "Error Message:\t" + "TestEngine" + ":: " + ErrorMessage + ".\n";
                } else {
                    message = "Error Message:\t" + Thread.currentThread().getStackTrace()[2].getMethodName() + ":: " + ErrorMessage + ".\n";
                }
            }

            TEST_DETAIL_LOG.add(dateTimeStringFormat("TIMESTAMP", null, null, null, null) + " " + message);

            if(message.toLowerCase().contains("com.mislbd.ababilng.testautomation") || message.toLowerCase().contains("org.openqa.selenium.support")){
                CURRENT_GBL_PARAM.setProperty("GblSystemSpecificError", "TRUE");
                message = "Error Message:\t" + Thread.currentThread().getStackTrace()[2].getMethodName() + ":: " +"Object specific system error occurred! \n";
            } else {
                CURRENT_GBL_PARAM.setProperty("GblSystemSpecificError", "FALSE");
            }
            TEST_RESULT_REPORT.add(CURRENT_GBL_PARAM.get("GblCurrentTestStepTag") + ": " + message);
            message = "";
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static String errorStackTracePrint(Exception exception) {
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
