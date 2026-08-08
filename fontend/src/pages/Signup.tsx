import {  useNavigate } from 'react-router-dom';
import { useState, useEffect } from "react"; 
import axios from "axios";

function Signup(){
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        age: '',
        username: '',
        password: '',
        });
         const [error, setError] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };


    const handleSave = async () =>{
        if (!formData.name || !formData.age || !formData.username || !formData.password) {
            setError("please enter all the details")
            return;
        }
        if (!/^\d+$/.test(formData.age)){
            setError("make sure age is a number")
            return;
        }
        if (formData.username.length > 10){
            setError("make sure username is atleast 10 letters")
        }
        if (formData.password.length > 10){
            setError("make sure password is atleast 10 letters")
        }

        try {
            await axios.post("http://localhost:8080/customer/createcustomer", formData);
            navigate("/");
        } catch (err) {
            if (axios.isAxiosError(err)) {
                setError(err.response?.data?.message || "Signup failed.");
            }else {
                setError("Something went wrong.");
            }
        }
    }
    
    return(
        <div>
            <div className='title'> <h1> sign up</h1> </div>
             <div className='credentials'>
              
                
                <div>
                    <label htmlFor="NAME: "> NAME: </label>
                    <input id = "name" name="name" type="text" value = {formData.name} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor="AGE: ">AGE: </label>    
                    <input id = "age" type="text" name="age" value={formData.age} onChange={handleChange} />
                </div>
                <div>
                    <label htmlFor="username"> USERNAME: </label>
                    <input id = "username" type="text" name="username" value={formData.username}  onChange={handleChange}/>
                </div>
                <div>
                    <label htmlFor="password">PASSWORD: </label>    
                    <input id = "password" type="password" name="password" value={formData.password} onChange={handleChange}
                    />
                </div>
                   {error && <p className="error-message" role="alert">{error}</p>}
            </div>

            <div className='buttons'>
                <button onClick={() => handleSave()}> sign up</button>
                <button onClick={() => navigate("/")}> return</button>
            </div>
        </div>
    );

}
export default Signup;