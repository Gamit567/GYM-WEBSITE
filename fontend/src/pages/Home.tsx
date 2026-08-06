import {  useNavigate } from 'react-router-dom';
import "./Signup.css"

function Home() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Welcome to MY Portfolio GYM website</h1>
      <div className="buttons">
        <button onClick={() => navigate("/login")}>login</button>
        <button onClick={() => navigate("/Signup")}>sign up</button>
      </div>
      <p>
        this is my model gym website which is connected to a fully functional backend, where users can login and sign up to the website to
        access a fictional membership.
      </p>
    </div>
  );
}

export default Home;