package com.example.bookpagination.service.specification;
import com.example.bookpagination.dao.entity.BookEntity;
import com.example.bookpagination.model.criteria.BookCriteria;
import com.example.bookpagination.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.bookpagination.model.constants.CriteriaConstants.NAME;
import static com.example.bookpagination.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class BookSpecification implements Specification<BookEntity> {
    private final BookCriteria bookCriteria;


    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(bookCriteria.getName(),
                        name -> criteriaBuilder.like(root.get(NAME), applyLikePattern(name))
                )
                .build();
        return criteriaBuilder.and(predicates);
    }
}
