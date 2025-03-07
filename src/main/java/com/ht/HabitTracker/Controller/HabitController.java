package com.ht.HabitTracker.Controller;

import com.ht.HabitTracker.Model.Habit;
import com.ht.HabitTracker.Service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https/localhost:")
public class HabitController {
    @Autowired
    private HabitService service;

    @PostMapping("/habit")
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit){
        service.createHabit(habit);
        return new ResponseEntity<>(habit, HttpStatus.OK);
    }

    @GetMapping("/habit")
    public List<Habit> getHabits(){
        return service.getAllHabits();
    }

    @GetMapping("/habit/{id}")
    public Optional<Habit> getHabitById(@PathVariable Long id){
        return service.getHabitById(id);
    }

    @PutMapping("/habit/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id,
                                             @RequestBody Habit habit){
        service.updateHabit(habit,id);
        return new ResponseEntity<>(habit,HttpStatus.OK);
    }

    @DeleteMapping("/habit/{id}")
    public String deleteHabit(@PathVariable Long id){
        return service.deleteHabit(id);
    }
}
