package group23.pacman.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Simple model class that works with a certain text file which saves the users scores and sends it to the leaderboard to be displayed **/
public class ScoreHandler {
	
	private String firstName;
	private String secondName;
	private String thirdName;
	
	private String firstMapName;
	private String secondMapName;
	private String thirdMapName;
	
	private int firstScore;
	private int secondScore;
	private int thirdScore;
	
	
	public ScoreHandler() {
		readScores();
	}
	
	
	private void readScores() {
		

		/* Read the file and store the scores */
		try {
			FileReader fileReader = new FileReader("highScores.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row = 0;
			String line;
			try {
				while (row < 3) {
					row++;
					line = bufferedReader.readLine();
					String[] nameAndScore = line.split(",");
					
					
					switch (row) {
						case 1 :
							firstName = nameAndScore[0].trim();
							firstScore = Integer.parseInt(nameAndScore[1].trim());
							firstMapName = nameAndScore[2].trim();
							break;
						case 2 :
							secondName = nameAndScore[0].trim();
							secondScore = Integer.parseInt(nameAndScore[1].trim());
							secondMapName = nameAndScore[2].trim();
							break;
						case 3 :
							thirdName = nameAndScore[0].trim();
							thirdScore = Integer.parseInt(nameAndScore[1].trim());
							thirdMapName = nameAndScore[2].trim();
							break;
						default :
							;
						
					}
					
				}
				bufferedReader.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("Invalid text file");
		}
		
		
	}

	/* Writing a new highscore to the text file, only if it has beaten an existing score */
	public void writeScore(int newScore,String newName, char newMapName) {
		
		if (newScore > firstScore) {
			thirdScore = secondScore;
			thirdName = secondName;
			thirdMapName = secondMapName;
			secondScore = firstScore;
			secondName = firstName;
			secondMapName = firstMapName;
			firstScore = newScore;
			firstName = newName;
			firstMapName = newMapName + "";
		}
		else if (newScore > secondScore) {
			
			thirdScore = secondScore;
			thirdName = secondName;
			thirdMapName = secondMapName;
			secondScore = newScore;
			secondName = newName;
			secondMapName = newMapName + "";
		}
		else if (newScore > thirdScore) {
			
			thirdScore = newScore;
			thirdName = newName;
			thirdMapName = newMapName + "";
		}
		
		saveScore();
		
	}
	
	private void saveScore() {
		
		try {
			FileWriter fileWriter = new FileWriter("highScores.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf(firstName + ",%d" + ",%c\n",firstScore,firstMapName.charAt(0));
			printWriter.printf(secondName + ",%d" + ",%c\n",secondScore,secondMapName.charAt(0));
			printWriter.printf(thirdName + ",%d" + ",%c\n",thirdScore,thirdMapName.charAt(0));
			printWriter.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* PUBLIC GETTERS */
	public String[] getNames() {
		
		String[] names = new String[3];
		names[0] = firstName;
		names[1] = secondName;
		names[2] = thirdName;
		
		return names;
	}
	
	public String[] getMaps() {
		
		String[] maps = new String[3];
		maps[0] = firstMapName;
		maps[1] = secondMapName;
		maps[2] = thirdMapName;
		
		return maps;
		
	}
	
	public int[] getHighScores() {
		
		int[] scores = new int[3];
		scores[0] = firstScore;
		scores[1] = secondScore;
		scores[2] = thirdScore;
		return scores;
	}
}
