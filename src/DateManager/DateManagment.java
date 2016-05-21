package DateManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangjian on 16-5-20.
 */
public class DateManagment {
    public static String Convert1970ToStdFormat(String timeFrom1970) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        long time_1970 = cal.getTimeInMillis();
        long time_now=time_1970+Long.decode(timeFrom1970)*1000;
        String result =  dateFormat.format(time_now);
        return result;
    }

}
