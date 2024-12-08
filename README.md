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

### **Car Management**
- Add cars with details such as make, model, price, and availability dates.
- View available cars, excluding the ones owned by the current user.

### **Car Rental**
- Rent cars for specific dates.
- Automatically split car availability for overlapping rental periods.

### **Notifications**
- Notify car owners when their car is rented.
- Display notifications for owners upon login.

### **Receipt Generation**
- Generate a detailed receipt for every successful rental.

### **Error Handling**
- Validate inputs to prevent invalid data (e.g., negative prices or invalid dates).

---

## Technologies Used
- **Programming Language:** Java  
- **Frameworks/Tools:** JUnit (for testing)  
- **Build Tools:** Maven/Gradle (if applicable)  
- **IDE:** IntelliJ IDEA, Eclipse, or any preferred Java IDE  

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
- Add your car for rental or browse available cars to rent.
- Rent a car by providing a valid date range.
- View notifications and rental receipts.

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

Feel free to use and modify the code as needed, but please provide proper attribution to the original repository.
