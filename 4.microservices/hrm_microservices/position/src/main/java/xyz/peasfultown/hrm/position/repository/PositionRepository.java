package xyz.peasfultown.hrm.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.peasfultown.hrm.position.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Short> {
}
