
import { BrowserRouter, Routes, Route, Link, useNavigate } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/login';
import Signup from './pages/Signup';

function App() {
  return (
    <BrowserRouter>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/Signup" element={<Signup />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;