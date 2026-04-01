import { useEffect } from "react";
import API from "../services/api";

function Dashboard() {
  const role = localStorage.getItem("role");

  // ✅ ADD THIS FUNCTION HERE
  const getUserData = async () => {
    try {
      const res = await API.get("/api/user");
      console.log(res.data);
    } catch (err) {
      console.log("Access denied");
    }
  };

  // ✅ CALL IT WHEN PAGE LOADS
  useEffect(() => {
    getUserData();
  }, []);

  return (
    <div>
      <h2>Dashboard</h2>

      <h3>Public Content</h3>

      {role === "USER" && <h3>User Content</h3>}
      {role === "ADMIN" && <h3>Admin Content</h3>}
    </div>
  );
}

export default Dashboard;