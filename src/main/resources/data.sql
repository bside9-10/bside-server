-- 기본 사용자 추가
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) VALUES(1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN', now(), now());
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) VALUES(2, 'bside@gmail.com', '홍길동', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) VALUES(3, 'testUser@gmail.com', '테스트1', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());

-- 목표 카테고리
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '운동');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '공부');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '시험/고시');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '이직준비');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '자기계발');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '+ 직접입력');
