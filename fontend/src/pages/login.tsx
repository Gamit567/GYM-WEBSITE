
import {  useNavigate } from 'react-router-dom';
import "./login.css"


function login() {
     const navigate = useNavigate();

  return (
   
    <div>
        <div className='title'> <h1> login page</h1></div>
        
        <div className='credentials'>
            <div>
                <label htmlFor="username: "> username: </label>
                <input type="text" />
            </div>
            <div>
                <label htmlFor="password: ">password: </label>    
                <input type="text" />
            </div>
        </div>
        
        <div className='buttons'>
            <button> login</button>
            <button onClick={() => navigate("/")}> return</button>
        </div>
    </div>
    
    
    
  );
}

export default login;