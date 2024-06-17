import React from 'react'

function SignUp() {
  return (
    <div id = "signupFormDiv">
        <form id = "signUpForm">
        <fieldset>
        <legend>Sign Up</legend>
          <div className="form-group col-md-9">
            
              <label htmlFor="email">Email: </label>
              <input type="text" className="form-control" id="email"/>

              <label htmlFor="userName">Username: </label>
              <input type="text" className="form-control" id="userName"/>

              <label htmlFor="password"> Password: </label>
              <input type="password" className="form-control" id="password"/>

              <label htmlFor="confirmPassword"> Confirm Password: </label>
              <input type="password" className="form-control" id="confirmPassword"/>
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