package xyz.peasfultown.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import xyz.peasfultown.models.Account;

public class AccountSpecification implements Specification<Account> {
    private String field;
    private String operator;
    private Object value;

    public AccountSpecification(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query
            , CriteriaBuilder criteriaBuilder) {
        if (operator.equalsIgnoreCase("LIKE")) {
            if (field.equalsIgnoreCase("department"))
                return criteriaBuilder.like(root.get(field).get("name"), "%" + value + "%");
            else return criteriaBuilder.like(root.<String>get(field), "%" + value.toString() + "%");
        }

        return null;
    }

}
