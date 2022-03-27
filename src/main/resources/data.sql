-- 기본 사용자 추가
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`) VALUES(1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN');
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`) VALUES(2, 'bside@gmail.com', '홍길동', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER');

-- 목적 카테고리
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '물 마시기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '다이어트 음식 먹기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '일찍 일어나기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '외국어 배우기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '코딩 배우기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '포트폴리오 만들기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '저축하기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '주식 공부하기');
INSERT INTO goal_category(created_date, modified_date, goal_category_name) VALUES(now(), now(), '그림 그리기');

-- 목적
INSERT INTO goal(created_date, modified_date, goal_category_id, user_id) VALUES(now(), now(), 1, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, user_id) VALUES(now(), now(), 3, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, user_id) VALUES(now(), now(), 5, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, user_id) VALUES(now(), now(), 4, 2);
