import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
    return (
        <div>
            <h1>Welcome to the Home Page</h1>
            <p>This is the content of the home page.</p>
            <Link to="/employees">
                <button>Quản trị nhân viên</button>
            </Link>
        </div>
    );
};

export default Home;