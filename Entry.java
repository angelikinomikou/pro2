import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Entry{


	ArrayList<String> entry;

	public void getMenu() {

		Scanner scan = new Scanner(System.in);

		while(true){
			System.out.println("Menu:");
			System.out.println("Press 1: Add Data");
			System.out.println("Press 2: Show Data");
			System.out.println("Press 3: Delete Data");
			System.out.println("Press 4: Change Data");
			int choice =scan.nextInt();
			if (choice==1){
				specifyEntry();
			}
			else if (choice==2){
				Attributes.printAttributesMenu();
				Entry.showData();
			}
			else if (choice==3){
				removeData(entries);
			}

			else if(choice==4){
				changeData(entries);

			} else {
				System.out.println("Can you please select a number from 1 to 4?");
			}
		}
	}


	public static ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();//a static array where all entries are stored

	public Entry() {
		try{
		if (Attributes.attributes.get(0) == null) {
			throw new NoAttributesDeclaredException("There have been no attributes declared for your database.");
		}
		}
		catch(NoAttributesDeclaredException x) {
			JOptionPane.showMessageDialog(null,"There have been no attributes declared for your database.");
		}


	}

	public  void specifyEntry() { //all the fields of an entry(attributes) are asked to be filled by the user
		int i = 0;

		Scanner input = new Scanner(System.in);

		ArrayList<String> entry = new ArrayList<String>();

		while ( i < Attributes.attributes.size()){
			String att = Attributes.attributes.get(i);
			String inputDialog = JOptionPane.showInputDialog("State the "+att+" field of your new entry");
			entry.add(inputDialog);
			i++;
		}
		entries.add(entry);
	}

	public static void showData() {
		for (int j=0; j<entries.size(); j++) {
			String result = "this is empty";
			result = entries.get(j).toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
			System.out.println(+(j+1)+" "+result);
		}
	}

	public void removeData(ArrayList<ArrayList<String>> entries) {
		System.out.println("Which entry would you like to remove? Please give its serial number.");
		Entry.showData();
		Scanner input = new Scanner(System.in);
		int d = input.nextInt();
		for(int i=0; i < entries.size(); i++) {
			if(d==(i+1)) {
				entries.remove(i);
			}
		}
	}

public void changeData(ArrayList<ArrayList<String>> entries) {

		String newValue = "";
		Scanner keyboard = new Scanner(System.in);

		System.out.println("To which entry would you like to make changes? Please select its serial number.");
		Entry.showData();
		Scanner input = new Scanner(System.in);
		int d = input.nextInt();
		int entryIndex = d - 1;
		boolean flag = true;

		outerloop:
		while(flag == true){

			System.out.println("Which attribute would you like to change? Select its serial number");
			Attributes.printNumberedAttributesMenu();
			int attr = input.nextInt();
			int att = attr - 1;


			while(true){
				if(att >= 0 && att <= Attributes.attributes.size()){

					System.out.println("Ok, now you can enter its new value!");
					newValue = keyboard.nextLine();
					ArrayList<String> entry = new ArrayList<String>();
					//entry = entries.get(entryIndex);
					entries.get(entryIndex).set(att, newValue);
					System.out.println("Your changes have been saved!");
					break  outerloop;


			}
			else {

				System.out.println("The given attribute serial number is incorrect");
				break outerloop;

			}

		}
	}
}

}
