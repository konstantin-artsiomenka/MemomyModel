package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
@Slf4j
public class WeakReferenceRunner {

  private List<String> stringsList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:NewRatio=2 -XX:SurvivorRatio=1 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    WeakReferenceRunner weakReferenceRunner = new WeakReferenceRunner();
    weakReferenceRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    Thread.sleep(10_000L);
    Reference<List<String>> weakReference = new WeakReference<>(new ArrayList<>());

    while (true) {
      stringsList = new ArrayList<>(1_000_000);
      Thread.sleep(1000L);
      if (weakReference.get() == null) {
        log.info("memory deallocated");
      } else {
        log.info("weak reference is still present " + weakReference.get());
      }
    }
  }
}
