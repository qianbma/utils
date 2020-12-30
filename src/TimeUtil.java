import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class TimeUtil {

    /**
     * 转化为太平洋时间时间戳
     *
     * @param date
     * @param format
     * @return
     */
    public static long convertToPSTStamp(String date, String format) {
        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        return parse.toInstant(ZoneOffset.ofHours(-8)).getEpochSecond();
    }

    /**
     * 转化为北京时间时间戳
     *
     * @param date
     * @param format
     * @return
     */
    public static long convertToBJSStamp(String date, String format) {
        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        return parse.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }

    /**
     * 转化为任何时间时间戳，eg: 北京时间offset=8
     *
     * @param date
     * @param format
     * @param offset
     * @return
     */
    public static long convertToStamp(String date, String format, int offset) {
        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        return parse.toInstant(ZoneOffset.ofHours(offset)).getEpochSecond();
    }

    /**
     * 转化为太平洋时间
     *
     * @param second
     * @param format
     * @return
     */
    public static String convertToPSTDate2(long second, String format) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), TimeZone.getTimeZone("PST").toZoneId()).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转化为太平洋时间
     *
     * @param second
     * @param format
     * @return
     */
    public static String convertToPSTDate(long second, String format) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(-8);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 转化为北京时间
     *
     * @param second
     * @param format
     * @return
     */
    public static String convertToBJSDate(long second, String format) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 转化为任何时区时间，eg: 北京时间offset=8
     *
     * @param second
     * @param format
     * @param offset
     * @return
     */
    public static String convertToDate(long second, String format, int offset) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(offset);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 在给定的当前时间戳基础上加几天
     *
     * @param second
     * @param format
     * @param offset
     * @param plusDays
     * @return
     */
    public static long plusDays(long second, String format, int offset, int plusDays) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(offset);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        String date = localDateTime.plusDays(plusDays).format(dateTimeFormatter);
        return convertToStamp(date, format, offset);
    }

    public static void main(String[] args) {
        String format = "yyyy-MM-dd HH:mm:ss";
        long epochSecond = Instant.now().getEpochSecond();
        String date1 = TimeUtil.convertToPSTDate(epochSecond, format);
        String date2 = TimeUtil.convertToBJSDate(epochSecond, format);
        String date3 = TimeUtil.convertToDate(epochSecond, format, -8);
        String date4 = TimeUtil.convertToDate(epochSecond, format, 8);
        long newSecond1 = TimeUtil.convertToPSTStamp(date1, format);
        long newSecond2 = TimeUtil.convertToBJSStamp(date2, format);
        long newSecond3 = TimeUtil.convertToStamp(date3, format, -8);
        long newSecond4 = TimeUtil.convertToStamp(date4, format, 8);

        long newSecond5 = TimeUtil.plusDays(epochSecond, format, 8, 3);
        String date5 = TimeUtil.convertToDate(newSecond5, format, 8);
    }
}
