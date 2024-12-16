-- Truncate both tables to reset data
TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE wallets CASCADE;
TRUNCATE TABLE transactions CASCADE;

-- password is "hashedpassword1" --

-- Insert into wallets table
INSERT INTO wallets (id, balance, time_created, password)
VALUES
('e6d07fc3-5f50-4c6c-a9f1-1b7e43444744', 1000, '2024-10-22 05:48:59.598160', '$2a$10$5nLrNZKuivUqJ3ZXrakqUewlC2.l8ti0nKT6Dgqw9KfAznlNWKhHS'),
('59c9c734-9a88-45ec-90ef-2e6a07d9b1f4', 5000, '2024-10-22 05:48:59.598160', '$2a$10$5nLrNZKuivUqJ3ZXrakqUewlC2.l8ti0nKT6Dgqw9KfAznlNWKhHS'),
('bd2c9145-6c0e-4628-91a9-10b7768dd839', 5000, '2024-10-22 05:48:59.598160', '$2a$10$5nLrNZKuivUqJ3ZXrakqUewlC2.l8ti0nKT6Dgqw9KfAznlNWKhHS');

-- Insert into users table
-- Note: Passwords are pre-hashed.
INSERT INTO users (id, first_name, last_name, email, phone_number, wallet_id, time_created)
VALUES
('4b3db6a4-e731-41cd-8b9f-8fb017edb768', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'e6d07fc3-5f50-4c6c-a9f1-1b7e43444744', '2024-10-22 05:48:59.598160'),
('a43f9f2a-7303-4657-9be6-0af6de57a1d5', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', '59c9c734-9a88-45ec-90ef-2e6a07d9b1f4', '2024-10-22 05:48:59.598160'),
('1c07a0c6-d4c1-414c-b7b8-36fb5d9f446a', 'Moh', 'Baba', 'mohbaba@email.com', '08155531802', 'bd2c9145-6c0e-4628-91a9-10b7768dd839', '2024-10-22 05:48:59.598160');
-- (100, 'John', 'Doe', 'john.doe@example.com', '1234567890', '$2a$10$5nLrNZKuivUqJ3ZXrakqUewlC2.l8ti0nKT6Dgqw9KfAznlNWKhHS', 100, '2024-10-22 05:48:59.598160'),
-- (102, 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', '$2a$10$ebYBX2tefMg6SldYDtnZW.jO5GMTDcazsPrle2X/od5dwB28omfsO', 102, '2024-10-22 05:48:59.598160'),
-- (103, 'Moh', 'Baba', 'mohbaba@email.com', '08155531802', '$2a$10$5nLrNZKuivUqJ3ZXrakqUewlC2.l8ti0nKT6Dgqw9KfAznlNWKhHS', 103, '2024-10-22 05:48:59.598160');

-- Insert into transactions table
-- Insert into transactions table
INSERT INTO transactions (id, amount, reference, type, status, wallet_id, created_at, updated_at)
VALUES
('b0d4a4e2-c2c7-41ec-b8f6-d3ae48fce1f0', 250.50, 'qfyd18kdxb', 1, 1, 'e6d07fc3-5f50-4c6c-a9f1-1b7e43444744', '2024-10-30 09:15:30', '2024-10-30 09:15:30'),
('c5a36e79-87d7-4dc6-b62e-66bd15d1726d', 500.75, 'qfyd18kdxb', 2, 2, '59c9c734-9a88-45ec-90ef-2e6a07d9b1f4', '2024-10-29 10:00:00', '2024-10-29 10:00:00'),
('e0b47aef-3a18-4e12-9ff8-d53c40b8fc5d', 150.00, 'qfyd18kdxb', 1, 3, 'bd2c9145-6c0e-4628-91a9-10b7768dd839', '2024-10-28 14:45:15', '2024-10-28 14:45:15'),
('f4ae8a65-47c9-40df-9d6b-8360cbdde5e9', 300.25, 'qfyd18kdxb', 2, 1, 'bd2c9145-6c0e-4628-91a9-10b7768dd839', '2024-10-27 16:30:00', '2024-10-27 16:30:00'),
('9e1c6c1e-99b3-4266-b0b3-3b8394d8a0b7', 450.25, 'qfyd18kdxb', 2, 1, 'bd2c9145-6c0e-4628-91a9-10b7768dd839', '2024-10-27 16:30:00', '2024-10-27 16:30:00');

