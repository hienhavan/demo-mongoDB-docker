# CRUD - Spring Boot & MongoDB

## 1. Giới thiệu

Dự án này cung cấp các API CRUD cơ bản để làm việc với MongoDB trong Spring Boot.

### Các API chính:
- **POST** `/user` - Thêm người dùng mới.
- **PUT** `/user/{id}` - Cập nhật thông tin người dùng.
- **DELETE** `/user/{id}` - Xóa người dùng theo ID.
- **GET** `/user` - Lấy danh sách người dùng có phân trang.
- **GET** `/user/{id}` - Lấy thông tin chi tiết của một người dùng theo ID.

## 2. Docker Commands

### 2.1. Docker Compose File (`docker-compose.yml`):

```yaml
version: '3.8'

services:
  mongodb:
    image: mongo:latest
    container_name: mongodb-container
    environment:
      - MONGO_INITDB_ROOT_USERNAME=root
      - MONGO_INITDB_ROOT_PASSWORD=123456789
      - MONGO_INITDB_DATABASE=demo
    ports:
      - "27017:27017"
    volumes:
      - mongodb-data:/data/db
    networks:
      - mongo-network
    command: ["mongod", "--auth"]

volumes:
  mongodb-data:
    driver: local

networks:
  mongo-network:
    driver: bridge
```

### 2.2. Các lệnh Docker cần thiết:

#### Khởi động MongoDB container:
```sh
docker-compose up -d
```

#### Dừng container:
```sh
docker-compose down
```

#### Xây dựng lại container (nếu có thay đổi cấu hình):
```sh
docker-compose up --build
```

#### Xóa volume (xóa toàn bộ dữ liệu MongoDB):
```sh
docker-compose down -v
```

## 3. Cách chạy ứng dụng Spring Boot

### Chạy ứng dụng bằng Gradle:
```sh
./gradlew bootRun
```
