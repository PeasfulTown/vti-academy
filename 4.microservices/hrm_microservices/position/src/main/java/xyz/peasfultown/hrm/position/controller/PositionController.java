package xyz.peasfultown.hrm.position.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.peasfultown.hrm.position.dto.PositionDto;
import xyz.peasfultown.hrm.position.entity.Position;
import xyz.peasfultown.hrm.position.service.PositionService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/positions")
@CrossOrigin("*")
public class PositionController {
    private final PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllPositions() {
        List<PositionDto> dtos = service.getAllPosition()
                .stream()
                .map(e -> new PositionDto(e.getId(), e.getName().toString()))
                .collect(toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionById(@PathVariable("id") short id) {
        Position pos = service.getPositionById(id);
        PositionDto dto = new PositionDto(pos.getId(),
                pos.getName().toString());
        return ResponseEntity.ok(dto);
    }
}
