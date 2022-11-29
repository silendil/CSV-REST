package com.luxoft.csvrest.controller;

import com.luxoft.csvrest.data.entity.CsvRecord;
import com.luxoft.csvrest.exception.NotFoundException;
import com.luxoft.csvrest.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
@Slf4j
@AllArgsConstructor
public class MainController {

    public static final String RECORD_WITH_THIS_ID_DO_NOT_EXIST = "Record with this id do not exist";
    private final DataService dataService;

    @PostMapping()
    public void saveData(@RequestParam(name = "file")MultipartFile file) {
        dataService.saveData(file);
    }

    @GetMapping
    public List<CsvRecord> getAllRecords() {
        return dataService.getAllRecords();
    }

    @GetMapping("/{id}")
    public CsvRecord getRecordById(@PathVariable("id") String id) {
        log.info("Getting record by id");
        Optional<CsvRecord> csvRecord = dataService.getRecordById(id);
        if (csvRecord.isEmpty()) {
            log.warn(RECORD_WITH_THIS_ID_DO_NOT_EXIST);
            throw new NotFoundException(RECORD_WITH_THIS_ID_DO_NOT_EXIST);
        }
        return csvRecord.get();
    }

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable("id") String id) {
        log.info("Deleting record by id");
        Optional<CsvRecord> csvRecord = dataService.getRecordById(id);
        if (csvRecord.isEmpty()) {
            log.warn(RECORD_WITH_THIS_ID_DO_NOT_EXIST);
            throw new NotFoundException(RECORD_WITH_THIS_ID_DO_NOT_EXIST);
        }
        dataService.deleteRecord(csvRecord.get());
        log.info("Record successfully deleted");
    }
}
