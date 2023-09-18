package wordle;

import java.util.Scanner;

public class Wordle 
{

	public static boolean containsChar(String answer, char letter) //checking answer if it contains a letter
	{
		boolean rbool = false;
		
		for (int i = 0; i < answer.length(); i++)
		{
			char ch = answer.charAt(i);
			if (ch == letter) 
			{
				rbool = true;
				}
		}
		
		return rbool;
	}
	
	public static boolean isValidInput(String uinput) // making sure the input is valid
	{
		boolean rbool = true;
		if (uinput.length() == 5 && uinput.matches("[a-zA-Z]+")) 
		{
			rbool = true;
		}
		else
		{
			return false;
		}
		return rbool;
	}
	
	public static boolean isCharCorrect(String answer, char letter, int position) //making sure that position matches 
	{ 
		boolean robool = true;
		
		if (answer.charAt(position) == letter)
		{
			robool = true;
		}
		
		else
		{
			robool = false;
		}
		
		return robool;
	}
	
	public static String removeWhitespace(String uinput) //remove all white space if the user inputs it
	{
		String newInput = uinput.replaceAll(" ", "");
		newInput = newInput.replaceAll("\t", "");
		newInput = newInput.replaceAll("\n", "");
		System.out.println(newInput);
		return newInput;
	}
	
	public static String generateRoundFeedback(String answer, String uinput) //was difficult to 
	{
		String returnvalue = "_____";
		
		boolean test1 = false;
		boolean test2  = false;
		char currentchar = ' ';
		
		uinput = uinput.toUpperCase();
		
		for(int i = 0; i < 5; i++)
		{
			currentchar = uinput.charAt(i);
			
			int x = i -1;
				
			test1 = containsChar(answer, currentchar);
				
			if (test1)
			{
				test2 = isCharCorrect(answer, currentchar, i);
				if(test2)
				{
					returnvalue = returnvalue.substring(0,x+1)+ currentchar + returnvalue.substring(x+1,4);
				}
			
				else if (test1 && !test2)
				{
					returnvalue = returnvalue.substring(0,x+1)+ '*' + returnvalue.substring(x+1,4);
				}
				else
				{
					returnvalue = returnvalue.substring(0,x+1)+ '_' + returnvalue.substring(x+1,4);
				}
			}
		}
		returnvalue = returnvalue.toUpperCase();
		return returnvalue;
	}
	
	public static boolean playRound(String answer, Scanner keyboard) 
	{
		boolean rbool = false;
		
		System.out.print("Your guess: ");
		String uinput = keyboard.nextLine();
		uinput = removeWhitespace(uinput);
		boolean test1 = isValidInput(uinput);
		if(test1)
		{
			System.out.println(generateRoundFeedback(answer, uinput));
			
			uinput = uinput.toUpperCase();
			answer = answer.toUpperCase();
			
			if (answer.equals(uinput))
			{
				rbool = true;
			}
		}
		return rbool;
	}
	
	public static void playGame(String answer, Scanner keyboard)
	{
		
		System.out.println("Welcome to WORDLE!");
		System.out.println("You will have 6 chances to guess a 5 letter word! Round begins now!");
		
		String response = "YOU LOSE";
		
		for (int i = 0; i < 5; i++ )
		{
			boolean test = playRound(answer, keyboard);
		
			if (test)
			{
				response = "YOU WIN";
				break;
			}
		}
		
		System.out.println(response);
	}
	
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		playGame("APPLE", keyboard);
		//playGame(“APPLE”, new Scanner(System.in));
	}
}
