package xyz.peasfultown.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.peasfultown.dtos.PositionDTO;
import xyz.peasfultown.models.Position;
import xyz.peasfultown.services.IPositionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/positions")
@CrossOrigin("*")
public class PositionController {
  private final IPositionService service;

  @Autowired
  public PositionController(IPositionService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<?> getAllPositions() {
    List<Position> positions = service.findAll();
    List<PositionDTO> dtos = new ArrayList<>();
    for (Position p : positions) {
      PositionDTO pdto = new PositionDTO();
      pdto.setId(p.getId());
      pdto.setName(p.getName().toString());
      dtos.add(pdto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }
}
