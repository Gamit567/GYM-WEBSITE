import {  useNavigate } from 'react-router-dom';

function Signup(){
     const navigate = useNavigate();
    return(
        <div>
            <div className='title'> <h1> sign up</h1> </div>
             <div className='credentials'>
                
                <div>
                    <label htmlFor="NAME: "> NAME: </label>
                    <input type="text" />
                </div>
                <div>
                    <label htmlFor="AGE: ">AGE: </label>    
                    <input type="text" />
                </div>
                <div>
                    <label htmlFor="username"> USERNAME: </label>
                    <input type="text" />
                </div>
                <div>
                    <label htmlFor="password">PASSWORD: </label>    
                    <input type="password" />
                </div>
            </div>

            <div className='buttons'>
                <button> sign up</button>
                <button onClick={() => navigate("/")}> return</button>
            </div>
        </div>
    );

}
export default Signup;