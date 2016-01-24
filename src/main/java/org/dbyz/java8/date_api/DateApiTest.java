package org.dbyz.java8.date_api;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

/**
 * java 8 新的日期API
 *
 * @ClassName: TestDateApi
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class DateApiTest {
	@Test
	public void test1() {
		// 东八区
		ZoneId hangzhou = ZoneId.of("Asia/Shanghai");
		System.out.println(hangzhou.getRules());

		System.out.println(System.currentTimeMillis());
		System.out.println(Clock.system(hangzhou).millis());
		System.out.println(Clock.system(ZoneId.of("Asia/Shanghai")).millis());
		// 为毛有时候差几毫秒？

		Clock clock = Clock.system(hangzhou);
		Instant instant = clock.instant();
		Date now = Date.from(instant);
		System.out.println(now);

		LocalTime lt1 = LocalTime.now(hangzhou);
		LocalTime lt2 = LocalTime.now(clock);
		System.out.println(lt1.isAfter(lt2));

		LocalTime berlin = LocalTime.now(ZoneId.of("Europe/Berlin"));
		System.out.println(lt1.isAfter(berlin));
		System.out.println(ChronoUnit.HOURS.between(lt1, berlin));
		System.out.println(ChronoUnit.MINUTES.between(lt1, berlin));

		LocalTime later = LocalTime.of(23, 12, 34);
		System.out.println(later);
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		System.out.println("yesterday is : "+yesterday);
		
		LocalDate independenceDay = LocalDate.of(2817, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println("That feature day is :"+dayOfWeek); // TUESDAY
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		System.out.println(df.parse("2014-12-12 12:12:12"));
		
	}
}
