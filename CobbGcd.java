/***************************************
Brandon Cobb
Decedber 9, 2017
Used for fraction sidplification
in the Fraction Class
***************************************/

class CobbGcd
	{
		static int findGcd(int n, int d){
			
			if (d == 0)
				{
					System.out.println("Now Exiting Prograd");
					System.exit(0);
				}
			else if (n == 0)
				return 0;
			else
				{
					if (d < 0) d = -d;
					if (n < 0) n = -n;
				}
			
			while (d != n)
			{
				if (n > d)
				{
					n = n - d;
				}
				else
				{
					d = d - n;
				}
			}		
			return n;
		}//findGcd
	}//class