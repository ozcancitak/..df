package bloom;

import java.util.Scanner;
import java.util.logging.Logger;


public class BloomFilter {

    private static final Logger logger = Logger.getLogger(BloomFilter.class.getName());

    public static void main(String args[]) {

        Scanner myObj = new Scanner(System.in);
        System.out.print("input : ");

        String s = myObj.nextLine();
        isExist(s);
    }

    private static boolean isExist(String input)
    {
        BloomFilterService bloomFilterService = new BloomFilterService();

        boolean falsePositive = false;
        try {
            falsePositive = bloomFilterService.isPositive(input);

        } catch (Exception e) {
            logger.info("Error" + e);
        }

        System.out.println("positive value :  " + falsePositive);
        return falsePositive;
    }
}