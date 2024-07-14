package brac.it.sample.repository;

import brac.it.sample.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer> {
    Optional<Battery> findByName(String name);
}
