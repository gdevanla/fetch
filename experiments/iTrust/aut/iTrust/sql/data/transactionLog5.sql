DELETE FROM transactionlog;
                    
INSERT INTO transactionlog(loggedInMID, secondaryMID, transactionCode, timeLogged, addedInfo) VALUES 
					 (9000000000, 1, 1900,CONCAT(SUBDATE(CURDATE(),1),' 08:15:00'),''),
					 (2         , 1, 1900,CONCAT(SUBDATE(CURDATE(),1),' 09:43:00'),''),
					 (9000000006, 1, 2100,CONCAT(SUBDATE(CURDATE(),1),' 10:04:00'),''),
					 (9000000003, 1, 1102,CONCAT(SUBDATE(CURDATE(),1),' 11:18:00'),'1'),
					 (8000000009, 1, 2602,CONCAT(SUBDATE(CURDATE(),1),' 12:02:00'),'10763-1'),
					 (9000000003, 1, 1600,CONCAT(SUBDATE(CURDATE(),1),' 12:58:00'),''),
					 (8000000009, 1, 1600,CONCAT(SUBDATE(CURDATE(),1),' 13:02:00'),''),
					 (9000000000, 1, 1600,CONCAT(SUBDATE(CURDATE(),1),' 13:15:00'),''),
					 (9000000000, 1, 1102,CONCAT(SUBDATE(CURDATE(),3),' 14:23:00'),'11');