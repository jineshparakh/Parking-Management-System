 CREATE TABLE parking(
    -> cname VARCHAR(50) NOT NULL,
    -> slot_start TIME,
    -> slot_end TIME,
    -> dateGiven DATE,
    -> vname VARCHAR(50) DEFAULT 'NO',
    -> FOREIGN KEY(cname) REFERENCES register_representative(cname) ON DELETE CASCADE,
    -> FOREIGN KEY(vname) REFERENCES register_visitor(vname) ON DELETE CASCADE,
    -> PRIMARY KEY(cname, slot_start, slot_end,dateGiven,vname)
    -> );
    
    CREATE TABLE register_visitor(
    -> vname VARCHAR(50) NOT NULL,
    -> password VARCHAR(50) NOT NULL,
    -> PRIMARY KEY(vname, password)
    -> );
    
     CREATE TABLE register_representative(
    -> cname VARCHAR(50) NOT NULL,
    -> password VARCHAR(50) NOT NULL,
    -> PRIMARY KEY(cname, password)
    -> );
