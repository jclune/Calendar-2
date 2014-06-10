//code by Justin Clune
import java.util.*;
import java.text.*;
import java.io.*;

public class Calendar_v2{
	public static void main(String[] args){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int intYear = 0;
		int intMonth = 0;

		//try-catch for date input and while loops to wait for valid year/month
		try{
			do{
				System.out.println("年を入力してください（例：2014）");
				String inputYear = reader.readLine();
				intYear = Integer.parseInt(inputYear);
 			}while(intYear <= 0);
			do{
                                System.out.println("月を入力してください（例：4）");
                                String inputMonth = reader.readLine();
                                intMonth = Integer.parseInt(inputMonth);
                        }while(intYear <= 0);
	
			makeCalendar(intYear, intMonth);
		
		}catch(IOException e){System.out.println(e);
	       	}catch(NumberFormatException e){
			System.out.println("数値の形式が正しくありません。");
                }
	}
	public static void makeCalendar(int intYear, int intMonth){
		Calendar cal = Calendar.getInstance();

		//Add a date instance based on user input and call it today
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.YEAR, intYear);
		cal.set(Calendar.MONTH, intMonth - 1);
		Date today = cal.getTime();

		//first day of the month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = cal.getTime();

		//first  day of week (1 = Sun, 2 = Mon, 3 = Tues...7) and number of days in month
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int numberDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");

		//today and weekday labels	
		System.out.println(year.format(today) + "年" + month.format(today) + " 月");
		System.out.println("日 月 火 水 木 金 土");
		
		//3 characters per day and 7 days, so  each row is 21 characters long
		String spaces = "                     ";

		//make one string for all days with correct spacing for single digits
		String allDays = "";
		for (int i = 1; i <= numberDays; i++){
			if (i < 10){
				allDays = allDays + " " + i + " ";
			} else{
				allDays = allDays + i + " ";
			}
		}

		//output rows of days by taking substrings. indexes use the first day of the week
		int start = 3*(dayOfWeek-1);
		System.out.println(spaces.substring(0,start)+allDays.substring(0,21-start));		
		System.out.println(allDays.substring(21-start,42-start));
                System.out.println(allDays.substring(42-start,63-start));
                System.out.println(allDays.substring(63-start,84-start));

		//if the month has 6 rows, then print the last 2 rows. else print 1.
		if ((numberDays == 30 && dayOfWeek == 7) || (numberDays == 31 && dayOfWeek >= 6)){
	                System.out.println(allDays.substring(84-start,105-start));
	                System.out.println(allDays.substring(105-start,allDays.length()));
		}else{
                	System.out.println(allDays.substring(84-start,allDays.length()));
		}
	}
}
