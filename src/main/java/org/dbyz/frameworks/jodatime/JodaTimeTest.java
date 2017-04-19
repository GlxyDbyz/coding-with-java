package org.dbyz.frameworks.jodatime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * JodaTime 时间框架使用的使用
 *
 * @ClassName: JodaTimeTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class JodaTimeTest {
    @Test
    public void test1() {
        // Normal
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.JANUARY, 1, 0, 0, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println(sdf.format(calendar.getTime()));

        // User JodaTime
        DateTime dateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
        System.out.println(
                dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }

    @Test
    public void test2() {
        DateTime dateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
                .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }

    @Test
    public void test3() {
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
                .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        calendar.setTime(dateTime.toDate());
    }
    
    @Test
    public void test4() {
        DateTime dateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
        System.out.println(dateTime.toString("dd-MM-yyyy HH:mm:ss"));
        System.out.println(dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa"));
        System.out.println(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
        System.out.println( dateTime.toString("MM/dd/yyyy HH:mm Z"));
    }
}
