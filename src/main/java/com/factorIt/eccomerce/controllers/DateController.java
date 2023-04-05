package com.factorIt.eccomerce.controllers;

import com.factorIt.eccomerce.models.SelectedDate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/date")
public class DateController {

    @Autowired
    SelectedDate selectedDate;

    @ApiOperation(value = "Cambiar la fecha de la aplicacion", notes = "Recibe un string en formato isoString de javascript lo parsea y cambia la fecha del programa.")
    @PostMapping("/change")
    public ResponseEntity<Object> changeDate(@RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateParse = LocalDateTime.parse(date, formatter);
        selectedDate.setSelectDate(dateParse);

        return new ResponseEntity<>("Date change suscesfully", HttpStatus.CREATED);
    }
    @ApiOperation(value = "Obtener la fecha del programa ", notes = "Retorna la fecha del programa, que por defecto es la actual.")
    @GetMapping("/get")
    public LocalDateTime getDate() {
        return selectedDate.getSelectDate();
    }
}
