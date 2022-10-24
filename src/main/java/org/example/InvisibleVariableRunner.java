package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
public class InvisibleVariableRunner {

  private List<String> stringsList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:NewRatio=2 -XX:SurvivorRatio=1 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    InvisibleVariableRunner invisibleVariableRunner = new InvisibleVariableRunner();
    invisibleVariableRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    try {
      List<String> pollutionStrings1 = new ArrayList<>(1_000_000);
      System.out.println("the first list of strings created" + pollutionStrings1);
      Thread.sleep(3000);
    } catch (Exception ex) {
      // do nothing
    }

    while (true) {
      stringsList = new ArrayList<>(1_000_000);
      Thread.sleep(1000L);
    }
  }
}
