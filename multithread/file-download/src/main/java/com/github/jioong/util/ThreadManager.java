package com.github.jioong.util;

import java.util.List;

public interface ThreadManager {
    List<Runnable> addThread(Runnable thread);
}
