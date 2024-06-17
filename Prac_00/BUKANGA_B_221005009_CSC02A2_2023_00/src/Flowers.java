public class Flowers
{
	public static void main (String[] args)
	{
		int counter = 0;
		for(int i = 0; i <= 50; i++)
		{
			counter = counter + 1;

			if (counter % 3 == 0) {
				System.out.println("ROSES");
			}
			if(counter % 7 == 0) {
				System.out.println("VIOLETS");
			}
			if(counter % 3 == 0 && counter % 7 == 0) {
				System.out.println("TULIPS");
			}
			else {
				System.out.println(counter);
			}
		}
		
	}
}