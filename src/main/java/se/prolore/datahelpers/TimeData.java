package se.prolore.datahelpers;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class TimeData {

    public String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        // Format the date according to something resembling of ISO standard.
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'hh_mm_ss");
        return formater.format(calendar.getTime());
    }

    public String timeTodayRandom(int minHour, int maxHour) {
        int randomHour = ThreadLocalRandom.current().nextInt(minHour, maxHour + 1);

        return ZonedDateTime.now().plusHours(randomHour).withMinute(0).withSecond(0).format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
    }

    public String nowPlusHour(int addHour) {
        return ZonedDateTime.now().plusHours(addHour).withMinute(0).withSecond(0).format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
    }
}
