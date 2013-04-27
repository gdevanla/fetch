INSERT INTO patients
(MID,
lastName, 
firstName,
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
dateofbirth,
dateofdeath,
mothermid,
fathermid,
bloodtype,
ethnicity,
gender,
topicalnotes)
VALUES
(20,
'Koopa',
'Bowser',
'bkoopa@gmail.com',
'123 Grumpy Dr',
'',
'Raleigh', 
'NC',
'27606',
'234-123-4567',
'Mom',
'333-222-2345',
'Aetna', 
'1234 Aetna Blvd', 
'Suite 602',
'Charlotte',
'NC',
'28215',
'704-555-1234', 
'ChetumNHowe', 
'2008-09-05',
NULL,
1,
0,
'AB+',
'African American',
'Male',
''
);

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
VALUES (20, '1a91d62f7ca67399625a4368a6ab5d4a3baa6073', 'patient', 'first letter?', 'a');
/*password: pw*/

INSERT INTO declaredhcp(PatientID,HCPID)
VALUE(20, 9000000003);
