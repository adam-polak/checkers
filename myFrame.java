package chess2;

import java.awt.*;

import javax.swing.*;

public class myFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel[] p1 = new JPanel[12];
	private JPanel[] p2 = new JPanel[12];
	private JPanel[] board = new JPanel[64];
	private GridLayout boardLayout = new GridLayout(8, 8);

	myFrame(Player one, Player two) {
		this.setSize(500,500);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(this.boardLayout);
		
		for(int i = 0; i < 12; i++) {
			this.p1[i] = new JPanel();
			this.p2[i] = new JPanel();
			this.p1[i].setBackground(Color.red);
			this.p2[i].setBackground(Color.cyan);
		}
		
		for(int i = 0; i < 64; i++) {
			this.board[i] = new JPanel();
			this.board[i].setBackground(Color.gray);
		}
		
		this.setBoard(one, two);
		
		this.setVisible(true);
	}
	
	public void setBoard(Player one, Player two) {
		int x = 1;
		int y = 1;
		int bX;
		int bY;
		int add = 0;
		int index = 0;
		int bIndex = 0;
		for(int i = 0; i < 64; i++) {
			
			for(int z = 0; z < 12; z++) {
				if(x == one.playerData()[z][0] && y == one.playerData()[z][1]) {
					add = 1;
					index = z;
				} else if(x == two.playerData()[z][0] && y == two.playerData()[z][1]) {
					add = -1;
					index = z;
				}
			}
			
			bX = x - 1;
			bY = y - 1;
			
			if(add == 1) {
				this.add(p1[index], bY, bX);
			} else if(add == -1) {
				this.add(p2[index], bY, bX);
			} else {
				if((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
					this.board[bIndex].setBackground(Color.lightGray);
				}
				this.add(this.board[bIndex], bY, bX);
				bIndex++;
			}
			
			if(x < 8) {
				x++;
			} else {
				x = 1;
				y++;
			}
			
			add = 0;
		}
		
		this.validate();
	}
	
	public void updateBoard(Player one, Player two) {
		
		this.getContentPane().removeAll();
		this.setBoard(one, two);
		
	}
	
	
}
