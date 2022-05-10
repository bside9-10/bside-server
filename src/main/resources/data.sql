-- 사용자 추가
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN', now(), now());
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (2, 'bside1@gmail.com', '테스트1', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (3, 'bside2@gmail.com', '테스트2', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());

-- 목표 카테고리
insert into goal_category (goal_category_id, created_date, modified_date, category) values (1, now(), now(), '운동');
insert into goal_category (goal_category_id, created_date, modified_date, category) values (2, now(), now(), '공부');
insert into goal_category (goal_category_id, created_date, modified_date, category) values (3, now(), now(), '시험/고시');
insert into goal_category (goal_category_id, created_date, modified_date, category) values (4, now(), now(), '이직준비');
insert into goal_category (goal_category_id, created_date, modified_date, category) values (5, now(), now(), '자기계발');
insert into goal_category (goal_category_id, created_date, modified_date, category) values (6, now(), now(), '+ 직접입력');

-- 실천 가능 시간
insert into goal_available_time (goal_available_time_id, created_date, modified_date, daily, weekly, user_id) values (1, now(), now(), 3, 5, 2);

-- 목표 카테고리 설정
insert into goal (goal_id, created_date, modified_date, goal_category_name, goal_category_id, user_id) values (1, now(), now(), '운동', 1, 2);
insert into goal (goal_id, created_date, modified_date, goal_category_name, goal_category_id, user_id) values (2, now(), now(), '이직준비', 4, 2);
insert into goal (goal_id, created_date, modified_date, goal_category_name, goal_category_id, user_id) values (3, now(), now(), '비사이드 프로젝트 진행', 5, 2);

-- 세부 목표 설정
insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (1, now(), now(), '2022-04-30', '1800', false, '2022-04-15', '0900', '스쿼트 매일 100개!!', 1, 2, 'DAY');

-- 세부 목표 달력
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-15', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-18', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-19', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-20', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-21', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-22', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-25', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-26', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-27', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-28', 1);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-29', 1);

-- 세부 목표 설정
insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (2, now(), now(), '2022-04-30', '1830', false, '2022-04-15', '0930', '런지 주말 50개!!', 1, 2, 'WEEKEND');

-- 세부 목표 달력
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-16', 2);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-17', 2);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-23', 2);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-24', 2);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-30', 2);

-- 세부 목표 설정
insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (3, now(), now(), '2022-04-30', '2200', true, '2022-04-15', '1900', '매일 이직 공부 3시간씩', 2, 2, 'DAILY');

-- 세부 목표 달력
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-15', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-16', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-17', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-18', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-19', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-20', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-21', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-22', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-23', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-24', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-25', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-26', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-30', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-27', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-28', 3);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-29', 3);

-- 세부 목표 설정
insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (4, now(), now(), '2022-04-30', '1800', false, '2022-04-15', '0900', '플랭크 매일 10분!!', 1, 2, 'DAY');

-- 세부 목표 달력
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-15', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-18', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-19', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-20', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-21', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-22', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-25', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-26', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-27', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-28', 4);
insert into goal_calendar (created_date, modified_date, completed, goal_date, goal_detail_id) values (now(), now(), false, '2022-04-29', 4);