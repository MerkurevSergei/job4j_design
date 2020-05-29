-- 1. Вывести список всех машин и все привязанные к ним детали.
select
    c.id,
    c.model,
    carcass.style,
    engine.type,
    transmission.type
from
    car c
inner join carcass on (c.carcass_id = carcass.id)
inner join engine on (c.engine_id = engine.id)
inner join transmission on (c.transmission_id = transmission.id);

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- FIRST
select
    style as detail
from
    car
right outer join carcass on (car.carcass_id = carcass.id)
where car.carcass_id is null
union all

-- SECOND
select
    type as detail
from
    car
right outer join engine on (car.engine_id = engine.id)
where car.engine_id is null
union all

-- THIRD
select
    type as detail
from
    car
right outer join transmission on (transmission.id = car.transmission_id)
where car.transmission_id is null;

