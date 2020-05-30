package io.github.notaphplover.catanserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"io.github.notaphplover.catanserver.user.adapter.db.model"})
@SpringBootApplication
public class CatanServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatanServerApplication.class, args);
  }
}
