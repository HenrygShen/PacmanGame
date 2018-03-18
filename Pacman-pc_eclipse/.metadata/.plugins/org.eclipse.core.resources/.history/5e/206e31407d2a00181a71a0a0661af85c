package group23.pacman.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Board {
	
	private static final int TILE_SIZE = 10;
	private static final int HELP = 1;
	
	private ArrayList<GameObject> objects;
	private boolean[][] status;
	private boolean[][] turn;

	public Board() {
		
		objects = new ArrayList<GameObject>();
		String line = null;
		status = new boolean[101][71];
		turn = new boolean[101][71];
		
		try {
			
			FileReader fileReader = new FileReader("testmap1.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row =0;
			int position = 0;
			while ((line = bufferedReader.readLine()) != null ) {
				position = 0;
				for (int i =0;i< line.length();i++) {
					if (line.charAt(i)==('0')) {
						Rectangle rect = new Rectangle();
						rect.setX(position*TILE_SIZE + 33);
						rect.setY(row*TILE_SIZE + 34);
						rect.setWidth(TILE_SIZE - 1);
						rect.setHeight(TILE_SIZE - 1);
						Wall wall = new Wall(rect);
						status[position][row] = false;
						turn[position][row] = false;
						objects.add(wall);
						position++;
					}
					else if (line.charAt(i) == 'P') {
						Pellet pellet = new Pellet(position*TILE_SIZE + 33,row*TILE_SIZE + 34);
						objects.add(pellet);
						status[position][row] = false;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == '1' ) {
						status[position][row] = false;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == 'R' ) {
						status[position][row] = true;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == 'T' ) {
						status[position][row] = true;
						turn[position][row] = true;
						position++;
					}
					else {
						
					}
				}
				row++;
			}
			bufferedReader.close();
		} 
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file ");
		}

		catch (IOException ex) {
			System.out.println("Error reading file ");
		}
		
	}
	
	public ArrayList<GameObject> getObjects() {
		
		return this.objects;
	}
	
	public boolean validTurningPoint(int x, int y) {
		
		if (((x - 33)%10 == 0) && ((y - 34)%10 == 0)){
			return this.status[(x - 33)/10][(y - 34)/10 ];
		}
		else {
			return false;
		}
	}
	
	public boolean isValidDestination(char direction, int x, int y) {
		
    	switch (direction) {
			case 'U':
				return this.status[(x - 33)/10][(y - HELP - 34)/10];
			case 'D':
				return this.status[(x - 33)/10][(y + TILE_SIZE - 34)/10];
			case 'L':
				return this.status[(x - HELP - 33)/10][(y - 34)/10];
			case 'R':
				return this.status[(x + TILE_SIZE - 33)/10][(y - 34)/10];
    	}
    	return false;
    }
}
