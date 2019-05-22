package defaultAndStatic;

public class Woman implements Human {
	private String name;
	private int age;
	
	public Woman(String name,int age){
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
