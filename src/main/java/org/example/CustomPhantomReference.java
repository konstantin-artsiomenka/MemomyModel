package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Konstantin Artsiomenka
 * @version 1.0.0
 */
@Slf4j
public class CustomPhantomReference extends PhantomReference<Object> {

  public CustomPhantomReference(
      Object referent, ReferenceQueue<? super Object> q) {
    super(referent, q);
  }

  public void doPrint() {
    // free resources
    log.info("Hello from CustomPhantomReference {}", this);
  }
}
