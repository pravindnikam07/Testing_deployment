-- Create task table
CREATE TABLE task (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  description VARCHAR(255),
  status VARCHAR(20)
);

-- Insert sample tasks
INSERT INTO task (title, description, status)
VALUES ('Task 1', 'Description for Task 1', 'Pending');

INSERT INTO task (title, description, status)
VALUES ('Task 2', 'Description for Task 2', 'In Progress');

INSERT INTO task (title, description, status)
VALUES ('Task 3', 'Description for Task 3', 'Completed');
