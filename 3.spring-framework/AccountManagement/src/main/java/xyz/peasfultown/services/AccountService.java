package xyz.peasfultown.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.peasfultown.forms.AccountCreationForm;
import xyz.peasfultown.forms.AccountUpdateForm;
import xyz.peasfultown.models.Account;
import xyz.peasfultown.models.Department;
import xyz.peasfultown.models.Position;
import xyz.peasfultown.repositories.IAccountRepository;
import xyz.peasfultown.repositories.IDepartmentRepository;
import xyz.peasfultown.repositories.IPositionRepository;

import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {
  private final IAccountRepository accountRepo;
  private final IDepartmentRepository departmentRepo;
  private final IPositionRepository positionRepo;

  @Autowired
  public AccountService(IAccountRepository accountRepo
      , IDepartmentRepository departmentRepo
      , IPositionRepository positionRepo) {
    this.accountRepo = accountRepo;
    this.departmentRepo = departmentRepo;
    this.positionRepo = positionRepo;
  }


  @Override
  public Account create(AccountCreationForm form) {
    Account account = new Account();
    account.setUsername(form.getUsername());
    account.setEmail(form.getUsername());
    account.setFullname(form.getFullname());
    Department department = departmentRepo.getReferenceById(form.getDepartmentId());
    account.setDepartment(department);
    Position position = positionRepo.getReferenceById(form.getPositionId());
    account.setPosition(position);
    return accountRepo.save(account);
  }

  @Override
  public Account update(short id, AccountUpdateForm form) {
    Account account = accountRepo.getReferenceById(id);
    account.setFullname(form.getFullname());
    Department department = departmentRepo.getReferenceById(form.getDepartmentId());
    account.setDepartment(department);
    Position position = positionRepo.getReferenceById(form.getPositionId());
    account.setPosition(position);
    return accountRepo.save(account);
  }

  @Override
  public List<Account> getAll() {
    return accountRepo.findAll();
  }

  @Override
  public Account getById(short id) {
    return accountRepo.getReferenceById(id);
  }

  @Override
  public Account getByEmail(String email) {
    return accountRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email not found: " + email));
  }

  @Override
  public Account getByUsername(String username) {
    return accountRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Username not found: " + username));
  }

  @Override
  public boolean existsByEmailOrUsername(String email, String username) {
    return accountRepo.existsByEmailOrUsername(email, username);
  }

  @Override
  public Account getLatest() {
    return accountRepo.findTopByOrderByIdDesc();
  }

  @Override
  public List<Account> getByIdRange(short from, short to) {
    return accountRepo.findByIdBetween(from, to);
  }

  @Override
  public List<Account> getByIds(short[] ids) {
    return accountRepo.findByIdIn(ids);
  }

  @Override
  public List<String> getAllUsernames() {
    return accountRepo.getUsernames();
  }

  @Override
  public List<Account> getByDepartmentId(short departmentId) {
    return accountRepo.getByDepartmentId(departmentId);
  }

  @Override
  public List<Account> getByDepartmentName(String departmentName) {
    return accountRepo.getByDepartmentName(departmentName);
  }

  @Override
  public List<Account> getByPositionName(String positionName) {
    return accountRepo.getByPositionName(positionName);
  }
}
