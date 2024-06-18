import { Link } from "react-router-dom";

function NavBar() {
  return (
    <div>
        <Link to={'/'}>Login</Link>
        <Link to={'/signup'}>Sign Up</Link>
    </div>
  )
}

export default NavBar