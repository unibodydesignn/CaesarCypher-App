package com.etu.app;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );

    }

    public static boolean cryptionWords(Integer[] cryptCount, Integer[] lastDigit, String[] wordList, String[] crypted) {

        if(cryptCount.length == 0 || lastDigit.length == 0 || wordList.length == 0)
            return false;

        if(cryptCount.length != wordList.length)
            return false;

        if(crypted.length == 0)
            return false;

        if(wordList.length == 0)
            return false;

        if(wordList.length != crypted.length)
            return false;

        else {
            String letters = "abcdefghijklmnopqrstuvwxyz";
            for(int i = 0; i < wordList.length; i++) {

                String cyberedWord = "";
                String toBeCybered = wordList[i].toLowerCase();
                int shiftAmount = cryptCount[i];

                for(int j = 0; j < toBeCybered.length(); j++) {

                    int charPosition = letters.indexOf(cyberedWord.charAt(j));
                    int keyVal = (shiftAmount + charPosition) % 26;
                    char replaceVal = letters.charAt(keyVal);
                    cyberedWord += replaceVal;


                }

                crypted[i] = cyberedWord;
            }

            return true;
        }



















    }
}
