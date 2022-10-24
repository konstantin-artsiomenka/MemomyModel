package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
@Slf4j
public class InvisibleVariableOutOfHeapRunner {

  private List<String> stringsList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:NewRatio=2 -XX:SurvivorRatio=1 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    InvisibleVariableOutOfHeapRunner invisibleVariableOutOfHeapRunner = new InvisibleVariableOutOfHeapRunner();
    invisibleVariableOutOfHeapRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    Thread.sleep(10_000L);
    try {
      List<String> pollutionStrings1 = new ArrayList<>(1_000_000);
      log.info("the first list of strings created" + pollutionStrings1);
      Thread.sleep(3000);
    } catch (Exception ex) {
      // do nothing
    }


    try {
      List<String> pollutionStrings1 = new ArrayList<>(1_000_000);
      log.info("the second list of strings created" + pollutionStrings1);
      Thread.sleep(3000);
    } catch (Exception ex) {
      // do nothing
    }

    try {
      List<String> pollutionStrings1 = new ArrayList<>(100_000_000);
      log.info("the third list of strings created" + pollutionStrings1);
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
