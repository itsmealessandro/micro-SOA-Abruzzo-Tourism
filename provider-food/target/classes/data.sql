INSERT INTO restaurant(id, name, location, rating, cuisine_type) 
VALUES
('r1', 'Taverna Ducale', 'Sulmona', 4.7, 'TRADITIONAL'),
('r2', 'Ristorante del Lago', 'Scanno', 4.5, 'SEAFOOD'),
('r3', 'La Grotta dei Sapori', 'Roccaraso', 4.8, 'MOUNTAIN'),
('r4', 'Il Calesse', 'Pescocostanzo', 4.6, 'TRADITIONAL');

INSERT INTO restaurant_specialties (restaurant_id, specialty)
VALUES
('r1', 'Arrosticini'),
('r1', 'Maccheroni alla chitarra'),
('r2', 'Trota'),
('r2', 'Coregone'),
('r3', 'Polenta'),
('r3', 'Cinghiale'),
('r4', 'Agnello'),
('r4', 'Formaggi locali');