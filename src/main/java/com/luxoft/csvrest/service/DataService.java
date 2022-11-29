package com.luxoft.csvrest.service;

import com.luxoft.csvrest.data.entity.CsvRecord;
import com.luxoft.csvrest.data.repository.CsvRecordRepository;
import com.luxoft.csvrest.exception.ProblemProcessingException;
import com.luxoft.csvrest.mapper.CsvMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@AllArgsConstructor
@Slf4j
public class DataService {

    private final CsvRecordRepository csvRecordRepository;

    @Transactional
    public void saveData(MultipartFile file) {
        log.info("Start saving data");
        try (Scanner scanner = new Scanner(file.getInputStream())) {
            log.info("Reading file content");
            String template = scanner.nextLine();
            while (scanner.hasNext()) {
                CsvRecord csvRecord = CsvMapper.createRecord(template, scanner.nextLine());
                if (csvRecordRepository.findById(csvRecord.getPrimaryKey()).isEmpty())
                    csvRecordRepository.save(csvRecord);
            }
            log.info("Data successfully saved");
        } catch (IOException ex) {
            log.error("Problem with reading file", ex);
            throw new ProblemProcessingException("Problem with reading file");
        }
    }

    public List<CsvRecord> getAllRecords() {
        log.info("Getting all records");
        return csvRecordRepository.findAll();
    }

    public Optional<CsvRecord> getRecordById(String id) {
        return csvRecordRepository.findById(id);
    }

    @Transactional
    public void deleteRecord(CsvRecord csvRecord) {
        csvRecordRepository.delete(csvRecord);
    }
}


