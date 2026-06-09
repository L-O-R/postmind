//  all routes


import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Login from "../pages/Login";


const router = createBrowserRouter([
      {
            path: "/login",
            element: <Login />
      }
])


export default router
