package com.tsystems.study.jamboree.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventDto {
    private int id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
}
