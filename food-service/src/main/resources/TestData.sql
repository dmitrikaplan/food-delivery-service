INSERT INTO Users (activated, activation_code, email, firstname, lastname, password, role, username) VALUES
    (TRUE, 'abc', 'email@gmail.com', 'ivan', 'dorozhkin', 'testPass', 'ROLE_ADMIN', 'ivandorozhkin'),
    (TRUE, 'def', 'example@gmail.com', 'chimin', '-', 'testPass', 'ROLE_ADMIN', 'chimin_admin');

INSERT INTO Address (address) VALUES
    ('г. Санкт-Петербург ул. Саблинская д. 14 кв. 3'),
    ('г. Санкт-Петербург ул. Марата д. 4 кв. 9'),
    ('г. Санкт-Петербург ул. Звенигородская д. 134 кв. 19');

INSERT INTO Cart (cart_id, user_id) VALUES
    (1, 1);
    (2, 2);

INSERT INTO Category (category_name) VALUES
    ('Пицца'),
    ('Завтраки'),
    ('Грузия');

INSERT INTO Food (food_name, description, price, image_url) VALUES
    ('Английский завтрак',
    'Классический английский завтрак включает в себя: яичницу-глазунью, хрустящий бекон, поджаренные тонкие.',
     400,
     'https://attuale.ru/wp-content/uploads/2018/04/full-english-breakfast035.jpg'
    );

INSERT INTO Feedback (user_id, food_id, rating, comment) VALUES
    (1, 1, 4, 'Достаточно вкусно, но я бы добавил ещё специй.'),
    (2, 1, 5, 'Идеально!');

INSERT INTO cart_detail (cart_id, food_id, quantity) VALUES
    (7, 1, 1);

INSERT INTO Payment (user_id, card_number, expire_date) VALUES
    (1, 12345, '2024-01-01 12:00:00');

INSERT INTO Orber (user_id, address_id, order_date, payment_id, cart_id, full_price, state) VALUES
    (1,
     1,
     '2023-01-01 12:00:00',
     1,
     1,
     1500,
     'DELIVERED'
    );

