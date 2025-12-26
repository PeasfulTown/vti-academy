package xyz.peasfultown.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.peasfultown.dtos.AccountDTO;
import xyz.peasfultown.dtos.UsernameDTO;
import xyz.peasfultown.forms.AccountCreationForm;
import xyz.peasfultown.forms.AccountUpdateForm;
import xyz.peasfultown.models.Account;
import xyz.peasfultown.services.IAccountService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
  private final IAccountService service;
  private final ModelMapper mapper;

  @Autowired
  public AccountController(ModelMapper mapper, IAccountService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<?> getAllAccounts() {
    List<Account> accounts = service.getAll();
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO dto = mapper.map(a, AccountDTO.class);
      dtos.add(dto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getById(@PathVariable(name = "id") short id) {
    Account account = service.getById(id);
    AccountDTO dto = mapper.map(account, AccountDTO.class);

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody AccountCreationForm form) {
    Account account = service.create(form);
    AccountDTO dto = mapper.map(account, AccountDTO.class);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> update(@PathVariable(name = "id") short id, @RequestBody AccountUpdateForm form) {
    Account account = service.update(id, form);

    AccountDTO dto = mapper.map(account, AccountDTO.class);

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping(value = "/username/{username}")
  public ResponseEntity<?> getByUsername(@PathVariable(name = "username") String username) {
    Account account = service.getByUsername(username);

    AccountDTO dto = new AccountDTO();

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping(value = "/existsByEmailOrUsername")
  public ResponseEntity<?> getByEmail(@RequestParam(required = false) String email,
                                      @RequestParam(required = false) String username) {
    boolean exists = service.existsByEmailOrUsername(email, username);
    return new ResponseEntity<>(exists, HttpStatus.OK);
  }

  @GetMapping(value = "/latest")
  public ResponseEntity<?> getLatest() {
    Account account = service.getLatest();

    AccountDTO dto = new AccountDTO();
    dto.setId(account.getId());
    dto.setEmail(account.getEmail());
    dto.setUsername(account.getUsername());
    dto.setFullname(account.getFullname());
    dto.setDepartment(account.getDepartment().getName());
    dto.setPosition(account.getPosition().getName().toString());

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping(value = "/range")
  public ResponseEntity<?> getInIdRange(
      @RequestParam short from,
      @RequestParam short to
  ) {
    List<Account> accounts = service.getByIdRange(from, to);
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO adto = new AccountDTO();
      adto.setId(a.getId());
      adto.setEmail(a.getEmail());
      adto.setUsername(a.getUsername());
      adto.setFullname(a.getFullname());
      adto.setDepartment(a.getDepartment().getName());
      adto.setPosition(a.getPosition().getName().toString());
      dtos.add(adto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/byIds")
  public ResponseEntity<?> getIn(
      @RequestBody short[] ids
  ) {
    List<Account> accounts = service.getByIds(ids);
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO adto = new AccountDTO();
      adto.setId(a.getId());
      adto.setEmail(a.getEmail());
      adto.setUsername(a.getUsername());
      adto.setFullname(a.getFullname());
      adto.setDepartment(a.getDepartment().getName());
      adto.setPosition(a.getPosition().getName().toString());
      dtos.add(adto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/usernames")
  public ResponseEntity<?> getByDepartment() {
    List<String> usernames = service.getAllUsernames();
    List<UsernameDTO> dtos = new ArrayList<>();
    for (String s : usernames) {
      dtos.add(new UsernameDTO(s));
    }
    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/byDepartmentId")
  public ResponseEntity<?> getByDepartment(
      @RequestParam short departmentId
  ) {
    List<Account> accounts = service.getByDepartmentId(departmentId);
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO adto = new AccountDTO();
      adto.setId(a.getId());
      adto.setEmail(a.getEmail());
      adto.setUsername(a.getUsername());
      adto.setFullname(a.getFullname());
      adto.setDepartment(a.getDepartment().getName());
      adto.setPosition(a.getPosition().getName().toString());
      dtos.add(adto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/byDepartmentName")
  public ResponseEntity<?> getByDepartment(
      @RequestParam String departmentName
  ) {
    List<Account> accounts = service.getByDepartmentName(departmentName);
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO adto = new AccountDTO();
      adto.setId(a.getId());
      adto.setEmail(a.getEmail());
      adto.setUsername(a.getUsername());
      adto.setFullname(a.getFullname());
      adto.setDepartment(a.getDepartment().getName());
      adto.setPosition(a.getPosition().getName().toString());
      dtos.add(adto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  @GetMapping("/byPositionName")
  public ResponseEntity<?> getByPosition(
      @RequestParam String positionName
  ) {
    List<Account> accounts = service.getByPositionName(positionName);
    List<AccountDTO> dtos = new ArrayList<>();
    for (Account a : accounts) {
      AccountDTO adto = new AccountDTO();
      adto.setId(a.getId());
      adto.setEmail(a.getEmail());
      adto.setUsername(a.getUsername());
      adto.setFullname(a.getFullname());
      adto.setDepartment(a.getDepartment().getName());
      adto.setPosition(a.getPosition().getName().toString());
      dtos.add(adto);
    }

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }
}
