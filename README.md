# 🕵️‍♂️ Spyware – User Activity Monitoring System

**Spyware** is a Java-based user activity monitoring system designed to track and control suspicious user behavior in a network.  
It consists of two main components:

- **Spy-User (Client)** – Runs on the user's machine, capturing screenshots and key logs.
- **Spy-Server (Admin)** – Monitors incoming data, detects suspicious activity, and takes administrative actions.

---

## 📌 Features

- **Real-Time Screenshot Capture** – Client sends periodic screenshots to the server for monitoring.
- **Keylogging** – Tracks user keystrokes for detailed activity logs.
- **Suspicious Activity Detection** – Server can flag suspicious activities and issue alerts.
- **User Alerts** – Admin can notify users they are under surveillance.
- **System Control** – Option to suspend or shut down the user's system remotely for a specified duration.
- **Java Networking** – Implemented using TCP socket programming for communication between client and server.

---

## 🏗️ System Architecture
- **Spy-User**: Captures data and sends it to the server.
- **Spy-Server**: Receives data, displays it, and allows the admin to take actions.

---

## ⚙️ Technologies Used

- **Language**: Java (Java Networking, Swing/JavaFX for UI if applicable)
- **Networking**: TCP Sockets
- **Data Handling**: File I/O for storing screenshots and logs
- **Security**: Admin authentication (optional/if implemented)



## 🚀 How to Run

### 1 Start the Server
```bash
javac SpyServer.java
java SpyServer


## 2 Start the client

javac SpyUser.java
java SpyUser

