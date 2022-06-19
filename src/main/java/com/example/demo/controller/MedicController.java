package com.example.demo.controller;

import com.example.demo.dto.MedicDto;
import com.example.demo.model.Medic;
import com.example.demo.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medic")
public class MedicController {
    @Autowired
    private MedicService medicService;

    @PostMapping
    public int add(@RequestBody MedicDto medicDto){
        return medicService.add(medicDto);
    }

    @GetMapping
    public List<Medic> getAll(@RequestParam(required = false) String name){
        if(name == null)
            return medicService.getAll();
        return medicService.getByName(name);
    }

    @GetMapping("{id}")
    public Medic get(@PathVariable Integer id){
        var foundPatient = medicService.get(id);
        return foundPatient.orElse(null);
    }

    @PutMapping("{id}")
    public int update(@RequestBody MedicDto medicDto, @PathVariable Integer id){
        return medicService.update(medicDto, id);
    }

    @DeleteMapping("{id}")
    public int delete(@PathVariable Integer id){
        return medicService.delete(id);
    }
}
