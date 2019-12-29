package com.tsystems.study.jamboree.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tsystems.study.jamboree.util.ClientDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    @JsonDeserialize(using = ClientDateDeserializer.class)
    private Date startDate;
    @JsonDeserialize(using = ClientDateDeserializer.class)
    private Date endDate;
    private Set<User> participants;
}
