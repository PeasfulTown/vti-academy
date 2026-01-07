package xyz.peasfultown.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.peasfultown.exception.ResourceNotFoundException;
import xyz.peasfultown.forms.AccountCreationForm;
import xyz.peasfultown.forms.AccountUpdateForm;
import xyz.peasfultown.models.Account;
import xyz.peasfultown.models.Department;
import xyz.peasfultown.models.Position;
import xyz.peasfultown.repositories.IAccountRepository;
import xyz.peasfultown.repositories.IDepartmentRepository;
import xyz.peasfultown.repositories.IPositionRepository;
import xyz.peasfultown.specification.AccountSpecification;

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
    public Page<Account> getAll(String search, Pageable pageable) {
        Specification<Account> spec = null;
        if (!StringUtils.isEmpty(search)) {
            AccountSpecification nameSpec = new AccountSpecification(
                    "fullname", "LIKE", search);
            AccountSpecification emailSpec = new AccountSpecification("email"
                    , "LIKE", search);
            AccountSpecification usernameSpec = new AccountSpecification(
                    "username"
                    , "LIKE", search);

            spec = Specification.where(nameSpec).or(emailSpec).or(usernameSpec);
            return accountRepo.findAll(spec, pageable);
        } else {
            return accountRepo.findAll(pageable);
        }
    }

    @Override
    public Account getById(short id) {
        return accountRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find account id = " + id));
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found: " + email));
    }

    @Override
    public Account getByUsername(String username) {
        return accountRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username not found: " + username));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =
                accountRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cannot find username"));


        UserDetails userDetails = new User(account.getUsername(), account.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()));

        return userDetails;
    }
}
