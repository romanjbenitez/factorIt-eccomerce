import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Home from './pages/Home';
import Checkout from './pages/Checkout';
import Login from './pages/Login';
import Footer from './components/Footer';
import UsersInfo from './pages/UsersInfo';

function App() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="checkout" element={<Checkout />} />
        <Route path="user-info" element={<UsersInfo />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
