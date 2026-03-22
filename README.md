# To-Do List (Java Console Application)

A simple **console-based To-Do List application in Java** that helps you manage your daily tasks. You can **add, view, update, remove, mark as completed, and search tasks**.

---

## 1. Features
- View all tasks with **status** (Pending/Completed) and **priority** (High/Medium/Low)  
- Add new tasks with description and priority  
- Remove tasks by task number  
- Update task details  
- Mark tasks as completed  
- Search tasks by keyword

---

## 2. Project Structure

```
ToDoList/
│── ToDoList.java     # Main application file
└── README.md         # Documentation
```

---

## 3. Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/akritisshetty/ToDo-List
   cd ToDo-List
2. **Compile the program**
   ```bash
   javac ToDoList.java
3. **Run the program**
   ```bash
   java ToDoList

---

## 4. How It Works

- Tasks are stored in an ArrayList, which acts as the main in-memory data structure.
- Each task contains a description, priority level, and completion status.
- User interacts with the application through a menu-driven command-line interface.
- Tasks can be added, updated, removed, marked as completed, or searched by keyword.
- All tasks are saved to a local file (tasks.txt) for persistence.
- On startup, the application loads existing tasks from the file into memory.
- Any changes made during execution are immediately written back to the file.
