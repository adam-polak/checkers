package checkers;

import java.util.Scanner;

public class CheckerDriver {
	public static void main(String[] args) {

		Player p1 = new Player();
		p1.setPlayerNumber(1);
		p1.setPieces();
		
		Player p2 = new Player();
		p2.setPlayerNumber(2);
		p2.setPieces();
		
		int playerTurn = 2;
		int pieceChoice = 0;
		int moves = 0;
		int kingMoves = 0;
		int moveChoice = 0;
		boolean king = false;
		boolean jump = false;
		int kingMoveChoice = 0;
		
		Scanner input = new Scanner(System.in);
	
		
		while(playerTurn != 1) {
			
			if(jump) {
				if(playerTurn % 2 == 0) {
					if(p1.isKing(pieceChoice)) {
						king = true;
						System.out.println("Would you like to make your next jump forwards or backwards?\n1. Forwards\n2. Backwards");
						kingMoveChoice = input.nextInt();
						if(kingMoveChoice == 1) {
							king = false;
						}
						kingMoves = p1.availableKingMoves(p2.playerData(), pieceChoice);
						moves = p1.availableMoves(p2.playerData(), pieceChoice);
					} else {
						moves = p1.availableMoves(p2.playerData(), pieceChoice);
					}
				} else {
					if(p2.isKing(pieceChoice)) {
						king = true;
						System.out.println("Would you like to make your next jump forwards or backwards?\n1. Backwards\n2. Forwards");
						kingMoveChoice = input.nextInt();
						if(kingMoveChoice == 2) {
							king = false;
						}
						kingMoves = p2.availableKingMoves(p1.playerData(), pieceChoice);
						moves = p2.availableMoves(p1.playerData(), pieceChoice);
					} else {
						moves = p2.availableMoves(p1.playerData(), pieceChoice);
					}
					
				}
			} else {
				System.out.println("\nPlayer 1's pieces:");
				for(int i = 0; i < p1.playerData().length; i++) {
					System.out.println("Piece " + i + ": x: " + p1.playerData()[i][0] + " y: " + p1.playerData()[i][1]);
				}
				System.out.println();
				System.out.println("Player 2's pieces:");
				for(int i = 0; i < p2.playerData().length; i++) {
					System.out.println("Piece " + i + ": x: " + p2.playerData()[i][0] + " y: " + p2.playerData()[i][1]);
				}
				
				if (playerTurn % 2 == 0) {
					System.out.println("-----------------------\nPlayer 1's Turn.");
				} else {
					System.out.println("-----------------------\nPlayer 2's Turn.");
				}
				
				System.out.println("-----------------------\nWhich piece do you want to move? (0-11)");
				pieceChoice = input.nextInt();
				
				if (playerTurn % 2 == 0) {
					if(p1.isKing(pieceChoice)) {
						king = true;
						System.out.println("Would you like to move forwards or backwards?\n1. Forwards\n2. Backwards");
						kingMoveChoice = input.nextInt();
						if(kingMoveChoice == 1) {
							king = false;
						}
						kingMoves = p1.availableKingMoves(p2.playerData(), pieceChoice);
						moves = p1.availableMoves(p2.playerData(), pieceChoice);
					} else {
						moves = p1.availableMoves(p2.playerData(), pieceChoice);
					}
				} else {
					if(p2.isKing(pieceChoice)) {
						king = true;
						System.out.println("Would you like to move forwards or backwards?\n1. Backwards\n2. Forwards");
						kingMoveChoice = input.nextInt();
						if(kingMoveChoice == 2) {
							king = false;
						}
						kingMoves = p2.availableKingMoves(p1.playerData(), pieceChoice);
						moves = p2.availableMoves(p1.playerData(), pieceChoice);
					} else {
						moves = p2.availableMoves(p1.playerData(), pieceChoice);
					}
				}
			}
			
			
		if (jump) {
			if(king) {
				switch(kingMoves) {
					case 2:
						System.out.println("1. Jump Left\n2. Jump Right");
						moveChoice = input.nextInt();
						if(moveChoice == 1) {
							if(playerTurn % 2 == 0) {
								p2.removePiece(p1.kingLeftJump(pieceChoice, p2.playerData()));
							} else {
								p1.removePiece(p2.kingLeftJump(pieceChoice, p1.playerData()));
							}
						} else if (moveChoice == 2) {
							if(playerTurn % 2 == 0) {
								p2.removePiece(p1.kingRightJump(pieceChoice, p2.playerData()));
							} else {
								p1.removePiece(p2.kingRightJump(pieceChoice, p1.playerData()));
							}
						}
						break;
					case 3, 8:
						System.out.println("1. Jump Right");
						moveChoice = input.nextInt();
						if (moveChoice == 1) {
							if(playerTurn % 2 == 0) {
								p2.removePiece(p1.kingRightJump(pieceChoice, p2.playerData()));
							} else {
								p1.removePiece(p2.kingRightJump(pieceChoice, p1.playerData()));
							}
						} else {
							
						}
						break;
					case 4, 7:
						System.out.println("1. Jump Left");
						moveChoice = input.nextInt();
						if(moveChoice == 1) {
							if(playerTurn % 2 == 0) {
								p2.removePiece(p1.kingLeftJump(pieceChoice, p2.playerData()));
							} else {
								p1.removePiece(p2.kingLeftJump(pieceChoice, p1.playerData()));
							}
						}
						break;
				}
				
			} else {
				switch(moves) {
				case 2:
					System.out.println("1. Jump Left\n2. Jump Right");
					moveChoice = input.nextInt();
					if(moveChoice == 1) {
						if(playerTurn % 2 == 0) {
							p2.removePiece(p1.leftJump(pieceChoice, p2.playerData()));
						} else {
							p1.removePiece(p2.leftJump(pieceChoice, p1.playerData()));
						}
					} else if (moveChoice == 2) {
						if(playerTurn % 2 == 0) {
							p2.removePiece(p1.rightJump(pieceChoice, p2.playerData()));
						} else {
							p1.removePiece(p2.rightJump(pieceChoice, p1.playerData()));
						}
					}
					break;
				case 3, 8:
					System.out.println("1. Jump Right");
					moveChoice = input.nextInt();
					if (moveChoice == 1) {
						if(playerTurn % 2 == 0) {
							p2.removePiece(p1.rightJump(pieceChoice, p2.playerData()));
						} else {
							p1.removePiece(p2.rightJump(pieceChoice, p1.playerData()));
						}
					} else {
						
					}
					break;
				case 4, 7:
					System.out.println("1. Jump Left");
					moveChoice = input.nextInt();
					if(moveChoice == 1) {
						if(playerTurn % 2 == 0) {
							p2.removePiece(p1.leftJump(pieceChoice, p2.playerData()));
						} else {
							p1.removePiece(p2.leftJump(pieceChoice, p1.playerData()));
						}
					}
					break;
				}
			}
			
			} else if(king) {
				moves = kingMoves;
				while(moveChoice == 0) {
					switch(moves) {
						case 1:
							System.out.println("1. Move Left\n2. Move Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.kingLeft(pieceChoice);
								} else {
									p2.kingLeft(pieceChoice);
								}
							} else if (moveChoice == 2) {
								if (playerTurn % 2 == 0) {
									p1.kingRight(pieceChoice);
								} else {
									p2.kingRight(pieceChoice);
								}
							}
							
							break;
							
						case 2:
							System.out.println("1. Jump Left\n2. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingLeftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.kingLeftJump(pieceChoice, p1.playerData()));
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingRightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.kingRightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 3:
							System.out.println("1. Move Left\n2. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							
							if(moveChoice == 1) {
								if(playerTurn % 2 == 0) {
									p1.kingLeft(pieceChoice);
								} else {
									p2.kingLeft(pieceChoice);
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingRightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.kingRightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 4:
							System.out.println("1. Move Right\n2. Jump Left\n3. Exit");
							moveChoice = input.nextInt();
							
							if(moveChoice == 1) {
								if(playerTurn % 2 == 0) {
									p1.kingRight(pieceChoice);
								} else {
									p2.kingRight(pieceChoice);
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingLeftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.kingLeftJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 5:
							System.out.println("1. Move Left\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.kingLeft(pieceChoice);
								} else {
									p2.kingLeft(pieceChoice);
								}
							}
							
							break;
							
						case 6:
							System.out.println("1. Move Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.kingRight(pieceChoice);
								} else {
									p2.kingRight(pieceChoice);
								}
							}
							
							break;
							
						case 7:
							System.out.println("1. Jump Left\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingLeftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.kingLeftJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 8:
							System.out.println("1. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.kingRightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.kingRightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 0:
							System.out.println("No valid moves.\n3. Exit");
							moveChoice = input.nextInt();
							break;
					}
					
				}
				

			} else {
				
				while(moveChoice == 0) {
					switch(moves) {
						case 1:
							System.out.println("1. Move Left\n2. Move Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.left(pieceChoice);
								} else {
									p2.left(pieceChoice);
								}
							} else if (moveChoice == 2) {
								if (playerTurn % 2 == 0) {
									p1.right(pieceChoice);
								} else {
									p2.right(pieceChoice);
								}
							}
							
							break;
							
						case 2:
							System.out.println("1. Jump Left\n2. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.leftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.leftJump(pieceChoice, p1.playerData()));
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.rightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.rightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 3:
							System.out.println("1. Move Left\n2. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							
							if(moveChoice == 1) {
								if(playerTurn % 2 == 0) {
									p1.left(pieceChoice);
								} else {
									p2.left(pieceChoice);
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.rightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.rightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 4:
							System.out.println("1. Move Right\n2. Jump Left\n3. Exit");
							moveChoice = input.nextInt();
							
							if(moveChoice == 1) {
								if(playerTurn % 2 == 0) {
									p1.right(pieceChoice);
								} else {
									p2.right(pieceChoice);
								}
							} else if (moveChoice == 2) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.leftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.leftJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 5:
							System.out.println("1. Move Left\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.left(pieceChoice);
								} else {
									p2.left(pieceChoice);
								}
							}
							
							break;
							
						case 6:
							System.out.println("1. Move Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								if (playerTurn % 2 == 0) {
									p1.right(pieceChoice);
								} else {
									p2.right(pieceChoice);
								}
							}
							
							break;
							
						case 7:
							System.out.println("1. Jump Left\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.leftJump(pieceChoice, p2.playerData()));;
								} else {
									p1.removePiece(p2.leftJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 8:
							System.out.println("1. Jump Right\n3. Exit");
							moveChoice = input.nextInt();
							if (moveChoice == 1) {
								jump = true;
								if (playerTurn % 2 == 0) {
									p2.removePiece(p1.rightJump(pieceChoice, p2.playerData()));	
								} else {
									p1.removePiece(p2.rightJump(pieceChoice, p1.playerData()));
								}
							}
							
							break;
							
						case 0:
							System.out.println("No valid moves.\n3. Exit");
							moveChoice = input.nextInt();
							break;
					}
					
				}
			}
		
			if(jump) {
				System.out.println("Would you like to jump again? 1. Yes 2. No");
				moveChoice = input.nextInt();
				if(moveChoice == 1) {
					if(playerTurn % 2 == 0) {
						if(p1.availableJumps(p2.playerData(), pieceChoice) != 0) {
							moveChoice = 3;
						} else if (p1.availableKingJumps(p2.playerData(), pieceChoice) != 0 && p1.isKing(pieceChoice)) {
							moveChoice = 3;
						} else {
							jump = false;
						}
					} else {
						if(p2.availableJumps(p1.playerData(), pieceChoice) != 0) {
							moveChoice = 3;
						} else if (p2.availableKingJumps(p1.playerData(), pieceChoice) != 0 && p2.isKing(pieceChoice)) {
							moveChoice = 3;
						} else {
							jump = false;
						}
					}
				} else {
					jump = false;
				}
			}
			
			if(playerTurn % 2 == 0) {
				p1.setKing();
			} else {
				p2.setKing();
			}
			
			if(p1.checkPieceCount()) {
				playerTurn = 1;
				moveChoice = 3;
				System.out.println("Player Two Wins!");
			} else if(p2.checkPieceCount()) {
				playerTurn = 1;
				moveChoice = 3;
				System.out.println("Player One Wins!");
			}

			if(moveChoice != 3) {
				playerTurn++;
			}
			
			moveChoice = 0;
			king = false;
		}
		
		input.close();
		
	}
}
