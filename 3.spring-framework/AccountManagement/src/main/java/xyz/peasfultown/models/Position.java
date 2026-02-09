package xyz.peasfultown.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Position", catalog = "Testing_System")
public class Position {
  @Column(name = "PositionID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private short id;

  @Column(name = "PositionName", nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  private PositionName name;

  @OneToMany(mappedBy = "position")
  List<Account> accounts;

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public PositionName getName() {
    return name;
  }

  public void setName(PositionName name) {
    this.name = name;
  }
}
