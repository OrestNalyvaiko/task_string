package com.nalyvaiko.menu;

import com.nalyvaiko.stringutils.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

  private Map<String, String> menu;
  private Map<String, Runnable> methodsMenu;

  private Locale locale;
  private ResourceBundle bundle;

  public Menu() {
    locale = new Locale("en");
    bundle = ResourceBundle.getBundle("Menu", locale);
    methodsMenu = new LinkedHashMap<>();
    setMenu();

    methodsMenu.put("1", this::testStringUtils);
    methodsMenu.put("2", this::internationalizeMenuUkrainian);
    methodsMenu.put("3", this::internationalizeMenuEnglish);
    methodsMenu.put("4", this::splitWithTheYou);
    methodsMenu.put("5", this::replaceVowelsWithUnderscores);
    methodsMenu.put("6", this::checkSentence);
  }

  private void internationalizeMenuUkrainian() {
    locale = new Locale("uk");
    bundle = ResourceBundle.getBundle("Menu", locale);
    setMenu();
    start();
  }

  private void internationalizeMenuEnglish() {
    locale = new Locale("en");
    bundle = ResourceBundle.getBundle("Menu", locale);
    setMenu();
    start();
  }

  private void setMenu() {
    menu = new LinkedHashMap<>();

    menu.put("1", bundle.getString("1"));
    menu.put("2", bundle.getString("2"));
    menu.put("3", bundle.getString("3"));
    menu.put("4", bundle.getString("4"));
    menu.put("5", bundle.getString("5"));
    menu.put("6", bundle.getString("6"));
    menu.put("Q", bundle.getString("Q"));
  }

  private void showMenu() {
    System.out.println("\nMENU:");
    for (String str : menu.values()) {
      System.out.println(str);
    }
  }

  public void start() {
    String keyMenu;
    Scanner input = new Scanner(System.in);
    do {
      showMenu();
      System.out.print("Please, select action ");
      keyMenu = input.nextLine().toUpperCase();
      try {
        System.out.println();
        methodsMenu.get(keyMenu).run();
      } catch (Exception e) {
        if (!keyMenu.equals("Q")) {
          System.out.println("Incorrect action\n");
        }
      }
    } while (!keyMenu.equals("Q"));
  }

  private void splitWithTheYou() {
    System.out.println("Enter text for split");
    Scanner input = new Scanner(System.in);
    String text = input.nextLine();
    String[] splitTxt = text.split("you|the");
    System.out.println("\nText after split");
    for (String str : splitTxt) {
      System.out.print(str);
    }
    System.out.println();
  }

  private void replaceVowelsWithUnderscores() {
    System.out.println("Enter text for replace");
    Scanner input = new Scanner(System.in);
    String text = input.nextLine();
    text = text.replaceAll("[AaEeIiJjOoYyUu]", "_");
    System.out.println("\nText after replace vowels with \"_\"");
    System.out.println(text);
  }

  private void checkSentence() {
    System.out.println("Enter sentence for check");
    Scanner input = new Scanner(System.in);
    String text = input.nextLine();
    String pattern = "^[A-Z]+[^\\.]*\\.$";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(text);
    if (m.matches()) {
      System.out.println("Begins with a capital, ends with period");
    } else {
      System.out.println("Doesn't meet the requirements");
    }
  }

  private void testStringUtils() {
    System.out.print("String utils string: ");
    StringUtils utils = new StringUtils();
    utils.addToParameters(11)
        .addToParameters(22.05)
        .addToParameters("Nalyvaiko");
    System.out.println(utils.concat());
  }
}
