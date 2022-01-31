package com.example.springbootsampleproject.repositories;

import com.example.springbootsampleproject.entities.QUser;
import com.example.springbootsampleproject.entities.UserDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<UserDTO> findAllUsingQuerydsl() {
        // Q클래스를 이용한다.
        QUser user = QUser.user;

        // 다음과 같이 실제 테이블의 칼럼명이 아닌 다른 칼럼명으로 반환되게 설정해줄 수 있음
        return queryFactory.select(Projections.bean(
                    UserDTO.class,
                    user.userName.as("outerUserName"), // User 테이블의 userName 칼럼을 UserDTO 클래스의 outerUserName 에 맵핑되게 한다는 뜻
                    user.createdAt.as("outerCreatedAt")) // User 테이블의 createdAt 칼럼을 UserDTO 클래스의 outerCreatedAt 에 맵핑되게 한다는 뜻
                )
                .from(user)
                .where()
                .fetch();
    }
}
