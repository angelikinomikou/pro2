import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Entry{


	ArrayList<String> entry;

	public void getMenu() {

		String[] options = {"Add Data","Show Data",
			"Delete Data","Change Data"};

		while(true) {

			int n = JOptionPane.showOptionDialog(null,
			    "Menu:",
			    "Database NAVAJOS JAVEIROS",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    options,
    			null);

			if (n == 0) {

				specifyEntry();

			} else if (n == 1) {

				Attributes.printAttributesMenu();
				Entry.printData();

			} else if (n == 2) {

				removeData(entries);

			} else if (n == 3) {

				changeData(entries);

			}
		}
	}


	public static ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();//a static array where all entries are stored

	public Entry() {

		try{

			if (Attributes.attributes.get(0) == null) {

				throw new NoAttributesDeclaredException("There have been no attributes declared for your database.");

			}

		} catch (NoAttributesDeclaredException x) {

			JOptionPane.showMessageDialog(null,"There have been no attributes declared for your database.");

		}

	}

	public  void specifyEntry() { //all the fields of an entry(attributes) are asked to be filled by the user

		int i = 0;

		Scanner input = new Scanner(System.in);

		ArrayList<String> entry = new ArrayList<String>();

		while (i < Attributes.attributes.size()) {

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

		Entry.printData();

		String inputDialog = JOptionPane.showInputDialog("Which entry would you like to remove? Please give its serial number.");

		int d = Integer.parseInt(inputDialog);

		for(int i=0; i < entries.size(); i++) {

			if (d==(i+1)) {

				entries.remove(i);

			}

		}

	}

	public static void printData() {  //prints all entries with their respective attributes

			String[] column = new String[Attributes.attributes.size() + 1];
			String[][] data = new String[Entry.entries.size()][Attributes.attributes.size() + 1 ];

			int m = 0 ;

			column[0] = "#";

			for (int j = 0; j < Attributes.attributes.size(); j++) {

				column[j + 1] = Attributes.attributes.get(j);

			}

			for (int k = 0; k < entries.size(); k++) {

				ArrayList<String> currentArrayList = entries.get(k);

				for (m = 0; m < Attributes.attributes.size(); m++) {

					data[k][0] = String.valueOf(k + 1);
					data[k][m + 1] = currentArrayList.get(m);

				}

				m = 0;

			}

			JTable jt = new JTable(data,column);
			JFrame frame = new JFrame("All Database Åntries");
			JScrollPane sp = new JScrollPane(jt);
			frame.add(sp);
			frame.setSize(300, 400);
			frame.setVisible(true);

	}


	public void changeData(ArrayList<ArrayList<String>> entries) {

		Entry.printData();

		String newValue = "";

		String inputDialog = JOptionPane.showInputDialog("To which entry would you like to make changes? Please select its serial number.");

		int d =  Integer.parseInt(inputDialog);
		int entryIndex = d - 1;

		boolean flag = true;

		outerloop:
		while(flag == true){

			Object[] choices = Attributes.createAttributesArray();
			String s = (String)JOptionPane.showInputDialog(
								null,
			                    "Which attribute would you like to change?\n",
			                    "Customized Dialog",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    choices,
                   				"");

			int attr = 0;

			for (int i = 0; i < Attributes.attributes.size(); i++) {

				if (s.equals(Attributes.attributes.get(i))) {
					 attr = i;

				}

			}

			while(true) {

					String inputDialog2 = JOptionPane.showInputDialog("Ok, now you can enter its new value!");

					ArrayList<String> entry = new ArrayList<String>();

					entries.get(entryIndex).set(attr, inputDialog2);

					JOptionPane.showMessageDialog(null,"Your changes have been saved!");

					break  outerloop;

			}

		}

	}

}
