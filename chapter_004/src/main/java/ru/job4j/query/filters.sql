-- Написать запрос получение всех продуктов с типом "СЫР".
SELECT p.name, p.expired_date, p.price, t.name FROM product AS p
INNER JOIN type as t ON (p.type_id = t.id)
WHERE LOWER(t.name) = LOWER('СЫР');

-- Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
SELECT p.name, p.expired_date, p.price FROM product AS p
WHERE p.name ILIKE '%Мороженное%';


-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT p.name, p.expired_date, p.price FROM product AS p
WHERE date_trunc('year', CURRENT_TIMESTAMP) = date_trunc('year', p.expired_date)
AND date_trunc('month', CURRENT_TIMESTAMP + interval '1 month') = date_trunc('month', p.expired_date);


-- Написать запрос, который выводит самый дорогой продукт.
SELECT p.name, p.expired_date, p.price FROM product AS p
ORDER BY p.price DESC
LIMIT 1;
 
-- OR
SELECT p.name, p.expired_date, p.price FROM product AS p
WHERE p.price = (SELECT MAX(p1.price) FROM product AS p1);

-- OR
SELECT p.name, p.expired_date, p.price FROM product AS p
INNER JOIN (SELECT MAX(price) as price FROM product) AS p1 ON (p.price = p1.price);


-- Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, count(1) FROM type AS t
LEFT OUTER JOIN product AS p ON t.id = p.type_id
GROUP BY t.name;


-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
SELECT p.name, p.expired_date, p.price, t.name FROM product AS p
INNER JOIN type as t ON (p.type_id = t.id)
WHERE LOWER(t.name) = LOWER('СЫР') OR LOWER(t.name) = LOWER('МОЛОКО');



-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name, count(1) FROM type AS t
LEFT OUTER JOIN product AS p ON t.id = p.type_id
GROUP BY t.name
HAVING(count(1)) < 10;


-- Вывести все продукты и их тип.
SELECT p.name, p.expired_date, p.price, t.name FROM product AS p
LEFT OUTER JOIN type AS t ON (p.type_id = t.id);