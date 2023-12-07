package com.cst438.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;

public interface SegmentRepository extends CrudRepository<Segment, Integer> {
    Segment findById(int id);
}
