INSERT INTO roles(id, name) VALUES(1, 'ADMIN');
INSERT INTO roles(id, name) VALUES(2, 'USER');
INSERT INTO users (id, first_name, last_name, registration_date, email, password, role_id, profile_pic, bio) VALUES (1,'John', 'Doe', '2023-01-01', 'john@email.com', '{noop}john', 1, 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png', 'hi');
INSERT INTO users (id, first_name, last_name, registration_date, email, password, role_id, profile_pic, bio) VALUES (2,'Jane', 'Smith', '2023-05-05', 'jane@email.com', '{noop}jane', 2, 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png', 'hi');


INSERT INTO categories (name) VALUES ('Java');
INSERT INTO categories (name) VALUES ('Oracle');
INSERT INTO categories (name) VALUES ('JDK');
INSERT INTO categories (name) VALUES ('Amber');
INSERT INTO categories (name) VALUES ('Babylon');
INSERT INTO categories (name) VALUES ('Cloud');
INSERT INTO categories (name) VALUES ('Performance');
INSERT INTO categories (name) VALUES ('Networking');



INSERT INTO posts (content, title, author_id, date, category_id, views) VALUES('<p>ciao</p>', 'The Arrival of Java 21', 1, '2023-01-01', 1, 10);
INSERT INTO posts (content, title, author_id, date, category_id, views) VALUES('<p>sono</p>', 'JavaOne is Back!', 1, '2023-02-01', 2, 23);
INSERT INTO posts (content, title, author_id, date, category_id, views) VALUES('<p>premio</p>', 'JCON World 2023: A virtual conference for Java developers', 1, '2023-03-01', 3, 4);
INSERT INTO posts (content, title, author_id, date, category_id, views) VALUES('<p>quadro</p>', 'Go Native with Spring Boot 3 and GraalVM', 1, '2023-04-01', 4, 5);
INSERT INTO posts (content, title, author_id, date, category_id, subheading) VALUES('<p>Questo è il mio primo post sul mio nuovo blog. Sono entusiasta di condividere con voi le mie idee e le mie esperienze. Spero che troverete i miei articoli interessanti e informativi.</p><p>Ecco alcune delle cose di cui parlerò nel mio blog:</p><ul><li>Web development</li><li>Tecnologie emergenti</li><li>Consigli su carriera</li></ul><p>Non vedo l\'ora di iniziare questa avventura con voi. Restate sintonizzati per ulteriori aggiornamenti!</p>', 'Benvenuto nel mio blog', 1, '2023-05-01', 5, 'subheading');

INSERT INTO comments (content, date_time, user_id, post_id) VALUES ('Commento 1 per Mino', NOW(), 2, 1);
INSERT INTO comments (content, date_time, user_id, post_id) VALUES ('Commento 2 per Mino', NOW(), 2, 1);
INSERT INTO comments (content, date_time, user_id, post_id) VALUES ('Commento 3 per Mino', NOW(), 1, 1);
INSERT INTO comments (content, date_time, user_id, post_id) VALUES ('Commento 4 per Sono', NOW(), 1, 2);
INSERT INTO comments (content, date_time, user_id, post_id) VALUES ('Commento 5 per Sono', NOW(), 2, 2);

INSERT INTO messages (name, email, text, date) VALUES ('John Doe', 'john@example.com', 'Hello, this is a test message.', CURRENT_DATE);
INSERT INTO messages (name, email, text, date) VALUES ('Alice Smith', 'alice@example.com', 'Testing message from Alice.', CURRENT_DATE);
INSERT INTO messages (name, email, text, date) VALUES ('Bob Johnson', 'bob@example.com', 'This is another test message.', CURRENT_DATE);
INSERT INTO messages (name, email, text, date) VALUES ('Emma Brown', 'emma@example.com', 'Test message content for Emma.', CURRENT_DATE);
INSERT INTO messages (name, email, text, date) VALUES ('Michael Davis', 'michael@example.com', 'Just testing the message functionality.', CURRENT_DATE);



