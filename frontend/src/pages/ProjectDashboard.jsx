import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getProjects } from '../services/api';

const ProjectDashboard = () => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        // This will likely fail without a valid token, which is expected for now.
        const response = await getProjects();
        setProjects(response.data);
      } catch (error) {
        console.error("Failed to fetch projects", error);
      } finally {
        setLoading(false);
      }
    };

    fetchProjects();
  }, []);

  if (loading) {
    return <div>Loading projects...</div>;
  }

  return (
    <div>
      <h1>Project Dashboard</h1>
      <Link to="/login">Go to Login Page</Link>
      <ul>
        {projects.length > 0 ? (
          projects.map(project => (
            <li key={project.projectId}>{project.projectName}</li>
          ))
        ) : (
          <p>No projects found. You may need to log in.</p>
        )}
      </ul>
    </div>
  );
};

export default ProjectDashboard;
