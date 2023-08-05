CREATE TABLE IF NOT EXISTS
    pet_adoption (
        id_requester INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        pet_type VARCHAR(255),
        description VARCHAR(255),
        telephone VARCHAR(10)
    );

