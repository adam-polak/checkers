package checkers;

public class Player {
	
	private static int[][] one = new int[12][2];
	private static int[][] two = new int[12][2];
	private static boolean[] oneKings = new boolean[12];
	private static boolean[] twoKings = new boolean[12];
	private static int piece = -1;
	private static int playerTurn = 1;
	
	public Player() {
		setPlayers();
	}
	
	private static void setPlayers() {
		int x = 2, y = 6;
		for(int i = 0; i < Player.one.length; i++) {
			Player.one[i][0] = x;
			Player.one[i][1] = y;
			
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
		
		x = 1;
		y = 1;
		for(int i = 0; i < Player.two.length; i++) {
			Player.two[i][0] = x;
			Player.two[i][1] = y;
			
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
		
		for(int i = 0; i < 12; i++) {
			Player.oneKings[i] = false;
			Player.twoKings[i] = false;
		}
	}
	
	public static boolean movePiece(boolean jump, int index, int x, int y) {
		
		int[][] cords;
		boolean matchCords = false;
		int removIndex = -1;
		
		if(jump) {
			cords = showJumps();
		} else {
			cords = showMoves(index);
		}
		
		for(int i = 0; i < cords.length; i++) {
			if(Player.playerTurn == 1 && cords[i][0] == x && cords[i][1] == y) {
				matchCords = true;
			} else if(Player.playerTurn == 2 && cords[i][0] == x && cords[i][1] == y){
				matchCords = true;
			}
		}
		
		if(Player.playerTurn == 1) {
			if(Player.one[index][0] - x == -2) {
				if(Player.one[index][1] - y == -2) {
					for(int i = 0; i < Player.two.length; i++) {
						if(Player.one[index][0] - Player.two[i][0] == -1 && Player.one[index][1] - Player.two[i][1] == -1) {
							removIndex = i;
						}
					}
				} else if (Player.one[index][1] - y == 2) {
					for(int i = 0; i < Player.two.length; i++) {
						if(Player.one[index][0] - Player.two[i][0] == -1 && Player.one[index][1] - Player.two[i][1] == 1) {
							removIndex = i;
						}
					}
				}
			} else if (Player.one[index][0] - x == 2) {
				if(Player.one[index][1] - y == -2) {
					for(int i = 0; i < Player.two.length; i++) {
						if(Player.one[index][0] - Player.two[i][0] == 1 && Player.one[index][1] - Player.two[i][1] == -1) {
							removIndex = i;
						}
					}
				} else if (Player.one[index][1] - y == 2) {
					for(int i = 0; i < Player.two.length; i++) {
						if(Player.one[index][0] - Player.two[i][0] == 1 && Player.one[index][1] - Player.two[i][1] == 1) {
							removIndex = i;
						}
					}
				}
			}
			
			if(matchCords) {
				if(removIndex != -1) {
					Player.two[removIndex][0] = -1;
					Player.two[removIndex][1] = -1;
				}
				
				if(y == 1) {
					Player.oneKings[index] = true;
				}
				
				Player.one[index][0] = x;
				Player.one[index][1] = y;
				
				return true;
			}
			
		} else if (Player.playerTurn == 2) {
			
			if(Player.two[index][0] - x == -2) {
				if(Player.two[index][1] - y == -2) {
					for(int i = 0; i < Player.one.length; i++) {
						if(Player.two[index][0] - Player.one[i][0] == -1 && Player.two[index][1] - Player.one[i][1] == -1) {
							removIndex = i;
						}
					}
				} else if (Player.two[index][1] - y == 2) {
					for(int i = 0; i < Player.one.length; i++) {
						if(Player.two[index][0] - Player.one[i][0] == -1 && Player.two[index][1] - Player.one[i][1] == 1) {
							removIndex = i;
						}
					}
				}
			} else if (Player.two[index][0] - x == 2) {
				if(Player.two[index][1] - y == -2) {
					for(int i = 0; i < Player.one.length; i++) {
						if(Player.two[index][0] - Player.one[i][0] == 1 && Player.two[index][1] - Player.one[i][1] == -1) {
							removIndex = i;
						}
					}
				} else if (Player.two[index][1] - y == 2) {
					for(int i = 0; i < Player.one.length; i++) {
						if(Player.two[index][0] - Player.one[i][0] == 1 && Player.two[index][1] - Player.one[i][1] == 1) {
							removIndex = i;
						}
					}
				}
			}
			
			if(matchCords) {
				if(removIndex != -1) {
					Player.one[removIndex][0] = -1;
					Player.one[removIndex][1] = -1;
				}
				
				if(y == 8) {
					Player.twoKings[index] = true;
				}
				
				Player.two[index][0] = x;
				Player.two[index][1] = y;
				
				return true;
			}
			
		}
		
		return false;
	}
	
	public static int getTurn() {
		return Player.playerTurn;
	}
	
	public static void nextTurn() {
		if(getTurn() == 1) {
			Player.playerTurn = 2;
		} else if(getTurn() == 2) {
			Player.playerTurn = 1;
		}
	}
	
	public static int[][] getOne() {
		return Player.one;
	}
	
	public static int[][] getTwo() {
		return Player.two;
	}
	
	private static int[][] setCordsVal(Move[] moves) {
		int[][] cords;
		cords = new int[moves.length][2];
		int count = 0;
		for(int i = 0; i < moves.length; i++) {
			if(i < 2) {
					switch(moves[i]) {
					case left:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] - 1;
							cords[i][1] = one[Player.piece][1] - 1;
						} else if (playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] - 1;
							cords[i][1] = two[Player.piece][1] + 1;
						}
						break;
					case leftJump:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] - 2;
							cords[i][1] = one[Player.piece][1] - 2;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] - 2;
							cords[i][1] = two[Player.piece][1] + 2;
						}
						break;
					case right:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] + 1;
							cords[i][1] = one[Player.piece][1] - 1;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] + 1;
							cords[i][1] = two[Player.piece][1] + 1;
						}
						break;
					case rightJump:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] + 2;
							cords[i][1] = one[Player.piece][1] - 2;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] + 2;
							cords[i][1] = two[Player.piece][1] + 2;
						}
						break;
					case NONE:
						cords[i][0] = -1;
						cords[i][1] = -1;
						break;
				}
			} else {
					switch(moves[i]) {
					case left:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] - 1;
							cords[i][1] = one[Player.piece][1] + 1;
						} else if (playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] - 1;
							cords[i][1] = two[Player.piece][1] - 1;
						}
						break;
					case leftJump:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] - 2;
							cords[i][1] = one[Player.piece][1] + 2;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] - 2;
							cords[i][1] = two[Player.piece][1] - 2;
						}
						break;
					case right:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] + 1;
							cords[i][1] = one[Player.piece][1] + 1;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] + 1;
							cords[i][1] = two[Player.piece][1] - 1;
						}
						break;
					case rightJump:
						if(Player.playerTurn == 1) {
							cords[i][0] = one[Player.piece][0] + 2;
							cords[i][1] = one[Player.piece][1] + 2;
						} else if (Player.playerTurn == 2) {
							cords[i][0] = two[Player.piece][0] + 2;
							cords[i][1] = two[Player.piece][1] - 2;
						}
						break;
					case NONE:
						cords[i][0] = -1;
						cords[i][1] = -1;
						break;
				}
			}
		}
		
		return cords;
	}
	
	public static int[][] showMoves(int setPiece) {
		Player.piece = setPiece;
		int[][] cords = setCordsVal(availableMoves());
		
		return cords;
	}
	
	public static int[][] showJumps() {
		
		int[][] cords = setCordsVal(availableJumps(availableMoves()));
		
		return cords;
	}

