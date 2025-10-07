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
│   ├── config/
│   │   └── db.js
│   ├── controllers/
│   │   ├── auth.controllers.js
│   │   ├── item.controllers.js
│   │   ├── order.controllers.js
│   │   ├── shop.controllers.js
│   │   └── user.controllers.js
│   ├── middlewares/
│   │   ├── isAuth.js
│   │   └── multer.js
│   ├── models/
│   │   ├── user.model.js
│   │   ├── shop.model.js
│   │   ├── item.model.js
│   │   ├── order.model.js
│   │   └── deliveryAssignment.model.js
│   ├── index.js
│   └── package.json
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── App.js
│   │   └── index.js
│   ├── public/
│   └── package.json
│
└── README.md
```

---

## ⚙️ Backend Setup

### 1️⃣ Clone Repository
```bash
git clone https://github.com/rishabhkheria/vingo.git
cd vingo/backend
```

### 2️⃣ Install Dependencies
```bash
npm install
```

### 3️⃣ Configure `.env`
Create a `.env` file inside the **backend** folder:
```bash
PORT=8000
MONGODB_URL=your_mongodb_connection_string
JWT_SECRET=your_secret_key
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
EMAIL=your_email
PASS=your_app_password
```

### 4️⃣ Run Backend Server
```bash
npm start
```
Server will start on **http://localhost:8000**

---

## 💻 Frontend Setup

### 1️⃣ Move to Frontend Directory
```bash
cd ../frontend
```

### 2️⃣ Install Dependencies
```bash
npm install
```

### 3️⃣ Create `.env` File (if applicable)
```bash
REACT_APP_BACKEND_URL=http://localhost:8000
```

### 4️⃣ Run Frontend
```bash
npm start
```
Frontend will run on **http://localhost:3000**

---

## 🌐 Environment Variables

| Variable | Description |
|-----------|-------------|
| `PORT` | Port on which backend runs |
| `MONGODB_URL` | MongoDB connection URI |
| `JWT_SECRET` | JWT secret key |
| `CLOUDINARY_CLOUD_NAME` | Cloudinary account name |
| `CLOUDINARY_API_KEY` | Cloudinary API key |
| `CLOUDINARY_API_SECRET` | Cloudinary API secret |
| `EMAIL` | Sender email (for notifications) |
| `PASS` | App password for email service |
| `REACT_APP_BACKEND_URL` | Backend base URL for frontend |

---

## 🔗 API Overview

| Feature | Endpoint | Method | Description |
|----------|-----------|--------|-------------|
| Auth | `/api/auth/signup` | POST | Register a new user |
| Auth | `/api/auth/login` | POST | Login existing user |
| Shop | `/api/shops` | GET | List all shops |
| Shop | `/api/shops/:id` | GET | Get shop details |
| Item | `/api/items/:shopId` | GET | Fetch items by shop |
| Order | `/api/orders` | POST | Create a new order |
| Order | `/api/orders/:id` | GET | Get specific order details |
| Delivery | `/api/delivery/assign` | POST | Assign a delivery agent |

---

## 🧩 Contributing

We welcome contributions!

1. **Fork** this repository  
2. **Create your feature branch**
   ```bash
   git checkout -b feature/YourFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m "Add new feature"
   ```
4. **Push the branch**
   ```bash
   git push origin feature/YourFeature
   ```
5. **Open a Pull Request** 🚀

---

## 🪪 License

This project is licensed under the **MIT License**.

---

## 📬 Contact

**👨‍💻 Author:** [Rishabh Kheria](https://github.com/rishabhkheria)  
📧 **Email:** rishabhkheria231@gmail.com  

---
> 💡 *Vingo – a scalable, modular, and modern full-stack food delivery solution.*
