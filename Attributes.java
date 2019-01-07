import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.ArrayList;

public class Attributes {

	public Attributes() {}


		public static ArrayList<String> attributes = new ArrayList<String>();
		Scanner input = new Scanner (System.in);


		int i = 0;


	public void setAttributes() {

		String attribute = new String();


		while (attribute.equals("0") == false) {
	    	attribute = JOptionPane.showInputDialog("Please enter the names of the attributes(when done press 0).");
			attributes.add(attribute);
			attributes.trimToSize();
			if (attribute.equals("0") == true) {
				attributes.remove(attributes.size() - 1);
			}
		}
	}

	public static void printAttributesMenu() { //prints the names of the attributes the user haw declared
		int i = 0;
		for (i = 0; i < attributes.size(); i++) {
			System.out.printf("  %s", attributes.get(i));
		}
		System.out.println("");
	}

	public static void printNumberedAttributesMenu() { //prints the names of the attributes the user has declared with serial numbers
			int i = 0;
			int count = 1;
			for (i = 0; i < attributes.size(); i++) {
				System.out.printf(+count+". "+attributes.get(i)+" ");
				count++;
			}
			System.out.println("");
	}

}

