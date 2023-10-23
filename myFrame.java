package checkers;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class myFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[] p1 = new JButton[12];
	private JButton[] p2 = new JButton[12];
	private JLabel[] p1Pieces = new JLabel[12];
	private JLabel[] p2Pieces = new JLabel[12];
	private JButton[] lightBoard = new JButton[32];
	private int[][] lightIndexes = new int[32][2];
	private JPanel[] darkBoard = new JPanel[32];
	private GridLayout boardLayout = new GridLayout(8, 8);
	private int index = -1;
	private boolean jump = false;

	myFrame() {
		this.setSize(500,500);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(this.boardLayout);
		
		for(int i = 0; i < 12; i++) {
			this.p1Pieces[i] = new JLabel("" + i);
			this.p2Pieces[i] = new JLabel("" + i);
			this.p1[i] = new JButton();
			this.p2[i] = new JButton();
			this.p1[i].setBackground(Color.red);
			this.p2[i].setBackground(Color.cyan);
			this.p1[i].add(this.p1Pieces[i]);
			this.p2[i].add(this.p2Pieces[i]);
			this.p1[i].addActionListener(this);
			this.p2[i].addActionListener(this);
			
		}
		
		for(int i = 0; i < 32; i++) {
			this.darkBoard[i] = new JPanel();
			this.darkBoard[i].setBackground(Color.gray);
			
			this.lightBoard[i] = new JButton();
			this.lightBoard[i].setBackground(Color.lightGray);
		}
		
		this.setBoard();
		
		this.setVisible(true);
	}

	public void setBoard() {
		this.getContentPane().removeAll();
		int x = 1;
		int y = 1;
		int bX;
		int bY;
		int add = 0;
		int index = 0;
		int lightIndex = 0;
		int darkIndex = 0;
		for(int i = 0; i < 64; i++) {
			
			for(int z = 0; z < 12; z++) {
				if(x == Player.getOne()[z][0] && y == Player.getOne()[z][1]) {
					add = 1;
					index = z;
				} else if(x == Player.getTwo()[z][0] && y == Player.getTwo()[z][1]) {
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
					this.lightBoard[lightIndex].setBackground(Color.lightGray);
					this.lightBoard[lightIndex].removeActionListener(this);
					this.add(this.lightBoard[lightIndex], bY, bX);
					this.lightIndexes[lightIndex][0] = x;
					this.lightIndexes[lightIndex][1] = y;
					lightIndex++;
				} else {
					this.add(this.darkBoard[darkIndex], bY, bX);
					darkIndex++;
				}
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
	
	public void updateBoard() {
		
		this.getContentPane().removeAll();
		this.setBoard();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < this.p1.length; i++) 
		{
			if(Player.getTurn() == 1 && e.getSource().equals(this.p1[i]))
			{
				this.index = i;
				int[][] cords = Player.showMoves(this.index);
				for(int x = 0; x < cords.length; x++) 
				{
					if(cords[x][0] != -1 && cords[x][1] != -1) 
					{
						for(int z = 0; z < 32; z++) 
						{
							if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
							{
								this.lightBoard[z].addActionListener(this);
							}
						}
					}
				}
			} else if (Player.getTurn() == 2 && e.getSource().equals(this.p2[i])) {
				this.index = i;
				int[][] cords = Player.showMoves(this.index);
				for(int x = 0; x < cords.length; x++) 
				{
					if(cords[x][0] != -1 && cords[x][1] != -1) 
					{
						for(int z = 0; z < 32; z++) 
						{
							if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
							{
								this.lightBoard[z].addActionListener(this);
							}
						}
					}
				}
			} else if (this.jump && Player.getTurn() == 2 && e.getSource().equals(this.p1[i])) {
				this.jump = false;
				Player.nextTurn();
				this.index = i;
				int[][] cords = Player.showMoves(this.index);
				for(int x = 0; x < cords.length; x++) 
				{
					if(cords[x][0] != -1 && cords[x][1] != -1) 
					{
						for(int z = 0; z < 32; z++) 
						{
							if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
							{
								this.lightBoard[z].addActionListener(this);
							}
						}
					}
				}
			} else if (this.jump && Player.getTurn() == 1 && e.getSource().equals(this.p2[i])) {
				this.jump = false;
				Player.nextTurn();
				this.index = i;
				int[][] cords = Player.showMoves(this.index);
				for(int x = 0; x < cords.length; x++) 
				{
					if(cords[x][0] != -1 && cords[x][1] != -1) 
					{
						for(int z = 0; z < 32; z++) 
						{
							if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
							{
								this.lightBoard[z].addActionListener(this);
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < this.lightBoard.length; i++) {
			
			if(e.getSource().equals(this.lightBoard[i])) {
				
				if(this.jump) {
					Player.movePiece(this.jump, this.index, this.lightIndexes[i][0], this.lightIndexes[i][1]);
					int[][] cords = Player.showJumps();
					int jumps = 0;
					for(int x = 0; x < cords.length; x++) 
					{
						if(cords[x][0] != -1 && cords[x][1] != -1) 
						{
							for(int z = 0; z < 32; z++) 
							{
								if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
								{
									this.lightBoard[z].addActionListener(this);
									jumps++;
								}
							}
						}
					}
					if(jumps > 0) {
						this.jump = true;
					} else {
						Player.nextTurn();
						this.jump = false;
					}
				} else if(Player.getTurn() == 1 && (Player.getOne()[this.index][0] - this.lightIndexes[i][0] == -2 || Player.getOne()[this.index][0] - this.lightIndexes[i][0] == 2)) {
					Player.movePiece(this.jump, this.index, this.lightIndexes[i][0], this.lightIndexes[i][1]);
					int[][] cords = Player.showJumps();
					int jumps = 0;
					for(int x = 0; x < cords.length; x++) 
					{
						if(cords[x][0] != -1 && cords[x][1] != -1) 
						{
							for(int z = 0; z < 32; z++) 
							{
								if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
								{
									this.lightBoard[z].addActionListener(this);
									jumps++;
								}
							}
						}
					}
					if(jumps > 0) {
						this.jump = true;
					} else {
						Player.nextTurn();
						this.jump = false;
					}
				} else if(Player.getTurn() == 2 && (Player.getTwo()[this.index][0] - this.lightIndexes[i][0] == -2 || Player.getTwo()[this.index][0] - this.lightIndexes[i][0] == 2)) {
					Player.movePiece(this.jump, this.index, this.lightIndexes[i][0], this.lightIndexes[i][1]);
					int[][] cords = Player.showJumps();
					int jumps = 0;
					for(int x = 0; x < cords.length; x++) 
					{
						if(cords[x][0] != -1 && cords[x][1] != -1) 
						{
							for(int z = 0; z < 32; z++) 
							{
								if (this.lightIndexes[z][0] == cords[x][0] && this.lightIndexes[z][1] == cords[x][1]) 
								{
									this.lightBoard[z].addActionListener(this);
									jumps++;
								}
							}
						}
					}
					
					if(jumps > 0) {
						this.jump = true;
					} else {
						Player.nextTurn();
						this.jump = false;
					}
				} else {
					if(Player.movePiece(this.jump, this.index, this.lightIndexes[i][0], this.lightIndexes[i][1])) {
						Player.nextTurn();
					}
				}
				
				this.setBoard();
				
			}
		}
		
	}
