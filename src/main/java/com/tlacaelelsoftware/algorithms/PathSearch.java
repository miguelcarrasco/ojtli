package com.tlacaelelsoftware.algorithms;

public interface PathSearch<T> {
    ResultPath<T> searchPath(T start, T goal);
}
