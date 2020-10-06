package io.pivotal.pal.tracker;

import jdk.javadoc.doclet.Reporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository repository ;;

//    public TimeEntryController(){ this.repository = new InMemoryTimeEntryRepository();}
    public TimeEntryController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        timeEntry = this.repository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable ("id") Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry t = this.repository.update(id, timeEntry);

        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable ("id") Long id) {
        TimeEntry timeEntry = this.repository.find(id);

        return timeEntry == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(this.repository.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id) {
//        TimeEntry t = this.repository.find(id);
//        if(t== null) return ResponseEntity.notFound().build();

        this.repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
