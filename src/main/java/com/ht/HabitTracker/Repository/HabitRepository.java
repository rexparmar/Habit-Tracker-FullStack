package com.ht.HabitTracker.Repository;

import com.ht.HabitTracker.Model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HabitRepository extends JpaRepository<Habit, Long> {

}
