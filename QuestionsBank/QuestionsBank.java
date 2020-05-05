import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.xml.soap.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


class Question{
	private String questionString;
	private String questionAnswer;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public Question(String questionString, String questionAnswer) {
		super();
		this.questionString = questionString;
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionString() {
		return questionString;
	}
	
	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}
	
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	@Override
	public String toString() {
		return "Question [questionString=" + questionString
				+ ", questionAnswer=" + questionAnswer + "]";
	}
	
}

class TureFalseQuestion extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionString;
	private String correctAnswer;
	private boolean correctChoice;
	private static JFrame parent;
	private JTextArea questionText;
	private JButton trueBtn;
	private JButton falseBtn;
	public TureFalseQuestion(){
		super(parent, "True/False Question", true);
		correctChoice = false;
		questionText = new JTextArea(questionString, 5, 20);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());       
		trueBtn = new JButton("True");
		falseBtn = new JButton("False");
		trueBtn.addActionListener(this);
		falseBtn.addActionListener(this);
		panel.add(trueBtn, BorderLayout.WEST);
        panel.add(falseBtn, BorderLayout.EAST);
		add(questionText,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		setSize(200, 200);
		setLocationRelativeTo(parent);
	}
	
	public void setQuestion(String question){
		this.questionString = question;
		questionText.setText(question);
	}
	
	public void setAnswer(String answer){
		this.correctAnswer = answer;
	}
	
	public boolean getIfCorrect(){
		return correctChoice;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == trueBtn){
			correctChoice = Boolean.parseBoolean(correctAnswer)==true;
		}else if(arg0.getSource() == falseBtn){
			correctChoice = Boolean.parseBoolean(correctAnswer)==false;
		}
		dispose();
	}
	
	
}

public class QuestionsBank extends JFrame implements ActionListener {
	JMenuBar MyBar;
	JMenu File;
	JMenuItem Open,Exit;
	JFileChooser OpenDlg;
	JTextArea Result;
	JTextField timer;
	ArrayList<Question> questionsList;
	int numberOfQuestionsAnswered;
	Thread timerThread;
	
	public String formatTime(int sec){
		String result = "";
		int hours = sec/3600;
		if (hours>0){
			sec -= (hours*3600);
		}
		int minutes = sec/60;
		if (minutes>0){
			sec -= (minutes*60);
		}
		if (hours <10){
			result += ("0" + hours+":");
		}else{
			result += (hours+":");
		}
		if (minutes<10){
			result += ("0" + minutes+":");
		}else{
			result += (minutes+":");
		}
		if (sec<10){
			result += ("0" + sec);
		}else{
			result += sec;
		}
		return result;
	}
	
