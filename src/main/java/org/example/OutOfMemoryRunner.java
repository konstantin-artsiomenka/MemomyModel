package org.example;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryRunner {

  private static final int INITIAL_CAPACITY = 1_000_000;
  private final List<List<String>> infiniteList = new ArrayList<>();
  private List<String> stringsList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx400m -XX:+UseSerialGC -XX:SurvivorRatio=1 -XX:NewRatio=3 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    OutOfMemoryRunner outOfMemoryRunner = new OutOfMemoryRunner();
    outOfMemoryRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      stringsList = new ArrayList<>(INITIAL_CAPACITY);
      infiniteList.add(new ArrayList<>(INITIAL_CAPACITY));
      Thread.sleep(1000L);
    }
  }
}
