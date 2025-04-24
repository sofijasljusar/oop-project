package com.plandiy.service.progress;

public interface ProgressContext {

    void setProgressStrategy(ProgressStrategy strategy);

    int calculateProgress();
}
