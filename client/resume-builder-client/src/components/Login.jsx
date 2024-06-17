import { Link } from "react-router-dom";
import '../App.css'
function Login() {
  return (
    <div id="loginForm">
        <form>
        <fieldset>
        <legend>Login</legend>
          <div className="form-group col-md-9">
              <label htmlFor="email">Email: </label>
              <input type="text" className="form-control" id="email"/>

              <label htmlFor="password"> password: </label>
              <input type="password" className="form-control" id="password"/>
              
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