-- 기본 사용자 추가
INSERT INTO users(user_id, email, name, password, provider, provider_id, `role`) VALUES(1, 'lifeplan@gmail.com', '관리자', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'ADMIN');
INSERT INTO users(user_id, email, name, password, provider, provider_id, `role`) VALUES(2, 'bside@gmail.com', '홍길동', '$2a$10$RJFLJ9r15drjz9xxxSTSiurBbnVOFMK2zk2Kzh7XnNXuThCIhySLC', 'local', '', 'USER');

-- 목적
INSERT INTO goals(goal_id, created_date, goal_title, modified_date) VALUES(1, now(), '이직 공부하기!!', now());
INSERT INTO goals(goal_id, created_date, goal_title, modified_date) VALUES(2, now(), '운동하기!!', now());
INSERT INTO goals(goal_id, created_date, goal_title, modified_date) VALUES(3, now(), '토익 만점!!', now());
INSERT INTO goals(goal_id, created_date, goal_title, modified_date) VALUES(4, now(), '라이프 플랜 출시하기', now());