-- 1. Вывести список всех машин и все привязанные к ним детали.
select
    c.id,
    c.model,
    carcass.style,
    engine.type,
    transmission.type
from
    car c
left outer join carcass on (c.carcass_id = carcass.id)
left outer join engine on (c.engine_id = engine.id)
left outer join transmission on (c.transmission_id = transmission.id);

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- FIRST
select
    style as detail
from
    carcass
left outer join car on (carcass.id = car.carcass_id)
where car.carcass_id is not null
union

-- SECOND
select
    type as detail
from
    engine
left outer join car on (engine.id = car.engine_id)
where car.engine_id is not null
union

-- THIRD
select
    type as detail
from
    transmission
left outer join car on (transmission.id = car.transmission_id)
where car.transmission_id is not null;

