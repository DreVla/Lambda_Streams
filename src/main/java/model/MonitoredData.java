/**
vlad
May 8, 2018

*/

package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class MonitoredData {

	private String startTime;
	private String endTime;
	private String activity;
	
	public MonitoredData(String startTime, String endTime, String activity){
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getAll() {
		return this.startTime + "		" + 
				this.endTime + "		" +
				this.activity;			
	}
	
	public int getDay() {
		return Integer.parseInt(getStartTime().split(" ")[0].split("-")[2]);
	}
	
	public long getHours() {
		long time = 0;		
		try {
			Date start = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(startTime);
			Date end = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(endTime);
			long minutes = end.getTime() - start.getTime();
			time = minutes / (60*60*1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	public long getMinutes() {
		long time = 0;		
		try {
			Date start = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(startTime);
			Date end = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(endTime);
			long minutes = end.getTime() - start.getTime();
			time = minutes / (60 * 1000) % 60;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	
}
