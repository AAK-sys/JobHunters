import { Link } from "react-router-dom"
import { useState } from 'react'
function Login() {

    const defaultLogin = {
        email: "",
        password: "",
    }

    const [user, setUser] = useState(defaultLogin);

    const handleChange = (event) => {
        const fieldName = event.target.id;
        const fieldValue = event.target.value;
      
        setUser(() => ({
          ...user,
          [fieldName]: fieldValue,
        }));
    };

    const handleSubmit = (event) => {
        console.log("authentication starts here")
    }

  return (
    <div id="loginForm">
        <form onSubmit={handleSubmit}>
        <fieldset>
        <legend>Login</legend>
          <div className="form-group col-md-9">

              <label htmlFor="email">Email: </label>
              <input type="text" className="form-control" id="email" value={user.email} onChange={handleChange}/>

              <label htmlFor="password"> password: </label>
              <input type="password" className="form-control" id="password" value ={user.password} onChange={handleChange}/>
              
            <div>
                <button id="loginSubmitBtn" type='submit'>login</button>
            </div>
            <div>
                <p>don't have an account with use?<Link to={'/signup'}> Sign up now!</Link></p>
            </div>
          </div>
          </fieldset>
        </form>
    </div>
  )
}

export default Login