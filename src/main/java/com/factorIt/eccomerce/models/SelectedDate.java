package com.factorIt.eccomerce.models;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class SelectedDate {

    private LocalDateTime selectDate;

    @PostConstruct
    private void init() {
        this.selectDate = LocalDateTime.now();
    }

    public LocalDateTime getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(LocalDateTime selectDate) {
        this.selectDate = selectDate;
    }
}
