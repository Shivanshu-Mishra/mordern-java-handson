package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

public class GenericsTest {
	
	@Test
	public void testGenericsCanContainOnlyDeclaredType(){
		List<String> strings=new ArrayList<>();
		List<Object> objects=new ArrayList<>();
		
		//List<String> is not subtype of List<Object>
		
		strings.add("Hello"); //Its possible to add its type
		objects.add(new Locale("Hindi")); //Same reason as above
		objects.add("Hindi");// As string is subtype of objects
		
		Object language="Hindi";
		//strings.add(language); Not possible as List<String> is not subtype of List<Object>		
	}
	
	@Test
	public void testUnboundedWildCards(){
		// A wild card is a type argument that uses a question mark
		
		//Limitation of unbounded Type arguments is that you can read from it but cannot write into it.
		List<?> stuff=new ArrayList<>();
		//stuff.add("This"); Not possible , only method which does not expected any type or read operation
		Assert.assertEquals(0,stuff.size());
		}
	
	@Test
	public void testUnboundedWildCardUse(){
		List<String> strings=new ArrayList<>();
		List<Integer> ints=new ArrayList<>();
		
		strings.add("Hello");strings.add("Namaste");strings.add("ciao");
		ints.add(1);ints.add(69);ints.add(23);
		
		printElement(strings);
		printElement(ints);
	}
	
	private void printElement(List<?> list){
		list.forEach(System.out::println);		
	}

}
