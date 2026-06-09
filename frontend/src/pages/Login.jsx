import { useState } from "react"
import api from "../api/axios"

const Login = () => {
      const [email, setEmail] = useState('')
      const [password, setPassword] = useState('')

      const handleSubmit = async (e) => {
            e.preventDefault();
            const res = await api.post('/auth/login', { email, password });
            console.log(res.data)
      }

      return (
            <section className="min-h-screen bg-black flex justify-center items-center">
                  <form className="flex flex-col gap-3 text-white" onSubmit={handleSubmit} >
                        <input className="border border-gray-500 p-2 rounded" type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        <input className="border border-gray-500 p-2 rounded" type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                        <button className="bg-blue-500 p-2 rounded" type="submit">Login</button>
                  </form>
            </section>
      )
}

export default Login