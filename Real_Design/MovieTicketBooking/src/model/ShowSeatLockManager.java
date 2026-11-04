package model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ShowSeatLockManager {

    private final ConcurrentHashMap<Integer, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(int showSeatId) {

        return locks.computeIfAbsent(showSeatId, id -> new ReentrantLock());
    }
}