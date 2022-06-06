package com.bside.study.goal.repository;

import com.bside.study.goal.dto.*;
import com.bside.study.user.entity.User;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.tomcat.jni.Local;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.bside.study.goal.entity.QGoal.goal;
import static com.bside.study.goal.entity.QGoalCalendar.goalCalendar;
import static com.bside.study.goal.entity.QGoalDetail.goalDetail;

public class GoalRepositoryImpl implements GoalRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public GoalRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalCategoryResponseDto> findGoalByUserId(Long userId) {
        return queryFactory
                .select(
                        new QGoalCategoryResponseDto(
                                goal.userCategory.id,
                                goal.goalCategoryName
                        )
                )
                .from(goal)
                .where(goal.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<LafflesResponseDto> findGoalsByUserIdAndCategoryId(User user, Long categoryId) {
        return queryFactory
                .select(
                        new QLafflesResponseDto(
                                goalDetail.id,
                                goalDetail.title,
                                goalDetail.startDate,
                                goalDetail.endDate,
                                JPAExpressions.select(goalCalendar.completed.count())
                                        .from(goalCalendar)
                                        .where(goalCalendar.completed.eq(true).and(goalCalendar.goalDetail.id.eq(goalDetail.id))),
                                JPAExpressions.select(goalCalendar.completed.count())
                                        .from(goalCalendar)
                                        .where(goalCalendar.completed.eq(false).and(goalCalendar.goalDetail.id.eq(goalDetail.id)))
                        )
                )
                .from(goalDetail)
                .join(goalDetail.goal, goal).on(goal.user.id.eq(user.getId()).and(goal.userCategory.id.eq(categoryId)))
                .orderBy(goalDetail.createdDate.desc())
                .fetch();
    }

    @Override
    public List<StampDto> findTodayGoalsCalendarStampByUserId(Long userId, String month) {
        Query nativeQuery = entityManager.createNativeQuery(
                "select goal_date, sum(completed = false), sum(completed = true)\n" +
                        "from goal_detail gd\n" +
                        "         left join goal_calendar gc on gd.goal_detail_id = gc.goal_detail_id\n" +
                        "where date_format(goal_date, '%x%m') = substr(" + month + ", 1, 6)\n" +
                        "  and gd.user_id = " + userId + "\n" +
                        "group by goal_date;"
        );

        List<Object[]> resultList = nativeQuery.getResultList();

        return resultList.stream()
                .map(objects -> new StampDto(LocalDate.parse(String.valueOf(objects[0])),
                        Long.valueOf(String.valueOf(objects[1])),
                        Long.valueOf(String.valueOf(objects[2]))))
                .collect(Collectors.toList());

//        return queryFactory
//                .select(
//                        new QStampDto(
//                                goalCalendar.goalDate,
//                                goalCalendar.completed.eq(false).count(),
//                                goalCalendar.completed.eq(true).count()
//                        )
//                )
//                .from(goalDetail)
//                .join(goalCalendar.goalDetail, goalDetail).on(goalDetail.user.id.eq(userId))
//                .where(
//                        Expressions.stringTemplate(
//                                        "DATE_FORMAT({0}, {1})"
//                                        , goalCalendar.goalDate
//                                        , ConstantImpl.create("%x%m"))
//                                .eq(
//                                        Expressions.stringTemplate(
//                                                "SUBSTR({0}, {1}, {2})"
//                                                , month
//                                                , 1, 6)
//                                )
//                )
//                .groupBy(goalCalendar.goalDate)
//                .fetch();
    }

}
