DELETE
FROM reservation;
DELETE
FROM date_price;
DELETE
FROM accommodation;

ALTER TABLE reservation
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE date_price
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE accommodation
    ALTER COLUMN id RESTART WITH 1;
