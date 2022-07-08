/*Jun Cheng Chua
 * CSCI 185 M05
 * Professor Wenjia Li
 * 4/25/2020
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
public class RPS {
	private final static ArrayList<String> human = new ArrayList<String>();
	private final static ArrayList<String> computer = new ArrayList<String>();
	private final static ArrayList<String> outcome = new ArrayList<String>();
	private final static ArrayList<Integer> test = new ArrayList<Integer>();
	public static void main(String[] args)
	{
		
		JFrame start = new JFrame("Rock Paper Scissors");
		
		JPanel choices = new JPanel();
		JPanel one = new JPanel(new BorderLayout());
		JPanel two = new JPanel(new BorderLayout());
		JPanel three = new JPanel(new BorderLayout());
		JPanel four = new JPanel(new BorderLayout());
		
		JButton rock = new JButton("Rock");
		JButton paper = new JButton("Paper");
		JButton scissors = new JButton("Scissors");
		JButton finish = new JButton("Click when your done");
		
		
		JLabel label = new JLabel("Pick your best choice");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JTextArea win = new JTextArea();
		
		
		rock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int tester = whoWon(0);
				human.add(" Rock ");
				if(tester == 0)
				{
					win.setText("The two rocks you threw at eachother collided and ultimately accomplished nothing");
					outcome.add(" you tied :/ (lame)");
				}
				else if(tester == 1)
				{
					win.setText("You just broke my new pair of scissors! And uh you won thanks a lot .-.");
					outcome.add(" you won!");
				}
				else if(tester == 2)
				{
					win.setText("Your rock gets covered by a piece of looseleaf! And you losee I guess");
					outcome.add(" you lost :c");
				}

			}
		});
	
		
		paper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int tester = whoWon(1);
				human.add(" Paper ");
				if(tester == 0)
				{
					win.setText("Two pieces of paper smushing together does nothing");
					outcome.add(" you tied :/ (lame)");
				}
				else if(tester == 1)
				{
					win.setText("Congratulations you made a paper covered rock and that means you win?");
					outcome.add(" you won!");
				}
				else if(tester == 2)
				{
					win.setText("They just cut your paper in half so uh... yea");
					outcome.add(" you lost :c");
				}

			}
		});
		
		
		scissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int tester = whoWon(2);
				human.add(" Scissors ");
				if(tester == 0)
				{
					win.setText("You both chucked scissors at eachother and does nothing");
					outcome.add(" you tied :/ (lame)");
				}
				else if(tester == 1)
				{
					win.setText("You channeled your kindergarten skills and successfully cut their piece of paper in half");
					outcome.add(" you won!");
				}
				else if(tester == 2)
				{
					win.setText("Your scissors get destroyed by their rock... kinda overkill if you think about it");
					outcome.add(" you lost :c");
				}

			}
		});
		
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				win.setText(displayRecord(test));
				System.out.println(displayRecord(test));
			}
		});
		
		
		
		one.add(rock);
		two.add(paper);
		three.add(scissors);
		four.add(finish);
		choices.add(one);
		choices.add(two);
		choices.add(three);
		choices.add(four);
		start.add(choices,BorderLayout.SOUTH);
		start.add(label,BorderLayout.NORTH);
		start.add(win,BorderLayout.CENTER);
		start.setVisible(true);
		start.setSize(500,200);
	}
	public static int whoWon(int a)
	{
		int compChoice = (int)(Math.random()*3);
		if(compChoice == 0)
		{
			computer.add(" Rock ");
		}
		else if(compChoice == 1)
		{
			computer.add(" Paper ");
		}	
		else if(compChoice == 2)
		{
			computer.add(" Scissors ");
		}
		test.add(compChoice);
		if(a == compChoice)
		{
			return 0;
		}
		else if(a == 0 && compChoice == 2)
		{
		
			return 1;
		}
		else if(a == 2 && compChoice == 0)
		{
			return 2;
		}
		else if(a - 1 < compChoice)
		{
	
			return 2;
		}
		else if(a - 1 >= compChoice)
		{
		
			return 1;
		}
		return -1;
	}
	public static String displayRecord(ArrayList<Integer> test)
	{
		String out = "";
		for (int i = 0;i < test.size();i++)
		{
			out += "Round " + (i + 1) + " You picked" + human.get(i) + ", the computer picked" + computer.get(i) + " so " + outcome.get(i) + "\n";
		}
		return out;
	}
}

