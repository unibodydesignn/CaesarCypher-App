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
  public static ArrayList<String> cryptionWords(ArrayList<Integer> cryptCount, ArrayList<Integer> lastDigit, ArrayList<String> wordList, ArrayList<String> crypted) {

      if(cryptCount.size() == 0 || lastDigit.size() == 0 || wordList.size() == 0)
          return null;

      if(cryptCount.size() != wordList.size())
          return null;

      if(crypted.size() == 0)
          return null;

      if(wordList.size() == 0)
          return null;

      if(wordList.size() != crypted.size())
          return null;

      else {
          String letters = "abcdefghijklmnopqrstuvwxyz";
          for(int i = 0; i < wordList.length(); i++) {

              String cyberedWord = "";
              String toBeCybered = wordList.get(i).toLowerCase();
              int shiftAmount = cryptCount.get(i);

              for(int j = 0; j < toBeCybered.length(); j++) {

                  int charPosition = letters.indexOf(cyberedWord.charAt(j));
                  int keyVal = (shiftAmount + charPosition) % 26;
                  char replaceVal = letters.charAt(keyVal);
                  cyberedWord += replaceVal;
                  cyberedWord += lastDigit.get(i);
              }

              crypted.add(cyberedWord);
          }

          return crypted;
      }
  }

    public static void main(String[] args) {
        ArrayList<Integer> clist = new ArrayList<>(5);
        ArrayList<Integer> lastdigitlist = new ArrayList<>(5);
        ArrayList<String> wordlist = new ArrayList<>(5);
        ArrayList<String> cryptedlist;

        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          //java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            //int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            String word = sc1.next().substring(0, sc1.next().indexOf(" "));
            int i = Integer.parseInt(sc1.next().substring(sc1.next().indexOf(" ")));
            wordlist.add(word);
            clist.add(i);
          }

          System.out.println(inputList);


          //String input2 = req.queryParams("input2").replaceAll("\\s","");
          //int input2AsInt = Integer.parseInt(input2);

          cryptedlist = App.cryptionWords(clist, lastdigitlist, wordlist, cryptedlist);

          //boolean result = App.search(inputList, input2AsInt);

         Map map = new HashMap();
          //map.put("result", result);
          map.put("result", cryptedlist);
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
