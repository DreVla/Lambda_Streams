/**
vlad
May 8, 2018

*/

package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.MonitoredData;
import view.Gui;

public class Controller {

	private ArrayList<MonitoredData> dataFromFile = new ArrayList<MonitoredData>();
	private Gui gui;

	
	public Controller() {
		gui = new Gui();
		readFile();
		
		gui.viewAllData(e->{
			//readFile();
			//gui.setTextArea("");
			List<String> md = dataFromFile.stream()
					.map(data->data.getAll()) // takes all the data from the file
					.collect(Collectors.toList()); // collects it to a list
			for(Object o: md) {
				gui.textAreaAppend(o.toString() + "\n"); // prints list
			}
		});
		
		gui.countDays(e->{
			//readFile();
			gui.setTextArea("Total days: " +
					(int) dataFromFile.stream()
					.map(day->day.getDay()) // gets the date of the day
					.distinct() // takes the distinct ones
					.count()); // counts them
		});
		
		gui.getActivities(e->{
			//readFile();
			gui.setTextArea("");
			Map<String, Integer> map = new HashMap<String, Integer>(); 
			dataFromFile.stream().forEach(a ->{
				map.computeIfPresent(a.getActivity(), (key,value)->value=value+1); // If the value for the 
				//specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value. 
				map.putIfAbsent(a.getActivity(), 1); //If the specified key is not already associated
				//with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
			});

			for(String key: map.keySet()) {
				gui.textAreaAppend(map.get(key) + "  " + key + "\n");
			}
		});
		
		gui.getEachDayActivity(e->{
			//readFile();
			gui.setTextArea("");
			Map<Integer, Map<String, Integer>> eachDay = new HashMap<Integer, Map<String, Integer>>();
			dataFromFile.stream()
			.forEach(day->eachDay.put(Integer.parseInt(day.getStartTime().split(" ")[0].split("-")[2]), new HashMap<String, Integer>()));
			dataFromFile.stream()
			.forEach(day->{
				eachDay.get(day.getDay()).computeIfPresent(day.getActivity(), (key,value)->value=value+1);
				eachDay.get(day.getDay()).putIfAbsent(day.getActivity(), 1);
			});
	
			for(Integer key: eachDay.keySet()) {
				gui.textAreaAppend(key + "  " + eachDay.get(key) + "\n");
			}
		});
		
		gui.getDuration(e->{
			gui.setTextArea("");
			Map<String, Long> time = new HashMap<String, Long>();
			time = dataFromFile.stream()
					.collect(Collectors.groupingBy(MonitoredData::getActivity, // groups activities
							Collectors.summingLong(MonitoredData::getHours))); // summs the hours of all
			time.entrySet().stream()
				.filter(a->a.getValue()>10)
				.forEach(a->gui.textAreaAppend(a.getValue() + " " + a.getKey() + "\n"));
//			for(String key: time.keySet()) {
//				gui.textAreaAppend(time.get(key) + "  " + key + "\n");
//			}
		});
		
		gui.filterActivities(e->{
			gui.setTextArea("");
			Map<String, Integer> map = new HashMap<String, Integer>();
			dataFromFile.stream().forEach(a ->{
				map.computeIfPresent(a.getActivity(), (key,value)->value=value+1);
				map.putIfAbsent(a.getActivity(), 1);
			});
			// this will take the activities
			Map<String, Integer> time = new HashMap<String, Integer>();
			List<String> activities = new ArrayList<String>();
			dataFromFile.stream()
				.filter(a->a.getMinutes()<5)
				.forEach(a->{
					time.computeIfPresent(a.getActivity(), (key,value)->value=value+1); //increments the value
					time.putIfAbsent(a.getActivity(), 1);
				});
			//time.entrySet().stream().forEach(a->gui.textAreaAppend(a.getKey() + " " + a.getValue() + "\n"));
			time.entrySet().stream()
			.filter(a->a.getValue()>=map.get(a.getKey())*90/100)
			.forEach(a->activities.add(a.getKey()));
			
			activities.stream().forEach(a->gui.textAreaAppend(a + "\n")); // Performs an action for each element of this stream. 
			//This is a terminal operation. 

		});
	}
	
	public void readFile() {
		String fileName = "F:\\Work\\Facultate\\anu2\\sem2\\PT2018\\pt2018_30422_dreghici_popa_vlad_assignment_5\\src\\main\\java\\model\\Activities.txt";
		Object[] linesRead = null;
		try(Stream<String> stream = Files.lines(Paths.get(fileName))){
			//stream.forEach(System.out::println);
			linesRead = stream.toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Object o: linesRead) {
			String l = o.toString();
			String[] s = l.split("		"); // 2 tabs
			MonitoredData monitor = new MonitoredData(s[0], s[1], s[2]);
			//monitor.setActivity(monitor.getActivity().replaceAll("\\s", ""));
			dataFromFile.add(monitor);
		}
		
	}
}
