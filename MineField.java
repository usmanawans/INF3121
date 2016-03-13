import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
        private boolean boom;

	private final int rowMax = 5;
	private final int colMax = 10;

	/*
	*	This is a constructor for MainField obejct
	*/
	MineField(){

		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;

		initMap();

		int counter2=15;
		int randomRow,randomCol;
		Random RGenerator=new Random();

		while(counter2>0){

			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);

			if(trymove(randomRow,randomCol)){
				counter2--;
			}
		}
	}


	/*
	*	it updats the game's baord
	*/
	private void initMap(){

		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}

	}

	/*
	*	This It evaluates the game input numbers
	*/
	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}

	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();

	}

	/*
	*	this function return the calculated character
	*/
	private char drawChar(int row, int col) {
            
                int count = 0;

		if(visible[row][col]){
			if(mines[row][col]) return '*';

			count = initDrawChar( row, col );
		}
		else{
			if(boom){
				return '-';
			}
		}

		// it will return the digits as characters
		return Character.forDigit( count, 10 );
		}

	public int initDrawChar( int row, int col ){

		int count=0;

		for(int irow=row-1;irow<=row+1;irow++){
			for(int icol=col-1;icol<=col+1;icol++){
				if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
					if(mines[irow][icol]) count++;
				}
			}
		}
                return count;
	}

	public boolean getBoom(){

		return boom;
	}


	/*
	*	this function gets the values and split them into valied moves
	*/
	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row,col;

		try{
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}

		if(legalMoveValue(row,col))
			return true;
		else
			return false;
	}

	/*
	*	This function evaluates the splited input values
	*/
	private boolean legalMoveValue(int row, int col) {

		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;
		}
		else
			visible[row][col]=true;


		if(mines[row][col]){
			boom();
			return false;
		}
		return true;
	}

	/*
	*	this function shows the playing board
	*/
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		initShow();
		System.out.println("   ---------------------");
	}

	public void initShow() {
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));
			}
			System.out.println(" |");
		}
	}

}
