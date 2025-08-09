# 🕵️‍♂️ Spyware – User Activity Monitoring System

**Spyware** is a Java-based **user activity monitoring system** designed to track and control suspicious user behavior in a network.  
It consists of two main components:

- **Spy-User (Client)** – Runs on the user's machine, capturing screenshots and key logs.
- **Spy-Server (Admin)** – Monitors incoming data, detects suspicious activity, stores data in a database, and takes administrative actions.

---

## 📌 Features

- **Real-Time Screenshot Capture** – Client sends periodic screenshots to the server for monitoring.
- **Keylogging** – Tracks user keystrokes for detailed activity logs.
- **Suspicious Activity Detection** – Server can flag suspicious activities and issue alerts.
- **User Alerts** – Admin can notify users they are under surveillance.
- **System Control** – Option to suspend or shut down the user's system remotely for a specified duration.
- **Java Networking** – Implemented using TCP socket programming.
- **Database Integration (MySQL)** – Stores:
  - User details
  - Screenshot metadata
  - Key logs
  - Alert history
  - Action history (suspensions/shutdowns)

---

- **Spy-User**: Captures data and sends it to the server.
- **Spy-Server**: Receives data, logs it in MySQL, displays it to the admin, and allows actions.

---

## ⚙️ Technologies Used

- **Language**: Java
- **Networking**: TCP Sockets
- **Database**: MySQL (via JDBC)
- **Data Handling**: File I/O for screenshots, SQL for logs
- **Security**: Admin authentication (optional)

---



## 🚀 How to Run

### 1 Start the Server
```bash
javac SpyServer.java
java SpyServer


## 2 Start the client

javac SpyUser.java
java SpyUser

