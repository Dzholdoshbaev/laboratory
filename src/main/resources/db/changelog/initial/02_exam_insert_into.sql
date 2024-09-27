insert into authorities (authority)
values ('USER');

//qwe password
INSERT INTO users (login, email, password, enabled, authority_id,name,bio,avatar)
VALUES
    ('Chyngyz_official','medzholdoshbaev@gmail.com', '$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',  true, (SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'chyngyz','Muslim','5aa4eff0-a876-4146-a5f8-84271e60a3f2_IMG_2616 pp.jpg'),
('elena_official','elenamusina@gmail.com','$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',true,(SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'Elena Musina','Travel Blogger , make UP artist','65b4966fe41f9cc297a79b87e77836df.jpg'),
('sabina_official','sabinaahmedova@gmail.com','$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',true,(SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'Sabina Ahmedova','Student SPBGU','images21.jpeg'),
('victoria_official','victoriaMarmeledko@gmail.com','$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',true,(SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'Victoria Marmeledko','Mom of 3 angels , Happy wife','images.jpeg'),
('adam_official','adamKo@gmail.com','$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',true,(SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'Adam Zoluhev','MMA figther , champion WWEI1','depositphotos_560566494-stock-photo-strict-male-portrait-guy-classic.jpg'),
('egor_official','egorveselleev@gmail.com','$2a$12$nbJcXR6aa/XQZijdKOFWweOgFrZhlsFLk.lWSbpQAhNTlqnJF1y3W',true,(SELECT id FROM AUTHORITIES WHERE AUTHORITY = 'USER'),'Egor Veselleev','Model Hugo BoSS','depositphotos_60545285-stock-photo-profile-of-a-sad-man.jpg');


INSERT INTO posts (author_id, image, description, likes, comments, created)
VALUES
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), '8b653696-56ea-4a41-8b75-82a98a4b55c7_1579611305_2-p-svyashchennaya-kaaba-2.jpg', 'Holy Kaaba most popular place for muslims', 5, 3, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'elena_official'), 'samarkand.jpeg', 'Samarkand very famous and old place in uzbekistan', 2, 1, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'sabina_official'), 'spbgu.jpeg', 'My university ;)', 0, 0, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'victoria_official'), 'kids.jpeg', 'My Angels love you all !', 2, 1, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'adam_official'), 's-l1200.jpg', 'Belt in the house alhamdullilah', 0, 0, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'egor_official'), '240709_HBME_112_FA24_Tier3_BMB_032_733x1222.jpeg', 'Hugo Boss new collection in sell right now go ahead to order !', 0, 0, CURRENT_TIMESTAMP);

INSERT INTO followers (user_id, follower_id, created_at)
VALUES
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), (SELECT id FROM users WHERE login = 'elena_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), (SELECT id FROM users WHERE login = 'victoria_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), (SELECT id FROM users WHERE login = 'adam_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'Chyngyz_official'), (SELECT id FROM users WHERE login = 'egor_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'elena_official'), (SELECT id FROM users WHERE login = 'Chyngyz_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'sabina_official'), (SELECT id FROM users WHERE login = 'Chyngyz_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'victoria_official'), (SELECT id FROM users WHERE login = 'Chyngyz_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'adam_official'), (SELECT id FROM users WHERE login = 'Chyngyz_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'egor_official'), (SELECT id FROM users WHERE login = 'Chyngyz_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'elena_official'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'elena_official'), (SELECT id FROM users WHERE login = 'victoria_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'sabina_official'), (SELECT id FROM users WHERE login = 'elena_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'sabina_official'), (SELECT id FROM users WHERE login = 'victoria_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'victoria_official'), (SELECT id FROM users WHERE login = 'elena_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE login = 'victoria_official'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP);

INSERT INTO likes (post_id, user_id, liked_at)
VALUES
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'elena_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'victoria_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'adam_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'egor_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Samarkand very famous and old place in uzbekistan'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Samarkand very famous and old place in uzbekistan'), (SELECT id FROM users WHERE login = 'victoria_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'My Angels love you all !'), (SELECT id FROM users WHERE login = 'elena_official'), CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'My Angels love you all !'), (SELECT id FROM users WHERE login = 'sabina_official'), CURRENT_TIMESTAMP);

INSERT INTO comments (post_id, commentator_id, comment, created_at)
VALUES
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'adam_official'), 'Inshallah i will be there soon', CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'elena_official'), 'Beautiful place , i want visit there', CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Holy Kaaba most popular place for muslims'), (SELECT id FROM users WHERE login = 'victoria_official'), 'My husband work near city', CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'Samarkand very famous and old place in uzbekistan'), (SELECT id FROM users WHERE login = 'sabina_official'), 'This is my country :)', CURRENT_TIMESTAMP),
    ((SELECT id FROM posts WHERE description = 'My Angels love you all !'), (SELECT id FROM users WHERE login = 'elena_official'), 'So cute )))', CURRENT_TIMESTAMP);
