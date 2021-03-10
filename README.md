# Employee Management System

> This repository is the backend service of Employee Management System (EMS)
> 
> For frontend service, please visit https://github.com/YuKangYin1997/BrightZone-Frontend

## Features

- Login to system as an administrator
- Add a new employee
- Edit an employee
- Delete an employee
- List and filter employees according to combination of different criteria

## Requirements

- SpringBoot 2.3.9.RELEASE
- MySQL 8.0

## Backend Service Setup

1. Run `init.sql` at `src/main/resources/db/init.sql` to initialize the `ems` database and populate data.
   The resulting database consists of：
   - 2 admin users (123456 is their default passwords)
   - 25 employees
    
2. start backend service at http://localhost:8080


## Sample

1. login
   ![login](src/main/resources/repo/login.png)
   
2. add an employee
   ![addAnEmployee](src/main/resources/repo/addAnEmployee.png)

3. edit an employee
   ![editAnEmployee](src/main/resources/repo/editAnEmployee.png)

4. delete an employee
   ![deleteAnEmployee](src/main/resources/repo/deleteAnEmployee.png)
   
5. filter employee by title
   ![filterEmployeeByTitle](src/main/resources/repo/filterEmployeeByTitle.png)
   
6. filter employee by name
   ![filterEmployeeByName](src/main/resources/repo/filterEmployeeByName.png)
   
7. filter employee by name and title
   ![filterEmployeeByNameAndTitle](src/main/resources/repo/filterEmployeeByNameAndTitle.png)
   
8. logout
   ![logout](src/main/resources/repo/logout.png)