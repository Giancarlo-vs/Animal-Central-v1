CREATE TABLE IF NOT EXISTS
    pet_credit (
        id_pet INT AUTO_INCREMENT PRIMARY KEY,
        is_active BOOLEAN DEFAULT true,
        total_amount NUMERIC(8, 2) DEFAULT 0,
        amount_paid NUMERIC(8, 2),
        description VARCHAR(255)
    );
