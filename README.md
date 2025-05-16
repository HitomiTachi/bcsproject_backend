# bcsproject_backend
Có thư mục tên bcsproject_backend trước, rồi tải cái này:
https://drive.google.com/file/d/1NdzpsaUnK1lpC9N8Oas04w8vTurnBnYC/view?usp=sharing

Nếu đã clone git về rồi thì thôi, phòng hờ (Trong đó có đầy đủ thư viện maven các thứ)
Nếu dùng IDE thì Đợi nó load maven, nếu VS Code thì tải hết plugin Maven về

Trong đây là dùng lombok các thứ nên API ghi khá gọn, có j kêu chat phân tích rồi code dựa theo đó
Muốn test code thì dùng PostMan như xưa ông thầy dạy
Dưới đây là csdl dùng cho MySQL:
--------------------------------------------------------------------------------------------------
-- Xóa cơ sở dữ liệu cũ nếu tồn tại
DROP DATABASE IF EXISTS class_management;

-- Tạo cơ sở dữ liệu mới
CREATE DATABASE class_management;
USE class_management;

-- Bảng users (thông tin cá nhân)
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(50) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role ENUM('Student', 'ClassLeader', 'Teacher', 'Guest') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng accounts (thông tin đăng nhập, username trùng student_id hoặc mã giáo viên)
CREATE TABLE accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng classes (lớp học)
CREATE TABLE classes (
    class_id INT AUTO_INCREMENT PRIMARY KEY,
    class_name VARCHAR(255) NOT NULL,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- Bảng class_members (thành viên lớp học)
CREATE TABLE class_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    user_id INT NOT NULL,
    is_class_leader BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng assignments (bài tập)
CREATE TABLE assignments (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    class_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    deadline DATETIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE
);

-- Bảng assignment_submissions (nộp bài tập)
CREATE TABLE assignment_submissions (
    submission_id INT PRIMARY KEY AUTO_INCREMENT,
    assignment_id INT NOT NULL,
    student_id INT NOT NULL,
    file_url VARCHAR(255),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assignment_id) REFERENCES assignments(assignment_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng posts (bài đăng)
CREATE TABLE posts (
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    class_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng comments (nhận xét)
CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng groups (nhóm học tập)
CREATE TABLE groups (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    class_id INT NOT NULL,
    group_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE
);

-- Bảng group_members (thành viên nhóm)
CREATE TABLE group_members (
    group_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng projects (dự án)
CREATE TABLE projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    project_name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);

-- Bảng tasks (nhiệm vụ)
CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE
);

-- Bảng task_assignments (phân công nhiệm vụ)
CREATE TABLE task_assignments (
    task_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (task_id, user_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Truy vấn dữ liệu từ bảng accounts
SELECT * FROM accounts;

-- Tạm tắt kiểm tra khóa ngoại để TRUNCATE không bị lỗi
SET FOREIGN_KEY_CHECKS = 0;

-- Xoá dữ liệu cũ đúng thứ tự phụ thuộc
TRUNCATE TABLE task_assignments;
TRUNCATE TABLE tasks;
TRUNCATE TABLE projects;
TRUNCATE TABLE group_members;
TRUNCATE TABLE class_groups;
TRUNCATE TABLE posts;
TRUNCATE TABLE assignments;
TRUNCATE TABLE class_members;
TRUNCATE TABLE classes;
TRUNCATE TABLE accounts;
TRUNCATE TABLE users;

SET FOREIGN_KEY_CHECKS = 1;

-- ✅ Chỉnh ENUM 'status' trong bảng tasks
ALTER TABLE tasks 
MODIFY COLUMN status ENUM('Pending', 'InProgress', 'Completed') DEFAULT 'Pending';

-- Chèn dữ liệu mẫu users
INSERT INTO users (student_id, full_name, email, role) VALUES
('SV001', 'Nguyen Van A', 'a@example.com', 'Student'),
('SV002', 'Tran Thi B', 'b@example.com', 'Student'),
('SV003', 'Le Van C', 'c@example.com', 'ClassLeader'),
('GV001', 'Pham Thi D', 'd@example.com', 'Teacher');

-- Chèn dữ liệu mẫu accounts
INSERT INTO accounts (user_id, username, password) VALUES
(1, 'SV001', '1'),
(2, 'SV002', '1'),
(3, 'SV003', '1'),
(4, 'GV001', '1');

-- Thêm lớp học
INSERT INTO classes (class_name, teacher_id) VALUES
('CNTT_2023_A', 4),
('CNTT_2023_B', 4);

-- Thêm thành viên lớp học
INSERT INTO class_members (class_id, user_id, is_class_leader) VALUES
(1, 3, TRUE),
(1, 1, FALSE),
(2, 2, FALSE);

-- Thêm bài tập
INSERT INTO assignments (class_id, title, description, deadline) VALUES
(1, 'Bài tập Python', 'Viết chương trình quản lý danh bạ.', '2025-06-20 23:59:00'),
(2, 'Bài tập HTML', 'Tạo trang web cá nhân.', '2025-06-25 23:59:00');

-- Thêm bài đăng
INSERT INTO posts (class_id, user_id, content) VALUES
(1, 3, 'Thông báo: Lớp CNTT_2023_A sẽ có bài kiểm tra vào tuần sau.'),
(2, 4, 'Mọi người nhớ hoàn thành bài tập HTML trước hạn nhé!');

-- Thêm nhóm học tập (đã đổi tên bảng thành class_groups)
INSERT INTO class_groups (class_id, group_name) VALUES
(1, 'Nhóm Python'),
(2, 'Nhóm HTML');

-- Thêm thành viên nhóm học tập
INSERT INTO group_members (group_id, user_id) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 3);

-- Thêm dự án
INSERT INTO projects (group_id, project_name, description) VALUES
(1, 'Dự án Python', 'Xây dựng ứng dụng quản lý danh bạ'),
(2, 'Dự án HTML', 'Tạo trang web cá nhân');

-- Thêm nhiệm vụ
INSERT INTO tasks (project_id, title, description, status) VALUES
(1, 'Thiết kế giao diện', 'Thiết kế giao diện người dùng', 'Pending'),
(2, 'Tạo trang chủ', 'Tạo trang chủ cho website', 'InProgress');

-- Thêm phân công nhiệm vụ
INSERT INTO task_assignments (task_id, user_id) VALUES
(1, 1),
(2, 2);