//returns true if there is no piece in the way
	private static boolean checkLeft() {
		
		int boundsX = 1;
		int boundsY;
		int testX = -1;
		int testY = 0;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] - 1;
			testY = Player.one[Player.piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] - 1;
			testY = Player.two[Player.piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if (testX < 1) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				selfX = Player.one[i][0];
				selfY = Player.one[i][1];
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				selfX = Player.two[i][0];
				selfY = Player.two[i][1];
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(i == Player.piece) {
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
	
	private static boolean checkRight() {
		
		int boundsY;
		int testX = -1;
		int testY = 0;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] + 1;
			testY = Player.one[Player.piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] + 1;
			testY = Player.two[Player.piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if (testX > 8) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				selfX = Player.one[i][0];
				selfY = Player.one[i][1];
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				selfX = Player.two[i][0];
				selfY = Player.two[i][1];
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(i == Player.piece) {
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
	
	private static boolean checkLeftJump() {
		
		if(checkLeft()) {
			return false;
		}
		
		int boundsY;
		int testX = -1;
		int testY = -1;;
		int testX2 = -1;
		int testY2 = -1;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		boolean jump = false;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] - 2;
			testX2 = Player.one[Player.piece][0] - 1;
			testY = Player.one[Player.piece][1] - 2;
			testY2 = Player.one[Player.piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] - 2;
			testX2 = Player.two[Player.piece][0] - 1;
			testY = Player.two[Player.piece][1] + 2;
			testY2 = Player.two[Player.piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if(testX < 1) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				if(i != Player.piece) {
					selfX = Player.one[i][0];
					selfY = Player.one[i][1];
				}
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if(Player.playerTurn == 2) {
				if(i != Player.piece) {
					selfX = Player.two[i][0];
					selfY = Player.two[i][1];
				}
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(testX == otherX && testY == otherY) {
				return false;
			} else if (testX == selfX && testY == selfY) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkRightJump() {
		
		if(checkRight()) {
			return false;
		}
		
		int boundsY;
		int testX = -1;
		int testY = -1;;
		int testX2 = -1;
		int testY2 = -1;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		boolean jump = false;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] + 2;
			testX2 = Player.one[Player.piece][0] + 1;
			testY = Player.one[Player.piece][1] - 2;
			testY2 = Player.one[Player.piece][1] - 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] + 2;
			testX2 = Player.two[Player.piece][0] + 1;
			testY = Player.two[Player.piece][1] + 2;
			testY2 = Player.two[Player.piece][1] + 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if(testX > 8) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				if(i != Player.piece) {
					selfX = Player.one[i][0];
					selfY = Player.one[i][1];
				}
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if(Player.playerTurn == 2) {
				if(i != Player.piece) {
					selfX = Player.two[i][0];
					selfY = Player.two[i][1];
				}
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(testX == otherX && testY == otherY) {
				return false;
			} else if (testX == selfX && testY == selfY) {
				return false;
			}
		}
		
		return true;

	}
	
private static boolean checkKingLeft() {
		
	int boundsY;
	int testX = -1;
	int testY = 0;
	int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
	
	if (Player.playerTurn == 1) {
		boundsY = 1;
		testX = Player.one[Player.piece][0] - 1;
		testY = Player.one[Player.piece][1] + 1;
		if (testY < boundsY) {
			return false;
		}
	} else if (Player.playerTurn == 2) {
		boundsY = 8;
		testX = Player.two[Player.piece][0] - 1;
		testY = Player.two[Player.piece][1] - 1;
		if (testY > boundsY) {
			return false;
		}
	}
	
	if (testX < 1) {
		return false;
	}
	
	for(int i = 0; i < 12; i++) {
		if(Player.playerTurn == 1) {
			selfX = Player.one[i][0];
			selfY = Player.one[i][1];
			otherX = Player.two[i][0];
			otherY = Player.two[i][1];
		} else if (Player.playerTurn == 2) {
			selfX = Player.two[i][0];
			selfY = Player.two[i][1];
			otherX = Player.one[i][0];
			otherY = Player.one[i][1];
		}
		
		if(i == Player.piece) {
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
	
	private static boolean checkKingRight() {
		
		int boundsY;
		int testX = -1;
		int testY = 0;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] + 1;
			testY = Player.one[Player.piece][1] + 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] + 1;
			testY = Player.two[Player.piece][1] - 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if (testX > 8) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				selfX = Player.one[i][0];
				selfY = Player.one[i][1];
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				selfX = Player.two[i][0];
				selfY = Player.two[i][1];
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(i == Player.piece) {
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
	
	private static boolean checkKingLeftJump() {
		
		if(checkKingLeft()) {
			return false;
		}
		
		int boundsY;
		int testX = -1;
		int testY = -1;;
		int testX2 = -1;
		int testY2 = -1;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		boolean jump = false;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] - 2;
			testX2 = Player.one[Player.piece][0] - 1;
			testY = Player.one[Player.piece][1] + 2;
			testY2 = Player.one[Player.piece][1] + 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] - 2;
			testX2 = Player.two[Player.piece][0] - 1;
			testY = Player.two[Player.piece][1] - 2;
			testY2 = Player.two[Player.piece][1] - 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if(testX < 1) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				if(i != Player.piece) {
					selfX = Player.one[i][0];
					selfY = Player.one[i][1];
				}
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if(Player.playerTurn == 2) {
				if(i != Player.piece) {
					selfX = Player.two[i][0];
					selfY = Player.two[i][1];
				}
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(testX == otherX && testY == otherY) {
				return false;
			} else if (testX == selfX && testY == selfY) {
				return false;
			}
		}
		
		return true;

	}
	
	private static boolean checkKingRightJump() {
		
		if(checkKingRight()) {
			return false;
		}
		
		int boundsY;
		int testX = -1;
		int testY = -1;;
		int testX2 = -1;
		int testY2 = -1;
		int selfX = -1, selfY = -1, otherX = -1, otherY = -1;
		boolean jump = false;
		
		if (Player.playerTurn == 1) {
			boundsY = 1;
			testX = Player.one[Player.piece][0] + 2;
			testX2 = Player.one[Player.piece][0] + 1;
			testY = Player.one[Player.piece][1] + 2;
			testY2 = Player.one[Player.piece][1] + 1;
			if (testY < boundsY) {
				return false;
			}
		} else if (Player.playerTurn == 2) {
			boundsY = 8;
			testX = Player.two[Player.piece][0] + 2;
			testX2 = Player.two[Player.piece][0] + 1;
			testY = Player.two[Player.piece][1] - 2;
			testY2 = Player.two[Player.piece][1] - 1;
			if (testY > boundsY) {
				return false;
			}
		}
		
		if(testX > 8) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if (Player.playerTurn == 2) {
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			if (testX2 == otherX && testY2 == otherY) {
				jump = true;
			}
		}
		
		if (!jump) {
			return false;
		}
		
		for(int i = 0; i < 12; i++) {
			if(Player.playerTurn == 1) {
				if(i != Player.piece) {
					selfX = Player.one[i][0];
					selfY = Player.one[i][1];
				}
				otherX = Player.two[i][0];
				otherY = Player.two[i][1];
			} else if(Player.playerTurn == 2) {
				if(i != Player.piece) {
					selfX = Player.two[i][0];
					selfY = Player.two[i][1];
				}
				otherX = Player.one[i][0];
				otherY = Player.one[i][1];
			}
			
			if(testX == otherX && testY == otherY) {
				return false;
			} else if (testX == selfX && testY == selfY) {
				return false;
			}
		}
		
		return true;
		
	}
	
	private static Move[] availableJumps(Move[] moves) {
		Move[] jumpMoves = new Move[moves.length];

		for(int i = 0; i < moves.length; i++) {
			if(moves[i] == Move.leftJump || moves[i] == Move.rightJump) {
				jumpMoves[i] = moves[i];
			} else {
				jumpMoves[i] = Move.NONE;
			}
		}
		
		return jumpMoves;
	}
	
	private static Move[] availableMoves() {
//index 0 will be the move available on the left side of piece and index 1 will be the right
		Move[] moves;
		if(playerTurn == 1 && oneKings[piece]) {
			moves = new Move[4];
		} else if (playerTurn == 2 && twoKings[piece]) {
			moves = new Move[4];
		} else {
			moves = new Move[2];
		}
		
		if(checkLeft() && checkRight()) {
			moves[0] = Move.left;
			moves[1] = Move.right;
		} else if (checkLeftJump() && checkRightJump()) {
			moves[0] = Move.leftJump;
			moves[1] = Move.rightJump;
		} else if (checkLeft() && checkRightJump()) {
			moves[0] = Move.left;
			moves[1] = Move.rightJump;
		} else if (checkLeftJump() && checkRight()) {
			moves[0] = Move.leftJump;
			moves[1] = Move.right;
		} else if (checkLeft()) {
			moves[0] = Move.left;
			moves[1] = Move.NONE;
		} else if (checkRight()) {
			moves[0] = Move.NONE;
			moves[1] = Move.right;
		} else if (checkLeftJump()) {
			moves[0] = Move.leftJump;
			moves[1] = Move.NONE;
		} else if (checkRightJump()) {
			moves[0] = Move.NONE;
			moves[1] = Move.rightJump;
		} else {
			moves[0] = Move.NONE;
			moves[1] = Move.NONE;
		}
		
		if(moves.length == 4) {
			if(checkKingLeft() && checkKingRight()) {
				moves[2] = Move.left;
				moves[3] = Move.right;
			} else if (checkKingLeftJump() && checkKingRightJump()) {
				moves[2] = Move.leftJump;
				moves[3] = Move.rightJump;
			} else if (checkKingLeft() && checkKingRightJump()) {
				moves[2] = Move.left;
				moves[3] = Move.rightJump;
			} else if (checkKingLeftJump() && checkKingRight()) {
				moves[2] = Move.leftJump;
				moves[3] = Move.right;
			} else if (checkKingLeft()) {
				moves[2] = Move.left;
				moves[3] = Move.NONE;
			} else if (checkKingRight()) {
				moves[2] = Move.NONE;
				moves[3] = Move.right;
			} else if (checkKingLeftJump()) {
				moves[2] = Move.leftJump;
				moves[3] = Move.NONE;
			} else if (checkKingRightJump()) {
				moves[2] = Move.NONE;
				moves[3] = Move.rightJump;
			} else {
				moves[2] = Move.NONE;
				moves[3] = Move.NONE;
			}
		}
		
		
		
		return moves;
	}
	
}
