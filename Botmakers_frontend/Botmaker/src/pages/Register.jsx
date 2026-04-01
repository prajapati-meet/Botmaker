import {useForm} from "react-hook-form";
import API from "../services/api";
import {useNavigate} from "react-router-dom";

function Register(){
    const {register, handleSubmit}=useForm();
    const navigate=useNavigate();

    const onSubmit=async(data)=>{
        try{
            await API.post("/auth/register", data);
            alert("Registered Successfully");
            navigate("/login");
            }catch(err)
            {
                alert("Error in Registration");
            }
        }

            return (
                <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                <h2 className="login-title">Register</h2>

                <input className="login-input" placeholder="Name" {...register("name")} />
                <input className="login-input" placeholder="Email" {...register("email")} />
                <input className="login-input" type="password" placeholder="Password" {...register("password")} />

                <select className="login-input" {...register("role")}>
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
                <br></br>
                
                <button className="login-btn" type="submit">Register</button>
            
                </form>
            );
    }


export default Register;