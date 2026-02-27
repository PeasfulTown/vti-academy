package xyz.peasfultown.hrm.position.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.peasfultown.hrm.position.entity.Position;
import xyz.peasfultown.hrm.position.repository.PositionRepository;

import java.util.List;
import java.util.Objects;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository repo;

    @Autowired
    public PositionServiceImpl(PositionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Position> getAllPosition() {
        return repo.findAll();
    }

    // TODO: finish
    @Override
    public Position getPositionById(short id) {
        return repo.findById(id).orElseGet(Position::new);
    }
}
