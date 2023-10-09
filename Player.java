package checkers;

public class Player {
	
	private int playerNumber = 0;
	private int[][] data = new int[12][2];
	private int pieceCount = 12;
	private boolean[] king = new boolean[12];
	
	public boolean isKing(int piece) {
		
		return this.king[piece];
		
	}
	
	public void setKing() {
		if(this.playerNumber == 1) {
			for(int i = 0; i < this.data.length; i++) {
				if(this.data[i][1] == 1) {
					this.king[i] = true;
				}
			}
		} else if (this.playerNumber == 2) {
			for(int i = 0; i < this.data.length; i++) {
				if(this.data[i][1] == 8) {
					this.king[i] = true;
				}
			}
		}
	}
	
	public void setPlayerNumber(int number) {
		this.playerNumber = number;
	}
	
	public void setPieces() {
		
		int x = 0;
		int y = 0;
		
		if (this.playerNumber == 1) {
			
			x = 2;
			y = 6;
			
		} else if (this.playerNumber == 2) {
			
			x = 1;
			y = 1;
			
		}
		
		for(int i = 0; i < this.data.length; i++) {
			
			this.data[i][0] = x;
			this.data[i][1] = y;
			
			if (x == 8) {
				x = 1;
				y++;
			} else if (x == 7) {
				x = 2;
				y++;
			} else {
				x += 2;
			}
			
		}
		
	}
	
	public int[][] playerData() {
		return this.data;
	}
	
	public boolean checkPieceCount() {
		if(this.pieceCount == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removePiece(int[][] newData) {
		this.data = newData;
		this.pieceCount--;
	}
	
	public int availableKingMoves(int[][] otherPlayer, int piece) {
		if(checkPiece(piece)) {
			return 0;
		}
		
		if(checkKingLeft(otherPlayer, piece) && checkKingRight(otherPlayer, piece)) {
			return 1;
		} else if(checkKingLeftJump(otherPlayer, piece) && checkKingRightJump(otherPlayer, piece)) {
			return 2;
		} else if (checkKingLeft(otherPlayer, piece) && checkKingRightJump(otherPlayer, piece)) {
			return 3;
		} else if (checkKingRight(otherPlayer, piece) && checkKingLeftJump(otherPlayer, piece)) {
			return 4;
		} else if (checkKingLeft(otherPlayer, piece)) {
			return 5;
		} else if (checkKingRight(otherPlayer, piece)) {
			return 6;
		} else if (checkKingLeftJump(otherPlayer, piece)) {
			return 7;
		} else if (checkKingRightJump(otherPlayer, piece)) {
			return 8;
		}
		
		return 0;
	}
	
	public int availableJumps(int[][] otherPlayer, int piece) {
		if(checkPiece(piece)) {
			return 0;
		}
		
		if(checkLeftJump(otherPlayer, piece) && checkRightJump(otherPlayer, piece)) {
			return 1;
		} else if(checkLeftJump(otherPlayer, piece)) { 
			return 2;	
		} else if (checkRightJump(otherPlayer, piece)) {
			return 3;
		}
		
		return 0;
	}
	
	public int availableKingJumps(int[][] otherPlayer, int piece) {
		if(checkPiece(piece)) {
			return 0;
		}
		
		if(checkKingLeftJump(otherPlayer, piece) && checkKingRightJump(otherPlayer, piece)) {
			return 1;
		} else if(checkKingLeftJump(otherPlayer, piece)) { 
			return 2;	
		} else if (checkKingRightJump(otherPlayer, piece)) {
			return 3;
		}
		
		return 0;
	}
	
	public int availableMoves(int[][] otherPlayer, int piece) {
		
		if(checkPiece(piece)) {
			return 0;
		}
		
		if(checkLeft(otherPlayer, piece) && checkRight(otherPlayer, piece)) {
			return 1;
		} else if (checkLeftJump(otherPlayer, piece) && checkRightJump(otherPlayer, piece)) {
			return 2;
		} else if (checkLeft(otherPlayer, piece) && checkRightJump(otherPlayer, piece)) {
			return 3;
		} else if (checkRight(otherPlayer, piece) && checkLeftJump(otherPlayer, piece)) {
			return 4;
		} else if (checkLeft(otherPlayer, piece)) {
			return 5;
		} else if (checkRight(otherPlayer, piece)) {
			return 6;
		} else if (checkLeftJump(otherPlayer, piece)) {
			return 7;
		} else if (checkRightJump(otherPlayer, piece)) {
			return 8;
		}
		
		return 0;
	}
	
	public void kingLeft(int piece) {
		if (this.playerNumber == 2) {
			this.data[piece][0] -= 1;
			this.data[piece][1] -= 1;
		} else if (playerNumber == 1) {
			this.data[piece][0] -= 1;
			this.data[piece][1] += 1;
		}
	}
	
	public void kingRight(int piece) {
		if (this.playerNumber == 2) {
			this.data[piece][0] += 1;
			this.data[piece][1] -= 1;
		} else if (playerNumber == 1) {
			this.data[piece][0] += 1;
			this.data[piece][1] += 1;
		}
	}
	
	public int[][] kingLeftJump(int piece, int[][] otherData) {
		if (this.playerNumber == 2) {
			this.data[piece][0] -= 2;
			this.data[piece][1] -= 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] + 1 && otherData[i][1] == this.data[piece][1] + 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		} else if (this.playerNumber == 1) {
			this.data[piece][0] -= 2;
			this.data[piece][1] += 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] + 1 && otherData[i][1] == this.data[piece][1] - 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		}
		
		return otherData;
	}
	
