import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignUp() {

    const defaultSignUp = {
        email: "",
        userName: "",
        password: "",
        confirmPassword: ""
    }

    const navigate = useNavigate();
    const [user, setUser] = useState(defaultSignUp);

    const handelChange = (event) => {
        const fieldName = event.target.id;
        const fieldValue = event.target.value;
      
        setUser(() => ({
          ...user,
          [fieldName]: fieldValue,
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("user signs up and get sent to login");
        alert("you are signed up");
        navigate("/");
    }

    return (
    <div id = "signupFormDiv">
        <form id = "signUpForm" onSubmit={handleSubmit}>
        <fieldset>
        <legend>Sign Up</legend>
          <div className="form-group col-md-9">
            
              <label htmlFor="email">Email: </label>
              <input type="text" className="form-control" id="email" value={user.email} onChange={handelChange}/>

              <label htmlFor="userName">Username: </label>
              <input type="text" className="form-control" id="userName" value={user.userName} onChange={handelChange}/>

              <label htmlFor="password"> Password: </label>
              <input type="password" className="form-control" id="password" value={user.password} onChange={handelChange}/>

              <label htmlFor="confirmPassword"> Confirm Password: </label>
              <input type="password" className="form-control" id="confirmPassword" value={user.confirmPassword} onChange={handelChange}/>

            <div>
                <button id="SignupSubmitBtn" type='submit'>Sign up!</button>
            </div>
          </div>
          </fieldset>
        </form>
    </div>
  )
}

export default SignUp