package xyz.peasfultown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.peasfultown.models.Account;
import xyz.peasfultown.models.PositionName;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Short> {
  @Query("select a from Account a where position.name = ?1")
  List<Account> getByPositionName(String positionName);

  @Query("select a from Account a where department.name = ?1")
  List<Account> getByDepartmentName(String departmentName);

  @Query("select a from Account a where department.id = :depIdParam")
  List<Account> getByDepartmentId(@Param("depIdParam") short departmentId);

  @Query("select a.username from Account a")
  List<String> getUsernames();

  List<Account> findByIdIn(short[] id);

  List<Account> findByIdBetween(short from, short to);

  Account findTopByOrderByIdDesc();

  Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);

  Account getByUsername(String username);

  Account getByEmail(String email);

  boolean existsByEmailOrUsername(String email, String username);

}
