CREATE TABLE tasks_table (
    task_id SERIAL PRIMARY KEY,
    name CHAR(16),
    dead_line DATE,
    actual_time INT NULL,
    planned_time INT NULL,
    status CHAR(7)
);