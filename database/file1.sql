DROP TABLE RECIPES IF EXISTS;
DROP TABLE DOCTORS IF EXISTS;
DROP TABLE PATIENTS IF EXISTS;

CREATE TABLE PATIENTS (
    patient_id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30) NOT NULL,
    phone VARCHAR(15) UNIQUE
);

CREATE TABLE DOCTORS (
    doctor_id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30) NOT NULL,
    specialization VARCHAR(60) NOT NULL
);

CREATE TABLE RECIPES (
    recipe_id BIGINT IDENTITY PRIMARY KEY,
    doctor_id BIGINT FOREIGN KEY REFERENCES DOCTORS,
    patient_id BIGINT FOREIGN KEY REFERENCES PATIENTS,
    description VARCHAR(1000) NOT NULL,
    creation_date DATE  NOT NULL,
    expiration_date DATE  NOT NULL,
    priority  nvarchar(16) not null
);


