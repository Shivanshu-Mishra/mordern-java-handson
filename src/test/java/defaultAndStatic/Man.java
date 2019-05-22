package defaultAndStatic;

public class Man implements Human {
	private String name;
	private int age;
	
	public Man(String name,int age){
		this.name=name;
		this.age=age;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getAge() {
		return this.age;
	}

}
