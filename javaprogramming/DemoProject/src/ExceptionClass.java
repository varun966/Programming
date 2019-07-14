import java.util.*;
public class ExceptionClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i=1;
		Scanner input=new Scanner(System.in);
		
		do {
		try {
		System.out.println("Enter the first number");
		int number1=input.nextInt();
		System.out.println("Enter the second number");
		int number2=input.nextInt();
		int sum=number1/number2;
		System.out.println(sum);
		i=2;
		}
		catch(Exception e)
		{
			System.out.println("dont do that");
		}
		
		}
		
		while(i==1);
		{
		System.out.println("programming is cool");
		
		}
		
	}

}
