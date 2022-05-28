package methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utilityClasses.DateTimeUtilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static reports.ErrorMessageReport.*;
import static utilityClasses.ExternalFileUtilities.*;

/**
 * Author & Architect: Md. Sayem Sikder
 * QA Automation Engineer & Software Developer
 **/

public class FrameworkSubroutine {
    public static Actions action;
    public static WebDriver driver;
    public static ArrayList<String> TEST_RESULT_REPORT, TEST_DETAIL_LOG, BATCH_RUN_REPORT;
    public static Properties UTILITY_OBJECTS, CURRENT_GBL_PARAM, COMMON_OBJECTS;
    public static Map<String, Map<String, String>> array = new HashMap<>();

    public static String JAR_FILE_PATH, CURRENT_DATE_TIME, LOGIN_PAGE_TAB_NAME, HOME_PAGE_TAB_NAME;
    public static int SCREENSHOT_COUNT;

    public static void frmSubGenericObjectRepositoriesAndParamsLoad() {
        try {
            JAR_FILE_PATH = new File("").getAbsolutePath();
            CURRENT_DATE_TIME = DateTimeUtilities.currentDateAndTimeGet("yyyy-MM-dd hh:mm:ss a").replace(":", " ").replace(" ", "-");
            TEST_DETAIL_LOG = new ArrayList<>();
            TEST_RESULT_REPORT = new ArrayList<>();
            BATCH_RUN_REPORT = new ArrayList<>();
            CURRENT_GBL_PARAM = externalObjectRepositoryGet("GlobalParams");
            COMMON_OBJECTS = externalObjectRepositoryGet("CommonObjects");
            UTILITY_OBJECTS = externalObjectRepositoryGet("UtilityObjects");
            //FrmSubExternalFilesLoad("GlobalParams", CURRENT_GBL_PARAM);
            SCREENSHOT_COUNT = Integer.parseInt(CURRENT_GBL_PARAM.getProperty("GblScreenShotCount").split("~")[0]);
            LOGIN_PAGE_TAB_NAME = COMMON_OBJECTS.getProperty("GuiLoginTab").split("~")[1];
            HOME_PAGE_TAB_NAME = COMMON_OBJECTS.getProperty("GuiAppHomePageTab").split("~")[1];

        } catch (IOException e) {
            errorMsgArrayInsert(errorStackTracePrint(e), null);
        }
    }
}
