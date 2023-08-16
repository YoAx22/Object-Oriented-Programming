import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Data {

	private ArrayList<Event> list;

	public static void main(String[] args) throws IOException {

		System.out.println("STARTING");
		// This puts all the data from the file into a list
		Data data = new Data("covid-data.csv");

		PrintWriter output = new PrintWriter("output.txt");

		// See video for information about testing using the display() methods

		// You can use the display method to write your results
		// to a file. This makes it easier to view larger results.
		Set<String> locations = data.allLocations();
//		display(output, locations);
//		display(output, data.allLocations());
//		System.out.println(data.casesByMonth());
//		display(output, data.casesByMonth()); 
//		display(output, data.locationsByContinent());
//		display(output, data.getLocationsInThisContinent("Europe"));
//		display(output, data.deathsByContinent());
//		display(output, data.casesByLocation()); 
//		display(output, data.casesByLocation(11));
//		display(output, data.codeCounts());
//		display(output, data.casesByDate());
//		display(output, data.mostCasesByDate());
//		display(output, data.locationsSortedByCaseCount());
		
//		Set<String> locations = data.allLocations();
//		System.out.println(locations);
//		System.out.println(locations.size());

		output.close();
		System.out.println("DONE");
	}

	// solved in video
	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allLocations() {

		Set<String> result = new TreeSet<>();

		// list contains all events
		// loop through the list grabbing each location
		// and putting it in the set
		for (Event e : list) {
			result.add(e.location);
		}
		return result;
	}

	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allContinents() {
		Set<String> result = new TreeSet<>();

		for (Event e : list) {
			result.add(e.continent);
		}
		return result;
	}

	/*
	 * Returns all the locations in a particular continent. The empty set is
	 * returned if the continent does not have any locations.
	 */
	public Set<String> getLocationsInThisContinent(String continent) {
		Set<String> result = new TreeSet<>();

		for (Event e : list) {
			if (e.continent.equals(continent)) {
				result.add(e.location);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to a continent, where the value is the total number of
	 * deaths in that continent.
	 */
	public Map<String, Integer> deathsByContinent() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event e : list) {
			String continent = e.continent;
			int deaths = e.deaths;

			if (!result.containsKey(continent)) {
				result.put(continent, deaths);
			} else {
				result.put(continent, deaths + result.get(continent));
			}
		}
		return result;
	}

	/*
	 * Returns a map keyed to a location, where the value is the total number of
	 * cases for that location. The map should only contain locations that had at
	 * least 1 case.
	 */
	public Map<String, Integer> casesByLocation() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event e : list) {
			String location = e.location;
			int cases = e.cases;

			if (!result.containsKey(location) && cases > 0) {
				result.put(location, cases);
			} else if (cases > 0) {
				result.put(location, cases + result.get(location));
			}
		}
		return result;
	}

	/*
	 * For a specified month (0 is January, 1 is February, and so on), returns a map
	 * keyed to a location, where the value is the total number of cases for that
	 * location. The map should only contain locations that had at least one case.
	 * If there are no cases for a given month, returns an empty map. NOTE: Date has
	 * a getMonth() method you can use. You may get a warning that getMonth() is a
	 * deprecated method, but that's ok.
	 */
	public Map<String, Integer> casesByLocation(int month) {

		Map<String, Integer> result = new TreeMap<>();

		for (Event e : list) {
			String location = e.location;
			int cases = e.cases;

			if (!result.containsKey(location) && cases > 0) {
				if (e.date.getMonth() == month)
					result.put(location, cases);
			} else if (cases > 0 && e.date.getMonth() == month) {
				result.put(location, cases + result.get(location));
			}
		}
		return result;
	}

	// solved in video
	/*
	 * Returns a map keyed to the continent, where the value for each continent is a
	 * set of all locations in that continent.
	 */
	public Map<String, Set<String>> locationsByContinent() {
		Map<String, Set<String>> result = new TreeMap<>();

		for (Event e : list) {
			String continent = e.continent;
			String location = e.location;

			// if the continent is not already in the map,
			// then add it, with an initially empty set
			if (!result.containsKey(continent)) {
				result.put(continent, new TreeSet<String>());
			}
			// Once I get here, I know the continent is in the map
			// this code grabs the set of location ot the
			// continent and adds the location to it

			Set<String> locs = result.get(continent);
			locs.add(location);
			// one line version
			// result.get(continent).add(location);
		}

		return result;
	}

	/*
	 * Returns a map keyed to a location's 3-letter code, where the value is the
	 * total number of times that code appears in the data.
	 */
	public Map<String, Integer> codeCounts() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event e : list) {
			String code = e.abbreviation;
			int count = 0;

			if (!result.containsKey(code)) {
				result.put(code, count += 1);
			} else {
				result.put(code, count += 1 + result.get(code));
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value is the total number of cases
	 * for that date.
	 */
	public Map<Date, Integer> casesByDate() {
		Map<Date, Integer> result = new TreeMap<>();

		for (Event e : list) {
			Date date = e.date;
			int cases = e.cases;

			if (!result.containsKey(date)) {
				result.put(date, cases);
			} else {
				result.put(date, cases + result.get(date));
			}
		}
		return result;
	}

	/*
	 * Returns a list of the locations in the database, sorted by the total number
	 * of cases reported for that location. The location with the fewest cases
	 * should be first, and the location with the most cases should be last.
	 */
	public List<String> locationsSortedByCaseCount() {

		Map<String, Integer> temp = new TreeMap<>();
		
		for (Event e : list) {
			String location = e.location;
			int cases = e.cases;

			if (!temp.containsKey(location)) {
				temp.put(location, cases);
			} else {
				temp.put(location, cases + temp.get(location));
			}
		}

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(temp.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> arg0, Map.Entry<String, Integer> arg1) {
				return (arg0.getValue()).compareTo(arg1.getValue());
			}
		});
		
		List<String> result = new ArrayList<>();
		
		for (Map.Entry<String, Integer> entry : list) {
			result.add(entry.getKey());
		}
		
		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value for that date is the
	 * location (or locations) that had the highest number of cases on that date.
	 * It's possible that the set will contain only one location. But if there are
	 * ties, the set will contain more than one location.
	 */
	public Map<Date, Set<String>> mostCasesByDate() {
		Map<Date, Set<String>> result = new TreeMap<>();

		Map<Date, Integer> temp = new TreeMap<>();
		for (Event e : list) {
			Date date = e.date;
			int cases = e.cases;

			if (!temp.containsKey(date)) {
				temp.put(date, cases);
			} else {
				temp.put(date, Math.max(cases, temp.get(date)));
			}
		}

		for (Event ev : list) {
			Set<String> locs = new TreeSet<String>();
			String location = ev.location;
			int cases = ev.cases;

			if (temp.get(ev.date).equals(cases)) { // find events where the max numbers of cases matches

				if (result.containsKey(ev.date)) {
					result.get(ev.date).add(location);
				} else {
					locs.add(location);
					result.put(ev.date, locs);
				}
			}
		}
		return result;
	}

	// Solved in video
	/*
	 * Returns a map keyed to a month (0=January, 1=February, and so on), where the
	 * value is the number of reported cases for that month. Only include months
	 * that are listed in the data. NOTE: Date has a getMonth() method you can use.
	 * You may get a warning that getMonth() is a deprecated method, but that's ok.
	 */
	public Map<Integer, Integer> casesByMonth() {
		Map<Integer, Integer> result = new TreeMap<>();

		for (Event e : list) {
			int month = e.date.getMonth();
			int cases = e.cases;

			// Handle two cases: where the month is alradt in map,
			// where it's not

			if (!result.containsKey(month)) { // case where month not in map
				result.put(month, cases);
			} else {// case where month is in map, get() data and add cases
				result.put(month, cases + result.get(month));
			}
		}

		return result;
	}

	/********** DON'T MODIFY ANY OF THE CODE BELOW ************/

	// Reads file data into an ArrayList of Event objects.
	// Don't modify this code
	public Data(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		list = new ArrayList<Event>();
		in.nextLine();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] line = input.split(",");
			String abbreviation = line[0];
			String continent = line[1];
			String location = line[2];
			Date date = simpleDateFormat.parse(line[3], new ParsePosition(0));
			int cases = Integer.parseInt(line[4]);
			int deaths = Integer.parseInt(line[5]);
			Event d = new Event(abbreviation, continent, location, date, cases, deaths);
			list.add(d);
		}

		in.close();
	}

	/*
	 * Writes a collection (list, set) to a specified file. DON'T CHANGE THIS.
	 */
	public static <T> void display(PrintWriter output, Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}

	/*
	 * Writes a map to a specified file DON'T CHANGE THIS.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}

		for (K key : items.keySet()) {
			output.print(key + "---------->");
			Object o = items.get(key);
			if (o instanceof Collection) {
				output.println();
				display(output, (Collection<Object>) items.get(key));
			} else {
				output.println(items.get(key));
			}
		}
	}

	/*
	 * Inner class for organizing event information. DON'T CHANGE THIS.
	 */
	private class Event {
		private String abbreviation;
		private String continent;
		private String location;
		private Date date;
		private int cases;
		private int deaths;

		private Event(String abbreviation, String continent, String location, Date date, int cases, int deaths) {
			this.abbreviation = abbreviation;
			this.continent = continent;
			this.location = location;
			this.date = date;
			this.cases = cases;
			this.deaths = deaths;
		}

		@Override
		public String toString() {
			return "[" + abbreviation + "," + continent + "," + location + "," + date + "," + cases + "," + deaths
					+ "]";
		}

	}

}
