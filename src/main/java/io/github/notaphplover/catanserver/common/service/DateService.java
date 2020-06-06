package io.github.notaphplover.catanserver.common.service;

import java.time.Clock;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DateService implements IDateService {

  public Date getCurrent() {
    return new Date();
  }

  public Date getWithOffset(Long millis) {
    return new Date(System.currentTimeMillis() + millis);
  }
}
