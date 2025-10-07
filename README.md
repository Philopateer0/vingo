# 🍕 Vingo

**Vingo** is a full-stack food delivery web application that connects **users**, **shop owners**, and **delivery agents** through a unified digital platform.  
It allows customers to browse shops, order food, and track deliveries — while shop owners can manage menus, items, and orders seamlessly.

---

## 🧭 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Environment Variables](#environment-variables)
- [API Overview](#api-overview)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## 📘 About

Vingo aims to provide an end-to-end solution for a modern online food delivery system.  
It is built using the **MERN stack** and includes modular controllers for authentication, item management, order processing, and shop operations — with file uploads via **Multer** and **Cloudinary**.

---

## 🚀 Features

✅ Secure Authentication with JWT  
✅ User, Shop, and Delivery Agent roles  
✅ CRUD APIs for Shops, Items, and Orders  
✅ File uploads using **Multer** & **Cloudinary**  
✅ Order tracking and delivery assignment  
✅ MongoDB-based persistence layer  
✅ Environment-based configuration  
✅ Scalable folder and module structure  

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend** | React.js, Axios, React Router, TailwindCSS |
| **Backend** | Node.js, Express.js |
| **Database** | MongoDB (via Mongoose) |
| **File Storage** | Cloudinary |
| **Auth** | JWT (JSON Web Tokens), bcrypt |
| **Utilities** | Multer, dotenv, cors, cookie-parser |

---

## 🗂️ Project Structure
```
vingo/
├── backend/
│ ├── config/
│ │ └── db.js
│ ├── controllers/
│ │ ├── auth.controllers.js
│ │ ├── item.controllers.js
│ │ ├── order.controllers.js
│ │ ├── shop.controllers.js
│ │ └── user.controllers.js
│ ├── middlewares/
│ │ ├── isAuth.js
│ │ └── multer.js
│ ├── models/
│ │ ├── user.model.js
│ │ ├── shop.model.js
│ │ ├── item.model.js
│ │ ├── order.model.js
│ │ └── deliveryAssignment.model.js
│ ├── index.js
│ └── package.json
│
├── frontend/
│ ├── src/
│ │ ├── components/
│ │ ├── pages/
│ │ ├── services/
│ │ ├── App.js
│ │ └── index.js
│ ├── public/
│ └── package.json
│
└── README.md


