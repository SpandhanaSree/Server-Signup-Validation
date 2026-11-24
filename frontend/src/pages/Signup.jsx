import "./Signup.css";
import { useState } from "react";
import api from "../services/api";

function Signup() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    age: "",
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");


const handleSubmit = async (e) => {
  e.preventDefault();

  setLoading(true);
  setErrors({});
  setSuccess("");

  try {
    const response = await api.post("/register", {
      ...formData,
      age: formData.age === "" ? null : Number(formData.age),
    });

    setSuccess(`Account created successfully for ${response.data.name}!`);

    setFormData({
      name: "",
      email: "",
      password: "",
      age: "",
    });
  } catch (error) {
    if (error.response?.data?.errors) {
      setErrors(error.response.data.errors);
    } else {
      setErrors({ general: "Something went wrong. Please try again." });
    }
  } finally {
    setLoading(false);
  }
};


  return (
    <div>
      <h1>Create Account</h1>

      <form onSubmit={handleSubmit}>
  <div>
    <label>Name</label>
    <input
      type="text"
      placeholder="Enter your full name"
      value={formData.name}
      onChange={(e) => {
  setFormData({ ...formData, name: e.target.value });
  setErrors({ ...errors, name: undefined });
}}
    />
    {errors.name && <p>{errors.name}</p>}
  </div>

  <div>
    <label>Email</label>
    <input
      type="text"
      placeholder="Enter your email"
      value={formData.email}
      onChange={(e) => {
  setFormData({ ...formData, email: e.target.value });
  setErrors({ ...errors, email: undefined });
}}
    />
    {errors.email && <p>{errors.email}</p>}
  </div>

  <div>
    <label>Password</label>
    <input
      type="password"
    
placeholder="Create a strong password"
      value={formData.password}
     onChange={(e) => {
  setFormData({ ...formData, password: e.target.value });
  setErrors({ ...errors, password: undefined });
}}
    />
    {errors.password && <p>{errors.password}</p>}
  </div>

  <div>
    <label>Age</label>
    <input
      type="number"
        placeholder="Enter your age"
      value={formData.age}
      onChange={(e) => {
  setFormData({ ...formData, age: e.target.value });
  setErrors({ ...errors, age: undefined });
}}
    />
    {errors.age && <p>{errors.age}</p>}
  </div>

  <button type="submit" disabled={loading}>
  {loading ? "Creating account..." : "Create Account"}
</button>

{errors.general && <p>{errors.general}</p>}
{success && <p className="success-message">{success}</p>}
</form>
    </div>
  );
}

export default Signup;
