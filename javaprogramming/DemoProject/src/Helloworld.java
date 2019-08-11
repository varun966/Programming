import java.util.*;

public class Helloworld {

	//private char[] x;

	//private static final char[] x = null;

	int x= 5;
	
	
	static int add(int x,int y)
	{
		return x+y;

	}
	
	static int compare(int x, int y)
	{
		if(x>y) {
			
			System.out.println("highest value is:" + x);
			return x;
		}
		else { 
			
			System.out.println("highest value is" + y);
		return y;
		}
	}

	static void myMethod(String fname)
	{
		System.out.println("why so sweet ?"+" " + fname);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Scanner scan = new Scanner(System.in);
		
		System.out.println("enter the input");
		
		int x=scan.nextInt();
		
		scan.close(); 
		
		//int y=x;
		
		//System.out.println("value of y :"+ y);
	
		*/
		
		//Helloworld Obj1= new Helloworld();
		
		//System.out.println(Obj1.x);
		
		myMethod("Sugarcrush");
		System.out.println(add(5,10));
		System.out.println(compare(20,25));
	}

}
