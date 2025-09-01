import React from 'react';

const KanbanColumn = ({ title, tasks }) => (
  <div style={{ border: '1px solid #ccc', padding: '10px', margin: '10px', width: '250px' }}>
    <h3>{title}</h3>
    {tasks.map(task => (
      <div key={task.taskId} style={{ border: '1px solid #eee', padding: '5px', margin: '5px 0' }}>
        <p>{task.taskName}</p>
        <small>Assignee: {task.assignee}</small>
      </div>
    ))}
  </div>
);

const KanbanBoard = ({ tasks }) => {
  const todoTasks = tasks.filter(t => t.status === 'TODO');
  const inProgressTasks = tasks.filter(t => t.status === 'IN_PROGRESS');
  const doneTasks = tasks.filter(t => t.status === 'DONE');

  return (
    <div style={{ display: 'flex' }}>
      <KanbanColumn title="To Do" tasks={todoTasks} />
      <KanbanColumn title="In Progress" tasks={inProgressTasks} />
      <KanbanColumn title="Done" tasks={doneTasks} />
    </div>
  );
};

export default KanbanBoard;
