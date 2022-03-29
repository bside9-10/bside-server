-- 기본 사용자 추가
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) VALUES(1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN', now(), now());
INSERT INTO user(user_id, email, name, password, provider, provider_id, `role`, created_date, modified_date) VALUES(2, 'bside@gmail.com', '홍길동', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER', now(), now());

-- 목적 카테고리
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '운동');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '다이어트');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '생활습관');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '독서');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '학습');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '업무');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '자기만의 시간');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '재테크');
INSERT INTO goal_category(created_date, modified_date, category) VALUES(now(), now(), '취미');

-- 목적 카테고리 상세
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '물 마시기', 2);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '다이어트 음식 먹기', 2);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '일찍 일어나기', 3);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '외국어 배우기', 5);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '코딩 배우기', 5);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '포트폴리오 만들기', 5);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '저축하기', 8);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '주식 공부하기', 8);
INSERT INTO goal_category_detail(created_date, modified_date, detail, goal_category_id) VALUES(now(), now(), '그림 그리기', 9);

-- 목적
INSERT INTO goal(created_date, modified_date, goal_category_id, goal_category_detail_id, user_id) VALUES(now(), now(), 2, 1, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, goal_category_detail_id, user_id) VALUES(now(), now(), 3, 3, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, goal_category_detail_id, user_id) VALUES(now(), now(), 5, 5, 2);
INSERT INTO goal(created_date, modified_date, goal_category_id, goal_category_detail_id, user_id) VALUES(now(), now(), 8, 7, 2);
