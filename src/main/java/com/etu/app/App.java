package com.etu.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{
    /*public static boolean search(ArrayList<Integer> array, int e) {
      System.out.println("inside search");
      if (array == null) return false;

      for (int elt : array) {
        if (elt == e) return true;
      }
      return false;
  }*/
  public static ArrayList<String> cryptionWords(ArrayList<String> wordList, ArrayList<String> crypted, ArrayList<Integer> cryptCount, ArrayList<Integer> lastDigit) {

      int count = 0;    
      if(cryptCount.size() == 0 || lastDigit.size() == 0 || wordList.size() == 0)
          return null;

      count++;

      if(cryptCount.size() != wordList.size())
          return null;

      count++;

      if(crypted.size() == 0)
          return null;

      count++;

      if(wordList.size() == 0)
          return null;

      count++;

      if(wordList.size() != crypted.size())
          return null;

      count++;

      if(count == 5)    {  
        for(int m = 0; m < wordList.size(); m++) {
            if(wordList.get(m).equals(""))
                return null;
        } }

        count++;

        if(count == 6) {
             for(int k = 0; k < cryptCount.size(); k++) {
                if(cryptCount.get(k) < 0)
                    return null;
        } }

      else {
          ArrayList<String> newlist = new ArrayList<>();

          String letters = "abcdefghijklmnopqrstuvwxyz";
          for(int i = 0; i < wordList.size(); i++) {

              String cyberedWord = "";
              String toBeCybered = wordList.get(i).toLowerCase();
              int shiftAmount = cryptCount.get(i);

              for(int j = 0; j < toBeCybered.length(); j++) {

                  int charPosition = letters.indexOf(toBeCybered.charAt(j));
                  int keyVal = (shiftAmount + charPosition) % 26;
                  char replaceVal = letters.charAt(keyVal);
                  cyberedWord += replaceVal;
                  
              }
                cyberedWord += lastDigit.get(i);
                cyberedWord += crypted.get(i);
                newlist.add(cyberedWord);
          }

          return newlist;
      }

      return null;
  }

    public static void main(String[] args) {



        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          //java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          ArrayList<String> wordlist = new ArrayList<>(5);
          while (sc1.hasNext())
          {
            //int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            String word = sc1.next();
            wordlist.add(word);
          }


          //System.out.println(inputList);
          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          sc2.useDelimiter("[;\r\n]+");
          //java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          ArrayList<String> cryptedList = new ArrayList<>();
          while (sc2.hasNext())
          {
            //int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            String word = sc2.next();
            cryptedList.add(word);
          }


          String input3 = req.queryParams("input3");
          java.util.Scanner sc3 = new java.util.Scanner(input3);
          sc3.useDelimiter("[;\r\n]+");

          ArrayList<Integer> shiftlist = new ArrayList<>(5);
          while(sc3.hasNext()) {

              int i = Integer.parseInt(sc3.next().replaceAll("//s",""));
              shiftlist.add(i);
          }

          String input4 = req.queryParams("input4");
          java.util.Scanner sc4 = new java.util.Scanner(input4);
          sc4.useDelimiter("[;\r\n]+");
          ArrayList<Integer> lastdigitlist = new ArrayList<>(5);
          while(sc4.hasNext()) {

              int j = Integer.parseInt(sc4.next().replaceAll("//s", ""));
              lastdigitlist.add(j);
          }


          ArrayList<String> nl = App.cryptionWords(wordlist, cryptedList, shiftlist, lastdigitlist);

          //boolean result = App.search(inputList, input2AsInt);

         Map map = new HashMap();
          //map.put("result", result);
          map.put("result", nl);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
