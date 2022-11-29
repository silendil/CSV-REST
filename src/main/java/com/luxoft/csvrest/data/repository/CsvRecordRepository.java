package com.luxoft.csvrest.data.repository;

import com.luxoft.csvrest.data.entity.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvRecordRepository extends JpaRepository<CsvRecord, String> {
}
