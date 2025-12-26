package xyz.peasfultown.services;

import xyz.peasfultown.models.Position;

import java.util.List;

public interface IPositionService {
  List<Position> findAll();
}
