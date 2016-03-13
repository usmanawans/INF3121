import java.util.Scanner;


/*
*	This constructor initializes the default class values
*/

public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5;
	private String[] name;
	private int[] record;
	private int last;

	Ranking(){
		name=new String[MAX_PEOPLE_LIMIT];
		record=new int[MAX_PEOPLE_LIMIT];

		last=0;
	}

/*
*	This function records the name, and recent scire tby the player
* It updates the record at 5th position of the list
*/
	public void recordName(int result) {
		System.out.print("\n Please enter your name -");
		Scanner in=new Scanner(System.in);
		String newName=in.nextLine();
		if((last==MAX_PEOPLE_LIMIT)&&record[MAX_PEOPLE_LIMIT-1]>result){
			System.out.println("\nSorry you cannot enter top "+(MAX_PEOPLE_LIMIT)+" players");
			return;
		}


		else{
			name[MAX_PEOPLE_LIMIT-1]=newName;
			record[MAX_PEOPLE_LIMIT-1]=result;
			last++;


		}

		sort();
		show();
	}

/*
*	This function shows the record list
*/
	public void show() {
		if(last==0){
			System.out.println("Still no results");
			return;
		}
		System.out.println("N Name\t\tresult");
		for(int i=0;i<last;i++){
			System.out.println((i+1)+" "+name[i]+"\t"+record[i]);
		}
	}

	/*
*	This function sorts the record list
*/
	private void sort(){

		for( int i=0; i < MAX_PEOPLE_LIMIT; i++   ){
			for( int j=0; j < (MAX_PEOPLE_LIMIT - 1); j++ )

				// Swap if
				if( record[j] < record[j+1] ){
					int backupScore = record[j];
					record[j] = record[j+1];
					record[j+1] = backupScore;

					String backupString = name[j];
					name[j] = name[j+1];
					name[j+1] = backupString;
				}
		}
	}
}
