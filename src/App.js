import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/layout/header';
// import Footer from './components/layout/footer';
import Login from './components/pages/login';
import Main from './components/pages/main';
import Join from './components/pages/join';
import Search from './components/pages/search';

function App() {
  return (
    <React.Fragment>
      <BrowserRouter>
        <Header/>
        <Routes>
          <Route path='/' element={<Main/>}/>
          <Route path='/login' element={<Login/>} />
          <Route path='/join' element={<Join/>} />
          <Route path='/search' element={<Search/>} />
        </Routes>
        {/* <Footer/> */}
      </BrowserRouter>
    </React.Fragment>
  );
}

export default App;
