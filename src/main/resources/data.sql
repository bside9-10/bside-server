-- 기본 사용자 추가
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN', now(), now());
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (2, 'bside@gmail.com', '홍길동', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());
insert into user (user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) values (3, 'testUser@gmail.com', '테스트1', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());

-- 목표 카테고리
insert into goal_category (created_date, modified_date, category) values (now(), now(), '운동');
insert into goal_category (created_date, modified_date, category) values (now(), now(), '공부');
insert into goal_category (created_date, modified_date, category) values (now(), now(), '시험/고시');
insert into goal_category (created_date, modified_date, category) values (now(), now(), '이직준비');
insert into goal_category (created_date, modified_date, category) values (now(), now(), '자기계발');
insert into goal_category (created_date, modified_date, category) values (now(), now(), '+ 직접입력');

insert into goal_available_time (goal_available_time_id, created_date, modified_date, daily, weekly, user_id) values (1, now(), now(), 3, 5, 2);

insert into goal (goal_id, created_date, modified_date, goal_category_name, goal_category_id, user_id) values (1, now(), now(), '운동', 1, 2);

insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (1, now(), now(), '2022-05-10', '1800', false, '2022-04-09', '0900', '스쿼트 매일 100개!!', 1, 2, 'DAY');
insert into goal_detail (goal_detail_id, created_date, modified_date, end_date, end_time, notification, start_date, start_time, title, goal_id, user_id, goal_date_status)
values (2, now(), now(), '2022-05-10', '1830', false, '2022-04-15', '0930', '런지 주말 50개!!', 1, 2, 'WEEKEND');