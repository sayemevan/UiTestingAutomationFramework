package utilityClasses;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static reports.ErrorMessageReport.errorMsgArrayInsert;
import static reports.ErrorMessageReport.errorStackTracePrint;

public class ExternalFileUtilities {
    //Object repository property file load functionality
    public static Properties externalObjectRepositoryGet(String propertyFileName) throws IOException {
        try {
            InputStream stream = null;
            stream = ClassLoader.getSystemResourceAsStream(propertyFileName + ".properties");
            Properties properties = new LinkedProperties();
            properties.load(stream);
            return properties;

        } catch (Exception e) {
            errorMsgArrayInsert(errorStackTracePrint(e), null);
            throw new IOException(e);
        }
    }
}
