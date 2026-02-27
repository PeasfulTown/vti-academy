package xyz.peasfultown.hrm.position.service;

import xyz.peasfultown.hrm.position.entity.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAllPosition();
    Position getPositionById(short id);
}
