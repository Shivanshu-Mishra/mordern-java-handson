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

}
