package android.gabriel_izzo_c196_scheduler.Utility;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}