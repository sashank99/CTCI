package moderate;

import java.util.Random;

/**
 * Given a list of people with their birth and death years, implement a method
 * to compute the year with the most number of people alive. You may assume that
 * all the people were born between 1900 and 2000(inclusive). If a person was
 * alive during any portion of that year, they should be included in that year’s
 * count. For example, Person(birth=1908, death=1909) is included in the counts
 * for both 1908 and 1909
 */
public class LivingPeople {

	public int getMaxPeopleAlive(Person[] persons, int minYr, int maxYr) {
		int[] deltaArr = new int[maxYr - minYr + 1];
		buildDeltaArray(persons, deltaArr, minYr);
		int maxAliveYr = getMaxAliveYear(deltaArr);
		return minYr + maxAliveYr;
	}

	public void buildDeltaArray(Person[] persons, int[] deltaArr, int minYr) {
		for (Person person : persons) {
			deltaArr[person.birth - minYr]++;
			deltaArr[person.death - minYr + 1]--;
		}
	}

	public int getMaxAliveYear(int[] deltaArr) {

		int currentlyAlive = 0;
		int maxAlive = 0;
		int maxAliveYear = 0;
		for (int i = 0; i < deltaArr.length; i++) {
			currentlyAlive += deltaArr[i];
			if (currentlyAlive > maxAlive) {
				maxAlive = currentlyAlive;
				maxAliveYear = i;
			}
		}
		return maxAliveYear;
	}
	
	public static void main(String[] args) {
		int n = 100;
		int first = 1900;
		int last = 2000;
		
		Random random = new Random();
		Person[] people = new Person[n + 1];
		for (int i = 0; i < n; i++) {
			int birth = first + random.nextInt(last - first);
			int death = birth + random.nextInt(last - birth);
			people[i] = new Person(birth, death);
			System.out.println(birth + ", " + death);
		}
		people[n] = new Person(first, first);
		
		int year = new LivingPeople().getMaxPeopleAlive(people, first, last);
		System.out.println( year);
	}
}

class Person {
	public int birth;
	public int death;

	public Person(int birth, int death) {
		this.birth = birth;
		this.death = death;
	}
}
