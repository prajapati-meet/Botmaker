import { useForm } from "react-hook-form";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function Login() {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const res = await API.post("/auth/login", data);

      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);

      

      navigate("/dashboard");
    } catch (err) {
      alert("Invalid credentials");
    }
  };

  return (
    <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
      <h2 className="login-title">Login</h2>

      <input className="login-input" placeholder="Email" {...register("email")} />
      <input className="login-input" type="password" placeholder="Password" {...register("password")} />

      <button className="login-btn" type="submit">Login</button>
      <button className="register-btn" type="button" onClick={() => navigate("/register")}>
        Go to Register
      </button>
    </form>
  );
}

export default Login;