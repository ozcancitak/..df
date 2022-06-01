package bloom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;


public class BloomFilterService {

    int bitArray[] , size = 1000;

    BloomFilterService() {
        bitArray = new int[size];

        for (int i = 0; i < size; i++)
        {
            bitArray[i] = 0;
        }
    }


    public boolean isPositive(String str) throws Exception {

        URL text = new URL("http://codekata.com/data/wordlist.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(text.openStream()));

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String line = bufferedReader.readLine();

        while (line != null)
        {
            messageDigest.update(line.getBytes(), 0, line.length());
            String hashValue = new BigInteger(1, messageDigest.digest()).toString(10);
            setPosition(hashValue);
            line = bufferedReader.readLine();

        }
        bufferedReader.close();

        String wordMD5 = textToMD5(str);

        boolean isAvailable = true;
        int textLengthMD5 = getMD5WordLength(wordMD5);
        int remainCount = textLengthMD5;

        for (int i = 0; i < textLengthMD5; i = i + 3)
        {
            if (remainCount < 3 && remainCount > 0)
            {
                String remainingHash = wordMD5.substring(i, textLengthMD5);
                int intValue = Integer.parseInt(remainingHash);

                if (bitArray[intValue] != 1) {
                    isAvailable = false;
                    break;
                }

            }

            if (remainCount > 3) {
                String subPart = wordMD5.substring(i, i + 3);
                int intValue = Integer.parseInt(subPart);
                if (bitArray[intValue] != 1) {
                    isAvailable = false;
                    break;
                }
            }
            remainCount = remainCount - 3;
        }
        return isAvailable;
    }


    private void updateArray(int position) {
        bitArray[position] = 1;

    }


    private void setPosition(String hashValueMD5) {

        int wordLengthMD5 = getMD5WordLength(hashValueMD5);
        int remainCount = wordLengthMD5;

        for (int i = 0; i < wordLengthMD5; i = i + 3) {

            if (remainCount < 3 && remainCount > 0) {
                String remainingHash = hashValueMD5.substring(i, wordLengthMD5);
                int intValue = Integer.parseInt(remainingHash);
                updateArray(intValue);
            }

            if (remainCount > 3) {
                String subPart = hashValueMD5.substring(i, i + 3);
                int intValue = Integer.parseInt(subPart);
                updateArray(intValue);
            }
            remainCount = remainCount - 3;
        }
    }


    private String textToMD5(String text) throws Exception
    {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(text.getBytes(), 0, text.length());
        String hashString = new BigInteger(1, messageDigest.digest()).toString(10);
        return hashString;
    }

    private int getMD5WordLength(String wordMD5)
    {
        return wordMD5.length();
    }
}