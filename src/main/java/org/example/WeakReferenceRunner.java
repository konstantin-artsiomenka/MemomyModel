package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * {@link WeakReference} reference stays after the {@link java.lang.ref.SoftReference} in the references hierarchy.
 * Objects which have only weak references will be erased once at the first GC stage.
 *
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

    Map<Object, Object> weakHashMap = new WeakHashMap<>();
    weakHashMap.put(new Object(), new ArrayList<>());


    while (true) {
      stringsList = new ArrayList<>(1_000_000);
      Thread.sleep(1000L);
      if (weakReference.get() == null) {
        log.info("memory deallocated");
      } else {
        log.info("weak reference is still present " + weakReference.get());
      }
      if (weakHashMap.isEmpty()) {
        log.info("map memory deallocated");
      } else {
        log.info("map weak reference is still present ");
      }
    }
  }
}
