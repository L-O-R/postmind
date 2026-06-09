import { useState } from "react"
import api from "../api/axios"

const Register = () => {
      const [name, setName] = useState('')
      const [username, setUsername] = useState('')
      const [email, setEmail] = useState('')
      const [password, setPassword] = useState('')
      const [confirmPassword, setConfirmPassword] = useState('')

      const handleSubmit = async (e) => {
            e.preventDefault();
            if (password !== confirmPassword) {
                  alert("Password Do not match")
                  return
            }
            try {
                  const res = await api.post('/auth/register', {
                        name, username, email, password
                  })
                  console.log(res.data)
            } catch (error) {
                  console.log(error)
            }

      }


      return (
            <section className="min-h-screen bg-black text-white flex justify-center items-center">
                  <form onSubmit={handleSubmit} className="flex flex-col gap-3">
                        <div>
                              <input className="border border-gray-500 p-2 rounded" type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
                        </div>
                        <div>
                              <input className="border border-gray-500 p-2 rounded" type="text" placeholder="username" value={username} onChange={(e) => setUsername(e.target.value)} />
                        </div>
                        <div>
                              <input className="border border-gray-500 p-2 rounded" type="email" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        </div>
                        <div>
                              <input className="border border-gray-500 p-2 rounded" type="password" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                        </div>
                        <div>
                              <input className="border border-gray-500 p-2 rounded" type="password" placeholder="confirm password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
                        </div>
                        <button type="submit">Register</button>
                  </form>
            </section>
      )
}

export default Register