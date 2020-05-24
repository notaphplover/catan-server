package io.github.notaphplover.catanserver.common.adapter.api.reqHandler;

public interface IReqHandler<R, O> {
    
    O handle(R request);
}