	public int[][] kingRightJump(int piece, int[][] otherData) {
		if (this.playerNumber == 2) {
			this.data[piece][0] += 2;
			this.data[piece][1] -= 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] - 1 && otherData[i][1] == this.data[piece][1] + 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		} else if (this.playerNumber == 1) {
			this.data[piece][0] += 2;
			this.data[piece][1] += 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] - 1 && otherData[i][1] == this.data[piece][1] - 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		}
		
		return otherData;
	}
	
	public boolean checkKingLeft(int otherPlayer[][], int piece) {
		
		int boundsX = 1;
		int boundsY;
		int testX = this.data[piece][0] - 1;
		int testY = 0;
		int selfX, selfY, otherX, otherY;
		
		if (testX < boundsX) {
			return false;
		}
		
		if (this.playerNumber == 2) {
			boundsY = 1;
			testY = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 1) {
			boundsY = 8;
			testY = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkKingRight(int[][] otherPlayer, int piece) {
		
		int boundsX = 8;
		int boundsY;
		int testX = this.data[piece][0] + 1;
		int testY = 0;
		int selfX, selfY, otherX, otherY;
		
		if (testX > boundsX) {
			return false;
		}
		
		if (this.playerNumber == 2) {
			boundsY = 1;
			testY = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 1) {
			boundsY = 8;
			testY = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkKingLeftJump(int[][] otherPlayer, int piece) {
		
		int boundsX = 1;
		int boundsY;
		int testX = this.data[piece][0] - 2;
		int testY = 0;
		int testX2 = this.data[piece][0] - 1;
		int testY2 = 0;
		int selfX, selfY, otherX, otherY;
		boolean jump = false;
		
		if (testX < boundsX) {
			return false;
		}
		
		if (this.playerNumber == 2) {
			boundsY = 1;
			testY = this.data[piece][1] - 2;
			testY2 = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 1) {
			boundsY = 8;
			testY = this.data[piece][1] + 2;
			testY2 = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < otherPlayer.length; i++) {
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if (i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkKingRightJump(int[][] otherPlayer, int piece) {
		
		int boundsX = 8;
		int boundsY;
		int testX = this.data[piece][0] + 2;
		int testY = 0;
		int testX2 = this.data[piece][0] + 1;
		int testY2 = 0;
		int selfX, selfY, otherX, otherY;
		boolean jump = false;
		
		if (testX > boundsX) {
			return false;
		}
		
		if (this.playerNumber == 2) {
			boundsY = 1;
			testY = this.data[piece][1] - 2;
			testY2 = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 1) {
			boundsY = 8;
			testY = this.data[piece][1] + 2;
			testY2 = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < otherPlayer.length; i++) {
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < this.data.length; i++) {
			
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void left(int piece) {
		
		if (this.playerNumber == 1) {
			this.data[piece][0] -= 1;
			this.data[piece][1] -= 1;
		} else if (playerNumber == 2) {
			this.data[piece][0] -= 1;
			this.data[piece][1] += 1;
		}
		
	}
	
	public void right(int piece) {
		
		if (this.playerNumber == 1) {
			this.data[piece][0] += 1;
			this.data[piece][1] -= 1;
		} else if (playerNumber == 2) {
			this.data[piece][0] += 1;
			this.data[piece][1] += 1;
		}
		
	}
	
	public int[][] leftJump(int piece, int[][] otherData) {
		
		if (this.playerNumber == 1) {
			this.data[piece][0] -= 2;
			this.data[piece][1] -= 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] + 1 && otherData[i][1] == this.data[piece][1] + 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		} else if (this.playerNumber == 2) {
			this.data[piece][0] -= 2;
			this.data[piece][1] += 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] + 1 && otherData[i][1] == this.data[piece][1] - 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		}
		
		return otherData;
		
	}
	
	public int[][] rightJump(int piece, int[][] otherData) {
		
		if (this.playerNumber == 1) {
			this.data[piece][0] += 2;
			this.data[piece][1] -= 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] - 1 && otherData[i][1] == this.data[piece][1] + 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		} else if (this.playerNumber == 2) {
			this.data[piece][0] += 2;
			this.data[piece][1] += 2;
			for(int i = 0; i < otherData.length; i++) {
				if(otherData[i][0] == this.data[piece][0] - 1 && otherData[i][1] == this.data[piece][1] - 1) {
					otherData[i][0] = -1;
					otherData[i][1] = -1;
				}
			}
		}
		
		return otherData;
		
	}
	
	public boolean checkPiece(int piece) {
		if(this.data[piece][0] == -1) {
			return true;
		} else if(this.data[piece][1] == -1) {
			return true;
		} else if(this.data[piece][0] > 8 || this.data[piece][0] < 1) {
			return true;
		} else if(this.data[piece][1] > 8 || this.data[piece][1] < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkLeft(int[][] otherPlayer, int piece) {
		
		int boundsX = 1;
		int boundsY;
		int testX = this.data[piece][0] - 1;
		int testY = 0;
		int selfX, selfY, otherX, otherY;
		
		if (testX < boundsX) {
			return false;
		}
		
		if (this.playerNumber == 1) {
			boundsY = 1;
			testY = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 2) {
			boundsY = 8;
			testY = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkRight(int[][] otherPlayer, int piece) {
		
		int boundsX = 8;
		int boundsY;
		int testX = this.data[piece][0] + 1;
		int testY = 0;
		int selfX, selfY, otherX, otherY;
		
		if (testX > boundsX) {
			return false;
		}
		
		if (this.playerNumber == 1) {
			boundsY = 1;
			testY = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 2) {
			boundsY = 8;
			testY = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkLeftJump(int[][] otherPlayer, int piece) {
		
		int boundsX = 1;
		int boundsY;
		int testX = this.data[piece][0] - 2;
		int testY = 0;
		int testX2 = this.data[piece][0] - 1;
		int testY2 = 0;
		int selfX, selfY, otherX, otherY;
		boolean jump = false;
		
		if (testX < boundsX) {
			return false;
		}
		
		if (this.playerNumber == 1) {
			boundsY = 1;
			testY = this.data[piece][1] - 2;
			testY2 = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 2) {
			boundsY = 8;
			testY = this.data[piece][1] + 2;
			testY2 = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < otherPlayer.length; i++) {
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < this.data.length; i++) {
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if (i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkRightJump(int[][] otherPlayer, int piece) {
		
		int boundsX = 8;
		int boundsY;
		int testX = this.data[piece][0] + 2;
		int testY = 0;
		int testX2 = this.data[piece][0] + 1;
		int testY2 = 0;
		int selfX, selfY, otherX, otherY;
		boolean jump = false;
		
		if (testX > boundsX) {
			return false;
		}
		
		if (this.playerNumber == 1) {
			boundsY = 1;
			testY = this.data[piece][1] - 2;
			testY2 = this.data[piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (this.playerNumber == 2) {
			boundsY = 8;
			testY = this.data[piece][1] + 2;
			testY2 = this.data[piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		for(int i = 0; i < otherPlayer.length; i++) {
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < this.data.length; i++) {
			
			selfX = this.data[i][0];
			selfY = this.data[i][1];
			otherX = otherPlayer[i][0];
			otherY = otherPlayer[i][1];
			
			if(i == piece) {
				if(testX == otherX && testY == otherY) {
					return false;
				}
			} else {
				if (testX == otherX && testY == otherY) {
					return false;
				} else if (testX == selfX && testY == selfY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
