-- Initialize games data
INSERT INTO games (name, review_type, description, developer, publisher, release_date, price, genre, metacritic_score, steam_reviews) VALUES
('The Witcher 3: Wild Hunt', 'OVERWHELMINGLY_POSITIVE', 'Story-driven open world RPG set in a visually stunning fantasy universe.', 'CD Projekt Red', 'CD Projekt', '2015-05-19', 39.99, 'RPG', 93, 547821),
('Portal 2', 'OVERWHELMINGLY_POSITIVE', 'The sequel to the acclaimed Portal features a single-player campaign and a co-op mode.', 'Valve', 'Valve', '2011-04-19', 9.99, 'Puzzle', 95, 154673),
('Stardew Valley', 'OVERWHELMINGLY_POSITIVE', 'You''ve inherited your grandfather''s old farm plot in Stardew Valley.', 'ConcernedApe', 'ConcernedApe', '2016-02-26', 14.99, 'Simulation', 89, 231845),
('Hades', 'OVERWHELMINGLY_POSITIVE', 'Defy the god of the dead as you hack and slash out of the Underworld.', 'Supergiant Games', 'Supergiant Games', '2020-09-17', 24.99, 'Action', 93, 156821),
('Terraria', 'OVERWHELMINGLY_POSITIVE', 'Dig, fight, explore, build! Nothing is impossible in this action-packed adventure game.', 'Re-Logic', 'Re-Logic', '2011-05-16', 9.99, 'Sandbox', 81, 876543),
('Hollow Knight', 'OVERWHELMINGLY_POSITIVE', 'Forge your own path in Hollow Knight! An epic action adventure through a ruined kingdom.', 'Team Cherry', 'Team Cherry', '2017-02-24', 14.99, 'Metroidvania', 90, 143256),
('Celeste', 'OVERWHELMINGLY_POSITIVE', 'Help Madeline survive her inner demons on her journey to the top of Celeste Mountain.', 'Maddy Makes Games', 'Maddy Makes Games', '2018-01-25', 19.99, 'Platformer', 94, 89321),
('DOOM Eternal', 'VERY_POSITIVE', 'Hell''s armies have invaded Earth. Become the Slayer in an epic single-player campaign.', 'id Software', 'Bethesda Softworks', '2020-03-20', 59.99, 'FPS', 88, 98765),
('Cyberpunk 2077', 'MOSTLY_POSITIVE', 'An open-world, action-adventure story set in Night City.', 'CD Projekt Red', 'CD Projekt', '2020-12-10', 59.99, 'RPG', 86, 234567),
('Among Us', 'VERY_POSITIVE', 'Play with 4-15 players online or via local WiFi as you attempt to prep your spaceship.', 'InnerSloth', 'InnerSloth', '2018-06-15', 4.99, 'Party', 85, 456789),
('Fall Guys', 'MOSTLY_POSITIVE', 'Fall Guys is a massively multiplayer party game with up to 60 players.', 'Mediatonic', 'Devolver Digital', '2020-08-04', 19.99, 'Party', 79, 123456),
('Red Dead Redemption 2', 'VERY_POSITIVE', 'America, 1899. The end of the Wild West era has begun.', 'Rockstar Games', 'Rockstar Games', '2019-12-05', 59.99, 'Action', 96, 345678),
('Grand Theft Auto V', 'VERY_POSITIVE', 'When a young street hustler, a retired bank robber and a terrifying psychopath find themselves entangled.', 'Rockstar North', 'Rockstar Games', '2015-04-14', 29.99, 'Action', 96, 1234567),
('Minecraft', 'OVERWHELMINGLY_POSITIVE', 'Minecraft is a game about placing blocks and going on adventures.', 'Mojang Studios', 'Microsoft Studios', '2011-11-18', 26.95, 'Sandbox', 93, 987654),
('Counter-Strike 2', 'VERY_POSITIVE', 'For over two decades, Counter-Strike has offered an elite competitive experience.', 'Valve', 'Valve', '2023-09-27', 0.00, 'FPS', 87, 765432),
('Dota 2', 'MOSTLY_POSITIVE', 'Every day, millions of players worldwide enter battle as one of over a hundred Dota heroes.', 'Valve', 'Valve', '2013-07-09', 0.00, 'MOBA', 90, 543210),
('Apex Legends', 'MOSTLY_POSITIVE', 'Conquer with character in Apex Legends, a free-to-play Battle Royale shooter.', 'Respawn Entertainment', 'Electronic Arts', '2019-02-04', 0.00, 'Battle Royale', 89, 432109),
('Valorant', 'MOSTLY_POSITIVE', 'Blend your style and experience on a global, competitive stage.', 'Riot Games', 'Riot Games', '2020-06-02', 0.00, 'FPS', 84, 321098),
('Rocket League', 'VERY_POSITIVE', 'Soccer meets driving once again in this physics-based multiplayer-focused sequel.', 'Psyonix', 'Psyonix', '2015-07-07', 0.00, 'Sports', 86, 654321),
('Fortnite', 'MIXED', 'Fortnite is the completely free multiplayer game where you and your friends collaborate.', 'Epic Games', 'Epic Games', '2017-07-25', 0.00, 'Battle Royale', 78, 789012),
('Dead Cells', 'VERY_POSITIVE', 'Dead Cells is a roguelike, metroidvania inspired, action-platformer.', 'Motion Twin', 'Motion Twin', '2018-08-07', 24.99, 'Metroidvania', 91, 87654),
('Ori and the Will of the Wisps', 'VERY_POSITIVE', 'Play as the guardian spirit Ori and explore a world of secrets, challenges and danger.', 'Moon Studios', 'Xbox Game Studios', '2020-03-11', 29.99, 'Platformer', 90, 76543),
('Cuphead', 'VERY_POSITIVE', 'Cuphead is a classic run and gun action game heavily focused on boss battles.', 'Studio MDHR', 'Studio MDHR', '2017-09-29', 19.99, 'Action', 86, 65432),
('Slay the Spire', 'OVERWHELMINGLY_POSITIVE', 'We fused card games and roguelikes together to make the best single player deckbuilder.', 'MegaCrit', 'MegaCrit', '2019-01-23', 24.99, 'Card Game', 89, 234567),
('Factorio', 'OVERWHELMINGLY_POSITIVE', 'Factorio is a game about building and creating automated factories.', 'Wube Software', 'Wube Software', '2020-08-14', 35.00, 'Strategy', 90, 145632);

-- Initialize users data
INSERT INTO users (username, email, password, role) VALUES
('user1', 'user1@example.com', 'Password123', 'ROLE_USER'),
('admin1', 'admin1@example.com', 'Password123', 'ROLE_ADMIN'),
('moderator1', 'moderator1@example.com', 'Password123', 'ROLE_MODERATOR');
