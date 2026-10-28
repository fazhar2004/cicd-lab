package com.spring_boot.cicd_lab.service;

import com.spring_boot.cicd_lab.model.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final ConcurrentMap<Long, Task> tasks =
            new ConcurrentHashMap<>();

    private final AtomicLong idSequence =
            new AtomicLong(0);

    public List<Task> findAll() {
        return tasks.values().stream()
                .sorted(Comparator.comparingLong(Task::id))
                .toList();
    }

    public Task create(String title) {
        long id = idSequence.incrementAndGet();
        Task task = new Task(id, title, false);

        tasks.put(id, task);
        return task;
    }
}