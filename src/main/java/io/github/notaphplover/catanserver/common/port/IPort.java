package io.github.notaphplover.catanserver.common.port;

public interface IPort<I, O> {
    public O transform(I input);
}