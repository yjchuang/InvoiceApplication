import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class InvoiceApplication {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		BigDecimal[] price = new BigDecimal[100];
		BigDecimal subTotal, grantTotal, taxTotal;
		BigDecimal taxRate = BigDecimal.ZERO;
		
		int numberOfPrice = 0;

		//BigDecimal pi = new BigDecimal("3.14159265358979323846");
		          
		//System.out.println(pi);
		
		System.out.println("Tax Rate? ");
		taxRate = sc.nextBigDecimal();
		sc.nextLine();
		
		numberOfPrice = GetPrice(price, sc);
		
		System.out.println("-------------------------------------------------------");
		System.out.println("\nReceipt: ");
		
		PrintPrice(price, numberOfPrice);
		      
		subTotal = CalculateSubTotalPrice(price, numberOfPrice);
		
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);

		System.out.print(nf.format(subTotal));
		System.out.println("   Sub Total\n");
		
		taxTotal = taxRate.multiply(subTotal);
		System.out.print(nf.format(taxTotal));
		System.out.println("   Tax\n");
		
		grantTotal = taxTotal.add(subTotal);
		System.out.print(nf.format(grantTotal));
		System.out.println("   Grant Total\n");
		
		
		//double d = yourDoubleValue;  
		//String formattedData = String.format("%.02f", d);
	}
	
	public static BigDecimal CalculateSubTotalPrice(BigDecimal[] price, int count)
	{
		BigDecimal subTotal= BigDecimal.ZERO;

		for(int i = 0; i < count; ++i)
		{
			subTotal = subTotal.add(price[i]);
		}
		return(subTotal);
	}
	
	public static void PrintPrice(BigDecimal[] price, int itemCount)
	{
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);

		for (int i = 0; i < itemCount-1; ++i)
		{
			System.out.println(nf.format(price[i]));
		}
	}
	
	public static int GetPrice(BigDecimal[] price, Scanner sc)
	{
		int numberOfItem = 0;
		
		System.out.println();
		
		for(int i = 0; i < 100; ++i) 
		{
			System.out.printf("Price # %d:", i+1);
			
			price[i] = sc.nextBigDecimal();
			sc.nextLine();
			++numberOfItem;
			System.out.println();
			
			if (price[i].compareTo(new BigDecimal("0.00")) == 0)
			{
				return numberOfItem;
			}
		}
		
		return numberOfItem;
		
		
	}

}
