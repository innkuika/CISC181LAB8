package pkgCore;

public class cMain {

	public enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}

	static Day day;

	public static void main(String[] args) {
		System.out.println("Hello World");
		day = Day.MONDAY;

		NewSwitch_2();
	}

	public static void NewSwitch_1() {
		switch (day) {
		case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
		case TUESDAY -> System.out.println(7);
		case THURSDAY, SATURDAY -> System.out.println(8);
		case WEDNESDAY -> System.out.println(9);
		}
	}

	public static void NewSwitch_2() {
		int j = switch (day) {
		case MONDAY -> 0;
		case TUESDAY -> 1;
		default -> {
			int k = day.toString().length();
			break k;
		}
		};

		System.out.println(j);
	}

	public static void OldSwitch() {

		switch (day) {
		case MONDAY:
			System.out.println("Mondays are bad.");
			break;

		case FRIDAY:
			System.out.println("Fridays are better.");
			break;

		case SATURDAY:
		case SUNDAY:
			System.out.println("Weekends are best.");
			break;

		default:
			System.out.println("Midweek days are so-so.");
			break;
		}
	}
}
