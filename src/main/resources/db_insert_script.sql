insert into cruise.user values(1, "Admin", "admin", "Admin", "Admin", "Admin", "2017-12-15");
insert into cruise.user values(2, "Panya", "Qwerty12", "Igor", "Vladimirovich", "Panyok", "2017-12-15");

insert into cruise.country values(1, "Finland", "Helsinki");
insert into cruise.country values(2, "Denmark", "Copengagen");
insert into cruise.country values(3, "Ukraine", "Odessa");
insert into cruise.country values(4, "Turkey", "Islamabad");
insert into cruise.country values(5, "Sweeden", "Stockholm");
insert into cruise.country values(6, "Belgium", "Antverpen");
insert into cruise.country values(7, "Holland", "Amsterdam");
insert into cruise.country values(8, "Norway", "Oslo");
insert into cruise.country values(9, "France", "Nance");

insert into cruise.ship values(1, "Titanic", 1500);
insert into cruise.ship values(2, "Saint Jesus", 2500);
insert into cruise.ship values(3, "Judas Die", 500);
insert into cruise.ship values(4, "Hammer Smashed Face", 500);
insert into cruise.ship values(5, "Sweet cradle", 1000);
insert into cruise.ship values(6, "Alcoship", 4000);

insert into cruise.staff values(1, 1, "Paul", "Cook");
insert into cruise.staff values(2, 1, "George", "Officiant");

insert into cruise.cruise values(1, 1, "On Finland", 2, 1, '2018-01-01 05:45:00', '2018-01-02 07:45:00', 2, "econom", 320);
insert into cruise.cruise values(2, 1, "From Finland", 1, 2, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 2, "econom", 350);
insert into cruise.cruise values(3, 3, "Finland Dream Tour", 2, 5, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 1, "econom", 350);
insert into cruise.cruise values(4, 3, "Finland Dream Tour", 2, 5, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 1, "business", 370);
insert into cruise.cruise values(5, 3, "Finland Dream Tour", 2, 5, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 1, "vip", 450);
insert into cruise.cruise values(6, 4, "Tour Love", 4, 8, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 3, "econom", 150);
insert into cruise.cruise values(7, 4, "Tour Love", 4, 8, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 3, "business", 250);
insert into cruise.cruise values(8, 6, "Not Love Tour", 9, 6, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 3, "econom", 250);
insert into cruise.cruise values(9, 4, "Not Love Tour", 6, 9, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 3, "econom", 250);
insert into cruise.cruise values(10, 2, "Tour Econom", 10, 3, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 4, "econom", 270);
insert into cruise.cruise values(11, 4, "Tour Econom", 3, 10, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 4, "econom", 270);
insert into cruise.cruise values(12, 5, "Tour Econom", 3, 10, '2018-01-02 05:45:00', '2018-01-03 07:45:00', 4, "business", 370);

insert into cruise.excursion values(1, 1, "In the city", 20);
insert into cruise.excursion values(2, 1, "Good", 25);
insert into cruise.excursion values(3, 5, "Sea", 15);