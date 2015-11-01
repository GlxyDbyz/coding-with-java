package org.dbyz.java8.some_userful_interface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.dbyz.java8.functional_interface.Person;
import org.junit.Test;

/**
 * java8 自带的一些Lambda接口，我们可以直接拿来使用(就是实现它们，免去了自己定义的步骤)
 *
 * @ClassName: TestSomePersonfulInterface
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class TestSomeUsefulInterface {
	/**
	 * Predicate 接口（判断）
	 * 
	 * @Title: testPredicate
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testPredicate() {
		/**
		 * 和我们自定义的 Lambda 接口一样，Predicate接口有一个抽象方法 boolean test(T t);
		 * 我们需要去实现它，而其他的 default 的方法则已经实现当工具使用
		 */
		Predicate<String> notEmpty = (s) -> {
			return (s != null && s.trim().length() > 0);
		};
		/**
		 * test方法（是我们实现的）
		 */
		System.out.println(notEmpty.test(null));// false
		System.out.println(notEmpty.test(""));// false
		System.out.println(notEmpty.test(" "));// false
		System.out.println(notEmpty.test("not empty"));// true

		/**
		 * 其他 default 的方法（好吧，我们称它们为工具方法）
		 */

		// 1.取反咯（这些方法返回的都是一个实例哦，这样你就可以链式编程了，是不是很棒）
		System.out.println(notEmpty.negate().test("not empty"));// false
		// 2.有取反当然有 or 和 and 咯
		System.out.println(notEmpty.or(notEmpty).test(""));// false
		System.out.println(notEmpty.and(notEmpty).test(""));// false

		// 额，这样不好理解?好吧我再新实现一个Predicate
		Predicate<String> startWithA = (s) -> {
			return (s != null && s.startsWith("A"));
		};
		// 这样你还是不理解我就没办法了
		System.out.println(notEmpty.or(startWithA).test("A"));// true
		System.out.println(notEmpty.and(startWithA).test("not empty"));// false
		// ctrl + 单击 看看他们的源码吧，真的很简单

		// 你还可以这样使用它们（奥。。。。,头晕了，善用链式编程 ）
		notEmpty.and(startWithA).or(notEmpty.or(startWithA).and(notEmpty))
				.test("XXXXX");
	}

	/**
	 * Supplier接口 (获取对象)
	 * 
	 * @Title: testSupplier
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testSupplier() {
		Supplier<Person> personSupplier = Person::new;
		Person p = personSupplier.get(); // new Person
		System.out.println(p);
	}

	/**
	 * Function接口（连续操作）
	 * 
	 * @Title: testFunction
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testFunction() {
		Function<String, Integer> toInteger = Integer::valueOf;
		toInteger.apply("2015");// Integer 2015

		Function<String, Integer> calculate = toInteger.andThen((p) -> p * p)
				.andThen((p) -> p / 2).compose((p) -> p + 0);
		// compose最先执行
		// "6"->"60"->60->3600->1800
		System.out.println(calculate.apply("6"));
	}

	/**
	 * 单对象操作接口
	 * 
	 * @Title: testConsumer
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testConsumer() {
		Consumer<Person> greeter = (p) -> System.out.println("Hello, "
				+ p.getName());
		greeter.accept(new Person("Dbyz"));
	}

	/**
	 * 比较接口
	 * 
	 * @Title: testComparator
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testComparator() {
		Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(
				p2.getName());
		Person p1 = new Person("Dbyz1");
		Person p2 = new Person("Dbyz2");
		comparator.compare(p1, p2); // -1
		comparator.reversed().compare(p1, p2); // 1
	}

	/**
	 * 容器接口
	 * 
	 * @Title: testOptional
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testOptional() {
		Optional<String> optional = Optional.of("empty");
		optional.isPresent(); // true
		optional.get(); // "empty"
		optional.orElse("full"); // "empty"
		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "e"
	}

	// 这些接口实际上并没有特别的意义，你可以使用他们，也可以不使用他们，
	// 但是如果大家都统一在某些特定的情景下使用他们，我们的代码会变得优雅可读
	// 他们只是一套具有指导意义的规范

	/**
	 * Stream 接口（流式操作）
	 * 
	 * @Title: testStream
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testStream() {
		List<String> list = new ArrayList<>();
		// 打乱了
		list.add("a2");
		list.add("e2");
		list.add("c1");
		list.add("a1");
		list.add("e1");
		list.add("b2");
		list.add("d1_a");
		list.add("c2");
		list.add("d2");
		list.add("b1");
		Stream<String> stream = list.stream();
		// Collection接口默认实现Stream
		System.out.println(stream);
		// java.util.stream.ReferencePipeline$Head@1f8bb67

		// filter 过滤（Predicate 判断）
		stream.filter((p) -> p.startsWith("a")).forEach(System.out::println);

		System.out.println("-----------COUNT-----------");
		System.out.println(list.stream()
				.filter((p) -> p.startsWith("a") || p.startsWith("b")).count());

		System.out.println("-----------STORED-----------");
		// 默认排序
		list.stream().sorted().forEach(System.out::println);

		System.out.println("-----------MAP-----------");
		list.stream().map((p) -> p + "_Maped").sorted((a, b) -> a.compareTo(b))
				.forEach(System.out::println);

		System.out.println("-----------MATCH-----------");
		System.out.println(list.stream().anyMatch((p) -> p.endsWith("a")));// true
		System.out.println(list.stream().allMatch((p) -> p.endsWith("a")));// false
		System.out.println(list.stream().noneMatch((p) -> p.endsWith("b")));// true

		System.out.println("-----------REDUCE-----------");
		Optional<String> reduced = list.stream()// .sorted()
				.reduce((s1, s2) -> s1 + "****" + s2);
		reduced.ifPresent(System.out::println);
	}

	/**
	 * 并行的流操作（效率问题）
	 * 
	 * @Title: testParallelStream
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void testParallelStream() {
		List<String> list = new ArrayList<>();
		// 打乱了
		list.add("a2");
		list.add("e2");
		list.add("c1");
		list.add("a1");
		list.add("e1");
		list.add("b2");
		list.add("d1_a");
		list.add("c2");
		list.add("d2");
		list.add("b1");
		Stream<String> stream = list.parallelStream();
		// Collection接口默认实现Stream
		System.out.println(stream);
		// java.util.stream.ReferencePipeline$Head@1f8bb67

		// filter 过滤（Predicate 判断）
		stream.filter((p) -> p.startsWith("a")).forEach(System.out::println);

		System.out.println("-----------COUNT-----------");
		System.out.println(list.parallelStream()
				.filter((p) -> p.startsWith("a") || p.startsWith("b")).count());

		System.out.println("-----------STORED-----------");
		// 默认排序
		list.parallelStream().sorted().forEach(System.out::println);

		System.out.println("-----------MAP-----------");
		list.parallelStream().map((p) -> p + "_Maped")
				.sorted((a, b) -> a.compareTo(b)).forEach(System.out::println);

		System.out.println("-----------MATCH-----------");
		System.out.println(list.parallelStream().anyMatch(
				(p) -> p.endsWith("a")));// true
		System.out.println(list.parallelStream().allMatch(
				(p) -> p.endsWith("a")));// false
		System.out.println(list.parallelStream().noneMatch(
				(p) -> p.endsWith("b")));// true

		System.out.println("-----------REDUCE-----------");
		Optional<String> reduced = list.parallelStream()// .sorted()
				.reduce((s1, s2) -> s1 + "****" + s2);
		reduced.ifPresent(System.out::println);

		System.out.println("-----------parallelStream-----------");
		int max = 2000000;
		list = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			list.add(uuid.toString());
		}

		long t0 = System.nanoTime();
		long count = list.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println("sequential sort took: " + millis);

		t0 = System.nanoTime();
		count = list.parallelStream().sorted().count();
		System.out.println(count);
		t1 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println("parallel sort took: " + millis);
	}
}