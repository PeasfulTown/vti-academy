package xyz.peasfultown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.peasfultown.models.Position;
import xyz.peasfultown.repositories.IPositionRepository;

import java.util.List;

@Service
public class PositionService implements IPositionService {
  private final IPositionRepository repo;

  @Autowired
  public PositionService(IPositionRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<Position> findAll() {
    return this.repo.findAll();
  }
}