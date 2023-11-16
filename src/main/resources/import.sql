INSERT INTO users (id, first_name, last_name, registration_date, email, password) VALUES (1,'John', 'Doe', '2023-01-01', 'john@email.com', '{noop}john');
INSERT INTO users (id, first_name, last_name, registration_date, email, password) VALUES (2,'Jane', 'Smith', '2023-05-05', 'jane@email.com', '{noop}jane');
INSERT INTO roles(id, name) VALUES(1, 'ADMIN');
INSERT INTO roles(id, name) VALUES(2, 'USER');
INSERT INTO users_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles(user_id, roles_id) VALUES(2, 2);

INSERT INTO categories (name) VALUES ('Travel Adventures');
INSERT INTO categories (name) VALUES ('Health & Wellness');
INSERT INTO categories (name) VALUES ('Food & Recipes');
INSERT INTO categories (name) VALUES ('Technology & Gadgets');
INSERT INTO categories (name) VALUES ('Fashion & Style');
INSERT INTO categories (name) VALUES ('Personal Development & Growth');
INSERT INTO categories (name) VALUES ('Book Reviews & Recommendations');
INSERT INTO categories (name) VALUES ('Parenting & Family');
INSERT INTO categories (name) VALUES ('Home Decor & DIY');
INSERT INTO categories (name) VALUES ('Career & Professional Development');



INSERT INTO posts (content, title, author_id, date, category_id) VALUES('<p>ciao</p>', 'Mino', 1, '2023-01-01', 1);
INSERT INTO posts (content, title, author_id, date, category_id) VALUES('<p>sono</p>', 'Sono', 1, '2023-02-01', 2);
INSERT INTO posts (content, title, author_id, date, category_id) VALUES('<p>premio</p>', 'Premio', 1, '2023-03-01', 3);
INSERT INTO posts (content, title, author_id, date, category_id) VALUES('<p>quadro</p>', 'Quadro', 1, '2023-04-01', 4);
INSERT INTO posts (content, title, author_id, date, category_id) VALUES('<h1>Benvenuti nel mio blog</h1><p>Questo è il mio primo post sul mio nuovo blog. Sono entusiasta di condividere con voi le mie idee e le mie esperienze. Spero che troverete i miei articoli interessanti e informativi.</p><p>Ecco alcune delle cose di cui parlerò nel mio blog:</p><ul><li>Web development</li><li>Tecnologie emergenti</li><li>Consigli su carriera</li></ul><p>Non vedo l\'ora di iniziare questa avventura con voi. Restate sintonizzati per ulteriori aggiornamenti!</p>', 'Benvenuto nel mio blog', 1, '2023-05-01', 5);


