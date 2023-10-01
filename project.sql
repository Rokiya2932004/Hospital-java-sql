create database hospital1


create table t_Doctor(

Name varchar (20) not null,
Dssn int primary key,
Specialization nvarchar (50)not null
);
---------------------------------------------------------------------
create table t_Patient (

P_Name varchar (20) not null,
SSN int primary key,
Medical_insurance varchar (max) not null, 
Date_admitted nvarchar(max),
Date_of_check_out nvarchar(max) 
);
---------------------------------------------------------------------
create table PatDoc (

d_Dssn int foreign key references t_Doctor(Dssn),
p_SSN int foreign key references t_Patient(SSN)
);
---------------------------------------------------------------------
create table t_Test (

Test_id int primary key,
Test_name varchar (50) not null,
Date_test date,
Time_test nvarchar (15) not null,
Result nvarchar (max) not null 
);
---------------------------------------------------------------------
create table TestDoc(

t_id int foreign key references t_Test(Test_id),
d_Dssn int foreign key references t_Doctor(Dssn),

);
---------------------------------------------------------------------
create table PatTest(

p_SSN int foreign key references t_Patient(SSN),
t_id int foreign key references t_Test(Test_id)
);