package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
@Slf4j
public class PhantomReferenceRunner {

  private List<String> stringsList = new ArrayList<>();
  private final List<List<String>> infiniteList = new ArrayList<>();
  private Reference<Object> reference1;
  private Reference<Object> reference2;

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:NewRatio=2 -XX:SurvivorRatio=1 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    PhantomReferenceRunner phantomReferenceRunner = new PhantomReferenceRunner();
    phantomReferenceRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    reference1 = new CustomPhantomReference(new ArrayList<>(10_000_000), referenceQueue);
    reference2 = new CustomPhantomReference(new ArrayList<>(10_000_000), referenceQueue);
    while (true) {
      stringsList = new ArrayList<>(500_000);
      infiniteList.add(new ArrayList<>(500_000));
      Thread.sleep(1000L);

      Reference reference = referenceQueue.poll();
      if (reference == null) {
        log.info("reference is empty");
      } else {
        ((CustomPhantomReference) reference).doPrint();
      }
    }
  }
}
