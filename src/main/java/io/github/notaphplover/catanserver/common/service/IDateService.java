package io.github.notaphplover.catanserver.common.service;

import java.time.Clock;
import java.util.Date;

public interface IDateService {

  Date getCurrent();

  Date getWithOffset(Long millis);
}
