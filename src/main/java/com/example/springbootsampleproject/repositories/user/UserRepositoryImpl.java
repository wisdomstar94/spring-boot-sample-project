package com.example.springbootsampleproject.repositories.user;

import com.example.springbootsampleproject.entities.QUser;
import com.example.springbootsampleproject.entities.User;
import com.example.springbootsampleproject.entities.UserDTO;
import com.example.springbootsampleproject.entities.UserSearchCondition;
import com.example.springbootsampleproject.libraries.CommonLibrary;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<UserDTO> findAllUsingQuerydsl(UserSearchCondition condition, Pageable pageable) {
        // Q클래스를 이용한다.
        QUser user = QUser.user;

        List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(pageable, user);

        // 다음과 같이 실제 테이블의 칼럼명이 아닌 다른 칼럼명으로 반환되게 설정해줄 수 있음
        JPAQuery<UserDTO> query = queryFactory.select(Projections.bean(
                    UserDTO.class,
                    user.seq.as("outerSeq") // User 테이블의 seq 칼럼을 UserDTO 클래스의 outerSeq 에 맵핑되게 한다는 뜻
                ))
                .from(user)
                .where(
                    this.eqSeq(condition.getSeq(), user),
                    this.goeCreateStartAt(condition.getCreateStartAt(), user),
                    this.containsUserName(condition.getUserName(), user)
                )
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new));
        Integer totalCount = query.fetch().size();

        query.select(Projections.bean(
            UserDTO.class,
            user.seq.as("outerSeq"), // User 테이블의 seq 칼럼을 UserDTO 클래스의 outerSeq 에 맵핑되게 한다는 뜻
            user.userName.as("outerUserName"), // User 테이블의 userName 칼럼을 UserDTO 클래스의 outerUserName 에 맵핑되게 한다는 뜻
            user.createdAt.as("outerCreatedAt")) // User 테이블의 createdAt 칼럼을 UserDTO 클래스의 outerCreatedAt 에 맵핑되게 한다는 뜻
        );
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<UserDTO> list = query.fetch();

//        return query.fetch();
        return new PageImpl<UserDTO>(list, pageable, totalCount);
    }

    private BooleanExpression eqSeq(Integer seq, QUser user) {
        return seq == null || seq == 0 ? null : user.seq.eq(seq);
    }

    private BooleanExpression goeCreateStartAt(Long createStartAt, QUser user) {
        return createStartAt == null || createStartAt == 0 ? null : user.createdAt.goe(CommonLibrary.getTimestamp(createStartAt));
    }

    private BooleanExpression containsUserName(String userName, QUser user) {
        return userName == null || userName.equals("") ? null : user.userName.contains(userName);
    }

    public OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable, QUser user) {

        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!isEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "seq":
                        OrderSpecifier<?> orderId = getSortedColumn(direction, user.seq, "seq");
                        ORDERS.add(orderId);
                        break;
                    case "userName":
                        OrderSpecifier<?> orderUser = getSortedColumn(direction, user.userName, "userName");
                        ORDERS.add(orderUser);
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }

    public User getUserInfo(String userKey) {
        QUser user = QUser.user;
        JPAQuery<User> query = queryFactory.selectFrom(user)
            .where(
                user.userKey.eq(userKey)
            );
        List<User> list = query.fetch();
        return list.get(0);
    }
}
