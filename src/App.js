import "./App.css";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/layout/header";
// import Footer from './components/layout/footer';
import Login from "./components/pages/login";
import Main from "./components/pages/main";
import Join from "./components/pages/join";
import List from "./components/pages/list";
import Search from "./components/pages/search";
import UserMy from "./components/pages/userMy";
import Detail from "./components/pages/detail";
import Cart from "./components/pages/cart";
import Order from "./components/pages/Order";
import GlobalStyle from "./Util/GlobalStyle";
import styled from "styled-components";

const AppWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const PagesWrapper = styled.div`
  width: 100%;
  max-width: 1920px;
`;
function App() {
  return (
    <React.Fragment>
      <GlobalStyle />
      <BrowserRouter>
        <Header />
        <AppWrapper>
          <PagesWrapper>
            <Routes>
              <Route path="/" element={<Main />} />
              <Route path="/login" element={<Login />} />
              <Route path="/join" element={<Join />} />
              <Route path="/list" element={<List />} />
              <Route path="/search" element={<Search />} />
              <Route path="/my" element={<UserMy />} />
              <Route path="/detail" element={<Detail />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/order" element={<Order />} />
            </Routes>
          </PagesWrapper>
          {/* <Footer/> */}
        </AppWrapper>
      </BrowserRouter>
    </React.Fragment>
  );
}

export default App;
