package com.academy.backend.timeBlock.domain;

import com.academy.backend.timeTable.domain.TimeTable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeBlockId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeTableId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TimeTable timeTable;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private LocalTime startTime;
    private LocalTime endTime;

    @Builder
    public TimeBlock(TimeTable timeTable, Weekday weekday, LocalTime startTime, LocalTime endTime) {
        this.timeTable = timeTable;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
