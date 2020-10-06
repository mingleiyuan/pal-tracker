package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private long id = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++id);
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get((id));
    }

    @Override
    public List<TimeEntry> list() {
        return List.copyOf(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry f = this.find(id);
        if (f==null) return null;

        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }
}
