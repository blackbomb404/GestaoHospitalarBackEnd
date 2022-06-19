package com.example.demo.controller;

import com.example.demo.model.Specialty;
import com.example.demo.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public int add(@RequestBody Specialty specialty){
        return specialtyService.add(specialty);
    }

    @GetMapping
    public List<Specialty> getAll(@RequestParam(required = false) String name){
        if(name == null)
            return specialtyService.getAll();
        return specialtyService.getByName(name);
    }

    @GetMapping("{id}")
    public Specialty get(Integer id){
        var foundSpecialty = specialtyService.get(id);
        return foundSpecialty.orElse(null);
    }

    @PutMapping("{id}")
    public int update(@RequestBody Specialty specialty, @PathVariable Integer id){
        return specialtyService.update(specialty, id);
    }

    @DeleteMapping("{id}")
    public int delete(@PathVariable Integer id){
        return specialtyService.delete(id);
    }
}
