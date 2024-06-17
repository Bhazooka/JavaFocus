public class Flowers
{
	// Main entry point
	public static void main(String[] args)
	{
		final String d3="ROSES";
		final String d7="VIOLETS";
		final String d21="TULIPS";
		
		for (int i = 1; i <= 50; i++)
		{
			// For better output to check our answers:
			System.out.printf("%2d:\t", i);
			if (i % 3 == 0 && i % 7 == 0)
			{
				System.out.println(d21);
			}
			else if (i % 7 == 0)
			{
				System.out.println(d7);
			}
			else if (i % 3 == 0)
			{
				System.out.println(d3);
			}
			else
			{
				System.out.println(i);
			}
		}
	}
}
