package com.luxoft.csvrest.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.luxoft.csvrest.mapper.CsvMapper.DESCRIPTION;
import static com.luxoft.csvrest.mapper.CsvMapper.NAME;
import static com.luxoft.csvrest.mapper.CsvMapper.PRIMARY_KEY;
import static com.luxoft.csvrest.mapper.CsvMapper.UPDATED_TIMESTAMP;

@Entity
@Getter
@Setter
@ToString
@Table(name = "record")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvRecord {
    @Id
    @Column(name = PRIMARY_KEY, nullable = false)
    private String primaryKey;

    @Column(name = NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = UPDATED_TIMESTAMP)
    private LocalDateTime updatedTimestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CsvRecord csvRecord = (CsvRecord) o;
        return primaryKey != null && Objects.equals(primaryKey, csvRecord.primaryKey);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
