package xyz.peasfultown.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.peasfultown.forms.AccountCreationForm;
import xyz.peasfultown.forms.AccountUpdateForm;
import xyz.peasfultown.models.Account;

import java.util.List;

public interface IAccountService extends UserDetailsService {
  Account create(AccountCreationForm form);

  Account update(short id, AccountUpdateForm form);

  Page<Account> getAll(String search, Pageable pageable);

  Account getLatest();

  Account getById(short id);

  Account getByEmail(String email);

  Account getByUsername(String username);

  boolean existsByEmailOrUsername(String email, String username);

  List<Account> getByIdRange(short from, short to);

  List<Account> getByIds(short[] ids);

  List<String> getAllUsernames();

  List<Account> getByDepartmentId(short departmentId);

  List<Account> getByDepartmentName(String departmentName);

  List<Account> getByPositionName(String positionName);
}
