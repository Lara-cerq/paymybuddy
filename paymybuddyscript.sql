create database if not exists paymybuddy;
use paymybuddy;

CREATE TABLE if not exists utilisateur (
                id_utilisateur integer NOT NULL AUTO_INCREMENT,
                id_utilisateur_parent integer,
                prenom VARCHAR(100) NOT NULL,
                nom VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
				role VARCHAR(100) default 'user',
                enabled tinyint(4) default true,
                PRIMARY KEY(id_utilisateur)
);


CREATE TABLE if not exists transaction (
                id_transaction integer NOT NULL AUTO_INCREMENT,
                type VARCHAR(100) NOT NULL,
                type_transaction VARCHAR(100) NOT NULL,
                montant DOUBLE PRECISION NOT NULL,
                cout DOUBLE PRECISION NOT NULL,
                id_utilisateur integer NOT NULL,
                PRIMARY KEY (id_transaction)
);


CREATE TABLE if not exists transaction_utilisateur (
                id_transaction integer NOT NULL,
                destinataire VARCHAR(100) NOT NULL,
				PRIMARY KEY (id_transaction)
);


CREATE TABLE if not exists transaction_banque (
                id_transaction integer NOT NULL,
                PRIMARY KEY (id_transaction)
);


CREATE TABLE if not exists compte (
                id_compte integer NOT NULL AUTO_INCREMENT,
                type VARCHAR(100) NOT NULL,
                id_utilisateur integer NOT NULL,
                type_compte VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_compte)
);


CREATE TABLE if not exists compte_pay_my_buddy (
                id_compte integer NOT NULL,
                solde DOUBLE PRECISION NOT NULL,
                PRIMARY KEY (id_compte)
);


CREATE TABLE if not exists compte_bancaire (
                id_compte integer NOT NULL,
                numero_compte VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_compte)
);

ALTER TABLE utilisateur ADD CONSTRAINT utilisateur_ami_fk
FOREIGN KEY (id_utilisateur_parent)
REFERENCES utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE compte ADD CONSTRAINT utilisateur_compte_fk
FOREIGN KEY (id_utilisateur)
REFERENCES utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transaction ADD CONSTRAINT utilisateur_transaction_fk
FOREIGN KEY (id_utilisateur)
REFERENCES utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transaction_banque ADD CONSTRAINT transaction_transaction_banque_fk
FOREIGN KEY (id_transaction)
REFERENCES transaction (id_transaction)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transaction_utilisateur ADD CONSTRAINT transaction_transaction_utilisateur_fk
FOREIGN KEY (id_transaction)
REFERENCES transaction (id_transaction)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE compte_bancaire ADD CONSTRAINT compte_compte_bancaire_fk
FOREIGN KEY (id_compte)
REFERENCES compte (id_compte)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE compte_pay_my_buddy ADD CONSTRAINT compte_compte_pay_my_buddy_fk
FOREIGN KEY (id_compte)
REFERENCES compte (id_compte)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

insert into utilisateur(id_utilisateur,id_utilisateur_parent,nom,prenom,email,password, role, enabled) values(1,null,'Cerqueira','Lara','lara@hotmail.fr','$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu', 'admin',1);
insert into compte(id_compte, type, type_compte,id_utilisateur) values (1, 'CB','bancaire',1);
insert into compte(id_compte, type, type_compte, id_utilisateur) values (2, 'CPMB','utilisateur',1);
insert into compte_pay_my_buddy(id_compte, solde) values(2,25);
insert into transaction(id_transaction,type, type_transaction, montant, cout, id_utilisateur) values (1,'TB','versement', 25, 1.25,1);
insert into transaction_banque(id_transaction) values(1);
commit;