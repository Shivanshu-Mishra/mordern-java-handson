package consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Consumer functional interface that accept a value and return nothing.
 * Consumer interface has 2 method.
 * 1. void accept(T t);
 * 2. default Consumer<T> andThen(Consumer<? super T> after);
 *
 */
public class ConsumerTest {
	
	@Test
	public void test001(){
		List<String> words = Arrays.asList("this", "is", "a", "list", "of", "strings");
		System.out.println("Printing lambda expression for consumer");
		words.forEach(word -> System.out.println(word));
		}
	
	@Test
	public void test002(){
		List<String> words = Arrays.asList("this", "is", "a", "list", "of", "strings");
		System.out.println("Print Method reference for consumer");
		words.forEach(System.out::println);	
	}
	
	@Test
	public void testConsumerInOptional(){
		List<String> words = Arrays.asList("this", "is", "a", "list", "of", "strings");
		System.out.println("Consumer optional usage");
		Optional<String> q_word= words.stream().filter(word -> word.startsWith("q")).findFirst();
		q_word.ifPresent(word -> System.out.println("First word in list starting with q "+word));
		
		Optional<String> wordContaining_S= words.stream().filter(word -> word.contains("s")).findFirst();
		wordContaining_S.ifPresent(word -> System.out.println("First word in list containing S --->"+word));
	}
	
	/**
	 * Consumer has following primitive variant.
	 * 1. IntConsumer - void accept(int x)
	 * 2. DoubleConsumer - void accept(double d)
	 * 3. LongConsumer - void accept(Long l) 
	 */
	@Test
	public void testConsumerPrimitiveVariants(){
		List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("Even number in list of number till 10");
		numbers.stream().filter(num -> num%2==0).forEach(System.out::println);
	}
	
	/**
	 * BiConsumer is a variant of Consumer Interface that can accept 2 argument of different type.
	 * void accept(T t,U u)
	 * 
	 * Its has following primitive variants.
	 * 1. ObjIntConsumer - void accept(T t,int i)
	 * 2. ObjLongConsumer - void accept(T t,long l)
	 * 3. ObjDoubleConsumer - void accept(T t,double d)
	 * 
	 */
	@Test
	public void testBiConsumer(){
		Map<Integer,String> employees=new HashMap<>();
		employees.put(1, "Akriti");
		employees.put(2, "Sunita");
		employees.forEach((id,name) -> System.out.println("Employee id:"+id+",name:"+name));		
	}
}
