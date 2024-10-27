package com.greenfin.sequence.generator.reporitory;

import com.greenfin.sequence.generator.model.entity.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SequenceRepository extends JpaRepository<Sequence, Long> {

    @Query("SELECT COUNT(s) from Sequence s")
    int countAll();

    Sequence findFirstByOrderBySequenceIdDesc();

}
