package defaultAndStatic;

import org.junit.Assert;
import org.junit.Test;

public class HumanTest {

	@Test
	public void testDefaultMethod() {
		Human boy = new Man("Sachin", 32);
		Human girl = new Man("Savitri", 29);
		Assert.assertEquals(boy.getAgeType(),"Middle Age");
		Assert.assertEquals(girl.getAgeType(),"Adulthood");
	}
	
	@Test
	public void testStaticMethod(){
		Human ram=new Man("Ram",34);
		Human mohan=new Man("Mohan",74);	
		Assert.assertEquals(ram, Human.getYounger(mohan,ram));
	}

}
