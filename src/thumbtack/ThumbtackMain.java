package thumbtack;

import java.util.Scanner;

public class ThumbtackMain {

	private static Scanner scanner = new Scanner(System.in);
	public enum Commands {
	    BEGIN,SET,GET,UNSET,
	    NUMEQUALTO,ROLLBACK,COMMIT,END 
	}
	
	public static void processCommand(String[] input) {
		Commands cmd = Commands.valueOf(input[0].toUpperCase());
		Thumbtack test = new Thumbtack(input);
		switch (cmd) {
	        case BEGIN:
	        	test.processBegin();
	        	break;
	        case SET:
	        	test.processSet();
	        	break;
	        case GET:
	        	test.processGet();
	        	break;
	        case UNSET:
	        	test.processUnSet();
	        	break;
	        case NUMEQUALTO:
	        	test.processNumEqualTo();
	        	break;
	        case ROLLBACK:
	        	test.processRollBack();
	        	break;
	        case COMMIT:
	        	test.processCommit();
	        	break;
	        case END:
	        	break;
	        default:
	        	System.out.println("Invalid Command");
	        	break;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] src; 
		do{
			String input = scanner.nextLine();
			src = input.split(" ");
			processCommand(src);
		}while(! src[0].equals("END"));
	}
}
