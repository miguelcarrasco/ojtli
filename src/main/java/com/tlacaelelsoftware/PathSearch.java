package com.tlacaelelsoftware;

public interface PathSearch<T> {
    ResultPath<T> searchPath(T start, T goal);
}
