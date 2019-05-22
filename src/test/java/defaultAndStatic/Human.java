package defaultAndStatic;

public interface Human {

	String getName();

	int getAge();
	
	static Human getYounger(Human human1,Human human2){
		return human1.getAge()<human2.getAge()?human1:human2;
	}

	default String getAgeType() {
		if (getAge() < 1)
			return "Not born";
		else if (getAge() == 1)
			return "Infant";
		else if (getAge() >= 2 && getAge() < 4)
			return "Toddler";
		else if (getAge() >= 4 && getAge() < 9)
			return "Childhood";
		else if (getAge() >= 9 && getAge() < 14)
			return "Puberty";
		else if (getAge() >= 14 && getAge() < 19)
			return "Older adolescence";
		else if (getAge() >= 19 && getAge() < 31)
			return "Adulthood";
		else if (getAge() >= 31 && getAge() < 50)
			return "Middle Age";
		else if (getAge() > 50)
			return "Senior Age";
		else
			return "Dead";
	}
}
