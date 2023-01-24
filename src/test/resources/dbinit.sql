SET DB_CLOSE_DELAY 10;

CREATE TABLE "route" (
  "route_id" int NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  "route" varchar(200) NOT NULL,
  PRIMARY KEY ("route_id"),
  UNIQUE KEY "route_id_UNIQUE" ("route_id"),
  UNIQUE KEY  ("name")
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "route" VALUES (1,'Romantic','Валлетта, Барселона, Марсель, Генуя, Чивитавеккья, Палермо, Валлетта'),(2,'Love','Одеса,Валлетта, Барселона, Марсель, Генуя, Чивитавеккья, Палермо, Валлетта');

CREATE TABLE "ships" (
  "ship_id" int NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  "capacity" int NOT NULL,
  "image" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("ship_id"),
  UNIQUE KEY  ("name")
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "ships" VALUES (1,'Royal',200,'Royal.jpeg'),(2,'Voyage',220,'Voyage.jpeg'),(3,'Symphony of the Seas',300,'Symphony of the Seas.jpeg'),(4,'Oasis of the Seas',180,'Oasis of the Seas.jpeg'),(5,'Odyssey',270,'Odyssey.jpeg'),(6,'Fantasia',250,'Fantasia.jpeg');

CREATE TABLE "roles" (
  "role_id" int NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  PRIMARY KEY ("role_id"),
  UNIQUE KEY  ("name")
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "roles" VALUES (2,'admin'),(4,'not assigned'),(1,'passenger'),(3,'staff');

CREATE TABLE "users" (
  "users_id" int NOT NULL AUTO_INCREMENT,
  "surname" varchar(32) NOT NULL,
  "name" varchar(32) NOT NULL,
  "login" varchar(32) NOT NULL,
  "password" varchar(32) NOT NULL,
  "tel" varchar(12) NOT NULL,
  "roles_id" int NOT NULL DEFAULT '4',
  "date_of_birth" date NOT NULL,
  "money" double unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY ("users_id"),
  UNIQUE KEY "id_users_UNIQUE" ("users_id"),
  UNIQUE KEY "login_UNIQUE" ("login"),
  UNIQUE KEY "tel_UNIQUE" ("tel"),
  KEY "fk_role_id_idx" ("roles_id"),
  CONSTRAINT "fk_role_id" FOREIGN KEY ("roles_id") REFERENCES "roles" ("role_id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "users" VALUES (1,'Baranets','Artem','Artem','e20b21ae6508f22fc189c60a0880d0b8','380662252036',3,'1993-07-12',200),(2,'Baranets','Hanna','Hanna','e20b21ae6508f22fc189c60a0880d0b8','380662252035',3,'1993-07-12',50),(3,'Iam','Passenger','Passenger','e20b21ae6508f22fc189c60a0880d0b8','380662252034',1,'1993-07-12',600),(7,'Baranets','Artem','admin','e20b21ae6508f22fc189c60a0880d0b8','380662252039',2,'1993-03-10',0),(12,'Пивоваров','Андрій','Andriy','e20b21ae6508f22fc189c60a0880d0b8','380502221133',4,'1900-01-01',400);


CREATE TABLE "cruise" (
  "cruise_id" int NOT NULL AUTO_INCREMENT,
  "ship_id" int NOT NULL,
  "ship_name" varchar(45) NOT NULL,
  "route_id" int NOT NULL,
  "cruise_name" varchar(100) NOT NULL,
  "number_of_ports" int NOT NULL,
  "price" double NOT NULL,
  "status" varchar(45) NOT NULL DEFAULT 'didn"t start',
  "start_time" datetime NOT NULL,
  "end_time" datetime NOT NULL,
  "duration" int NOT NULL,
  "description" longtext NOT NULL,
  PRIMARY KEY ("cruise_id"),
  UNIQUE KEY "cruise_id_UNIQUE" ("cruise_id"),
  KEY "fk_ship_id_idx" ("ship_id"),
  KEY "fk_ship_name_idx" ("ship_name"),
  KEY "fk_route_id_idx" ("route_id"),
  CONSTRAINT "fk_route_id" FOREIGN KEY ("route_id") REFERENCES "route" ("route_id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_ship_id" FOREIGN KEY ("ship_id") REFERENCES "ships" ("ship_id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_ship_name" FOREIGN KEY ("ship_name") REFERENCES "ships" ("name") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "cruise" VALUES (1,1,'Royal',1,'Cruise',7,200,'started','2023-01-23 18:26:00','2023-01-27 18:26:00',6,'description'),(2,2,'Voyage',2,'started cruise',8,300,'didn"t start','2023-01-24 19:50:00','2023-01-30 18:49:00',8,'description'),(3,1,'Royal',1,'Royal Cruise',7,300,'didn"t start','2023-01-30 23:40:00','2023-02-04 23:40:00',5,'Description'),(9,5,'Odyssey',1,'Loving Cruise',7,200,'started','2023-01-24 00:00:00','2023-01-31 00:38:00',7,'Description');




CREATE TABLE "staff" (
  "id_of_ship" int NOT NULL,
  "staff_id" int NOT NULL,
  PRIMARY KEY ("id_of_ship","staff_id"),
  KEY "fk_staff_id_idx" ("staff_id"),
  CONSTRAINT "fk_ship_has_id" FOREIGN KEY ("id_of_ship") REFERENCES "ships" ("ship_id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_staff_id" FOREIGN KEY ("staff_id") REFERENCES "users" ("users_id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "staff" VALUES (1,1),(1,2);

CREATE TABLE "tickets" (
  "ticket_id" int NOT NULL AUTO_INCREMENT,
  "cruise_id" int NOT NULL,
  "user_id" int NOT NULL,
  "number_of_passengers" int NOT NULL,
  "total_price" double NOT NULL,
  "status" varchar(45) NOT NULL DEFAULT 'not paid',
  PRIMARY KEY ("ticket_id"),
  KEY "fk_user_id_idx" ("user_id"),
  KEY "fk_cruise_id_idx" ("cruise_id"),
  CONSTRAINT "fk_cruise_id" FOREIGN KEY ("cruise_id") REFERENCES "cruise" ("cruise_id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("users_id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO "tickets" VALUES (1,1,3,1,200,'rejected'),(2,1,3,1,200,'confirmed'),(3,1,3,2,400,'rejected'),(4,1,3,1,200,'confirmed'),(5,1,3,196,39600,'rejected'),(12,1,3,1,200,'confirmed'),(13,1,3,3,600,'rejected'),(14,3,12,1,300,'confirmed'),(15,1,12,1,200,'rejected'),(16,9,12,1,200,'rejected');

