# Object-Oriented Programming Coursework

This project was completed as the final project for the Object-Oriented Programming course, which I took during my second year of university.

## 📚 Project Overview

The main objective was to apply the core OOP principles.
In addition to the system logic, a demonstration page was developed to show how the project might look in the future.

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/sofijasljusar/oop-project.git
   ```
   
   <br>

2. **View the console version**
   <p>➤ Navigate to:</p>

   ```bash
   src > main > java > com.plandiy > ConsoleMain
   ```

   <p>➤ Uncomment to see the output.</p>

   <br>

3. **Run the demonstration page**
   <p>➤ Open MainApplication and run it to launch the UI demo.</p>

   <br>
---
   <br>

<details>
<summary><strong>📄 Original Project Requirements (Translated)</strong></summary>

<br>

#### Project Management System

The project management system is designed for planning, organizing, and controlling the execution of projects. It helps track tasks, deadlines, resources, and team collaboration. The system must support the following functionality:

#### Core Functional Requirements

- Create and manage projects, tasks, and subtasks
- Assign executors to tasks and track their progress
- Set and monitor task deadlines
- Manage project resources (human and material)
- Generate reports on project status and team performance
- Track project budgets and expenses
- Facilitate communication between project participants

#### Class Hierarchy

- **`Project`**  
  *Attributes:* ID, name, description, start date, end date, status, list of tasks, budget  
  *Methods:* add/remove task, update status, calculate progress  
  *Relations:* composition with `Task`, aggregation with `User`

- **`Task`**  
  *Attributes:* ID, name, description, status, priority, start date, deadline, executor, list of subtasks  
  *Methods:* add/remove subtask, assign executor, update status  
  *Relations:* composition with `Subtask`, aggregation with `User`

- **`Subtask`** *(inherits from `Task`)*  
  *Additional attributes and methods specific to subtasks*

- **`User`**  
  *Attributes:* ID, name, email, role, list of assigned tasks  
  *Methods:* add/remove task, update user info  
  *Relations:* aggregation with `Task`

- **`Resource`**  
  *Attributes:* ID, name, type (human/material), availability, cost  
  *Methods:* check availability, reserve, release

- **`Budget`**  
  *Attributes:* total budget, spent amount, remaining  
  *Methods:* add expense, check balance, generate financial report

- **`Report`**  
  *Attributes:* report type, period, data  
  *Methods:* generate report, export in different formats

- **`Communication`**  
  *Attributes:* sender, recipient, subject, content, date  
  *Methods:* send message, receive message, mark as read

- **`ProjectManagementSystem`**  
  *Attributes:* list of projects, users, resources  
  *Methods:* create project, assign user, manage resources, generate reports  
  *Relations:* composition with `Project`, `User`, `Resource`, `Report`

#### Application of SOLID Principles

- **SRP (Single Responsibility):**  
  Each component (task, resource, user) is represented by a dedicated class with its own responsibilities.

- **OCP (Open/Closed):**  
  New task or resource types can be added without changing existing code; supports modular extension.

- **LSP (Liskov Substitution):**  
  Different task or user types can be handled through a unified interface.

- **ISP (Interface Segregation):**  
  A user doesn’t need to know the internals of planning algorithms; a manager doesn’t need details of reporting internals.

- **DIP (Dependency Inversion):**  
  Abstract dependencies such as storage (database) and authentication are used instead of concrete implementations.

#### Design Patterns to Apply

- **Factory Method:** for creating various task and report types.
- **Observer:** for notifying status changes in tasks and projects.
- **Strategy:** for resource allocation and progress calculation strategies.
- **Command:** for implementing undo/redo functionality.

#### Additional Requirements

- **Advanced Analytics:**  
  Create an `AnalyticsEngine` class with methods for team performance analysis, project deadline forecasting, and risk detection. Integrate it with the main system.

- **Risk Management Enhancements:**  
  Create a `Risk` class with attributes like description, probability, impact, and mitigation strategy.  
  Create a `RiskManager` class for identifying, assessing, and managing risks.  
  Integrate it with `Project` for continuous monitoring.

- **Advanced Scheduling:**  
  Create a `Timeline` class to visualize tasks along a timeline.  
  Implement automatic scheduling/rescheduling based on dependencies and resources.  
  Integrate with `Project` for better deadline management.

- **Notification System:**  
  Create a `Notification` class with attributes like type, content, recipient, and status.  
  Create a `NotificationManager` for generating and sending alerts about deadlines and updates.  
  Integrate it with the system to support automated team communication.

</details>
