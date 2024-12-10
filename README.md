# Wheelzy Car Rental System

## Description
**Wheelzy** is a peer-to-peer car rental system that allows users to rent cars and car owners to list their vehicles for rental. The system ensures efficient booking, car availability management, and secure transactions, providing an intuitive user experience for both renters and owners.

The project includes:
- User authentication and registration.
- A car management system for adding, viewing, and renting cars.
- Notifications for car owners about rentals.
- Rental receipt generation for renters.
- Support for multiple car types (e.g., SUVs and Sedans).
- Intelligent availability splitting to handle multiple rental periods for the same car.

---

## Features

### **User Authentication**
- Secure login and registration.
- Validation for unique usernames, phone numbers, and email addresses.

![image](https://github.com/user-attachments/assets/32f73706-6227-42b0-91b2-f9cd11077de1)

### **Car Management**
- Add cars with details such as make, model, price, and availability dates.
- View available cars, excluding the ones owned by the current user.

![image](https://github.com/user-attachments/assets/2f5875ad-72c3-41dc-b27c-ae14df9cc2cd)

### **Car Rental**
- Rent cars for specific dates.

![image](https://github.com/user-attachments/assets/d0c4038c-d84e-4a36-a6a0-e41166906d75)

- Automatically split car availability for overlapping rental periods.

![image](https://github.com/user-attachments/assets/43554548-73a2-4a6d-a364-38e33caa0f13)


### **Notifications**
- Notify car owners when their car is rented.
- Display notifications for owners upon login.

![image](https://github.com/user-attachments/assets/19c04b4e-1814-4ff5-aed3-bf5253a9b595)


### **Receipt Generation**
- Generate a detailed receipt for every successful rental.

![image](https://github.com/user-attachments/assets/660af0ac-54bb-400f-95b2-94a18dfc3744)

### **Error Handling**
- Validate inputs to prevent invalid data (e.g., negative prices or invalid dates).

![image](https://github.com/user-attachments/assets/7e79b0b3-ed46-4f58-9d52-e35b93fbe04e)


---

## Technologies Used
- **Programming Language:** Java  
- **Frameworks/Tools:** JUnit (for testing)  
- **Build Tools:** Maven/Gradle (if applicable)  
- **IDE:** IntelliJ IDEA, Eclipse, or any preferred Java IDE  

---

# Unit Testing
## Purpose
Unit tests are implemented to ensure the core functionalities of the Wheelzy Car Rental System work as expected. These tests cover:

- User Authentication: Validates login, registration, and input validation.
- Car Management: Verifies adding, viewing, and availability of cars.
- Car Rental: Ensures correct rental booking, availability splitting, and receipt generation.
- Notifications: Confirms owners are notified upon rental events.
- Error Handling: Tests edge cases for invalid inputs, such as invalid dates or negative prices.

![image](https://github.com/user-attachments/assets/eac35e8e-5d2e-42eb-b242-a91edc6eadf0)

## Testing
 The project includes many automated tests (using JUnit) to ensure that all features, like registering, logging in, renting cars, and generating notifications and receipts, work as intended. These tests cover a wide range of scenarios, including correct operations and error handling and we got 77% over this Test.

---

## Contribute:
Total commit of project is 72 | Amer 32 commits - Abdulrahmn 40 commits
(There is github insights problem with Abdulrhamn contributed while there is commits by his name in the repo)


## Framework
JUnit: All unit tests are written using the JUnit framework.

---

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/wheelzy-rental-system.git
   ```
2. Navigate to the project directory:    

    ```bash
      cd wheelzy-rental-system
    ```
3. Open the project in your preferred Java IDE.

4. Build and run the project.

## Usage
1. Start the Application: 

- Run the Main.java file to start the system.

2. Features:

- Register as a new user or log in with existing credentials.

![image](https://github.com/user-attachments/assets/73b899c1-5977-4958-b51f-c3d8f714d508)

- Add your car for rental or browse available cars to rent.

![image](https://github.com/user-attachments/assets/6bec32c5-3894-4863-b429-7240e905c014)

- Rent a car by providing a valid date range.

![image](https://github.com/user-attachments/assets/6e639143-3e2a-41c4-9d0e-6ad858e648dc)

- View notifications and rental receipts.

![image](https://github.com/user-attachments/assets/73abc1cb-a53f-4a4d-a409-af823e160cec)


## Contribution
Contributions are welcome! Please follow these steps:

1. Fork the repository.

2. Create a new branch for your feature or fix:

  ```bash
    git checkout -b feature-name
  ```
3. Commit your changes and push to your branch:

  ```bash
    git push origin feature-name
  ```
4. Create a pull request explaining your changes.

---

## Overall
 "Wheelzy" demonstrates how to manage user accounts, handle car data, process rentals, send notifications, and produce receipts in a straightforward, text-based environment. It is a good example of how to build a simple, file-driven rental application in Java.

Feel free to use and modify the code as needed, but please provide proper attribution to the original repository.
