package xyz.peasfultown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.peasfultown.models.Position;

@Repository
public interface IPositionRepository extends JpaRepository<Position, Short> {
}
