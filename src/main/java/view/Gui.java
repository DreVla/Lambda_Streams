/**
vlad
May 8, 2018

*/

package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JFrame{

	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scroll;
	private JButton seeAllData;
	private JButton countDays;
	private JButton getActivities;
	private JButton getEachDayActivity;
	private JButton getDuration;
	private JButton filterActivities;

	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100,100,800,600); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("App");
		textArea = new JTextArea();
		textArea.setEditable(false);
		scroll = new JScrollPane(textArea);
		scroll.setBounds(10,200,760,350);
		panel.add(scroll);
		seeAllData = new JButton("See all Data");
		seeAllData.setBounds(10, 100, 120, 30);
		panel.add(seeAllData);
		countDays = new JButton("Count days");
		countDays.setBounds(140, 100, 120, 30);
		panel.add(countDays);
		getActivities = new JButton("Count activities");
		getActivities.setBounds(270, 100, 120, 30);
		panel.add(getActivities);
		getEachDayActivity = new JButton("Count each day activity");
		getEachDayActivity.setBounds(400, 100, 120, 30);
		panel.add(getEachDayActivity);
		getDuration = new JButton("Get duration");
		getDuration.setBounds(530, 100, 120, 30);
		panel.add(getDuration);
		filterActivities = new JButton("Filter");
		filterActivities.setBounds(660, 100, 120, 30);
		panel.add(filterActivities);


		this.add(panel);
		this.setVisible(true);
	}
	
	public void viewAllData(ActionListener e) {
		seeAllData.addActionListener(e);
	}
	
	public void countDays(ActionListener e) {
		countDays.addActionListener(e);
	}
	
	public void getActivities(ActionListener e) {
		getActivities.addActionListener(e);
	}
	
	public void getEachDayActivity(ActionListener e) {
		getEachDayActivity.addActionListener(e);
	}
	
	public void getDuration(ActionListener e) {
		getDuration.addActionListener(e);
	}
	
	public void filterActivities(ActionListener e) {
		filterActivities.addActionListener(e);
	}
	
	public void setTextArea(String text) {
		textArea.setText(text);
	}
	
	public void textAreaAppend(String text) {
		
		textArea.append(text);
		//textArea.revalidate();
	}
}
