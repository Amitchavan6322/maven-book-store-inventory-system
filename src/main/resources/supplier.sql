USE stepupdb;

CREATE TABLE Supplier (
    supplierID INT PRIMARY KEY AUTO_INCREMENT,
    supplierName VARCHAR(100) NOT NULL,
    supplierAddress VARCHAR(255) NOT NULL,
    supplierContact BIGINT NOT NULL,
    supplierEmailId VARCHAR(100) NOT NULL
);

-- Insert 100 sample records into the Supplier table
INSERT INTO Supplier (supplierName, supplierAddress, supplierContact, supplierEmailId) VALUES
('Wayne Enterprises', '1007 Mountain Dr, Gotham', 9876543211, 'info@wayneenterprises.com'),
('LexCorp', 'LexCorp Tower, Metropolis', 9876543212, 'contact@lexcorp.com'),
('Queen Industries', 'Star City Business Park, Star City', 9876543213, 'suppliers@queenindustries.com'),
('Stark Industries', '10880 Malibu Point, Malibu', 9876543214, 'support@starkindustries.com'),
('Oscorp', '20 Ingram St, New York', 9876543215, 'info@oscorp.com'),
('Hammer Industries', '52 Long Island, New York', 9876543216, 'contact@hammerindustries.com'),
('Pym Technologies', '34 Tech Way, San Francisco', 9876543217, 'service@pymtech.com'),
('Future Foundation', '4 Yancy Street, New York', 9876543218, 'support@futurefoundation.com'),
('Daily Planet', '354 West 38th St, Metropolis', 9876543219, 'news@dailyplanet.com'),
('Stagg Enterprises', 'Empire Heights, Gotham', 9876543220, 'business@staggenterprises.com');
