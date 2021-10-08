DROP DATABASE IF EXISTS blogdb;

CREATE DATABASE IF NOT EXISTS blogdb;

USE blogdb;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE userdata (
	user_id INT UNSIGNED AUTO_INCREMENT,
    user_name VARCHAR(20),
    role_name ENUM(
		'admin',
        'moderator',
        'user'
		),
    password VARCHAR(20),
    email_address VARCHAR(50),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);

INSERT INTO userdata(user_name, role_name, password, email_address, registration_date) VALUES
	('banjudy007', 'admin', 'hasselHoff01', 'banjudy@gmail.com', '2021-03-03T09:46:41'),
    ('klarika', 'moderator', 'tibcsi012', 'klarika@freemail.hu', '2021-02-03T10:46:41'),
    ('trollka', 'user', 'mekkmester9', 'ero@gmail.esz', '2021-06-03T09:42:41');


CREATE TABLE roleinformation(
	role_access_id INT UNSIGNED AUTO_INCREMENT,
    role_name ENUM(
		'admin',
        'moderator',
        'user'
		),
	access_type VARCHAR(80),
    PRIMARY KEY (role_access_id)
);
INSERT INTO roleinformation(role_name, access_type) VALUES
	('admin', 'read_all_userdata'),
    ('admin', 'read_post'),
    ('admin', 'modify_post'),
    ('admin', 'delete_post'),
    ('admin', 'modify_all_userdata'),
    ('admin', 'write_comments'),
    ('moderator', 'read_post'),
    ('moderator', 'modify_post'),
    ('moderator', 'delete_post'),
    ('moderator', 'write_comments'),
    ('user', 'read_own_userdata'),
    ('user', 'modify_own_userdata'),
    ('user', 'read_post'),
    ('user', 'modify_own_post'),
    ('user', 'delete_own_post'),
    ('user', 'write_comments');

CREATE TABLE blogtemplate(
	template_name VARCHAR(20) UNIQUE,
    category VARCHAR(20),
    main_color VARCHAR(20),
    background_img BLOB,
    PRIMARY KEY (template_name)
);

INSERT INTO blogtemplate(template_name, category, main_color) VALUES
	('citylife', 'traveling', 'grey'),
    ('nature', 'traveling', 'green'),
    ('food', 'cooking', 'red'),
    ('music', 'voice', 'pink'),
    ('fitness', 'sport', 'blue');

CREATE TABLE blog(
	blog_id INT UNSIGNED AUTO_INCREMENT,
    blog_name VARCHAR(50),
    created_by INT,
    chosen_template_name VARCHAR(20),
    created_on TIMESTAMP,
    PRIMARY KEY (blog_id),
    FOREIGN KEY (chosen_template_name) REFERENCES template_name(blogtemplate),
    FOREIGN KEY (created_by) REFERENCES user_id(userdata)
);

INSERT INTO blog(blog_name, created_by, chosen_template_name, created_on) VALUES
	('The Hungarian side of Medellín', 1, 'citylife', '2021-03-05T09:46:41' ),
    ('Wax The Hoff', 1, 'fitness', '2021-05-03T09:46:41'),
    ('Spinach in all my food', 2, 'food', '2021-07-07T09:46:41'),
    ('Naturally Troll', 3, 'nature', '2021-09-13T09:46:41');

CREATE TABLE post(
	post_id INT UNSIGNED AUTO_INCREMENT,
	post_title VARCHAR(50),
    post_status ENUM(
		'draft',
        'published',
        'archived'
        ),
	post_text VARCHAR(500),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (post_id)
);
INSERT INTO post(post_title, post_status, post_text, created_on) VALUES
	('First day in Laureles', 'published', 'once upon a time i will start to...', '2021-03-06T09:46:41'),
	('Weekly reminder for Wax the Hoff', 'archived', 'it is very important to...', '2021-05-06T09:46:41'),
    ('Trolling in nature as a real troll', 'published', 'blablablabla', '2021-09-16T09:46:41'),
    ('Smooth method for heating the wax', 'draft', 'it is very important to...', '2021-06-06T09:46:41');

CREATE TABLE receivedcomment(
	comment_id INT UNSIGNED AUTO_INCREMENT,
    comment_text VARCHAR(100),
    comment_type ENUM(
		'reply',
        'new'
		),
	date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (comment_id)
);
INSERT INTO receivedcomment(comment_text, comment_type, date_sent) VALUES
	('csigiri vagy migiri', 'new', '2021-03-07T09:46:41'),
    ('szerintem migiri', 'reply', '2021-03-07T10:46:41'),
    ('hova tunik a feher ha elolvad a ho?', 'new', '2021-09-17T09:46:41');
    
CREATE TABLE blog_post(
	id INT AUTO_INCREMENT,
	blog_id INT,
    post_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (blog_id) REFERENCES blog_id(blog),
    FOREIGN KEY (post_id) REFERENCES post_id(post)
);
INSERT INTO blog_post(blog_id, post_id) VALUES
	(1, 1),
    (2, 2),
    (4, 3),
    (2, 4);

CREATE TABLE post_receivedcomment(
	id INT AUTO_INCREMENT,
    post_id INT,
    receivedcomment_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post_id(post),
    FOREIGN KEY (receivedcomment_id) REFERENCES comment_id(receivedcomment)
);
INSERT INTO post_receivedcomment(post_id, receivedcomment_id) VALUES
	(2, 1),
    (2, 2),
    (3, 3);

SET FOREIGN_KEY_CHECKS = 0;