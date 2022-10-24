package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Soft reference is the next reference after the strong reference in references hierarchy. The only difference is
 * that memory deallocated just before throwing {@link OutOfMemoryError} error.
 *
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
@Slf4j
public class SoftReferenceRunner {

  private List<String> stringsList = new ArrayList<>();
  private final List<List<String>> infiniteList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:NewRatio=2 -XX:SurvivorRatio=1
  public static void main(String[] args) throws InterruptedException {
    SoftReferenceRunner softReferenceRunner = new SoftReferenceRunner();
    softReferenceRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    Thread.sleep(10_000L);
    Reference<List<String>> softReference = new SoftReference<>(new ArrayList<>(20_000_000));
    while (true) {
      infiniteList.add(new ArrayList<>(500_000));
      stringsList = new ArrayList<>(500_000);
      stringsList = new ArrayList<>(500_000);
      stringsList = new ArrayList<>(500_000);
      Thread.sleep(1000L);
      if (softReference.get() == null) {
        log.info("memory deallocated");
      } else {
        log.info("soft reference is still present " + softReference.get());
      }
    }
  }
}