	public QuestionsBank(){
		super("Questions Bank");
		questionsList = new ArrayList<Question>();
		MyBar=new JMenuBar();
		File=new JMenu("File");
		Open=new JMenuItem("Open");
		Exit=new JMenuItem("Exit");
		OpenDlg=new JFileChooser();
		MyBar.add(File);
		File.add(Open);
		File.add(Exit);
		Open.addActionListener(this);
		Exit.addActionListener(this);
		setJMenuBar(MyBar);
		timer = new JTextField("00:00:00");
		timer.setFont(new Font(Font.SANS_SERIF, 0, 20));
		timer.setHorizontalAlignment(JTextField.CENTER);
		JButton question1 = new JButton("First Question");
		question1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				question1.setEnabled(false);
				TureFalseQuestion tfQ = new TureFalseQuestion();
				tfQ.setQuestion(questionsList.get(0).getQuestionString());
				tfQ.setAnswer(questionsList.get(0).getQuestionAnswer());
				tfQ.show();
				boolean result = tfQ.getIfCorrect();
				if (result==true){
					question1.setBackground(Color.GREEN);
				}else{
					question1.setBackground(Color.RED);
				}
				numberOfQuestionsAnswered++;
				if(numberOfQuestionsAnswered==4){
					String message = "Good Job, finished in "+timer.getText();
					JOptionPane.showMessageDialog(null, message);
					timerThread.yield();
				}
			}
		});
		JButton question2 = new JButton("Second Question");
		question2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				question2.setEnabled(false);
				TureFalseQuestion tfQ = new TureFalseQuestion();
				tfQ.setQuestion(questionsList.get(1).getQuestionString());
				tfQ.setAnswer(questionsList.get(1).getQuestionAnswer());
				tfQ.show();
				boolean result = tfQ.getIfCorrect();
				if (result==true){
					question2.setBackground(Color.GREEN);
				}else{
					question2.setBackground(Color.RED);
				}
				numberOfQuestionsAnswered++;
				if(numberOfQuestionsAnswered==4){
					String message = "Good Job, finished in "+timer.getText();
					JOptionPane.showMessageDialog(null, message);
					timerThread.yield();
				}
			}
		});
		JButton question3 = new JButton("Third Question");
		question3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				question3.setEnabled(false);
				TureFalseQuestion tfQ = new TureFalseQuestion();
				tfQ.setQuestion(questionsList.get(2).getQuestionString());
				tfQ.setAnswer(questionsList.get(2).getQuestionAnswer());
				tfQ.show();
				boolean result = tfQ.getIfCorrect();
				if (result==true){
					question3.setBackground(Color.GREEN);
				}else{
					question3.setBackground(Color.RED);
				}
				numberOfQuestionsAnswered++;
				if(numberOfQuestionsAnswered==4){
					String message = "Good Job, finished in "+timer.getText();
					JOptionPane.showMessageDialog(null, message);
					timerThread.yield();
				}
			}
		});
		JButton question4 = new JButton("Fourth Question");
		question4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				question4.setEnabled(false);
				TureFalseQuestion tfQ = new TureFalseQuestion();
				tfQ.setQuestion(questionsList.get(3).getQuestionString());
				tfQ.setAnswer(questionsList.get(3).getQuestionAnswer());
				tfQ.show();
				boolean result = tfQ.getIfCorrect();
				if (result==true){
					question4.setBackground(Color.GREEN);
				}else{
					question4.setBackground(Color.RED);
				}
				numberOfQuestionsAnswered++;
				if(numberOfQuestionsAnswered==4){
					String message = "Good Job, finished in "+timer.getText();
					JOptionPane.showMessageDialog(null, message);
					timerThread.yield();
				}
			}
		});

		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());     
		centerPanel.add(question1, BorderLayout.WEST);
		centerPanel.add(question2, BorderLayout.EAST);
		southPanel.setLayout(new BorderLayout());     
		southPanel.add(question3, BorderLayout.WEST);
		southPanel.add(question4, BorderLayout.EAST);
		
		add(centerPanel,BorderLayout.NORTH);
		add(timer,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
		setLocationByPlatform(true);
		setSize(300,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		timerThread = new Thread();
		synchronized(timer) {
		    try {
		        // Calling wait() will block this thread until another thread
		        // calls notify() on the object.
		    	timer.wait();
		    } catch (InterruptedException e) {
		        // Happens if someone interrupts your thread.
		    }
		}
		int totalTime = 0;
		for(;;){
			try{
				timerThread.sleep(1000); // to wait for 1 sec
				totalTime++;
				timer.setText(formatTime(totalTime));
				if(numberOfQuestionsAnswered==4){
					break; //we are done ;)
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main (String []argv){
		new QuestionsBank();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object s=arg0.getSource();
		if(s==Open){			
			OpenDlg.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			OpenDlg.showOpenDialog(this);
			if(OpenDlg.getSelectedFile()!=null){
				File questionBank = OpenDlg.getSelectedFile();
				BufferedReader reader;
		        StringBuilder sb = new StringBuilder();
		        try {
		            reader = new BufferedReader(new FileReader(questionBank));
		            String line = reader.readLine();
		            while(line != null) {
		                sb.append(line);
		                sb.append(System.lineSeparator());
		                line = reader.readLine();
		                if (line!=null && line.length()>0){
			                String[] lineSplits = line.split(";");
			                Question q = new Question(lineSplits[0], lineSplits[1]);
			                questionsList.add(q);
			                Collections.shuffle(questionsList);
		                }
		            }
		            synchronized(timer) {
		            	timer.notify();
		            }
		        } 
		        catch (Exception e) {
		            e.printStackTrace();
		        }
			}
			else
				JOptionPane.showMessageDialog(null,"Error Opening the File/Directory");
		}
		else if(s==Exit){
			this.dispose();
		}
	}   
}
