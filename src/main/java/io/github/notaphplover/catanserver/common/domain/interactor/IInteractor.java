package io.github.notaphplover.catanserver.common.domain.interactor;

public interface IInteractor<I, O> {

  O interact(I input);
}
