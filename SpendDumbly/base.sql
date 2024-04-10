

create database mydb;
create table inscrit(id_inscrit smallint unsigned not null primary key AUTO_INCREMENT,utilisateur varchar(255) not null, email varchar(255) not null, motdepass varchar(255) not null);

create table depense(id_depense smallint unsigned not null primary key AUTO_INCREMENT, id_inscrit smallint unsigned not null, date datetime not null, montant bigint not null, categorie enum('nourriture','vetement','loyer','facture','autre')  not null, description varchar(255) , foreign key (id_inscrit) references inscrit(id_inscrit));

insert into inscrit (utilisateur, email, motdepass) values ('nathalie','nathalie@gmail.com','lol');

insert into depense (id_inscrit, date, montant, categorie, description) values (1,now(),20000, 'facture', "JIRAMA");

 insert into depense (id_inscrit, date, montant, categorie, description) values (1,now(),40000, 'autre', "frais de scolarité");
insert into depense (id_inscrit, date, montant, categorie, description) values (1,now(),4000, 'vetement', "body press");

